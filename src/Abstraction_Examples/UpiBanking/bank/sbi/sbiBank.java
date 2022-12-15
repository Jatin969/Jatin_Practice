package Abstraction_Examples.UpiBanking.bank.sbi;

import Abstraction_Examples.UpiBanking.Application.CardDetails;
import Abstraction_Examples.UpiBanking.Exceptions.UserNotFoundException;
import Abstraction_Examples.UpiBanking.bank.BankAccountDetails;
import Abstraction_Examples.UpiBanking.bank.BankInterface;

import javax.security.auth.login.LoginException;
import java.util.*;

public class sbiBank implements BankInterface {
    private class pairOfPINAndAccount{
        private SbiBankAccountDetails account;
        private String password;

        public pairOfPINAndAccount(SbiBankAccountDetails account, String password) {
            this.account = account;
            this.password = password;
        }

        public SbiBankAccountDetails getAccount() {
            return account;
        }

        public String getPassword() {
            return password;
        }
    }


    private final static String endCODE = "@sbi";
    private static class bankAccountsRepo {
        static List<SbiBankAccountDetails> BankAccounts = new ArrayList<>();
        static HashMap<String,pairOfPINAndAccount > upiAndPinAndAccount= new HashMap<>();

    }
    @Override
    public String ValidateCardDetails(CardDetails card, String userName) throws UserNotFoundException {
        Optional<SbiBankAccountDetails> account = bankAccountsRepo.BankAccounts.stream()
                .filter(acc -> {return acc.getCard().equals(card);}).findFirst();
        if(account.isPresent() == false) throw new UserNotFoundException();
        else{
            return ProvideUPiId(userName);
        }
    }

    @Override
    public String ProvideUPiId(String userName) {
        if(bankAccountsRepo.upiAndPinAndAccount.containsKey(userName)){
            return "AlreadyContainsUPI";
        }else {
            return userName + sbiBank.endCODE;
        }
    }

    @Override
    public boolean authenticateUPIpin(String password, String userName){
        return bankAccountsRepo.upiAndPinAndAccount.get(userName).getPassword().equals(password);
    }


    @Override
    public void SaveUpiAndPin(String upi, CardDetails card, String pass) {
        SbiBankAccountDetails account = bankAccountsRepo.BankAccounts.stream()
                .filter(acc -> {return acc.getCard().equals(card);}).findFirst().get();
        bankAccountsRepo.upiAndPinAndAccount
                .put(upi,new pairOfPINAndAccount(account,pass));
    }

    @Override
    public int fetchAmount(String upi, String pin) {
        if(!authenticateUPIpin(upi,pin)) return -1;
        SbiBankAccountDetails details = bankAccountsRepo.upiAndPinAndAccount.get(upi).getAccount();
        return details.getAmount();
    }

    @Override
    public int credit(String upi, int amount) throws LoginException {
        SbiBankAccountDetails details = bankAccountsRepo.upiAndPinAndAccount.get(upi).getAccount();
        int val = details.increment(amount);
        if(val == -1) throw new LoginException();
        return val;
    }

    @Override
    public int debit(String upi, int amount) {
        return 0;
    }
}
