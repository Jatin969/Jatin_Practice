package Abstraction_Examples.UpiBanking.Application;

import java.util.Base64;

public class UserAccount {

    private final String username;
    private String name;
    private String phoneNo;
    private String password;

    private final String ipuId;



    public void changeDetails(String... args){
        if(this.password.equals(args[0]) == false) return;
        this.name = args[2];
        this.phoneNo = args[3];

    }

    public UserAccount(String name, String phoneNo, String password, String username, String ipuId){
        this.username = username;
        this.ipuId = ipuId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }

    public String getUsername(){
        return this.username;
    }


    public String getPassword() {
        return password;
    }

}
