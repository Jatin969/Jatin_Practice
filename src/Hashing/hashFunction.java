package Hashing;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class hashFunction {
    private static MessageDigest md;

    static {
        try{
            md = MessageDigest.getInstance("SHA-256");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
    private final static String key = "4jzyxBaF51eMdQ9fXUt2rR7WGNnqcvbhuoPZCDpVlm6sSE3iIJg8HYk8LAwTKO0";

    public static String getHash(long millis) throws DigestException {

        String s = dateAsString(millis);

        byte[] StringValDigest;

        try {
            md.update(s.getBytes(StandardCharsets.UTF_8));
            MessageDigest tc1 = (MessageDigest) md.clone();
            StringValDigest = md.digest();
        } catch (CloneNotSupportedException cnse) {
            throw new DigestException("couldn't make digest of partial content");
        }finally{
            md.reset();
        }

        return CastingToKey(StringValDigest);
    }

    private static String dateAsString(long millis){
        String pattern = "yyyy/MM/dd HH:mm:ss.SSS Z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(millis));
    }

    private static String CastingToKey(byte[] arr){
        StringBuilder sb= new StringBuilder();
        for(byte b : arr){
            sb.append(key.charAt((int)(Math.abs(b) * 31) / key.length()));
//            if(b<0) sb.append(key.charAt((int)(Math.abs(b) * 31) / key.length()));
//            else sb.append(key.charAt(b / key.length()));
        }
        return sb.toString();
    }

    public hashFunction(){
        super();
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String generateKey(){
        String val = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(val.toUpperCase() + "01234567889" + val.toLowerCase());
        int length = sb.length();
        Random rand = new Random();
        StringBuffer buffer = new StringBuffer();
        while(sb.length() != 0){
            int idx = rand.nextInt(sb.length());
            char ch = sb.charAt(idx);
            sb.deleteCharAt(idx);
            buffer.append(ch);
        }
        return buffer.toString();
    }

    @Test
    public void testing() throws DigestException {
        int collisions = 0;
        Set<String> codes = new HashSet<>();
        List<String> dates = new ArrayList<>();
        long millis = System.currentTimeMillis();
        for(int i = 0; i<1000000; i++){

            dates.add(dateAsString(millis));

            String hashCode =  hashFunction.getHash(millis++);

            if(codes.contains(hashCode)) collisions++;
            else codes.add(hashCode);
        }
        System.out.println(collisions);
//        System.out.println(dates);
//        System.out.println(codes);
        System.out.println(dates.size() == codes.size());

    }

    @Test
    public void test2() throws DigestException {
        long millis = System.currentTimeMillis();
        String hashCode2 =  hashFunction.getHash(millis);
        String hashCode1 =  hashFunction.getHash(millis);
        System.out.println(hashCode1);
        System.out.println(hashCode2);
        System.out.println(hashCode1.equals(hashCode2));
//        System.out.println(md.getAlgorithm());
//        System.out.println(md.getProvider());
        System.out.println("TEST FOR HASHING...");

    }



}
