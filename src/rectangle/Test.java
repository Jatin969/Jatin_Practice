package rectangle;


import java.util.Scanner;

    class rectangle {
        int length, breadth;

        rectangle(int length, int breadth) {
            super();
            this.length = length;
            this.breadth = breadth;
        }

        public long area() {
            return (this.length * this.breadth);
        }

        public long perimeter() {
            return (2 * (long)(this.length + this.breadth));
        }
    }

    public class Test {
        private static final Scanner scn = new Scanner(System.in);

        /**
         * @param args
         * @throws InterruptedException
         *
         */
         public static void main(String[] args) throws InterruptedException {
             taskOfTest();
             /*
               Whenever we use thread related methods as these threads may be interrupted
               by anything else, the method that contains it should throw
               InterruptedException

              */
             Thread.sleep(2000);
             int count = 5;
             while (count > 0) {
                 int val = wantToContinue();
                 switch (val) {
                     case 0: {
                         taskOfTest();
                         break;
                     }
                     case 1: {
                         System.exit(0);
                         break;
                     }
                     default: {
                         count--;
                         System.out.println("Please choose a valid option.");
                         break;
                     }
                 }
             }
             System.exit(0);
         }

        private static final void taskOfTest() {
            int length = 0, breadth = 0;
            while (length == 0)
                length = setValue("length");
            while (breadth == 0)
                breadth = setValue("breadth");
            if (length < breadth) {
                int temp = length;
                length = breadth;
                breadth = temp;
            }
            rectangle first = new rectangle(length, breadth);
            System.out.println("Area : " + first.area());
            System.out.println("Perimeter : " + first.perimeter());

        }

        private static final int wantToContinue() {
            System.out.println("Want to continue? (y/n)");
            String s = scn.next().toLowerCase();
            if (s.equals("y"))
                return 0;
            else if (s.equals("n"))
                return 1;
            else
                return 2;
        }

        private static final int setValue(String value) {
            System.out.println("Enter  " + value + ": ");
            String s = scn.next();
            if (checkIfANumber(s))
                return Integer.parseInt(s);
            return 0;
        }

        private static final boolean checkIfANumber(String s) {
            int val = 0;
            try {
                val = Integer.parseInt(s);
                if (val <= 0)
                    System.out.println("Please enter a number greater than 0.");
                return val > 0;
            } catch (Exception e) {
                System.out.println("Please enter an Integer value.");
                return false;
            }
        }

    }
