package app.ui.console;

import java.util.ArrayList;
import java.util.Scanner;

public class SpecifyVaccineUI implements Runnable{
    public static Scanner ler = new Scanner(System.in);

    @Override
    public void run() {
        int op = 0;
        System.out.println("Specify Vaccine Type");
        System.out.println();

        ArrayList<Integer> minAge = new ArrayList<>();
        ArrayList<Integer> maxAge = new ArrayList<>();

        do {

            System.out.println("Vaccine Age Range");
            System.out.println();
            System.out.print("Insert MINIMUM age: ");
            minAge.add(ler.nextInt());
            System.out.print("Insert MAXIMUM age: ");
            maxAge.add(ler.nextInt());
            System.out.println();

            System.out.print("Insert dosage(ml): ");
            float dosage = ler.nextFloat();
            System.out.println();


            System.out.print("Insert number of doses: ");
            int doses = ler.nextInt();
            System.out.println();


            ArrayList<Integer> vaccineInterval = new ArrayList<>();
            if (doses > 1) {
                for (int i = 1; i < doses; i++) {
                    System.out.printf("Interval between doses %d and %d(days): ", i, i + 1);
                    vaccineInterval.add(ler.nextInt());
                    System.out.println();
                }
            }

            System.out.println("Do you wish to add another Age Range?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            op = ler.nextInt();

        }while (op != 2);
    }
}
