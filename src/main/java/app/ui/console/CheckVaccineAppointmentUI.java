package app.ui.console;

import app.controller.CheckVaccineAppointmentController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class CheckVaccineAppointmentUI implements Runnable {

    public static Scanner ler = new Scanner(System.in);
    private CheckVaccineAppointmentController ctrl = new CheckVaccineAppointmentController();


    @Override
    public void run() {

        Utils.showList(ctrl.getVaccinationFacilities(), "Select Vaccination Facility: ");
        ctrl.setIndex(Utils.selectsIndex(ctrl.getVaccinationFacilities()));


        System.out.printf("------------------------- Check vaccine appointment -------------------------%n%n");

        System.out.print("SNS User number: ");
        int number = ler.nextInt();

        if (checkSnsUserAppointment(number)) {
            System.out.printf("SNS User: %d%n", ctrl.getSnsUserAppointment().getSNSnumber());
            System.out.printf("Vaccine Type: %s%n", ctrl.getSnsUserAppointment().getTypeVaccine().getName());
            System.out.printf("Appointment Time: %s%n", ctrl.getSnsUserAppointment().getAppointmentTime().format(Constants.DATE_TIME_FORMATTER));
            System.out.println("---------------------------------------------------------------------------");
            if (ctrl.requestSnsUserArrivalRegistration()) {
                System.out.printf("Arrival:%nSNS User: %s%nTime of arrival: %s%n%n",
                        ctrl.getArrival().getSnSuser().getName(), ctrl.getArrival().getTimeOfArrival().format(Constants.DATE_TIME_FORMATTER));
                if (ctrl.requestToSaveSnsUserArrival()) {
                    System.out.println("------------------------- Successfully registered SNS User arrival and added to waiting list -------------------------");
                }
            }
        }
    }

    private boolean checkSnsUserAppointment(int number) {
        if (!ctrl.SNSuserExists(number)) {
            System.out.println("SNS User is not on the system");
            return false;
        } else if (!ctrl.checkAppointment()) {
            System.out.println("There are no appointments for this SNS User!");
            return false;
        } else if (!ctrl.checkAppointmentDay()) {
            System.out.println("Appointment is scheduled for a different day!");
            return false;
        }
        return true;
    }
}
