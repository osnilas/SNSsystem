package app.controller;

import app.domain.model.Company;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private AuthFacade authFacade;

    private App()
    {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap()
    {
        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_NURSE,Constants.ROLE_NURSE);
        this.authFacade.addUserRole(Constants.ROLE_SNS,Constants.ROLE_SNS);
        this.authFacade.addUserRole(Constants.ROLE_RES,Constants.ROLE_RES);
        this.authFacade.addUserRole(Constants.ROLE_COR,Constants.ROLE_COR);

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);


        this.authFacade.addUserWithRole("Test SNS user", "sns@lei.sem2.pt", "11",Constants.ROLE_SNS);

        this.authFacade.addUserWithRole(Constants.SN_SUSER_TESTER_FULL.getName(),Constants.SN_SUSER_TESTER_FULL.getEmail(),Constants.SN_SUSER_TESTER_FULL.getPassword(),Constants.ROLE_SNS);

        this.authFacade.addUserWithRole(Constants.SNS_USER_TESTER_ONE.getName(),Constants.SNS_USER_TESTER_ONE.getEmail(),Constants.SNS_USER_TESTER_ONE.getPassword(),Constants.ROLE_SNS);

        this.authFacade.addUserWithRole(Constants.SNS_USER_TESTER_EMPTY.getName(),Constants.SNS_USER_TESTER_EMPTY.getEmail(),Constants.SNS_USER_TESTER_EMPTY.getPassword(),Constants.ROLE_SNS);

        this.authFacade.addUserWithRole(" Test Receptionist", "res@lei.sem2.pt", "22",Constants.ROLE_RES);

        this.authFacade.addUserWithRole("Test nurse", "nurse@lei.sem2.pt", "33",Constants.ROLE_NURSE);

        this.authFacade.addUserWithRole("Coordinator", "coorPorto@lei.sem2.pt","44",Constants.ROLE_COR);
        this.authFacade.addUserWithRole("Tiago", "coorMaia@lei.sem2.pt","maia",Constants.ROLE_COR);
    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }
}