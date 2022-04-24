package app.domain.model;

import app.controller.App;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private List<Employee> EmployeesList;
    private static App app;


    public Company(String designation)
    {
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

    public static void addCardancials(String name,String email,String roleId){
        app.addUser(name,email, roleId);
    }

    public static Employee createEmployee(String name, String email, int phone, Adress adress, String roleId){
        return new Employee(name,adress,phone,email,roleId);
    }

    public boolean validateEmployee(Employee employee){
        if(employee==null){
            return false;
        }
        return ! this.EmployeesList.contains(employee);
    }


    public boolean saveEmployees(Employee employee){
    if(!validateEmployee(employee)){
        return false;
    }
    return this.EmployeesList.add(employee);
    }
}