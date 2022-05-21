package app.domain.model;

public class User {

    private String name;
    private String residenceAddress;
    private String email;
    private int phoneNumber;
    private int ccNumber;

    public User(String id, String name, String address, String email, int phoneNumber, int ccNumber){
        this.name= name;
        this.residenceAddress =address;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.ccNumber=ccNumber;

    }

    public String getEmail() {
        return email;
    }

    public int getCcNumber() {
        return ccNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public String getName() {
        return name;
    }
}
