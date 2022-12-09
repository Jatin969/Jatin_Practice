package Objects_Classes;

public class forExecution {
    /**
     *
     * @param args are entry point arguments.
     *             type : Array of (Object : String)
     * main method acts as the entrypoint for the execution of the program.
     *
     *
     */
    public static void main(String[] args) {
        /*
         * Created an object using the Dog class named Charlie.
         * We can create multiple objects using the same class architecture.
         */
        Dog Charlie = new Dog("Shepherd","Charlie", 1);
        Charlie.eatingHabits();
        Charlie.vocalBehaviour();

        Dog Bob = new Dog("Pug", "Bob", 3);
        int ageOfBob = Bob.getAge();
        System.out.println("Age of the dog: " + ageOfBob);
        System.out.println("Name of the dog: " + Bob.getName());
        System.out.println("Breed of the dog: " + Bob.getBreed());

    }
}