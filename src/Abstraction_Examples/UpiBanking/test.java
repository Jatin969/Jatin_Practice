package Abstraction_Examples.UpiBanking;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class test {

    public static void main(String[] args) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        UPI_UserRepository repo = new UPI_UserRepository();

        UPI_User current_User = repo.authenticate("1231@sbi", "password1");
        System.out.println(current_User);

    }
}
