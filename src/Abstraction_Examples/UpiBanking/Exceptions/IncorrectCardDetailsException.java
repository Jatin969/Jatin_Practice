package Abstraction_Examples.UpiBanking.Exceptions;

import java.io.IOException;

public class IncorrectCardDetailsException extends MarkerException {
    public IncorrectCardDetailsException(){
        super("The card credentials provided are wrong.");
    }
}
