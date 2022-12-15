package Abstraction_Examples.UpiBanking.Application;

import Abstraction_Examples.UpiBanking.Exceptions.InvalidPhoneNumberException;
import Abstraction_Examples.UpiBanking.User.COUNTRY;
import Abstraction_Examples.UpiBanking.User.Phone;
import Abstraction_Examples.UpiBanking.User.UserAccount;
import Abstraction_Examples.UpiBanking.bank.BankInterface;
import Abstraction_Examples.UpiBanking.bank.sbi.sbiBank;

import javax.security.auth.login.LoginException;
import java.util.*;


/**
 *  Going to use Enum as the list of bank Implementations.
 *
 */

enum banksForDemoApplication {

    State_Bank_of_India("sbi") {
        @Override
        public BankInterface bankResolver(){
            BankInterface sbi = (BankInterface) new sbiBank();
//            TODO : implement sbiCLass using BAnkInterface.
            return sbi;
        }
    };

    private final String val;

    banksForDemoApplication(String val){
        this.val = val;
    }

    public abstract BankInterface bankResolver();

}

public class DemoApplication implements ApplicationInterface{

    private static class ApplicationRepository{
        static List<UserAccount> userList = new ArrayList<>();

        static Set<String> usernameSet = new HashSet<>();

        static HashMap<String, String> bankList = new HashMap<>();

        static{
            bankList.put("sbi", "State Bank of India");
            bankList.put("apl","Axis Bank");
            bankList.put("fbl","Federal Bank");
            bankList.put("icici","ICICI bank");
            bankList.put("indus","IndusInd Bank");
            bankList.put("kmbl","Kotak Mahindra Bank");
        }

    }
    UserAccount user = null;
    boolean loggedIn = false;
    BankInterface bank = null;

    private String scan(){
        return scn.nextLine().trim().toLowerCase();
    }

    @Override
    public void welcome() {
        display("Welcome to Demo Application for Net Banking. ::::");
        display("Do you already have an account? (y/n");
        String answer = scan();
        if(answer.equals("y")) login();
        else if(answer.equals("n")) createAccount();
        else System.exit(0);

    }



    @Override
    public void login() {
        display("Enter username :");
        String username = scan();
        display("Enter password : ");
        String password  = scan();
        final Optional<UserAccount> userAccount = validateUser(username, password);
        if(userAccount.isPresent()) this.user = userAccount.get();
        else {
            display("wrong credentials, login again.");
            login();
        }
    }

    private Optional<UserAccount> validateUser(String username, String password){
        Optional<UserAccount> account = ApplicationRepository.userList.stream().filter(user -> {
            return this.user.getUsername().equals(username) && this.user.getPassword().equals(password);
        }).findFirst();
        return account;
    }



    private boolean validatePassword(String password){
        if(password.length() < 12) return false;
        boolean hasSpecialCharacter = false, hasCapitalLetter = false,
                hasNumbers  = false,hasLowerCaseLetter = false;
        for(char ch : password.toCharArray()) {
            int val = (int) ch;
            if((val >= 33 && val <= 47) || (val >= 58  && val <= 64)
                    || (val >= 91  && val <= 96) || (val >= 123  && val <= 126)) hasSpecialCharacter = true;
            if(val >= 65 && val <= 90) hasCapitalLetter = true;
            if(val >= 97 && val <= 122) hasLowerCaseLetter = true;
            if(ch >= '0' && ch >= '9') hasNumbers = true;
        }
        return hasCapitalLetter && hasSpecialCharacter && hasNumbers && hasLowerCaseLetter;
    }

    private boolean validateUsername(String username){
      return !ApplicationRepository.usernameSet.contains(username);
    }

    @Override
    public void createAccount() {
        String username = validateAndGetUsername();
        display("Enter name : ");
        String name = scan();
        Phone phoneNumber = validateAndGetPhoneNumber();
        String password = validateAndGetPassword();
        String upi = addCard(username);

        try{
            this.user = new UserAccount(name,
                    phoneNumber,
                    password,
                    username,
                    upi);
        }catch(InvalidPhoneNumberException e){
            display("Something went wrong.");
            createAccount();
        }

    }

    private String validateAndGetPassword() {
        display("Enter password : \n" +
                    "Password must contain : \n\t 1. Special characters \n\t 2. numbers \n\t "
                    + "3. lowercase and uppercase letters \n\t 4. minimum length 12.");
        display("Enter password :");
        String pass = scan();
        if(!validatePassword(pass)) validateAndGetPassword();
        return pass;

    }

    private Phone validateAndGetPhoneNumber(){
        // TODO options for countries:

        display("Enter phone number : ");
        String phone = scn.nextLine().trim();
        try{
            return (Phone) new Phone(COUNTRY.INDIA,phone);
        }catch(Exception e){
            System.out.println(e);
            validateAndGetPhoneNumber();
        }
        return null;
    }

    private String validateAndGetUsername(){
        display("Enter a unique Username :");
        String username = scan();
        if(!validateUsername(username)) {display("username already exists."); validateAndGetUsername();}
        return username;
    }

    private void display(String s){
        System.out.println(s);
    }


