package Abstraction_Examples.UpiBanking.Application;

import Abstraction_Examples.UpiBanking.User.UserAccount;
import Abstraction_Examples.UpiBanking.bank.BankInterface;
import Abstraction_Examples.UpiBanking.bank.sbi.sbiBank;

import java.util.List;
import java.util.Optional;


/**
 *  Going to use Enum as the list of bank Implementations.
 *
 */

enum banksForDemoAppication{


    State_Bank_of_India(1,"sbi") {
        @Override
        public BankInterface bankResolver(){
            BankInterface sbi = (BankInterface) new sbiBank();
//            TODO : implement sbiCLass using BAnkInterface.
            return sbi;
        }
    };

    private final int val;
    private final String text;

    banksForDemoAppication(int val, String text){
        this.val = val;
        this.text = text;
    }

    public abstract BankInterface bankResolver();

}

public class DemoApplication implements ApplicationInterface{
    UserAccount user = null;
    boolean loggedIn = false;

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
        Optional<UserAccount> account = ApplicationRepository.accountList.stream().filter(user -> {
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
        display("Enter name : ");
        String name = scan();
        String phoneNumber = validateAndGetPhoneNumber();
        String username = validateAndGetUsername();
        String password = validateAndGetPassword();
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

    private String validateAndGetPhoneNumber(){
        display("Enter phone number : ");
        String phone = scn.nextLine().trim();
        if(!validatePhoneNumber(phone)) validateAndGetPhoneNumber();
        return phone;
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

    private boolean validatePhoneNumber(String phone){
        for(char ch : phone.toCharArray()) if(ch < '0' || ch > '9') return false;
        return phone.length() == 10;
        // TODO validation trough OTP
    }

    @Override
    public String addCard() {
        String bank = getBank();
        String cardNo = validateAndGetCardNo();
        int[] expireDate = validateAndGetExpDate();
        int cvv = getCVV();
        String cardpin = validateAndGetCardpin();

        BankInterface bank = getBankInstance();

        return bank;
    }

    private BankInterface getBankInstance(int index){

        switch(index){
            case 1 :
                final BankInterface sbi = (BankInterface) banksForDemoAppication.State_Bank_of_India(1, "sbi").bankResolver();
                return sbi;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + index);
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

    private String validateAndGetCardNo() {
        display("Enter card number :");
        String cardNo = scan();
        return cardNo;
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
        return ApplicationRepository.bankList.get(banks.get(Integer.parseInt(val)-1));
    }

    private boolean validateNumber(String val, int start, int end) {
        for(char ch : val.toCharArray()) if(ch < '0' || ch > '9') return false;
        return Integer.parseInt(val) <= end && Integer.parseInt(val) >= start;
    }

    @Override
    public void updateAccount() {
        display("Enter old password :");
        String oldPass = scan();
        display("Enter ");
    }



    @Override
    public void payToUPI() {

    }

    @Override
    public void getCurrentAmount() {

    }


    @Override
    public void requestPaymentFromUPI() {

    }


    @Override
    public void exit() {
        System.exit(0);
    }
}
