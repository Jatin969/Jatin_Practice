package ArraysAndLoops;

public class forloopAndLabels {
    public static void main(String[] args) {

        //Lable based continue and break example:
        for(int i = 0; i<10; i++){
            lable1 :
            for(int j= 0; j<10; j++){
                for(int k = j; k<10; k++){
                    if(k == 5) break lable1;
                    System.out.println(String.format("i : %d, j : %d, k : %d", i, j,k));
                }
            }
        }

        //do while loop example
        //always gets executed one extra times as the while or check condition is calculated at the end of the loop.
        int val = 0;
        do{
            System.out.println(val);
        }while(val++ < 5);

        // while loop : Runs exactly val times as if -> val = 5 while loop gets
        // executed 5 times (with post decrement)
        while(val-->0){
            System.out.println(val);
        }


        //switch case :
        // used to construct conditional statements
        // break is necessary after every case
        // otherwise the previous case enters the block of the next case;
        switch (val){
            case 1 : System.out.println(" value is one."); break;
            case 2 : System.out.println(" value is two."); break;
            case 3 : System.out.println(" value is three."); break;
            default : System.out.println(" value is can't be determined as it is out of range."); break;
        }


        val = 3;
        int temp = 4, anotherTemp = 0;
        if(val == 0){

        }else if(val == 1){ // since this condition is false, we won't even enter this block.
            if(temp > 3 && anotherTemp != 0) {}
            else {}
        }else if (val == 4 || temp == 4){// since this condition is true, we shall even enter this block.
            if(anotherTemp == 0) System.out.println("this block would be executed.");
        }else {
            // since the previous condition gets executed this else block would be skipped.
        }

        if(val == 3) System.out.println("first");
        if(temp == 4)System.out.println("second");
        if(anotherTemp == 0)System.out.println("third");

        //above each block of if would be executed as they act separately.


        //below is an example for the terniary operator.
        val = (isEven(val)) ?  val + 1 : val / 2;

        System.out.println(0x10_23 ^ 100); //bitwise exclusive OR operation.
        System.out.println(7 | 0); //bitwise OR operation.
        System.out.println(7 & 0); // bitwise AND operation.






    }

    public static boolean isEven(int val){
        return val % 2 == 0;
    }
}
