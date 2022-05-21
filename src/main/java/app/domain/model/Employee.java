package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    private int employeeID;

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

        super(email,name,address,email,phone,cc);

        count++;

        this.employeeID = count;

        this.roleId = roleId;
    }

    //getters
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
                "name: " + getName() + '\t' +
                "residence adress: " + getResidenceAddress() +'\t'+
                "email: " + getEmail() + '\t' +
                "phone: " + getPhoneNumber() +'\t'+
                "roleId: " + roleId;
    }
}
