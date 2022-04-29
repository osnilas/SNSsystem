package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.User;
import mappers.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

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
    public boolean FillRoleArray(String role) {
        this.EmployeesRoleList=this.company.FillRoleArray(role);
        return this.company.validateRoleArray(this.EmployeesRoleList);
    }
    public void PrintListEmployeesFromRole(List<Employee> EmployeesRoleList) {
        this.company.PrintListEmployeesFromRole(EmployeesRoleList);
    }
}
