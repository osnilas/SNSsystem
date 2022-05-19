package app.ui.console;

import app.controller.App;
import app.controller.RegisterVaccinationCenterController;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import app.domain.shared.Validate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        String name, adress, emailAdress, websiteAdress, openingHoursString,closingHoursString, typeOfVaccine;
        int phoneNumber, faxNumber, slotDuration, maximumNumberOfVaccinesPerSlot;
        LocalTime openingHours,closingHours;

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
            openingHoursString = Utils.readLineFromConsole("Enter opening hours: ");
            if (openingHoursString.isBlank() && !Validate.validateTime(openingHoursString)) {
                System.out.println("Input opening hours, it can not be empty");
            }
        } while (openingHoursString.isBlank() && !Validate.validateTime(openingHoursString));
        openingHours=Utils.createTime(LocalDate.now(),openingHoursString);

        do {
            closingHoursString = Utils.readLineFromConsole("Enter closing hours: ");
            if (closingHoursString.isBlank() && !Validate.validateTime(openingHoursString)) {
                System.out.println("Input closing hours, it can not be empty");
            }
        } while (closingHoursString.isBlank() && !Validate.validateTime(openingHoursString));
        closingHours=Utils.createTime(LocalDate.now(),closingHoursString);

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

        //sucess = ctlr.createVaccinationCenter(name, adress, phoneNumber, emailAdress, faxNumber, websiteAdress, openingHours,closingHours, slotDuration, maximumNumberOfVaccinesPerSlot, typeOfVaccine);

        if (sucess){
            ctlr.printVaccinationCenter();
        }

        if (Utils.confirm("Is this correct? (s/n)")){
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

