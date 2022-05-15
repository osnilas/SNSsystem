package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;

/**
 * @author João Veiga
 * Controller of registration/creation of employee in US10
 */
public class RegisterEmployeeController {

    private Company company;
    private Employee em;
    private App app;

    public RegisterEmployeeController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public RegisterEmployeeController(Company company) {
        this.company = company;
        this.em = em;
    }

    /**
     * @author João Veiga
     * Asks company to create a employee and stores the employee in the controller
     * @param name      The employee's name
     * @param email     The employee's email address
     * @param phone     The employee's phone number
     * @param cc        The employee's cc number
     * @param adress    The employee's address
     * @param roleId    The employee's role
     * @return boolean of the result of validation of this Employee
     */
    public boolean createEmployee(String name, String email, int phone, int cc, String adress, String roleId) {
        this.em = this.company.createEmployee(name, email, phone, cc, adress, roleId);
        return this.company.validateEmployee(em);
    }

    /**
     * @author João Veiga
     * Saves employee in company
     * @return boolean of if the save was sucessful or not
     */
    public boolean saveEmployee() {
        return this.company.saveEmployees(em);
    }

    /**
     * @author João Veiga
     * Prints employee
     */
    public void printEmployee() {
        this.company.printEmployee(em);
    }

}
