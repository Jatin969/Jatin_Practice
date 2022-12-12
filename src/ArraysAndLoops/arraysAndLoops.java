package ArraysAndLoops;
import java.util.Arrays;
import java.util.Random;

public class arraysAndLoops {
    public static void main(String[] args){

        // Parameters are variables that provide extra information to a method;
        // both local variables and parameters are always classified as "variables" (not "fields").

        String[] copyFrom = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto" };

        String[] copyHere = new String[copyFrom.length];
        System.arraycopy(copyFrom,1,copyHere,1,5);
        for(String copyHereElements : copyHere) System.out.print(copyHereElements + ",\t");
        System.out.println();

        //Here 9 is exclusive end, i.e.  ending index is not included in the copy.
        String[] copyTo = Arrays.copyOfRange(copyFrom, 2, 9);

        //Enhanced for loop :
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");
        }

        int[] arr = new int[1000_000];
        Random rand = new Random();
        for(int i = 0; i<arr.length; i++){
            arr[i] = rand.nextInt(1000000);
        }

        int[] arrCopy = Arrays.copyOf(arr,arr.length);

        long start = System.currentTimeMillis();
        Arrays.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("Time is sequential sorting. : " + (end - start));

        start = System.currentTimeMillis();
        Arrays.parallelSort(arrCopy);
         end = System.currentTimeMillis();
        System.out.println("Time is sequential sorting. : " + (end - start));



    }
}
