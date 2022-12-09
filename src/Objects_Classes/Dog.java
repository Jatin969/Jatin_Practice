package Objects_Classes;


/**
 *  This class represents the basic characteristics of a dog.
 *  This class acts as a model from which the object of dog having different state and behaviour can be created.
 *
 */
public class Dog {
    private final String breed;
    private final String name;
    private final int age;
    public void vocalBehaviour(){
        System.out.println("A dog barks.");
    }

    public void eatingHabits() {
        System.out.println("A dog is an omnivore.");
    }

    public Dog(String breed, String name, int age){
        this.breed = breed;
        this.name = name;
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}


