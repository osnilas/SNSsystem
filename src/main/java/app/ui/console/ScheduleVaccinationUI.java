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
            setVaccinationFacility();
            selectVaccineType();
            getDateTimeAppoiment();

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

    private void setVaccinationFacility(){
        int index;
        do {
            List<String> list = ctlr.getVaccinationFacilities();
            Utils.showList(list, "Select a vaccination facility");
            index = Utils.selectsIndex(ctlr.getVaccinationFacilities());
        }while (index==-1);
        ctlr.setVaccinationFacility(index);
    }

    public void getDateTimeAppoiment() {
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
                 flag=getTime(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!flag);
    }

    private boolean getTime(LocalDate date) throws Exception {
        int index;
        boolean flag;
        List<String>  timeSlots=ctlr.getTimeSlotsDTO(date);
        do {
            index = Utils.showAndSelectIndex(timeSlots, "Select a time");
            if (index == -1) {
                throw new Exception("Time not chosen");
            }
            if (!ctlr.ValidateAppoimentTime(date,index)){
                Utils.printText("Slot:\n" + timeSlots.get(index) + " already full");
                flag = !ctlr.ValidateAppoimentTime(date,index);
            }
            flag = ctlr.ValidateAppoimentTime(date,index);
        } while (!flag);
        ctlr.setDate(date,index);
        return true;
    }

    private LocalDate getDate() throws Exception {
        List<LocalDate> dateList=ctlr.getDateList();
        Utils.showDate(dateList, "Select a date");
        int index = Utils.selectsIndex(dateList);
        if (index == -1) {
            throw new Exception("No date chosen");
        }
        return dateList.get(index);
    }

    public void selectVaccineType(){
        boolean flag=false;
        int typeCenter=ctlr.getTypeVaccineFromVaccinationFacility();
        switch (typeCenter){
            case 0:
                do {
                    try {
                        getTypeVaccineFromVaccinationCenter();
                    } catch (Exception e) {
                        e.printStackTrace();
                        flag = true;

                    }
                }while (flag);
            break;
            case 1:getTypeVaccineFromHealthCareCenter();
            break;
        }


    }

    private void getTypeVaccineFromVaccinationCenter() throws Exception {
        String typeVaccine = ctlr.getTypeVaccineFromVaccinationCenter();
        Utils.printText("Vaccine of this vaccination center:");
        Utils.printText(typeVaccine);
        if (Utils.confirm("Confirms type of Vaccine?")) {
            ctlr.setTypeVaccineMassVaccinationCenter();
        }
        else {
            throw new Exception("Vaccine type not chosen");
        }
    }

    private void getTypeVaccineFromHealthCareCenter(){
        List<String> list=ctlr.getTypeVaccineFromHealthCareCenter();
        Utils.showList(list, "Select vaccine");
        Utils.printText("The DGS recommends:" + Constants.TYPE_VACCINE_RECOMMENDED.getName());
        int index = Utils.selectsIndex(list);
        ctlr.setTypeVaccineHealthCareCenter(index);
    }

}
