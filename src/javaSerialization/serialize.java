package javaSerialization;

import java.io.*;


public class serialize  {


    /**
     * @param args
     *
     * Serialization only works when the object implements Serializable interface. It is a marker interface.
     * All the things objects that has a has - A relation with the object to be serialized should also implement
     * Serializable interface.
     * If the super class implements Serializable subclass don't need to do that again.
     * Used for storing and persisting the state of an object for future use or transportation purpose.
     * File is saved in the form of byte data and can be used by another machine or JVM instance.
     * static variables and methods have no effect on them of serialization and can still be change as
     * they are not part of the object but the class itself.
     * transient keyword prevents the data to preserve its state at time of serialization and is assigned to its
     * default value during the process.
     * * InputFileStream and ObjectInputStream are used to write the object in the form of a file in file system.
     * * OutputFileStream and ObjectOutputStream are used to read the object and convert in to real object from the
     * file system.
     */
    public static void main(String[] args)  {
        toBeSerialized s = new toBeSerialized();
        System.out.println(s);

//        final Properties properties = System.getProperties();
//        System.out.println(properties);
//        state.txt

//        String str = (String) System.getProperties().toString();
//        System.out.println(str);
//
//        File f = new File("./newFolder");
//        f.mkdirs();
//            f = f.getCanonicalPath()
//            f.createNewFile();
        File f = new File("");
        String  str = f.getAbsolutePath();
        System.out.println("path : " + str);

        try(FileOutputStream fos = new FileOutputStream("newFolder/source.txt");
            ObjectOutputStream writer = new ObjectOutputStream(fos)) {
            writer.writeObject(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileInputStream fis = new FileInputStream("newFolder/source.txt");
            ObjectInputStream reader = new ObjectInputStream(fis)){
            toBeSerialized bose = (toBeSerialized) reader.readObject();
            System.out.println(bose);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

class toBeSerialized implements  Serializable{
    public Address address;
    public Integer value;
    public String name;
    public static String serialize_code;
    public transient Object_temp temp;

    static {
        toBeSerialized.serialize_code = "Norway";
    }


    public toBeSerialized(){
        this.address = new Address();
        this.temp = new Object_temp();
        this.value = 100;
        this.name = "John";
    }

    @Override
    public String toString() {
        return "toBeSerialized{" +
                "address=" + address +
                ", value=" + value +
                ", name='" + name + '\'' +
                ", temp=" + temp +
                '}';
    }
}

class Address implements  Serializable{
    String House_Code;
    String Locality;
    String State;
    String country;
    Long pin;

    public Address(){
        this.House_Code = "A-123";
        this.country = "India";
        this.pin = 110096L;
        this.Locality = "Laxmi Nagar";
        this.State = "Delhi";
    }

    @Override
    public String toString() {
        return "Address{" +
                "House_Code='" + House_Code + '\'' +
                ", Locality='" + Locality + '\'' +
                ", State='" + State + '\'' +
                ", country='" + country + '\'' +
                ", pin=" + pin +
                '}';
    }
}

class Object_temp implements Serializable{
    String object_code;

    public Object_temp(){
        this.object_code = "Hello";
    }
    @Override
    public String toString(){
        return object_code;
    }
}
