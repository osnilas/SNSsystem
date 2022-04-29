package app.domain.model;

import app.ui.console.VaccineAdministrationUI;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private  static List<Employee> EmployeesList= new ArrayList<Employee>();
    private static List<User> UserList=new ArrayList<>();


    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.designation = designation;
        this.authFacade = new AuthFacade();
        EmployeesList.add(new Employee("null", "null", 111111111,11111111, "nullemail@nul.com", "null"));
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }


    public  void addCredencials(String name, String email, String roleId){

    }

    public User createUser(String id){
        return new User(id);
    }

    public boolean validateUser(User user){
        if(user==null){
            return false;
        }
        return ! this.UserList.contains(user);
    }

    public boolean saveUser(User user){
        if(!validateUser(user)){
            return false;
        }
        return this.UserList.add(user);
    }

    public  Employee createEmployee(String name, String email, int phone, int cc,String adress, String roleId){
        return new Employee(name,adress,phone,cc,email,roleId);
    }

    public boolean validateEmployee(Employee employee){
        if(employee==null){
            return false;
        }
        return  ! this.EmployeesList.contains(employee);
    }


    public boolean saveEmployees(Employee employee){
    if(!validateEmployee(employee)){
        return false;
    }
    return this.EmployeesList.add(employee);
    }

    public void printUser(User user){
        System.out.println(user.toString());
    }
    public void printEmployee(Employee employee){
        System.out.println(employee.toString());
    }

    public ArrayList <Employee> FillRoleArray(String role, List<Employee> EmployeeList){

        ArrayList<Employee> ListOfEmployeesFromRole = new ArrayList<>();
        return ListOfEmployeesFromRole;
    }

    public void PrintListEmployeesFromRole(List<Employee> listOfEmployeesFromRole) {

        for (int i = 0; i < listOfEmployeesFromRole.size(); i++) {
            Employee em = listOfEmployeesFromRole.get(i);
            System.out.println(em.toString());
        }
    }


    public VaccineAdministration createVaccineAdministration(List<Integer> age, List<Float> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
        return new VaccineAdministration(age, dosage, doses, vaccineInterval);
    }

    public void printVaccineAdministration (VaccineAdministration vaxAdm) {
        System.out.println(vaxAdm.toString());
    }
}