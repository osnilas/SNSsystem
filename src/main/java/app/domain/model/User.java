package app.domain.model;

public class User {

    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    private int ccNumber;

    public User(String id, String name, String address, String email, int phoneNumber, int ccNumber){
        this.name= name;
        this.address=address;
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

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
