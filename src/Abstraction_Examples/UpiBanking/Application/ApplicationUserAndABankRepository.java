package Abstraction_Examples.UpiBanking.Application;

import java.util.HashMap;

public class ApplicationUserAndABankRepository {
    protected static HashMap<String,UserAccount> userRepo = new HashMap<>();
    protected static HashMap<String, String> banksWithInitials = new HashMap<>();
    static{
        banksWithInitials.put("State Bank of India","sbi");
        banksWithInitials.put("Axis Bank","apl");
        banksWithInitials.put("Federal Bank","fbl");
        banksWithInitials.put("ICICI bank","icici");
        banksWithInitials.put("IndusInd Bank","indus");
        banksWithInitials.put("Kotak Mahindra Bank","kmbl");
    }

}
