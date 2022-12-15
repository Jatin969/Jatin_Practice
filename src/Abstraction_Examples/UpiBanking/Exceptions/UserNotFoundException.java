package Abstraction_Examples.UpiBanking.Exceptions;

public class UserNotFoundException extends MarkerException{
    public UserNotFoundException() {
        super("user not found");
    }
}
