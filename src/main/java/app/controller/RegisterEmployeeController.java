package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import mappers.EmployeeMapper;


public class RegisterEmployeeController {

    private Company company;
    private Employee em;
    private EmployeeMapper map;
    private App app;

    public RegisterEmployeeController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public RegisterEmployeeController(Company company) {
        this.company = company;
        this.em = em;
    }

    public boolean createEmployee(String name, String email, int phone, int cc, String adress, String roleId) {
        this.em = this.company.createEmployee(name, email, phone, cc, adress, roleId);
        return this.company.validateEmployee(em);
    }

    public boolean saveEmployee() {
        return this.company.saveEmployees(em);
    }

    public void printEmployee() {
        this.company.printEmployee(em);
    }

}
