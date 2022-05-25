package app.ui.console;

import app.controller.CheckVaccineAppointmentController;
import app.controller.RecepcionistController;
import app.domain.model.SNSuser;

import java.util.Scanner;

public class CheckVaccineAppointmentUI implements Runnable {

    public static Scanner ler = new Scanner(System.in);
    private CheckVaccineAppointmentController ctrl = new CheckVaccineAppointmentController();

    private RecepcionistController ctrlR = new RecepcionistController();

    @Override
    public void run() {

        System.out.printf("------------------------- Check vaccine appointment -------------------------%n%n");

        System.out.print("SNS User number: ");
        int number = ler.nextInt();

        if (checkSnsUserAppointment(ctrl.getSnSuser(), number)) {
            if (ctrl.requestSnsUserArrivalRegistration()) {
                if (ctrl.requestToSaveSnsUserArrival(ctrlR.getIndex())) {
                    System.out.println("------------------------- Successfully registered SNS User arrival and added to waiting list -------------------------");
                }
            }
        }
    }

    private boolean checkSnsUserAppointment(SNSuser snSuser, int number) {
        if (!ctrl.SNSuserExists(number)) {
            System.out.println("SNS User is not on the system");
            return false;
        } else if (!ctrl.checkAppointment(ctrlR.getIndex(), snSuser)) {
            System.out.println("There are no appointments for this SNS User!");
            return false;
        } else if (!ctrl.checkAppointmentTime(ctrlR.getIndex(), snSuser)) {
            System.out.println("Appointment time has passed!");
            return false;
        } else if (ctrl.checkAppointmentDay(ctrlR.getIndex())) {
            System.out.println("Appointment is scheduled for a different day!");
            return false;
        }
        return true;
    }
}
