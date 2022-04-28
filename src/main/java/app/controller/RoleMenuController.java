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
    private String roleId;


    public RoleMenuController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public RoleMenuController(Company company) {
        this.company = company;
        this.em = em;
    }

    public ArrayList<Employee> FillRoleArray(String role, List<Employee> EmployeeList) {
        ArrayList<Employee> listOfEmployeesFromRole = new ArrayList<Employee>();


        for (int i = 0; i < EmployeeList.size(); i++) {
            Employee em = EmployeeList.get(i);
            if (em.IsEmployee(role)) {
                listOfEmployeesFromRole.add(em);
            }
        }
        return listOfEmployeesFromRole;
    }
    public void PrintListEmployeesFromRole(List<Employee> listOfEmployeesFromRole) {

        for (int i = 0; i < listOfEmployeesFromRole.size(); i++) {
            Employee em = listOfEmployeesFromRole.get(i);
            System.out.println(em.toString());
        }
    }

}
