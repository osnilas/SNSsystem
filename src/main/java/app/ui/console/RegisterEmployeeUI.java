package app.ui.console;

import app.controller.AddUserController;
import app.controller.App;
import app.controller.RegisterEmployeeController;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

public class RegisterEmployeeUI implements Runnable {

    private RegisterEmployeeController ctlr;
    private AddUserController ctlr2;
    private Employee em;
    private App app;

    //iniciates controller
    public RegisterEmployeeUI() {
        ctlr = new RegisterEmployeeController();
        ctlr2 = new AddUserController();
    }

    public void run() {
        boolean sucess = register();
    }

    private boolean register() {
        boolean sucess = false;
        boolean sucess2=false;
        System.out.println("\nRegistration UI:");


        String name = Utils.readLineFromConsole("Enter name: ");

        String adress = Utils.readLineFromConsole("Enter adress: ");
        String email = Utils.readLineFromConsole("Enter email adress: ");
        int cc = Utils.readIntegerFromConsole("Enter CC number");
        String id = email;
        int number = Utils.readIntegerFromConsole("Enter phone number: ");
        String role = null;
        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1 + "-" + Constants.RoleList[i]);
        }

        int option = Utils.readIntegerFromConsole("Option:");
        switch (option) {
            case 1:
                role = Constants.RoleList[0];
                break;
            case 2:
                role = Constants.RoleList[1];
                break;
            case 3:
                role = Constants.RoleList[2];
                break;
            default:
                System.out.println("ERROR");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role not chosen");
        } else {
            sucess2=ctlr2.createUser(email);
            sucess = ctlr.createEmployee(name, email, number, cc, adress, role);

        }
        if (sucess&&sucess2) {
            ctlr2.printUser();
            ctlr.printEmployee();
        }
        if (Utils.confirm("Is it correct?")) {
            sucess2=ctlr2.saveUser();
            sucess = ctlr.saveEmployee();
        } else {
            sucess = false;
        }
        if (sucess&&sucess2) {
            System.out.println("-----------Registration done successfully-----------");
        }
        else{
            System.out.println("-----------Registration failed---------------");
        }
        return sucess;
    }
}
