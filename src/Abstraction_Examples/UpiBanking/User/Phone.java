package Abstraction_Examples.UpiBanking.User;

import Abstraction_Examples.UpiBanking.Exceptions.InvalidPhoneNumberException;

import java.util.HashMap;
import java.util.Map;

public class Phone {

    private static class countryList{

        static Map<COUNTRY, countryWiseCredentials> mapOfCountry = new HashMap<>();

        static {
            mapOfCountry.put(COUNTRY.INDIA,new countryWiseCredentials(10, "+91"));
        }
    }


    private final countryWiseCredentials credentials;
    private String phoneNumber;

    public Phone(COUNTRY country, String Number) throws InvalidPhoneNumberException {
            this.credentials = countryList.mapOfCountry.get(country.getName());
            validatePhone(this.credentials.lengthOfNumber, Number);
    }

    private void validatePhone(int size, String number) throws InvalidPhoneNumberException{
        if(size != number.length()) throw new InvalidPhoneNumberException();
        for(char ch : number.toCharArray())
            if(ch < '0' || ch > '9') throw new InvalidPhoneNumberException();
        this.phoneNumber = number;
    }


    public boolean equals(Phone o){
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return  this.getCredentials().equals(phone.getCredentials()) &&
                this.getPhoneNumber().equals(phone.getPhoneNumber());
    }

    public countryWiseCredentials getCredentials() {
        return credentials;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}



class countryWiseCredentials{
    public int lengthOfNumber;
    public String code;

    protected countryWiseCredentials(int lengthOfNumber, String code) {
        this.lengthOfNumber = lengthOfNumber;
        this.code = code;
    }

    public boolean equals(countryWiseCredentials o){
        if(this == o) return true;
        if(!(o instanceof countryWiseCredentials)) return false;
        countryWiseCredentials other = (countryWiseCredentials) o;
        return this.lengthOfNumber == other.lengthOfNumber && this.code.equals(other.code);
    }
}
