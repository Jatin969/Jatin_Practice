package Abstraction_Examples.UpiBanking.bank;

import Abstraction_Examples.UpiBanking.Application.CardDetails;
import Abstraction_Examples.UpiBanking.Exceptions.UserNotFoundException;
import Abstraction_Examples.UpiBanking.bank.sbi.sbiBank;

import javax.security.auth.login.LoginException;

public interface BankInterface {
    public String ValidateCardDetails(CardDetails card,String username) throws UserNotFoundException;
    public String ProvideUPiId(String username);
    public void SaveUpiAndPin(String upi,CardDetails card, String pass);
    public int fetchAmount(String upi, String pin);
    public int credit(String upi, int amount) throws LoginException;
    public int debit(String upi, int amount);

    public boolean authenticateUPIpin(String password, String upiusername);
}
