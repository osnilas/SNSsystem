package app.ui.console;

import app.controller.SNSUserController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import app.controller.RoleMenuController;

public class SNSUserUI implements Runnable{
    public SNSUserUI()
    {
    }
    /**
     * @author Filipe Magalhães
     * Iniciates controller
     */
    private SNSUserController ctrl = new SNSUserController();

    @Override
    public void run (){

    }
    /**
     * @author Filipe Magalhães
     * Menu for choosing the role to list the employees from
     * @return boolean if registration was sucessful
     */
    private boolean SNSUserMenu (){
        boolean sucess = false;

        int option = Utils.readIntegerFromConsole("Option:");
        switch (option) {
            case 1:
                System.out.println("Schedule a vaccine");
                break;

            default:
                System.out.println("ERROR");
        }

        if (sucess) {
            System.out.println("-----------List of Employees from chosen role done successfully-----------");
        } else {
            System.out.println("-----------List of Employees from chosen role done failed---------------");
        }
        return sucess;
    }

}
