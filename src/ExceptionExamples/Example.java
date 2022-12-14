package ExceptionExamples;

public class Example {
    static int val;
    public static void method1(){
        method2();
    }

    public static void method2(){
        method1();
    }

    public static void stackOverflowExceptionExample(){

        method1();
    }


    public static void IndexOutOfBoundExample(){
        int[] array = new int[10];
        for(int i = 0; i<=10; i++){
            array[i] = i;
        }

        //Exception would be thrown at index 10;
        //which is not available for an array of length 10 and
        //having maximum index of 9
    }

    public static void NullPointerExceptionExample(){
        String string = null;
        System.out.println(string.length());
    }

    public static void ClassCastExceptionExample(){
        Object obj = (Object) new String("Jatin");

        String string = (String) new Object();

    }



    static {
        //This code represents exception in Initializer and this exception also gives the cause of this exception

        Example.val = 10/0;
    }

    public void IllegalArgumentExeptionExample(){
        Thread t = new Thread();
        t.setPriority(10);
        // only takes values in range 1 - 10;
        t.setPriority(100);
        // causes the exception;
    }

    public void NumberFormatExceptionExample(){
        int number = Integer.parseInt("ten");
    }

    public void IllegalStateExceptionExample(){
        Thread t = new Thread();
        t.start();
        t.start();// thread already started, hence causes exception
    }

    public void AssertionStateException(){
        assert(10/0 == 0);
        //assertions are by default desabled in java
        // we have to use java -ea example
    }

    public static void main(String[] args) {

    }
}
