package app.ui.console;

import app.controller.App;
import app.controller.VaccinationScheduleController;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            e.printStackTrace();
        }
    }

    private boolean Schedule() throws Exception {
        boolean sucess = false;

        if (ctlr.checkIfVaccinationFaciltyListIsEmpty()) {
            setSNSuser();
            SelectVaccinationFacilityUI ui=new SelectVaccinationFacilityUI();
            ui.run();
            ctlr.setVaccinationFacility(ui.getIndex());
            ctlr.getTypeVaccineFromVaccinationFacility();
            ctlr.getDateAppoiment();

        }
        if (ctlr.validateCreationSchedule()) {
            ctlr.createSchedule();
            Utils.printText(ctlr.printSchedule());
            if (Utils.confirm("Is this correct?")) {
                if (ctlr.validateScheduleVaccine()){
                    sucess = ctlr.saveSchedule();
                }
            }

        }
        if (sucess) {
            Utils.printText("-----Appoiment added sucessfully-----");
        } else {
            Utils.printText("----Appoiment creation failed----");
        }


        return sucess;
    }

    private void setSNSuser() throws Exception {

        if (!ctlr.checkIfSNSuser()) {
            int snsNubmer;
            do {
                snsNubmer = Utils.readIntegerFromConsole("Enter SNS number");
                if (!Validate.validateCC(snsNubmer)) {
                    Utils.printText("Input a valid SNS number, it has 8 digits");
                }
            } while (!Validate.validateCC(snsNubmer));

            if (!ctlr.checkIfSNSuserExists(snsNubmer)) {
                throw new Exception("SNS user not registered on system");
            }
        }else {
            if(!ctlr.SNSuserExistsEmail()){
                throw new Exception("SNS user not registered on system");
            }
        }
    }
}
