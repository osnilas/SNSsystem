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

    private static int count=0;

    public Employee(String name,String adress,int phone,int cc,String email, String roleId){
        count++;
        this.id=count;
        this.name=name;
        this.adress=adress;

        this.roleId=roleId;

        if(!this.validateEmail(email)){
            throw new IllegalArgumentException("Invalid email.");
        }
        else {
            this.email = email;
        }
        if(!this.validatePhone(phone)){
            throw new IllegalArgumentException("Invalid phone number.");
        }
        else {
            this.phoneNumber = phone;
        }
        this.ccNumber=cc;
    }
    //getters
    public int getId() {return id;}
    public String getName() {return name;}
    public String getAdress() {return adress;}
    public String getEmail() {return email;}
    public int getPhone() {return phoneNumber;}
    public int getCc() {return ccNumber;}
    public String getRoleId() {return roleId;}

    public boolean validateEmail(String email){
            return StringUtils.isBlank(email) ? false : this.checkFormat(email);
    }

    //from the email class
    private boolean checkFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public boolean validatePhone(int phone){
        if(getDigits(phone)!= Constants.PHONE_NUMBER_LENGHT){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean validateName(String name){
        if(name.isEmpty()||name==null||name.trim().isEmpty()){
            return false;
        }
        return true;
    }

    public boolean validateAdress(String Adress){
        if(adress.isEmpty()||adress==null||adress.trim().isEmpty()){
            return false;
        }
        return true;
    }

    public boolean validateCC(int cc){
        if(getDigits(phoneNumber)!= Constants.CC_LENGHT){
        return false;
    }
    else{
        return true;
    }}

    public int getDigits(int number){
        int count=0;
        while (number>0){
            number=number/10;
            count++;
        }
        return count;
    }

    public boolean IsEmployee (String role){
        if (role.equals(this.roleId)){
            return true;
        }
            return false;
    }

    public ArrayList <Employee> FillRoleArray(String role, List<Employee> EmployeeList){
        ArrayList<Employee> listOfEmployeesFromRole = new ArrayList<Employee>();


            for (int i =0;i<EmployeeList.size();i++){
               Employee em= EmployeeList.get(i);
                if(em.IsEmployee(role)){
                    listOfEmployeesFromRole.add(em);
                }
            }
        return listOfEmployeesFromRole;
    }

    public void ListEmployeesFromRole (List<Employee> listOfEmployeesFromRole){

        for (int i = 0; i < listOfEmployeesFromRole.size(); i++) {
            Employee em= listOfEmployeesFromRole.get(i);
            System.out.println(em.toString());
        }
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
