package javaGenerics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class test {
    @Test
    public void test_number1(){
        genericHashMap<Integer, String> map = new genericHashMap<>();
        for(int i = 0; i<100; i++)
            map.put(i,"hello");
        System.out.println(map);
        System.out.println(map.getKeySet());
        System.out.println(map.sizeOfMapList());
    }
}
