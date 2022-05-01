package app.controller;

import app.domain.model.Company;
import app.domain.model.User;
import mappers.EmployeeMapper;


/**
 * @author João Veiga
 * Controller of registration/creation of user in US10
 */
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

    /**
     * @author João Veiga
     * Creates a user with the following attributes
     * @param id    The user's id, normally it's email
     * @return boolean of user validation
     */
    public boolean createUser(String id) {
        this.us = this.company.createUser(id);
        return this.company.validateUser(us);
    }

    /**
     * @author João Veiga
     * Saves user in company
     * @return boolean of if the save was sucessful or not
     */
    public boolean saveUser() {
        return this.company.saveUser(us);
    }

    /**
     * @author João Veiga
     * Prints user's info
     */
    public void printUser() {
        this.company.printUser(us);
    }
}
