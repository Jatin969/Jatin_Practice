package Abstraction_Examples.UpiBanking.bank;

import java.util.Calendar;

public abstract class BankAccountDetails {

    private final String AccountHolder;
    private final String address;
    private final String phone;
    private final String bank;
    private final long cardNo;
    private final Calendar expireDate;
    private final int cvv;
    private final int cardPin;
    protected int amount;

    protected BankAccountDetails(String accountHolder, String address, String phone,
                              String bank, long cardNo, Calendar expireDate,
                              int cvv, int cardPin, int amount) {
        this.AccountHolder = accountHolder;
        this.address = address;
        this.phone = phone;
        this.bank = bank;
        this.cardNo = cardNo;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.cardPin = cardPin;
        this.amount = amount;
    }

    protected abstract int getAmount();

    protected abstract int decrement(int debit);

    protected abstract int increment(int credit);

}
