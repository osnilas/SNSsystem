package app.domain.model;


import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static app.domain.model.Employee.FillRoleList;
import static app.domain.model.Employee.PrintListEmployees;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private static List<Employee> EmployeesList= new ArrayList<>();

    private static List<Employee> EmployeesListRole=new ArrayList<Employee>();
    private static List<User> UserList=new ArrayList<>();

    private static List<VaccineAdministration> VaccineAdministrationList = new ArrayList<>();

    private static List<VaccinationCenter> VaccinationCentersList= new ArrayList<VaccinationCenter>();


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

    public ArrayList<Employee> FillRoleArray(String role) {
        return FillRoleList(role, EmployeesList);
    }

    public boolean validateRoleArray(ArrayList<Employee> EmployeesRoleList){
        if(EmployeesRoleList== null){
            return false;
        }
        return true;

    }


    public VaccineAdministration createVaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Double> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
        return new VaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval);
    }

    public boolean validateVaccineAdministration (VaccineAdministration vaxAdm) {
        if(vaxAdm == null)  {
            return false;
        }
        return !VaccineAdministrationList.contains(vaxAdm);
    }

    public boolean saveVaccineAdministration (VaccineAdministration vaxAdm) {
        if (!validateVaccineAdministration(vaxAdm)) {
            return false;
        }
        return addVaccineAdministration(vaxAdm);
    }

    public void printVaccineAdministration (VaccineAdministration vaxAdm) { vaxAdm.toString(); }

    private boolean addVaccineAdministration (VaccineAdministration vaxAdm) {
        return VaccineAdministrationList.add(vaxAdm);
    }

    public void PrintListEmployeesFromRole(List<Employee> EmployeesRoleList) {
        PrintListEmployees(EmployeesRoleList);
    }

    public  VaccinationCenter createVaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, String openingAndClosingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot, String typeOfVaccine){
        return new VaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingAndClosingHours, slotDuration, maximumNumberOfVaccinesPerSlot, typeOfVaccine);
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