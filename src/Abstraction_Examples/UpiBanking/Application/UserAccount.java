package Abstraction_Examples.UpiBanking.Application;

import java.util.Base64;

public class UserAccount {

    private String email;

    private String username;
    private String name;
    private String phoneNo;
    private String password;

    private String ipuId;



    protected void changeDetails(String name, String password){
        this.name = name;
        this.password = password;
    }

    public UserAccount(String name, String phoneNo, String password, String email, String username, String ipuId){
        this.email = email;
        this.username = username;
        this.ipuId = ipuId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

}
