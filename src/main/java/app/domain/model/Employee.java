package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    private int ccNumber;
    private String roleId;

    private static int count = 0;

    /**@author João Veiga
     * Constructor
     * Creates an Employee with the following arttributes
     * @param name          The Employee name
     * @param address       The Employee name
     * @param phone         The Employee phone number
     * @param cc            The Employee cc numer
     * @param email         The Employee email address
     * @param roleId        The Employee role
     */
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
    public static ArrayList<Employee> FillRoleList(String role, List<Employee> EmployeesList) {
        ArrayList<Employee> listOfEmployeesFromRole = new ArrayList<Employee>();


        for (int i = 0; i < EmployeesList.size(); i++) {
            Employee em = EmployeesList.get(i);
            if (em.IsEmployee(role)) {
                listOfEmployeesFromRole.add(em);
            }
        }
        return listOfEmployeesFromRole;
    }
    public static void PrintListEmployees(List<Employee> listOfEmployeesFromRole) {

        for (int i = 0; i < listOfEmployeesFromRole.size(); i++) {
            Employee em = listOfEmployeesFromRole.get(i);
            System.out.println(em.toString());
        }
    }

    @Override
    public String toString() {
        return "Employee: \n" +
                "id: " + id + '\t' +
                "name: " + name + '\t' +
                "adress: " + address +'\t'+
                "email: " + email + '\t' +
                "phone: " + phoneNumber +'\t'+
                "roleId: " + roleId;
    }
}
