package javaGenerics;

import java.util.*;

public class generics {
    public <U extends Number> void help(U u){
        System.out.println(u);
    }

//    A class extends X impliments Y, Z;
//    expression should be like <A extends x & y & z>

    // new HashMap();
//     leads to warning as unchecked conversion warning

}