    @Override
    public String addCard(String username) {
        String bankAbbrvt = getBank();
        BankInterface bank = null;
        try{
             bank = getBankInstance(bankAbbrvt);
        }catch(IllegalStateException e){
            System.out.println("This bank is not available.");
            bankAbbrvt = getBank();
        }
        long cardNo = validateAndGetCardNo();
        int[] expireDate = validateAndGetExpDate();
        int cvv = getCVV();
        String cardpin = validateAndGetCardpin();
        String upi = null;
        CardDetails card = null;
        try{
            card = new CardDetails(bankAbbrvt,cardNo,expireDate[0],expireDate[1],cvv, cardpin);
            upi = bank.ValidateCardDetails(card,username);

        }catch(Exception e){
            System.out.println("Something went wrong.");
            e.printStackTrace();
            addCard(username);
        }

        try{
            if(upi.equals("AlreadyContainsUPI")){
                if(bank.authenticateUPIpin(getUPIPIN(), username))
                    return upi;
                else {
                    System.out.println("Enter correct upi pin.");
                    bank.authenticateUPIpin(getUPIPIN(), username);
                }
            }else {
                bank.SaveUpiAndPin(upi, card,getUPIPIN());
                return upi;
            }
        }catch(Exception e){
            System.out.println("Something went wrong.");
            e.printStackTrace();
            addCard(username);
        }

        return null;
    }

    private String getUPIPIN() {
        display("Enter pin for upi");
        String pin = scan();
        if(pin.length() >= 6)
            return pin;
        else {
            display("Enter pin of length 6 or more.");
            getUPIPIN();
        }
        return null;
    }

    private BankInterface getBankInstance(String abbrvt){

        switch(abbrvt){
            case "sbi" : {
                banksForDemoApplication bankEnum = banksForDemoApplication.State_Bank_of_India;
                final BankInterface sbi = (BankInterface) bankEnum.bankResolver();
                return sbi;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + abbrvt);
        }

    }

    private String validateAndGetCardpin() {
        display("Enter pin :");
        String pin = scan();
        if(pin.length() != 4 || !validateNumber(pin,0,9999)) validateAndGetCardpin();
        return pin;
    }

    private int getCVV() {
        display("Enter cvv number :");
        String cvv = scan();
        if(cvv.length() != 3 || !validateNumber(cvv,100,999)) getCVV();
        return Integer.parseInt(cvv);
    }



    private int[] validateAndGetExpDate() {
        display("Enter month :");
        String month = scan();
        display("Enter year :");
        String year = scan();
        if(!validateMonthAndYear(month, year)) validateAndGetExpDate();
        return new int[]{Integer.parseInt(month), Integer.parseInt(year)};
    }

    private boolean validateMonthAndYear(String month, String year){
        return (validateNumber(month,1,12) && validateNumber(year,2000, 2050));
    }

    private long validateAndGetCardNo() {
        display("Enter card number :");
        String cardNo = scan();
        try{
            return (long) Long.parseLong(cardNo);
        }catch(ClassCastException e){
            return 0L;
        }
    }

    private String getBank() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Available banks: \n");
        List<String> banks = (List<String>) ApplicationRepository.bankList.keySet();
        for(int i = 0; i<banks.size(); i++){
            sb.append((i+1) + ". " + ApplicationRepository.bankList.get(banks.get(i)) + "\n");
        }
        sb.append("Choose a number form above.");
        display(sb.toString());
        String val = scan();
        if(!validateNumber(val,1,banks.size())) getBank();
        return banks.get(Integer.parseInt(val) - 1);
    }

    private boolean validateNumber(String val, int start, int end) {
        for(char ch : val.toCharArray()) if(ch < '0' || ch > '9') return false;
        return Integer.parseInt(val) <= end && Integer.parseInt(val) >= start;
    }

    @Override
    public void updateAccount() {
        display("Enter old password :");
        String oldPass = scan();
        display("Enter name");
        String name = scan();
        this.user.setName(name);
        display("Enter new password");
        String newPass = validateAndGetPassword();
        this.user.setPassword(newPass);

        display("1.Add  or 2.Delete phone number (1/2)");
        int option = 0;
        try{
            option = Integer.parseInt(scan());
        }catch(ClassCastException e){
            updateAccount();
        }
        if(option == 1){
            Phone phoneNumber = validateAndGetPhoneNumber();
            try{
                this.user.addPhoneNumber(phoneNumber);
            }catch(Exception e){
                e.printStackTrace();
                updateAccount();
            }
        }else if(option == 2){
            Phone phoneNumber = validateAndGetPhoneNumber();
            try{
                this.user.deletePhoneNumber(phoneNumber);
            }catch(Exception e){
                e.printStackTrace();
                updateAccount();
            }
        }


    }

    private void updateAccount(UserAccount acc, UserAccount accountNew){
        List<UserAccount> list = ApplicationRepository.userList;
        list.remove(acc);
        list.add(accountNew);
    }



    @Override
    public void payToUPI() {
        display("Enter user upi");
        String upi = scan();
        display("Enter amount");
        try{
            Integer amount = Integer.parseInt(scan());
            payToUPI(upi, amount);
        } catch (ClassCastException e){
            e.printStackTrace();
            payToUPI();
        }
    }
    private void payToUPI(String upi, int amount) {
        int bal = 0;
        display("Enter password of your upi");
        String pass = scan();
        if(!this.bank.authenticateUPIpin(pass, this.user.getUPIId())) {
            display("Enter valid UPIID");
            payToUPI(upi, amount);
        }
        BankInterface transferBank = null;
        try{
            transferBank = getBankInstance(
                    upi.split("@")[1]
            );
        }catch(Exception e){
            System.out.println("bank not found");
            payToUPI();
        }
        try{
            bal = transferBank.credit(upi, amount);
        }catch(Exception e){
            display("Something went wrong");
            payToUPI();
        }
        display(Integer.toString(bal));
    }

    @Override
    public void getCurrentAmount(String pass) {
        int bal = this.bank.fetchAmount(this.user.getUPIId(),pass);
        display(Integer.toString(bal));
    }


    @Override
    public void exit() {
        System.exit(0);
    }
}
