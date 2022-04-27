package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.User;
import mappers.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

public class RoleMenuController {

    private Company company;
    private Employee em;
    private App app;
    private EmployeeMapper map;

    public RoleMenuController() {
        this.company=App.getInstance().getCompany();
        this.app=App.getInstance();
    }

    public RoleMenuController (Company company){
        this.company=company;
        this.em=em;
    }
    public ArrayList<Employee> FillRoleArray(String role, List<Employee> EmployeeList) {
        return new ArrayList<Employee>();
    }


    }
