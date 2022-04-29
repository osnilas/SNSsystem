package app.ui.console;


import app.controller.VaccineAdministrationController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class VaccineAdministrationUI implements Runnable{
    public static Scanner ler = new Scanner(System.in);

    private VaccineAdministrationController ctrl = new VaccineAdministrationController();



    @Override
    public void run() {
        int op;
        System.out.println("Specify Vaccine Type");
        System.out.println();

        ArrayList<Integer> age = new ArrayList<>();
        ArrayList<Float> dosage = new ArrayList<>();
        ArrayList<Integer> doses = new ArrayList<>();
        ArrayList<Integer> vaccineInterval = new ArrayList<>();

        do {

            System.out.println("Vaccine Age Range");
            System.out.println();
            System.out.print("Insert MINIMUM age: ");
            age.add(ler.nextInt());
            System.out.print("Insert MAXIMUM age: ");
            age.add(ler.nextInt());
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

            String ageQuestion = "Do you wish to add another Age Range?";
            op = Utils.showAndSelectIndex(Constants.YES_OR_NO, ageQuestion) + 1;

        }while (op == 1);

       // ctrl.createVaccineAdministration(age, dosage, doses, vaccineInterval);

    }
}
