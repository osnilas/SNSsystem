package app.ui.console;


import app.controller.VaccineAdministrationController;
import app.domain.model.Company;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VaccineAdministrationUI implements Runnable {
    public static Scanner ler = new Scanner(System.in);

    private VaccineAdministrationController ctrl = new VaccineAdministrationController();

    private Company company;
    /**
     * @author Pedro Nogueira
     * Register's a vaccine administration
     */
    @Override
    public void run() {
        boolean success;
        int op;
        int temp = 0;
        System.out.println("Specify Vaccine Type");
        System.out.println();

        String brand;
        ArrayList<Integer> minAge = new ArrayList<>();
        ArrayList<Integer> maxAge = new ArrayList<>();
        ArrayList<Double> dosage = new ArrayList<>();
        ArrayList<Integer> doses = new ArrayList<>();
        ArrayList<Integer> vaccineInterval = new ArrayList<>();

        System.out.println("Vaccine brand: ");
        brand = ler.nextLine();
        System.out.println();

        do {

            System.out.println("Vaccine Age Range");
            System.out.println();


            do {
                success = true;
                minAge.add(Utils.readIntegerFromConsole("Insert MINIMUM age: "));
                if (!Validate.validateMinimumAge(minAge, temp)) {
                    System.out.println("Invalid age!");
                    minAge.remove(temp);
                    success = false;
                }
            } while (!success);


            do {
                success = true;
                maxAge.add(Utils.readIntegerFromConsole("Insert MAXIMUM age: "));
                if (!Validate.validateMaximumAge(minAge, maxAge, temp)) {
                    System.out.println("Invalid age!");
                    maxAge.remove(temp);
                    success = false;
                }
            } while (!success);


            System.out.println();


            do {
                success = true;
                dosage.add(Utils.readDoubleFromConsole("Insert dosage (ml): "));
                if (!Validate.validateDosage(dosage, temp)) {
                    System.out.println("Invalid quantity!");
                    dosage.remove(temp);
                    success = false;
                }
            } while (!success);


            System.out.println();


            do {
                success = true;
                doses.add(Utils.readIntegerFromConsole("Insert number of doses: "));
                if (!Validate.validateDoses(doses, temp)) {
                    System.out.println("Invalid number!");
                    doses.remove(temp);
                    success = false;
                }
            } while (!success);


            System.out.println();


            if (doses.get(temp) > 1) {
                for (int i = 1; i < doses.get(temp); i++) {
                    do {
                        success = true;
                        try {
                            System.out.printf("Interval between Doses %d and %d(days): ", i, i + 1);
                            vaccineInterval.add(ler.nextInt());
                            if (!Validate.validateVaccineInterval(vaccineInterval, i - 1)) {
                                System.out.println("Invalid number!");
                                vaccineInterval.remove(i - 1);
                                success = false;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid number!");
                            ler.next();
                            success = false;
                        }
                    } while (!success);
                }
            }

            System.out.println();

            System.out.println("Do you wish to add another Age Range?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            op = ler.nextInt();
            temp++;

            System.out.println();

        } while (op == 1);

        if (ctrl.createVaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval)) {
            int x = 0;
            System.out.printf("Brand: %s%n", brand);
            System.out.println();
            for (int i = 0 ; i < minAge.size() ; i++) {
                System.out.printf("Age Range: %d - %d%n", minAge.get(i), maxAge.get(i));
                System.out.printf("Dosage: %.2f ml%n", dosage.get(i));
                System.out.printf("Doses: %d%n", doses.get(i));
                if (doses.get(i) > 1) {
                    for (int j = 1; j < doses.get(i); j++) {
                        System.out.printf("Vaccine interval between doses %d and %d: %d days%n", j, j+1, vaccineInterval.get(x));
                        x += 1;
                    }
                }
                System.out.println();
            }
        }

        if (Utils.confirm("Are you sure you want to save? (s/n)")) {
            ctrl.saveVaccineAdministration();
            System.out.println("Successfully Saved!");
        }
    }
}
