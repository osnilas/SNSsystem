package app.controller;

import app.domain.model.Adress;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.ui.console.RegisterEmployeeUI;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

public class RegisterEmployeeController {

    private Company company;
    private Employee em;

    public RegisterEmployeeController(){
        this(App.getInstance().getCompany());
    }

    public RegisterEmployeeController(Company company){
        this.company=company;
        this.em=em;
    }

    public boolean createEmployee(String name, String email, int phone, Adress adress,String roleId){
        this.em=Company.createEmployee( name,  email,  phone,  adress,roleId);
        Company.addCardancials(name,email,roleId);
        return this.company.validateEmployee(em);
    }

    public boolean saveEmployee(){
        return this.company.saveEmployees(em);
    }


}
