package tests;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US011 {

    Company company = App.getInstance().getCompany();

    @Test
    void fillRoleList() {
        Employee nurse = new Employee("Hugo","Rua da asasd",987654321,87654321,"hugo@gmail.com","Nurse");
        Employee receptionist = new Employee("Jorge","Rua da dfsf",987658321,87654621,"jorge@gmail.com","Receptionist");
        ArrayList<Employee> listOfEmployees = new ArrayList<Employee>();
        String role = Constants.RoleList[1];

        listOfEmployees.add(nurse);
        listOfEmployees.add(receptionist);
        company.FillRoleArray(role);

        assertTrue(company.FillRoleArray(role).isEmpty());

    }

}