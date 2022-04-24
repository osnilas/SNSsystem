package app.ui.console;


import app.controller.App;
import app.controller.RegisterEmployeeController;
import app.domain.model.Adress;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.User;
import pt.isep.lei.esoft.auth.domain.model.UserRole;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterEmployeeUI implements Runnable{
    private RegisterEmployeeController ctlr;
    private Employee em;

    public RegisterEmployeeUI() {ctlr= new RegisterEmployeeController();}

    public void run()
    {
        boolean sucess=register();
    }

    private boolean register() {
        System.out.println("\nRegistration UI:");

        String name = Utils.readLineFromConsole("Enter name: ");
        String id = Utils.readLineFromConsole("Enter Email: ");
        String adressfull= Utils.readLineFromConsole("Enter adress: ");
        String email= Utils.readLineFromConsole("Enter email adress: ");
        Adress adress= new Adress();
        adress.addAdress(adressfull);
        int number =Utils.readIntegerFromConsole("Enter number: ");
        String role=null;
        System.out.println("Select role for employee");
        System.out.println("1-Nurse");
        System.out.println("2-Recepionist");
        System.out.println("3-Coordinatior");
        int option= Utils.readIntegerFromConsole("Option:");
        switch(option){
            case 1: role=Constants.ROLE_NURSE;
            break;
            case 2: role=Constants.ROLE_RES;
            break;
            case 3:role=Constants.ROLE_COR;
            break;
            default:
                System.out.println("ERROR");
        }
        if(role==null){
            throw new IllegalArgumentException("Role not chosen");
        }
        else {
            ctlr.createEmployee(name, email, number, adress, role);
        }





        return true;
    }
}
