package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
//import mappers.EmployeeMapper;

import java.util.List;

import static app.domain.shared.Constants.EMPLOYEE_ARRAY_LIST;

/**
 * @author Filipe Magalhães
 * Controller of the Role Menu to list Employees in US011
 */

public class RoleMenuController {

    private Company company;
    private App app;
    private String roleId;



    public RoleMenuController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public RoleMenuController(Company company) {
        this.company = company;
    }
    /**
     * @author Filipe Magalhães
     * Copies the array with the Employees of the chosen role to another array
     * @param role      Chosen role
     * @return boolean of the result of validation of this Role Array
     */
    public boolean fillRoleArray(String role) {
        EMPLOYEE_ARRAY_LIST.addAll(this.company.FillRoleArray(role));
        return this.company.validateRoleArray(EMPLOYEE_ARRAY_LIST);
    }

}
