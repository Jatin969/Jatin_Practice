package Abstraction_Examples.UpiBanking.bank;

import Abstraction_Examples.UpiBanking.Application.CardDetails;
import Abstraction_Examples.UpiBanking.User.Address;
import Abstraction_Examples.UpiBanking.User.Phone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class BankAccountDetails {

    private final String AccountHolder;
    private final Address address;
    private final List<Phone> phoneNos;

    private final long accountNumber;

    protected int amount;


    public BankAccountDetails(String accountHolder, Address address, Phone phone, int amount, long accountNumber) {
        this.AccountHolder = accountHolder;
        this.address = address;
        this.phoneNos = new ArrayList<Phone>();
        this.phoneNos.add(phone);
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    protected abstract int getAmount();

    protected abstract int decrement(int debit);

    protected abstract int increment(int credit);



}
