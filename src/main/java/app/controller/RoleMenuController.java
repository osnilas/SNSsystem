package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.User;
import mappers.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Filipe Magalhães
 * Controller of the Role Menu to list Employees in US011
 */

public class RoleMenuController {

    private Company company;
    private ArrayList<Employee> EmployeesRoleList = new ArrayList<>();
    private App app;
    private EmployeeMapper map;
    private String roleId;


    public RoleMenuController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public RoleMenuController(Company company) {
        this.company = company;
    }
    /**
     * @author João Veiga
     * Asks company to fill an array with all the employees registered with the previously chosen role
     * @param role      Chosen role
     * @return boolean of the result of validation of this Role Array
     */
    public boolean FillRoleArray(String role) {
        this.EmployeesRoleList=this.company.FillRoleArray(role);
        return this.company.validateRoleArray(EmployeesRoleList);
    }
    /**
     * @author Filipe Magalhães
     * Prints the list of employees from the chosen role
     */
    public void PrintListEmployeesFromRole(List<Employee> EmployeesRoleList) {
        this.company.PrintListEmployeesFromRole(EmployeesRoleList);
    }

}
