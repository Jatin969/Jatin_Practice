package Abstraction_Examples.UpiBanking.bank;

import Abstraction_Examples.UpiBanking.Application.CardDetails;

public interface BankInterface {
    public boolean ValidateCardDetails(CardDetails card);
    public String ProvideUPiId();
    public void SaveUpiAndPin();
    public int fetchAmount(String upi, String pin);
    public int credit(String upi, int amount);
    public int debit(String upi, int amount);
}
