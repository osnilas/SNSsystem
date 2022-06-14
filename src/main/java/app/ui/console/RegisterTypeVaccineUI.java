package app.ui.console;

import app.controller.RegisterTypeVaccineController;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class RegisterTypeVaccineUI implements Runnable{

    public static Scanner ler = new Scanner(System.in);

    private RegisterTypeVaccineController ctrl = new RegisterTypeVaccineController();

    @Override
    public void run() {
        boolean success;
        String name;
        String description;
        String code;
        String vaccineTechnology;

        System.out.printf("-------------------------Register a new Vaccine-------------------------%n%n");

        do {
            name = Utils.readLineFromConsole("Vaccine name: ");

            description = Utils.readLineFromConsole("Enter vaccine description: ");

            do {
                success = true;
                code = Utils.readLineFromConsole("Vaccine code: ");
                if (!Validate.validateCode(code)) {
                    System.out.println("Invalid code!");
                    success = false;
                }
            } while (!success);
            System.out.println();

            vaccineTechnology = (String) Utils.showAndSelectOne(Constants.VACCINE_TECHNOLOGY, "Vaccine Technology");
            System.out.println();

            if (ctrl.createTypeVaccine(name, description, code, vaccineTechnology)) {
                System.out.printf("Name: %s%n", name);
                System.out.printf("Description: %s%n", description);
                System.out.printf("Code: %s%n", code);
                System.out.printf("Vaccine Technology: %s%n", vaccineTechnology);
            }

            if (Utils.confirm("Are you sure you want to save? (y/n)")) {
                ctrl.saveTypeVaccine();
                System.out.printf("-------------------------Successfully Saved!-------------------------%n%n");
                ctrl.save();
            }


        } while (Utils.showAndSelectIndex(Constants.YES_OR_NO, "Do you wish to add another vaccine?") + 1 == 1);
    }
}
