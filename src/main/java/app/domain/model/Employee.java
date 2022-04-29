package app.domain.model;

public class Employee {
    private int id;
    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    private int ccNumber;
    private String roleId;

    private static int count = 0;

    public Employee(String name, String address, int phone, int cc, String email, String roleId) {
        count++;
        this.id = count;

        this.name = name;

        this.address = address;

        this.roleId = roleId;

        this.email = email;

        this.phoneNumber = phone;

        this.ccNumber = cc;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phoneNumber;
    }

    public int getCc() {
        return ccNumber;
    }

    public String getRoleId() {
        return roleId;
    }


    public boolean IsEmployee (String role){
        if (role.equals(this.roleId)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", adress=" + address +
                ", email='" + email + '\'' +
                ", phone=" + phoneNumber +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
