package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;

import javax.management.relation.RoleList;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Employee {
    private int id;
    private String name;
    private String adress;
    private String email;
    private int phoneNumber;
    private int ccNumber;
    private String roleId;

    private static int count = 0;

    public Employee(String name, String adress, int phone, int cc, String email, String roleId) {
        count++;
        this.id = count;

        this.name = name;

        this.adress = adress;

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

    public String getAdress() {
        return adress;
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
                ", adress=" + adress +
                ", email='" + email + '\'' +
                ", phone=" + phoneNumber +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
