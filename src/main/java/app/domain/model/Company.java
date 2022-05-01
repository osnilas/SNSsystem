package app.domain.model;


import app.ui.console.VaccineAdministrationUI;

import org.apache.commons.lang3.ObjectUtils;

import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static app.domain.model.Employee.FillRoleList;
import static app.domain.model.Employee.PrintListEmployees;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private static List<Employee> EmployeesList = new ArrayList<Employee>();

    private static List<VaccinationCenter> VaccinationCentersList= new ArrayList<VaccinationCenter>();
    private static List<Employee> EmployeesListRole = new ArrayList<Employee>();
    private static List<User> UserList = new ArrayList<>();


    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.designation = designation;
        this.authFacade = new AuthFacade();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    //for future implementation for login
    public void addCredencials(String name, String email, String roleId) {

    }

    /**
     * @author João Veiga
     * Creates User with the following attributes
     * @param id The User id, normally the email
     * @return User
     */
    public User createUser(String id) {
        return new User(id);
    }

    /**
     * @author João Veiga
     * Validates user
     * @param user
     * @return returns true if user isn't null nor already in the system
     */
    public boolean validateUser(User user) {
        if (user == null) {
            return false;
        }
        return !this.UserList.contains(user);
    }

    /**
     * @author: João Veiga
     *  Saves user in Arraylist of Users
     * @param user
     * @return adds user to ArrayList if it passes validation
     * @author: João Veiga
     */
    public boolean saveUser(User user) {
        if (!validateUser(user)) {
            return false;
        }
        return this.UserList.add(user);
    }

    /**
     * @author: João Veiga
     * Creates employee with the following attributes
     * @param name    The employee name
     * @param address The employee name
     * @param phone   The employee phone number
     * @param cc      The employee cc numer
     * @param email   The employee email address
     * @param roleId  The employee role
     * @return employee
     */
    public Employee createEmployee(String name, String email, int phone, int cc, String address, String roleId) {
        return new Employee(name, address, phone, cc, email, roleId);
    }

    /**
     * @author João Veiga
     * Validates Employee
     * @param employee
     * @return returns true if user isn't null nor already in the system
     */
    public boolean validateEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }
        return !this.EmployeesList.contains(employee);
    }

    /**
     * @author João Veiga
     * Saves Employee in a ArrayList of Employees
     * @param employee
     * @return adds Employee to Array list if it passes validation
     */
    public boolean saveEmployees(Employee employee) {
        if (!validateEmployee(employee)) {
            return false;
        }
        return this.EmployeesList.add(employee);
    }

    /**
     * @author João Veiga
     * prints User info
     * @param user
     */
    public void printUser(User user) {
        System.out.println(user.toString());
    }

    /**
     * @author João Veiga
     * prints Employees info
     * @param employee
     */
    public void printEmployee(Employee employee) {
        System.out.println(employee.toString());
    }

    public ArrayList<Employee> FillRoleArray(String role) {
        return FillRoleList(role, EmployeesList);
    }

    public boolean validateRoleArray(ArrayList<Employee> EmployeesRoleList) {
        if (EmployeesRoleList == null) {
            return false;
        }
        return true;

    }

    public VaccineAdministration createVaccineAdministration(List<Integer> age, List<Float> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
        return new VaccineAdministration(age, dosage, doses, vaccineInterval);
    }

    public void printVaccineAdministration(VaccineAdministration vaxAdm) {
        System.out.println(vaxAdm.toString());
    }

    public void PrintListEmployeesFromRole(List<Employee> EmployeesRoleList) {
        PrintListEmployees(EmployeesRoleList);
    }


    public  VaccinationCenter createVaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, String openingAndClosingHours, int slotDuration, int maximumNumberOfVacinesPerSlot, String typeOfVaccine){
        return new VaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingAndClosingHours, slotDuration, maximumNumberOfVacinesPerSlot, typeOfVaccine);
    }

    public boolean validateVaccinationCenter(VaccinationCenter vaccinationCenter){
        if(vaccinationCenter==null){
            return false;
        }
        return  ! this.VaccinationCentersList.contains(vaccinationCenter);
    }

    public boolean saveVaccinationCenter(VaccinationCenter vaccinationCenter){
        if(!validateVaccinationCenter(vaccinationCenter)){
            return false;
        }
        return this.VaccinationCentersList.add(vaccinationCenter);
    }

    public void printVaccinationCenter(VaccinationCenter vaccinationCenter){
        System.out.println(vaccinationCenter.toString());
    }

}
