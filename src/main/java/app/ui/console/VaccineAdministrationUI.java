package app.ui.console;


import app.controller.App;
import app.controller.VaccineAdministrationController;
import app.domain.model.Company;
import app.domain.model.TypeVaccine;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VaccineAdministrationUI implements Runnable {
    public static Scanner ler = new Scanner(System.in);

    private VaccineAdministrationController ctrl = new VaccineAdministrationController();

    private Company company = App.getInstance().getCompany();
    /**
     * @author Pedro Nogueira
     * Register's a vaccine administration
     */
    @Override
    public void run() {
        boolean success;
        int op;
        int temp = 0;
        System.out.println("-------------------------Specify Vaccine Type-------------------------");
        System.out.println();

        String brand;
        ArrayList<Integer> minAge = new ArrayList<>();
        ArrayList<Integer> maxAge = new ArrayList<>();
        ArrayList<Integer> dosage = new ArrayList<>();
        ArrayList<Integer> doses = new ArrayList<>();
        ArrayList<Integer> temporary = new ArrayList<>();
        ArrayList<ArrayList<Integer>> vaccineInterval = new ArrayList<>();


        if (company.getTypeVaccineList().isEmpty()) {
            System.out.println("No vaccines available");
        } else {

            Utils.showTypeVaccinne(company.getTypeVaccineList(), "Select Vaccine: ");
            TypeVaccine typeVaccine = (TypeVaccine) company.getTypeVaccineList().get(Utils.selectsIndex(company.getTypeVaccineList()));
            System.out.println();

            System.out.println("Vaccine brand: ");
            brand = ler.nextLine();
            System.out.println();

            do {

                System.out.println("Vaccine Age Range");
                System.out.println();


                validation(minAge, "Insert MINIMUM age: ", "Invalid age!", temp);

                validation(maxAge, "Insert MAXIMUM age: ", "Invalid age!", temp);

                validation(dosage, "Insert dosage (ml): ", "Invalid quantity!", temp);

                validation(doses, "Insert number of doses: ", "Invalid number!", temp);


                if (doses.get(temp) > 1) {
                    for (int i = 1; i < doses.get(temp); i++) {
                        do {
                            success = true;
                            try {
                                System.out.printf("Interval between Doses %d and %d(days): ", i, i + 1);
                                temporary.add(ler.nextInt());
                                if (!Validate.validateVaccineInterval(temporary, i - 1)) {
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
                    vaccineInterval.add(temporary);
                    temporary = new ArrayList<>();
                }

                System.out.println();

                System.out.println("Do you wish to add another Age Range?");
                System.out.println("1. Yes");
                System.out.printf("2. No%n%n");
                System.out.print("Type your option: ");
                op = ler.nextInt();
                temp++;

                System.out.printf("%n%n");

            } while (op == 1);

            if (ctrl.createVaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval, typeVaccine)) {
                int x = 0;
                System.out.printf("Vaccine Type: %s%n%n", typeVaccine.getName());
                System.out.printf("Brand: %s%n%n", brand);
                for (int i = 0; i < minAge.size(); i++) {
                    System.out.printf("Age Range: %d - %d%n", minAge.get(i), maxAge.get(i));
                    System.out.printf("Dosage: %d ml%n", dosage.get(i));
                    System.out.printf("Doses: %d%n", doses.get(i));
                    if (doses.get(i) > 1) {
                        for (int j = 0; j < doses.get(i) - 1; j++) {
                            System.out.printf("Vaccine interval between doses %d and %d: %d%n", j, j + 1, vaccineInterval.get(i).get(j));
                        }
                    }
                    System.out.println();
                }
            }

            if (Utils.confirm("Are you sure you want to save? (s/n)")) {
                ctrl.saveVaccineAdministration();
                System.out.printf("-------------------------Successfully Saved!-------------------------%n%n");
            }
        }
    }

    public void validation (List<Integer> list, String header, String errorMessage, int temp) {
        boolean success;
        do {
            success = true;
            list.add(Utils.readIntegerFromConsole(header));
            if (!Validate.validateMinimumAge(list, temp)) {
                System.out.println(errorMessage);
                list.remove(temp);
                success = false;
            }
        } while (!success);
    }
}
