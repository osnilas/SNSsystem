package app.ui.console;

import app.controller.RegisterTypeVaccineController;
import app.controller.VaccineAdministrationController;
import app.ui.console.utils.Utils;
import jdk.jshell.execution.Util;

import java.util.Scanner;

public class RegisterTypeVaccineUI implements Runnable{

    public static Scanner ler = new Scanner(System.in);

    private RegisterTypeVaccineController ctrl = new RegisterTypeVaccineController();

    @Override
    public void run() {
        String name;
        String description;
        String code;
        String vaccineTechnology;
        System.out.printf("-------------------------Register a new Vaccine-------------------------%n%n");

        name = Utils.readLineFromConsole("Vaccine name: ");

        description = Utils.readLineFromConsole("Enter vaccine description: ");

        code = Utils.readLineFromConsole("Vaccine code: ");

        vaccineTechnology = Utils.readLineFromConsole("Vaccine Technology: ");


    }
}
