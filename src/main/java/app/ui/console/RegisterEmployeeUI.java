package app.ui.console;

import app.controller.App;
import app.controller.RegisterEmployeeController;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

public class RegisterEmployeeUI implements Runnable{

    private RegisterEmployeeController ctlr;
    private Employee em;
    private App app;

    //iniciates controller
    public RegisterEmployeeUI() {ctlr= new RegisterEmployeeController();}

    public void run()
    {
        boolean sucess=register();
    }

    private boolean register() {
        boolean sucess=false;
        System.out.println("\nRegistration UI:");


        String name = Utils.readLineFromConsole("Enter name: ");
        String adress= Utils.readLineFromConsole("Enter adress: ");
        String email= Utils.readLineFromConsole("Enter email adress: ");
        int cc=Utils.readIntegerFromConsole("Enter CC number");
        String id=email;
        int number =Utils.readIntegerFromConsole("Enter phone number: ");
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
            sucess=ctlr.createEmployee(name, email, number,cc, adress, role);
        }
        if(sucess){
            ctlr.printEmployee();
        }
        if(Utils.confirm("Is it correct?")){
            sucess=ctlr.saveEmployee();
        }
        else{
            sucess=false;
        }
        return sucess;
    }
}
