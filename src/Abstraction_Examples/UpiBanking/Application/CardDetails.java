package Abstraction_Examples.UpiBanking.Application;

import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class CardDetails {
    private final String bank;
    private final long cardNo;
    private final Calendar expireDate;
    private final int cvv;

    private final String cardpin;

    public CardDetails(String bank, long CardNo, int month, int year, int cvv, String cardpin){
        this.bank = bank;
        this.cardNo = CardNo;
        this.expireDate = new GregorianCalendar();
        this.expireDate.set(year,month,daysInMonthCalculator(month,year));
        this.cvv = cvv;
        this.cardpin = Base64.getEncoder().withoutPadding().encodeToString(cardpin.getBytes());
    }

    private int daysInMonthCalculator(int month, int year){
        int[] daysInMonth = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if(isLeapYear(year) && month == 2) return daysInMonth[month-1] + 1;
        else return daysInMonth[month-1];
    }

    private boolean isLeapYear(int year){
        if(year % 4 == 0 && year % 100 != 0) return true;
        else return year % 100 == 0 && year % 400 == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardDetails)) return false;
        CardDetails that = (CardDetails) o;
        return cardNo == that.cardNo && cvv == that.cvv && bank.equals(that.bank) && expireDate.equals(that.expireDate) && cardpin.equals(that.cardpin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, cardNo, expireDate, cvv, cardpin);
    }
}
