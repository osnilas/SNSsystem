package app.ui.console;

import app.controller.App;
import app.controller.VaccinationScheduleController;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleVaccinationUI implements Runnable {

    private VaccinationScheduleController ctlr;
    private App app;

    public ScheduleVaccinationUI() {
        ctlr = new VaccinationScheduleController();
    }

    @Override
    public void run() {
        try {
            boolean sucess = Schedule();
        } catch (Exception e) {
            Utils.printText(e.getMessage());
        }
    }

    private boolean Schedule() throws Exception {
        boolean success = false;
        if (ctlr.checkIfVaccinationFaciltyListIsEmpty()) {
            setSNSuser();
            setVaccinationFacility();
            ctlr.record();
            selectVaccineType();
            getDateTimeAppointment();

        }
        if (ctlr.validateCreationSchedule()) {
            ctlr.createSchedule();
            Utils.printText(ctlr.printSchedule());
            if (Utils.confirm("Is this correct?")) {
                success =ctlr.validateScheduleVaccine();
                if (success) {
                    ctlr.saveSchedule();
                    Utils.printText("-----Appointment added successfully-----");
                    ctlr.save();
                } else {
                    Utils.printText("----Appoint creation failed----");
                }

            }
        }
        return success;
    }

    /**
     * @author João Veiga
     * @Description This method sets the SNS user instance on the controller, depending on who is using the app, maybe be asked to insert an SNS number of an SNS user registered in the system
     * @throws Exception If no SNS user is found with the same login email or SNS number
     */
    private void setSNSuser() throws Exception {

        if (!ctlr.checkIfSNSuser()) {
            int snsNumber;
            do {
                snsNumber = Utils.readIntegerFromConsole("Enter SNS number");
                if (!Validate.validateSNS(snsNumber)) {
                    Utils.printText("Input a valid SNS number, it has 9 digits");
                }
            } while (!Validate.validateSNS(snsNumber));

            if (!ctlr.checkIfSNSuserExists(snsNumber)) {
                throw new Exception("SNS user not registered on system");
            }
        } else {
            if (!ctlr.SNSuserExistsEmail()) {
                throw new Exception("SNS user not registered on system");
            }
        }
    }

    /**
     * @author João Veiga
     * @Description This method presents a list of vaccination facilities and asks user to select one and set that one as the vaccination facility instance in the controller
     */
    private void setVaccinationFacility() {
        int index;
        do {
            List<String> list = ctlr.getVaccinationFacilities();
            Utils.showList(list, "Select a vaccination facility");
            index = Utils.selectsIndex(ctlr.getVaccinationFacilities());
        } while (index == -1);
        ctlr.setVaccinationFacility(index);
    }

    /**
     * @author João Veiga
     * @Description This method calls on other methods to set date and time of the vaccination schedule.
     */
    private void getDateTimeAppointment() {
        LocalDate date = null;
        LocalDateTime dateTime = null;
        boolean flag = false;
        do {
            try {
                date = getDate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (date == null);
        do {
            try {
                flag = getTime(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!flag);
    }

    /**
     * @author João Veiga
     * @Description This method gets a list of times from the controller and presents it to the user that then selects one.
     * @param date
     * @return Boolean if a time was chosen.
     * @throws Exception If a time wasn't chosen.
     */
    private boolean getTime(LocalDate date) throws Exception {
        int index;
        boolean flag;
        List<String> timeSlots = ctlr.getTimeSlotsDTO(date);
        do {
            index = Utils.showAndSelectIndex(timeSlots, "Select a time");
            if (index == -1) {
                throw new Exception("Time not chosen");
            }
            if (!ctlr.ValidateAppoimentTime(date, index)) {
                Utils.printText("Slot:\n" + timeSlots.get(index) + " already full");
                flag = !ctlr.ValidateAppoimentTime(date, index);
            }
            flag = ctlr.ValidateAppoimentTime(date, index);
        } while (!flag);
        ctlr.setDate(date, index);
        return true;
    }
    /**
     * @author João Veiga
     * @Description This method gets a list of days from the controller and presents it to the user that then selects one.
     * @return The chosen date
     * @throws Exception If a date wasn't chosen.
     */
    private LocalDate getDate() throws Exception {
        List<LocalDate> dateList = ctlr.getDateList();
        Utils.showDate(dateList, "Select a date");
        int index = Utils.selectsIndex(dateList);
        if (index == -1) {
            throw new Exception("No date chosen");
        }
        return dateList.get(index);
    }

    /**
     * @author João Veiga
     * @Description This method asks the controller was type of vaccination facility the chosen one is(vaccination center or health care center), then calls the appropriate method to get the vaccine type
     * @throws Exception If no vaccine type is chosen
     */
    public void selectVaccineType() throws Exception {
        boolean flag = false;
        int typeCenter = ctlr.getTypeVaccinationFacility();
        switch (typeCenter) {
            case 0:
                getTypeVaccineFromVaccinationCenter();
                break;
            case 1:
                getTypeVaccineFromHealthCareCenter();
                break;
        }
    }

    /**
     * @author João Veiga
     * @Description This method presents the vaccine type of the chosen vaccination center and asks the user to confirm it.
     * @throws Exception If not vaccine type is not chosen.
     */
    private void getTypeVaccineFromVaccinationCenter() throws Exception {
        String typeVaccine = ctlr.getTypeVaccineFromMassVaccinationCenter();
        Utils.printText("Vaccine of this vaccination center:");
        Utils.printText(typeVaccine);
        if (Utils.confirm("Confirms type of Vaccine?")) {
            ctlr.setTypeVaccineMassVaccinationCenter();
        } else {
            throw new Exception("Vaccine type not chosen");
        }
    }
    /**
     * @author João Veiga
     * @Description This method presents a list of vaccine types of the chosen heath care center and asks the user to choose one.
     * @throws Exception If not vaccine type is not chosen.
     */
    private void getTypeVaccineFromHealthCareCenter() throws Exception {
        List<String> list = ctlr.getTypeVaccineFromHealthCareCenter();
        Utils.showList(list, "Select vaccine");
        Utils.printText("The DGS recommends:" + Constants.TYPE_VACCINE_RECOMMENDED.getName());
        int index = Utils.selectsIndex(list);
        if(index==-1){
            throw new Exception("No Vaccine type selected");
        }
        ctlr.setTypeVaccineHealthCareCenter(index);
    }

}
