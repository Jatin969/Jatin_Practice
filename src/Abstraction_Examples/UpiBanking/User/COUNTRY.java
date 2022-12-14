package Abstraction_Examples.UpiBanking.User;

public enum COUNTRY {

    INDIA("INDIA");

    private final String name;
    COUNTRY(String name){
        this.name = name;
    }

    public String getName(){return this.name;}
}
