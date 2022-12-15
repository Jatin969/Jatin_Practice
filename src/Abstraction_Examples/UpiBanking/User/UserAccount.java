package Abstraction_Examples.UpiBanking.User;

import Abstraction_Examples.UpiBanking.Exceptions.InvalidPhoneNumberException;
import Abstraction_Examples.UpiBanking.Exceptions.PhoneListEmptyException;
import Abstraction_Examples.UpiBanking.Exceptions.PhoneNumberNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAccount {

    private final String username;
    private String name;

    private List<Phone> phoneNos;
    private String password;

    private final String ipuId;





    public UserAccount(String name, Phone phoneNo, String password, String username, String ipuId) throws InvalidPhoneNumberException {
        this.username = username;
        this.ipuId = ipuId;
        this.name = name;
        this.phoneNos = new ArrayList<Phone>();
        this.phoneNos.add(phoneNo);
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }


    public String getPassword() {
        return password;
    }

    public void addPhoneNumber(Phone phone){
        if(this.phoneNos.size() > 3) throw new ArrayIndexOutOfBoundsException();
        Optional<Phone> present = this.phoneNos.stream().filter(p -> {return p.equals(phone);}).findFirst();
        if(present.isPresent()==false) this.phoneNos.add(phone);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void deletePhoneNumber(Phone number) throws PhoneListEmptyException, PhoneNumberNotFoundException {
        if(this.phoneNos.size() == 0) throw new PhoneListEmptyException();
        boolean numberPresent = false;
        for(int i = 0; i<this.phoneNos.size(); i++)
            if(this.phoneNos.get(i).equals(number)) {numberPresent = true; this.phoneNos.remove(i);}
        if(!numberPresent) throw new PhoneNumberNotFoundException();
    }

    public String getUPIId(){
        return this.ipuId;
    }


}
