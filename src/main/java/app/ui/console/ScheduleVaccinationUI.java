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

public class ScheduleVaccinationUI implements Runnable{

     private VaccinationScheduleController ctlr;
     private App app;

     public ScheduleVaccinationUI(){
        ctlr= new VaccinationScheduleController();
     }

    @Override
    public void run() {
        try{
            boolean sucess = Schedule();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean Schedule() throws Exception {
         boolean sucess=false;
        int snsNumber = 0;
        TypeVaccine typeVaccine = null;
        LocalDateTime appoimmentDate = null;
        VaccinationFacility vaccinationFacility = null;

        if(ctlr.checkIfVaccinationFaciltyListIsEmpty()){
            snsNumber=getSNSnumber();
            vaccinationFacility=selectVaccinationFacility();
            typeVaccine=getTypeVaccineFromVaccinationFacility(vaccinationFacility);
             appoimmentDate=getDateAppoiment(vaccinationFacility);
        }
        if(snsNumber!=0&&typeVaccine!=null&&appoimmentDate!=null) {

            dtoScheduleVaccine dto = new dtoScheduleVaccine(snsNumber, appoimmentDate, typeVaccine);
            ctlr.createSchedule(dto);
            Utils.printText(ctlr.printSchedule(vaccinationFacility));
            if(Utils.confirm("Is this correct?")){
                if(ctlr.validateScheduleVaccine(dto.getTypeVaccine())) {
                    sucess = ctlr.saveSchedule(dto,vaccinationFacility);
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

    private boolean ValidateAppoimentTime(LocalDateTime date, VaccinationFacility center) {
        boolean flag=false;
        int count=0;
        List<VaccinationAppointment> scheduleList=center.getVaccinationScheduleList();
        for (int i = 0; i < scheduleList.size(); i++) {
            if (scheduleList.get(i).isAppointmentSameTime(date)) {
                count++;
                if (count == center.getMaximumNumberOfVaccinesPerSlot()) {
                    flag = false;
                    break;
                }
            }
            flag = true;
        }
        return flag;
    }

    public LocalDateTime getDateAppoiment(VaccinationFacility facility){
        LocalDate date=getDate(facility);

        LocalDateTime opening= LocalDateTime.of(date,facility.getOpeningHours());
        LocalDateTime closing=LocalDateTime.of(date,facility.getClosingHours());

        List <LocalDateTime> slotsPerDay=new ArrayList<>();
        LocalDateTime temp=opening;

        int index =0;
        boolean flag=true;
        do{
            slotsPerDay.add(temp);
            temp=temp.plusMinutes(facility.getSlotDuration());
        }while (closing.isAfter(temp));
        do {
            index = Utils.showAndSelectIndex(slotsPerDay, "Select a time");
            if(!ValidateAppoimentTime(slotsPerDay.get(index),facility)){
                Utils.printText("Slot"+ slotsPerDay.get(index) +"already taken");
                flag=!ValidateAppoimentTime(slotsPerDay.get(index),facility);
            }
            flag=ValidateAppoimentTime(slotsPerDay.get(index),facility);
        }while (!flag);
        return slotsPerDay.get(index);
    }

    private LocalDate getDate(VaccinationFacility center){
        int count=0;
        List<LocalDate> dateList=new ArrayList<>();
        LocalDate intial= LocalDate.now();
        LocalDate temp=intial;
        for(int i=intial.getDayOfMonth();i<31;i++){
            dateList.add(temp);
            temp=temp.plusDays(1);
        }
        Utils.showDate(dateList,"Select a date");
        return dateList.get(Utils.selectsIndex(dateList));
    }

    public VaccinationFacility selectVaccinationFacility(){
        List<VaccinationFacility> list=ctlr.getVaccinationFacilityList();
        Utils.showVaccinationFacility(list,"Select vaccination facility");
        return list.get(Utils.selectsIndex(list));
    }

    public TypeVaccine getTypeVaccineFromVaccinationFacility(VaccinationFacility facility) throws Exception {
        if (facility instanceof MassVaccinationCenter) {
            return getTypeVaccineFromVaccinationCenter((MassVaccinationCenter) facility);
        } else {
            return getTypeVaccineFromHealthCareCenter((HealthCareCenter) facility);
        }
    }

    private TypeVaccine getTypeVaccineFromVaccinationCenter(MassVaccinationCenter center) throws Exception {
        String typeVaccine=center.getTypeOfVaccine().getName();
        Utils.printText("Vaccine of this vaccination center:");
        Utils.printText("The DGS recommends:" +Constants.TYPE_VACCINE_RECOMMENDED.getName());
        Utils.printText(typeVaccine);
        if(Utils.confirm("Confirms type of Vaccine?")){
            return center.getTypeOfVaccine();
        }
        throw new Exception("Vaccine type not chosen");
    }

    private TypeVaccine getTypeVaccineFromHealthCareCenter(HealthCareCenter center){
        List<TypeVaccine> typeVaccineNameList=new ArrayList<>();
        for(int i=0;i<center.getTypeVaccineList().size();i++){
            typeVaccineNameList.addAll(center.getTypeVaccineList());
        }
        Utils.printText("Recomend type Vaccine is"+ Constants.TYPE_VACCINE_RECOMMENDED.getName());
        Utils.showTypeVaccinne(typeVaccineNameList,"Select vaccine");
        int index=Utils.selectsIndex(typeVaccineNameList);
        return center.getTypeVaccineList().get(index);
    }

    private boolean checkIfSNSuser(){
        return ctlr.checkIfSNSuser();
    }
}
