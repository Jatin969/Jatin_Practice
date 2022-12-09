package Abstraction_Examples.UpiBanking.Application;

import java.util.Scanner;

public interface ApplicationInterface {
    Scanner scn = new Scanner(System.in);

    public void welcome();
    public void login();
    public void createAccount();
    public void addCard();
    public void updateAccount();
    public void payToNumber();
    public void payToUPI();
    public void getCurrentAmount();
    public void requestPaymentFromUPI();
    public void requestPaymentFromPhone();
    public void exit();
}
