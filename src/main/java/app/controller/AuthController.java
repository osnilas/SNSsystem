package app.controller;

import app.domain.model.Company;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class AuthController {

    Company company;
    private App app;

    public AuthController()
    {
        this.app = App.getInstance();
        this.company=App.getInstance().getCompany();
    }

    public boolean doLogin(String email, String pwd)
    {
        try {
            return this.app.doLogin(email, pwd);
        } catch(IllegalArgumentException ex)
        {
            return false;
        }
    }

    public List<UserRoleDTO> getUserRoles()
    {
        if (this.app.getCurrentUserSession().isLoggedIn())
        {
            return this.app.getCurrentUserSession().getUserRoles();
        }
        return null;
    }
    public void save(){
        this.company.save();
    }


    public void doLogout()
    {
        this.app.doLogout();
    }
}
