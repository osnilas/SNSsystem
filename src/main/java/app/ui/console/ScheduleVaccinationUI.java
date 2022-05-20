package app.ui.console;

import app.controller.App;
import app.controller.VaccinationScheduleController;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;

import java.time.LocalDateTime;

public class ScheduleVaccinationUI implements Runnable{

     private VaccinationScheduleController ctlr;
     private App app;

     public ScheduleVaccinationUI(){
        ctlr= new VaccinationScheduleController();
     }

    @Override
    public void run() {
        try{
            boolean sucess = Schedule(checkIfSNSuser() );
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
            ctlr.printSchedule();
            if(Utils.confirm("Is this correct?")){
                if(ctlr.validateScheduleVaccine(dto.getTypeVaccine())) {
                    sucess = ctlr.saveSchedule(dto);
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

    private int getSNSnumber() throws Exception {
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
        return snsNubmer;
    }

    private boolean checkIfSNSuser(){
        return ctlr.checkIfSNSuser();
    }
}
