package Abstraction_Examples.UpiBanking.bank.sbi;

import Abstraction_Examples.UpiBanking.Application.CardDetails;
import Abstraction_Examples.UpiBanking.bank.BankInterface;

public class sbiBank implements BankInterface {
    @Override
    public boolean ValidateCardDetails(CardDetails card) {
        return false;
    }

    @Override
    public String ProvideUPiId() {
        return null;
    }

    @Override
    public void SaveUpiAndPin() {

    }

    @Override
    public int fetchAmount(String upi, String pin) {
        return 0;
    }

    @Override
    public int credit(String upi, int amount) {
        return 0;
    }

    @Override
    public int debit(String upi, int amount) {
        return 0;
    }
}
