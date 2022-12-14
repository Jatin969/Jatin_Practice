package ArraysAndLoops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectStreamComparision {
    enum Gender{
        MALE('M'), FEMALE('F');
        private Character character;
        Gender(Character ch){this.character = ch;}

        public Character getGender(){return character;}
    }
    static class Person implements Comparable<Person>{
        int age;
        String name;
        Gender orientation;

        public Person(int age, String name, Gender gender) {
            this.age = age;
            this.name = name;
            this.orientation = gender;
        }

        @Override
        public int compareTo(Person o) {
            return this.age - o.age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", orientation=" + orientation +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(12, "Ram", Gender.MALE));
        list.add(new Person(14, "Bala", Gender.FEMALE));
        list.add(new Person(10, "Jatin", Gender.MALE));
        list.add(new Person(11, "Piyush", Gender.MALE));


//        Collections.sort(list);

        Collections.sort(list,(a,b) ->{return a.orientation.toString().compareTo(b.orientation.toString());});
        List<Person> newList = list.stream().sorted(
                (a,b)->{return a.orientation.toString().compareTo(b.orientation.toString());}
                ).filter(person -> person.orientation.getGender() == 'M')
                .collect(Collectors.toList());
        list.stream().forEach(System.out::println);



    }

}
