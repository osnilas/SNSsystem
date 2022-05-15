package app.ui.console;

import app.controller.App;
import app.controller.RegisterVaccinationCenterController;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import app.domain.shared.Validate;

public class RegisterVaccinationCenterUI implements Runnable {

    private RegisterVaccinationCenterController ctlr;
    private VaccinationCenter vc;
    private App app;

    /**
     * @author Afonso Cunha
     * Iniciates controller
     */
    public RegisterVaccinationCenterUI() {
        ctlr = new RegisterVaccinationCenterController();
    }

    public void run() {
        boolean sucess = register();
    }

    /**
     * @author Afonso Cunha
     * Registation of a new vaccination center
     * @return boolean if registration was sucessful
     */
    private boolean register() {
        boolean sucess = false;
        boolean flag = false;
        System.out.println("\nRegistration UI:");
        String name, adress, emailAdress, websiteAdress, openingAndClosingHours, typeOfVaccine;
        int phoneNumber, faxNumber, slotDuration, maximumNumberOfVaccinesPerSlot;

        do {
            name = Utils.readLineFromConsole("Enter name: ");
            if (name.isBlank()) {
                System.out.println("Input a valid name, it can not be empty");
            }
        }
        while (name.isBlank());

        do {
            adress = Utils.readLineFromConsole("Enter adress: ");
            if (adress.isBlank()) {
                System.out.println("Input a valid address, it can not be empty");
            }
        } while (adress.isBlank());

        do {
            phoneNumber = Utils.readIntegerFromConsole("Enter phone number: ");
            if (!Validate.validatePhone(phoneNumber)) {
                System.out.println("Input a valid phone number, this system supports portuguese format with 9 digits");
            }
        } while (!Validate.validatePhone(phoneNumber));

        do {
            emailAdress = Utils.readLineFromConsole("Enter email adress: ");
            if (!Validate.validateEmail(emailAdress)) {
                System.out.println("Input a valid email, for exemple: isep@gmail.com");
            }
        } while (!Validate.validateEmail(emailAdress));

        do {
            typeOfVaccine = Utils.readLineFromConsole("Enter type of vaccine: ");
            if (!Validate.validateTypeOfVaccine(typeOfVaccine)) {
                System.out.println("Input a valid type of vaccine");
            }
        } while (!Validate.validateTypeOfVaccine(typeOfVaccine));

        do {
            faxNumber = Utils.readIntegerFromConsole("Enter fax number: ");
            if (!Validate.validatePhone(faxNumber)) {
                System.out.println("Input a valid fax number");
            }
        } while (!Validate.validatePhone(faxNumber));

        do {
            websiteAdress = Utils.readLineFromConsole("Enter website adress: ");
            if (!Validate.validateWebsiteAdress(websiteAdress)) {
                System.out.println("Input a valid website adress");
            }
        } while (!Validate.validateWebsiteAdress(websiteAdress));

        do {
            openingAndClosingHours = Utils.readLineFromConsole("Enter opening and closing hours: ");
            if (openingAndClosingHours.isBlank()) {
                System.out.println("Input opening and closing hours, it can not be empty");
            }
        } while (openingAndClosingHours.isBlank());

        do {
            slotDuration = Utils.readIntegerFromConsole("Enter the slot duration: ");
            if (!Validate.validateSlotDuration(slotDuration)) {
                System.out.println("Input a valid slot duration");
            }
        } while (!Validate.validateSlotDuration(slotDuration));

        do {
            maximumNumberOfVaccinesPerSlot = Utils.readIntegerFromConsole("Enter the maximum number of vaccines per slot: ");
            if (!Validate.validateMaximumNumberOfVaccinesPerSlot(maximumNumberOfVaccinesPerSlot)) {
                System.out.println("Input a valid number of maximum number of vaccines per slot");
            }
        } while (!Validate.validateMaximumNumberOfVaccinesPerSlot(maximumNumberOfVaccinesPerSlot));

        sucess = ctlr.createVaccinationCenter(name, adress, phoneNumber, emailAdress, faxNumber, websiteAdress, openingAndClosingHours, slotDuration, maximumNumberOfVaccinesPerSlot, typeOfVaccine);

        if (sucess){
            ctlr.printVaccinationCenter();
        }

        if (Utils.confirm("Is this correct? (y/n")){
            sucess = ctlr.saveVaccinationCenter();
        } else {
            sucess = false;
        }

        if (sucess){
            System.out.println("registration successful");
        } else {
            System.out.println("registration not successful");
        }
        return sucess;
    }
}

