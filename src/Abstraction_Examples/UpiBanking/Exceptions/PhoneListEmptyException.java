package Abstraction_Examples.UpiBanking.Exceptions;

public class PhoneListEmptyException extends MarkerException{
    public PhoneListEmptyException(){
        super("Phone list is empty.");
    }
}
