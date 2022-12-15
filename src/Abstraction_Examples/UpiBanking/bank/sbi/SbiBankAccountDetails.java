package Abstraction_Examples.UpiBanking.bank.sbi;

import Abstraction_Examples.UpiBanking.Application.CardDetails;
import Abstraction_Examples.UpiBanking.User.Address;
import Abstraction_Examples.UpiBanking.User.Phone;
import Abstraction_Examples.UpiBanking.bank.BankAccountDetails;

public class SbiBankAccountDetails extends BankAccountDetails {
    private final CardDetails card;

    public SbiBankAccountDetails(String accountHolder, Address address, Phone phone, int amount, long accountNumber, CardDetails card) {
        super(accountHolder, address, phone, amount, accountNumber);
        this.card = card;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public int decrement(int debit) {
        if(this.getAmount() < debit) return -1;
        return this.amount = this.amount - debit;
    }

    @Override
    public int increment(int credit) {
        return this.amount = this.amount + credit;
    }

    public CardDetails getCard(){
        return this.card;
    }
}
