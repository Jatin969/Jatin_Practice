package Abstraction_Examples.UpiBanking.Exceptions;

public class PhoneListEmptyException extends Exception{
    public PhoneListEmptyException(){
        super("Phone list is empty.");
    }
}
