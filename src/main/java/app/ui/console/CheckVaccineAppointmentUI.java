package app.ui.console;

import app.controller.App;
import app.controller.CheckVaccineAppointmentController;
import app.domain.model.Company;
import app.domain.model.SNSuser;


import java.util.Scanner;

public class CheckVaccineAppointmentUI implements Runnable{

    public static Scanner ler = new Scanner(System.in);

    private SNSuser snSuser;
    private Company company = App.getInstance().getCompany();
    private CheckVaccineAppointmentController ctrl = new CheckVaccineAppointmentController();

    @Override
    public void run() {

        System.out.printf("------------------------- Check vaccine appointment -------------------------%n%n");

        System.out.printf("SNS User number: ");
        int number = ler.nextInt();

        if (company.SNSuserExistsNumber(number) == null) {
            System.out.println("SNS user does not exist!");
        } else {
            snSuser = ctrl.SNSuserExists(number);
//            if (!ctrl.checkAppointment(index, snSuser)) {
//                System.out.println("No scheduled appointments!");
//            } else {
//
//            }
        }
    }
}
