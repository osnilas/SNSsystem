package app.ui.console;

import app.controller.SNSUserController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import app.controller.RoleMenuController;

import java.util.Scanner;

public class SNSUserUI implements Runnable{

    public static Scanner ler = new Scanner(System.in);

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
     * Menu for choosing the action from, for SNS users
     * @return boolean if registration was sucessful
     */
    private boolean SNSUserMenu (){
        boolean sucess = false;
        int aux = 0;
        String TypeVaccine;


        int option = Utils.readIntegerFromConsole("Option:");
        switch (option) {
            case 1:
                System.out.println("Schedule a vaccine");
                aux = 1;
                break;

            default:
                System.out.println("ERROR");
        }
        if (aux == 1){

            System.out.println("VaccineType: ");
            TypeVaccine = ler.nextLine();
            System.out.println();
            System.out.println("Choose a Vaccination Center: ");

        }

        if (sucess) {
            System.out.println("-----------List of Employees from chosen role done successfully-----------");
        } else {
            System.out.println("-----------List of Employees from chosen role done failed---------------");
        }
        return sucess;
    }

}
