package Abstraction_Examples.UpiBanking.Exceptions;

public class PhoneNumberNotFoundException extends Exception{
    public PhoneNumberNotFoundException() {
        super("Phone number not available in list.");
    }
}
