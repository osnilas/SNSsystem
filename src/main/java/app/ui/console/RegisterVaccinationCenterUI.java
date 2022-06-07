package app.ui.console;

import app.controller.App;
import app.controller.RegisterVaccinationCenterController;
import app.domain.model.MassVaccinationCenter;
import app.ui.console.utils.Utils;
import app.domain.shared.Validate;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegisterVaccinationCenterUI implements Runnable {

    private RegisterVaccinationCenterController ctlr;
    private MassVaccinationCenter vc;
    private App app;

    /**
     * @author Afonso Cunha
     * Iniciates controller
     */
    public RegisterVaccinationCenterUI() {
        ctlr = new RegisterVaccinationCenterController();
    }

    public void run() {
        boolean success = register();
    }

    /**
     * @author Afonso Cunha
     * Registation of a new vaccination center
     * @return boolean if registration was successful
     */
    private boolean register() {
        boolean success = false;
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
            adress = Utils.readLineFromConsole("Enter address: ");
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
            emailAdress = Utils.readLineFromConsole("Enter email address: ");
            if (!Validate.validateEmail(emailAdress)) {
                System.out.println("Input a valid email, for example: isep@gmail.com");
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
            websiteAdress = Utils.readLineFromConsole("Enter website address: ");
            if (!Validate.validateWebsiteAddress(websiteAdress)) {
                System.out.println("Input a valid website address");
            }
        } while (!Validate.validateWebsiteAddress(websiteAdress));

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

        //success = ctlr.createVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours,closingHours, slotDuration, maximumNumberOfVaccinesPerSlot, typeOfVaccine);

        if (success){
            ctlr.printVaccinationCenter();
        }

        if (Utils.confirm("Is this correct? (s/n)")){
            success = ctlr.saveVaccinationCenter();
        } else {
            success = false;
        }

        if (success){
            System.out.println("registration successful");
            ctlr.save();
        } else {
            System.out.println("registration not successful");
        }
        return success;
    }
}

