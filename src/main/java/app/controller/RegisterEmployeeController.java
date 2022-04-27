package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import mappers.EmployeeMapper;
import pt.isep.lei.esoft.auth.domain.store.UserRoleStore;
import pt.isep.lei.esoft.auth.mappers.UserRoleMapper;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;


public class RegisterEmployeeController {

    private Company company;
    private Employee em;
    private App app;
    private EmployeeMapper map;

    public RegisterEmployeeController(){
        this.company=App.getInstance().getCompany();
        this.app=App.getInstance();
    }

    public RegisterEmployeeController(Company company){
        this.company=company;
        this.em=em;
    }

    public boolean createEmployee(String name, String email, int phone,int cc, String adress,String roleId){
        this.em=this.company.createEmployee( name,  email, phone,cc,  adress,roleId);
        this.app.addUser(name,email,roleId);
        return this.company.validateEmployee(em);
    }

    public boolean saveEmployee(){
        return this.company.saveEmployees(em);
    }

    public void printEmployee(){
        this.company.printEmployee(em);
    }

}
