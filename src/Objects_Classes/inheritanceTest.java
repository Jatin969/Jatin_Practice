package Objects_Classes;

import java.util.ArrayList;


public class inheritanceTest {



        public static void main(String[] args) {
            jatin j = new jatin();
            j.add(10);
            j.add(100);
            j.add(1000);
            System.out.println(j.displayHello());
            System.out.println(j.displayNotHello());

        }


}
    class jatin implements hello, notHello{


        @Override
        public  void add(Integer val) {
            if(notHello.arr.contains(val) == false) notHello.arr.add(val);
        }

        @Override
        public void delete(Integer val) {
            if(hello.arr.contains(val)) hello.arr.remove(val);
        }

        public String displayHello(){
            return hello.arr.toString();
        }

        public String displayNotHello(){
            return notHello.arr.toString();
        }

    }

    interface hello{
        ArrayList<Integer> arr = new ArrayList<>();
        public void add(Integer val);
        public void delete(Integer val);
    }

    interface notHello{
        ArrayList<Integer>  arr =  new ArrayList<>();

        public void add(Integer val);
    }


