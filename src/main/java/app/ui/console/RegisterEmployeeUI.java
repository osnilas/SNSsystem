package app.ui.console;

import app.controller.RegisterEmployeeController;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoEmployee;

public class RegisterEmployeeUI implements Runnable {

    private RegisterEmployeeController ctlr;

    /**
     * @author João Veiga
     * Iniciates controller
     */
    public RegisterEmployeeUI() {
        ctlr = new RegisterEmployeeController();
    }

    public void run() {
        boolean sucess = register();
    }

    /**
     * @author João Veiga
     * Register's a Employee and user
     * @return boolean if registration was sucessful
     */
    private boolean register() {
        dtoEmployee dto = null;
        boolean sucess = false;
        boolean sucess2 = false;
        boolean flag = false;
        System.out.println("\nRegistration UI:");
        String email, name, address, id;
        int cc, number;

        do {
            name = Utils.readLineFromConsole("Enter name: ");
            if (name.isBlank()) {
                System.out.println("Input a valid name, it can not be empty");
            }
        } while (name.isBlank());

        do {
            address = Utils.readLineFromConsole("Enter adress: ");
            if (address.isBlank()) {
                System.out.println("Input a valid address, it can not be empty");
            }
        } while (address.isBlank());

        do {
            email = Utils.readLineFromConsole("Enter email adress: ");
            if (!Validate.validateEmail(email)) {
                System.out.println("Input a valid email, for exemple: isep@gmail.com");
            }
        } while (!Validate.validateEmail(email));

        do {
            cc = Utils.readIntegerFromConsole("Enter CC number");
            if (!Validate.validateCC(cc)) {
                System.out.println("Input a valid CC number, it has 8 digits");
            }
        } while (!Validate.validateCC(cc));
        id = email;

        do {
            number = Utils.readIntegerFromConsole("Enter phone number: ");
            if (!Validate.validatePhone(number)) {
                System.out.println("Input a valid phone number, this system suports portuguese format with 9 digits");
            }
        } while (!Validate.validatePhone(number));

        System.out.println("Choose the role of the employee");
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
             dto= new dtoEmployee(name,address,email,number,cc,role);
            sucess = ctlr.createEmployee(dto);

        }
        if (sucess) {
            ctlr.printEmployee();

            if (Utils.confirm("Is it correct?(s/n)")) {
                sucess = ctlr.saveEmployee(dto);
            } else {
                sucess = false;
            }
        }
        if (sucess ) {
            System.out.println("-----------Registration done successfully-----------");
        } else {
            System.out.println("-----------Registration failed---------------");
        }
        return sucess;
    }
}
