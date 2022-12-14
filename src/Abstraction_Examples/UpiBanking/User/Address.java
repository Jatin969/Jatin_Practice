package Abstraction_Examples.UpiBanking.User;

import java.util.Objects;

public class Address {

    private enum COUNTRY{
        INDIA, SINGAPORE;
    }
    private String firstLine;
    private String Locality;
    private String State;
    private int pinCode;

    private COUNTRY country;

    public COUNTRY getCountry() {
        return country;
    }

    public void setCountry(COUNTRY country) {
        this.country = country;
    }

    public Address(String firstLine, String locality, String state, int pinCode, COUNTRY country) {
        this.firstLine = firstLine;
        Locality = locality;
        State = state;
        this.pinCode = pinCode;
        this.country = country;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "firstLine='" + firstLine + '\'' +
                ", Locality='" + Locality + '\'' +
                ", State='" + State + '\'' +
                ", pinCode=" + pinCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return  getPinCode() == address.getPinCode() &&
                getFirstLine().equals(address.getFirstLine()) &&
                getLocality().equals(address.getLocality()) &&
                getState().equals(address.getState()) &&
                getCountry() == address.getCountry();
    }

}
