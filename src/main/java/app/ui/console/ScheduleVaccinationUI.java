package app.ui.console;

import app.controller.VaccinationScheduleController;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;

import java.time.LocalDateTime;

public class ScheduleVaccinationUI {

     private VaccinationScheduleController ctlr;

     public ScheduleVaccinationUI(){
        ctlr= new VaccinationScheduleController();
     }
    public void run(boolean flag) {
         try{
        boolean sucess = Schedule( flag);
         }
         catch (Exception e){
             e.printStackTrace();
         }
    }

    private boolean Schedule(boolean flag) throws Exception {
         boolean sucess=false;
        int snsNumber = 0;
        TypeVaccine typeVaccine = null;
        LocalDateTime appoimmentDate = null;
        VaccinationFacility vaccinationFacility = null;

        if(ctlr.checkIfVaccinationFaciltyListIsEmpty()){
            snsNumber=getSNSnumber();
            ctlr.selectVaccinationFacility();
            typeVaccine=ctlr.getTypeVaccineFromVaccinationFacility();
             appoimmentDate=ctlr.getDateAppoiment();
        }
        if(snsNumber!=0&&typeVaccine!=null&&appoimmentDate!=null) {

            dtoScheduleVaccine dto = new dtoScheduleVaccine(snsNumber, appoimmentDate, typeVaccine);
            ctlr.createSchedule(dto);
            printScheduleData(vaccinationFacility.getName(),dto.getAppointmentDate());
            if(Utils.confirm("Is this correct?")){
                if(ctlr.validateScheduleVaccine(dto.getTypeVaccine())){
                    sucess=ctlr.saveSchedule(dto);
                }
            }

        }
        if(sucess){
            Utils.printText("-----Appoiment added sucessfully-----");
        }
        else {
            Utils.printText("----Appoiment creation failed----");
        }


        return sucess;
    }


    public void printScheduleData(String VaccinationCenter, LocalDateTime dateSheducle) {
        Utils.printText("Schedule info:");
        Utils.printText("Location: " + VaccinationCenter);
        Utils.printText("Date: " + dateSheducle.format(Constants.FORMATTER));
    }

    public int getSNSnumber() throws Exception {
         int snsNubmer;
        do {
            snsNubmer = Utils.readIntegerFromConsole("Enter SNS number");
            if (!Validate.validateCC(snsNubmer)) {
                Utils.printText("Input a valid SNS number, it has 8 digits");
            }
        } while (!Validate.validateCC(snsNubmer));

        if(!ctlr.checkIfSNSuserExists(snsNubmer)){
            throw new Exception("SNS user not registered on system");
        }
        return snsNubmer;
    }
}
