package app.ui.console;

import app.controller.App;
import app.controller.RegisterVaccinationCenterController;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

public class RegisterVaccinationCenterUI implements Runnable{

    private RegisterVaccinationCenterController ctlr;
    private VaccinationCenter vc;
    private App app;

    public RegisterVaccinationCenterUI() {
        ctlr = new RegisterVaccinationCenterController();
    }

    public void run() {
        boolean sucess = register();
    }

    private boolean register() {
        boolean sucess = false;
        boolean sucess2 = false;
        boolean flag = false;
        System.out.println("\nRegistration UI:");
        String name, adress, emailAdress, websiteAdress, openingAndClosingHours, typeOfVaccine;
        int phoneNumber, faxNumber, slotDuration, maximumNumberOfVacinesPerSlot;

        do {
         name= Utils.readLineFromConsole("Enter name: ");
         if (name.isBlank()){
         System.out.println("Input a valid name, it can not be empty");
         }
         }
         while (adress.isBlank());

        return true;
    }
}
