package Abstraction_Examples.UpiBanking.Exceptions;

import java.io.IOException;

public class IncorrectCardDetailsException extends Exception {
    public IncorrectCardDetailsException(){
        super("The card credentials provided are wrong.");
    }
}
