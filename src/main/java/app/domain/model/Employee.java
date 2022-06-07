package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {

    private String name;
    private String residenceAddress;
    private String email;
    private int phoneNumber;
    private int ccNumber;
    private int employeeID;

    private String roleId;

    private static int count = 0;

    /**@author João Veiga
     * @Description Constructor,
     * Creates an Employee with the following arttributes
     * @param name          The Employee name
     * @param address       The Employee name
     * @param phone         The Employee phone number
     * @param cc            The Employee cc numer
     * @param email         The Employee email address
     * @param roleId        The Employee role
     */
    public Employee(String name, String address, int phone, int cc, String email, String roleId) {

        this.name=name;
        this.residenceAddress=address;
        this.phoneNumber=phone;
        this.ccNumber=cc;
        this.email=email;
        count++;
        this.employeeID = count;

        this.roleId = roleId;
    }

    //getters
    public String getName() {
        return name;
    }
    public String getResidenceAddress() {
        return residenceAddress;
    }
    public String getEmail() {
        return email;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public int getCcNumber() {
        return ccNumber;
    }
    public int getEmployeeID() {
        return employeeID;
    }

    public String getRoleId() {
        return roleId;
    }


    public boolean isEmployee (String role){
        if (role.equals(this.roleId)){
            return true;
        }
        return false;
    }
    public static ArrayList<Employee> fillRoleList(String role, List<Employee> EmployeesList) {
        ArrayList<Employee> listOfEmployeesFromRole = new ArrayList<Employee>();


        for (int i = 0; i < EmployeesList.size(); i++) {
            Employee em = EmployeesList.get(i);
            if (em.isEmployee(role)) {
                listOfEmployeesFromRole.add(em);
            }
        }
        return listOfEmployeesFromRole;
    }

    @Override
    public String toString() {
        return "Employee: \n" +
                "name: " + name + '\t' +
                "residence adress: " + residenceAddress +'\t'+
                "email: " + email + '\t' +
                "phone: " + phoneNumber +'\t'+
                "roleId: " + roleId;
    }
}
