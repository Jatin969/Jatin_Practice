package Abstraction_Examples.UpiBanking.Application;

import Abstraction_Examples.UpiBanking.Exceptions.InvalidPhoneNumberException;
import Abstraction_Examples.UpiBanking.User.UserAccount;

import java.util.Scanner;

public interface ApplicationInterface {
    Scanner scn = new Scanner(System.in);

    public void welcome();
    public void login();
    public void createAccount() throws InvalidPhoneNumberException;
    public String addCard(String username);
    public void updateAccount();
    public void payToUPI();
    public void getCurrentAmount(String pass);
    public void exit();
}
