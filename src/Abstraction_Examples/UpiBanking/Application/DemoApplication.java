package Abstraction_Examples.UpiBanking.Application;

import java.util.Scanner;

public class DemoApplication implements ApplicationInterface{
    UserAccount user = null;
    boolean loggedIn = false;

    @Override
    public void welcome() {
        System.out.println("Welcome to Demo Application for Net Banking. ::::");
        System.out.println("Do you already have an account? (y/n");
        String answer = scn.nextLine().trim().toLowerCase();
        if(answer.equals("y")) login();
        else if(answer.equals("n")) createAccount();
        else System.exit(0);

    }

    @Override
    public void login() {
        System.out.println("Enter username :");
        String answer = scn.nextLine().trim().toLowerCase();
        if(validateUsername(answer)) validatePassword();
    }

    private void validatePassword(){

    }

    private boolean validateUsername(String username){
//        return ApplicationUserRepo.containsKey(username);
        return true;
    }

    @Override
    public void createAccount() {
        System.out.println("Enter name : ");
        String name = scn.nextLine().trim();
        System.out.println("Enter phone number : ");
        String phone = scn.nextLine().trim();
        validatePhoneNumber(phone);

    }

    private boolean validatePhoneNumber(String phone){
        for(char ch : phone.toCharArray()) if(ch < '0' || ch > '9') return false;
        return phone.length() == 10;
        // TODO validation trough OTP
    }

    @Override
    public void addCard() {

    }

    @Override
    public void updateAccount() {

    }

    @Override
    public void payToNumber() {

    }

    @Override
    public void payToUPI() {

    }

    @Override
    public void getCurrentAmount() {

    }


    @Override
    public void requestPaymentFromUPI() {

    }

    @Override
    public void requestPaymentFromPhone() {

    }

    @Override
    public void exit() {

    }
}
