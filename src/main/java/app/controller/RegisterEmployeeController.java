package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import mappers.dto.dtoEmployee;

/**
 * @author João Veiga
 * @Description Controller of registration/creation of employee in US10
 */
public class RegisterEmployeeController {

    private Company company;
    private dtoEmployee dto;
    private Employee employee;
    private App app;

    public RegisterEmployeeController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public RegisterEmployeeController(Company company) {
        this.company = company;
        this.employee =employee;
    }

    /**
     * @author João Veiga
     * @Description Asks company to create a employee and stores the employee in the controller
     * @param dto dto of the class employee
     * @return boolean of the result of validation of this Employee
     */
    public boolean createEmployee(dtoEmployee dto) {
        this.employee = this.company.createEmployee(dto);
        return this.company.validateEmployee(employee);
    }

    /**
     * @author João Veiga
     * @Description Saves employee in company
     * @return boolean of if the save was sucessful or not
     */
    public boolean saveEmployee(dtoEmployee dto) {
        return this.company.saveEmployees(dto);
    }

    /**
     * @author João Veiga
     * @Description Prints employee
     */
    public void printEmployee() {
        this.company.printEmployee(employee);
    }
}
