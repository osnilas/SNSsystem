package app.ui.console;


import app.controller.VaccineAdministrationController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VaccineAdministrationUI implements Runnable{
    public static Scanner ler = new Scanner(System.in);

    private VaccineAdministrationController ctrl = new VaccineAdministrationController();



    @Override
    public void run() {
        int j = 0;
        int op;
        System.out.println("Specify Vaccine Type");
        System.out.println();

        String brand;
        ArrayList<Integer> minAge = new ArrayList<>();
        ArrayList<Integer> maxAge = new ArrayList<>();
        ArrayList<Float> dosage = new ArrayList<>();
        ArrayList<Integer> doses = new ArrayList<>();
        ArrayList<Integer> vaccineInterval = new ArrayList<>();

        do {

            System.out.println("Vaccine brand: ");
            brand = ler.nextLine();
            System.out.println();


            System.out.println("Vaccine Age Range");
            System.out.println();


            System.out.print("Insert MINIMUM age: ");
            try {
                minAge.add(ler.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("Invalid Number!");
            }
            System.out.print("Insert MAXIMUM age: ");
            maxAge.add(ler.nextInt());
            System.out.println();

            System.out.print("Insert dosage(ml): ");
            dosage.add(ler.nextFloat());
            System.out.println();


            System.out.print("Insert number of Doses: ");
            int temp = ler.nextInt();
            doses.add(temp);
            System.out.println();



            if (temp > 1) {
                for (int i = 1; i < temp; i++) {
                    System.out.printf("Interval between Doses %d and %d(days): ", i, i + 1);
                    vaccineInterval.add(ler.nextInt());
                    System.out.println();
                }
            }

           /* String ageQuestion = "Do you wish to add another Age Range?";
            op = Utils.showAndSelectIndex(Constants.YES_OR_NO, ageQuestion) + 1; */

            System.out.println("Do you wish to add another Age Range?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            op = ler.nextInt();

            j++;
        }while (op == 1);

        if (ctrl.createVaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval)) {
            ctrl.printVaccineAdministration();
        }

    }
}
