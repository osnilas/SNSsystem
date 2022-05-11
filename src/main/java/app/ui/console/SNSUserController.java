package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.User;
import mappers.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Filipe Magalhães
 * Controller of the SNS User in US001
 */

public class SNSUserController  {

    private Company company;
    private App app;
    private EmployeeMapper map;
    private String roleId;


    public SNSUserController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public SNSUserController(Company company) {
        this.company = company;
    }

}
