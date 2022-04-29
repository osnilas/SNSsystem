package app.controller;

import app.domain.model.Company;
import app.domain.model.User;
import mappers.EmployeeMapper;

public class AddUserController {
    private Company company;
    private User us;
    private App app;
    private EmployeeMapper map;

    public AddUserController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public AddUserController(Company company) {
        this.company = company;
        this.us = us;
    }

    public boolean createUser(String id) {
        this.us = this.company.createUser(id);
        return this.company.validateUser(us);
    }

    public boolean saveUser() {
        return this.company.saveUser(us);
    }

    public void printUser() {
        this.company.printUser(us);
    }
}
