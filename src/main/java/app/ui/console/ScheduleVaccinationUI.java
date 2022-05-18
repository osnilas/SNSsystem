package app.ui.console;

import app.controller.App;
import app.controller.VaccinationScheduleController;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;
import mappers.dto.dtoVaccinationFacility;
import mappers.dto.mapperVaccinationFacility;

import java.beans.VetoableChangeListener;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class ScheduleVaccinationUI {

     private VaccinationScheduleController ctlr;
     private mapperVaccinationFacility mapper=new mapperVaccinationFacility();

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
        List<HealthCareCenter> HealthCareCenterList=ctlr.getHealthCareCenterList();
        List<VaccinationCenter> VaccinationCenterList=ctlr.getVaccinationCenterList();
        HealthCareCenter HealthCareCenterTemp;
        VaccinationCenter VaccinationCenterTemp;
        List<dtoVaccinationFacility> VaccinationFacilityList= new ArrayList<>();
        dtoVaccinationFacility vaccinationFacility = null;
        VaccinationFacilityList=fillVaccinationFacilityList(VaccinationFacilityList,VaccinationCenterList,HealthCareCenterList);
        TypeVaccine typeVaccine = null;
        LocalDateTime appoimmentDate = null;
        VaccinationCenter vaccinationCenter;

        if(ctlr.checkIfVaccinationFaciltyListIsEmpty()){
            snsNumber=getSNSnumber();
            vaccinationFacility=selectVaccinationFacility(VaccinationFacilityList);
            if(vaccinationFacility.getTypeVaccine()!=null){
                VaccinationCenterTemp=mapper.toVaccinationCenter(vaccinationFacility);
                typeVaccine=getTypeVaccineFromVaccinationCenter(VaccinationCenterTemp);
            }
            else{
                HealthCareCenterTemp=mapper.toHeathCareCenter(vaccinationFacility);
                typeVaccine=getTypeVaccineFromHealthCareCenter(HealthCareCenterTemp);
            }
             appoimmentDate=getDateAppoiment(vaccinationFacility);
        }
        if(snsNumber!=0&&typeVaccine!=null&&appoimmentDate!=null) {
            dtoScheduleVaccine dto = new dtoScheduleVaccine(snsNumber, appoimmentDate, typeVaccine);
            sucess=ctlr.createSchedule(dto,vaccinationFacility);
            if(sucess){

            }
        }



        return false;
    }


    public void printScheduleData(String VaccinationCenter, LocalDateTime dateSheducle){
         Utils.printText("Schedule info:");
        Utils.printText("Location: "+VaccinationCenter);
        Utils.printText("Date: "+dateSheducle.format(Constants.FORMATTER));
    }

    public List<dtoVaccinationFacility> fillVaccinationFacilityList(List<dtoVaccinationFacility> VaccinationFacilityList, List<VaccinationCenter> vaccinationCenterList , List<HealthCareCenter> HealthCareCenterList){
        Utils.copyList(VaccinationFacilityList, mapper.toDTOVaccinationCenterList(vaccinationCenterList));
        Utils.copyList(VaccinationFacilityList, mapper.toDTOHealthCareCenterList(HealthCareCenterList));
        return VaccinationFacilityList;
    }

    public dtoVaccinationFacility selectVaccinationFacility(List<dtoVaccinationFacility> list){
         Utils.showVaccinationFacility(list,"Select vaccination facility");
         return list.get(Utils.selectsIndex(list));
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

    public TypeVaccine getTypeVaccineFromVaccinationCenter(VaccinationCenter center) throws Exception {
        String typeVaccine=center.getTypeOfVaccine().getName();
        if(Utils.confirm("Confirms type of Vaccine?")){
            return center.getTypeOfVaccine();
        }
        throw new Exception("Vaccine type not chosen");
    }

    public TypeVaccine getTypeVaccineFromHealthCareCenter(HealthCareCenter center){
        List<String> typeVaccineNameList=new ArrayList<>();
        for(int i=0;i<center.getTypeVaccineList().size();i++){
             typeVaccineNameList.add(center.getTypeVaccineList().get(i).getName());
         }
        Utils.printText("Recomend type Vaccine is"+ Constants.TYPE_VACCINE_RECOMMENDED.getName());
         int index=Utils.showAndSelectIndex(typeVaccineNameList,"Select type vaccine");
         return center.getTypeVaccineList().get(index);
    }

    public LocalDateTime getDateAppoiment(dtoVaccinationFacility center){
         LocalDate date=getDate(center);

         LocalDateTime opening= LocalDateTime.of(date,center.getOpeningHours());
         LocalDateTime closing=LocalDateTime.of(date,center.getClosingHours());

         List <LocalDateTime> slotsPerDay=new ArrayList<>();
         LocalDateTime temp=opening;

        int index =0;
        boolean flag=true;
         do{
             slotsPerDay.add(temp);
             temp.plusMinutes(center.getSlotDuration());
         }while (closing.isAfter(temp));
        do {
            index = Utils.showAndSelectIndex(slotsPerDay, "Select a time");
            if(!ctlr.ValidateAppoimentTime(slotsPerDay.get(index),center)){
                Utils.printText("Slot"+ slotsPerDay.get(index) +"already taken");
                flag=false;
            }
        }while (flag);
        return slotsPerDay.get(index);
    }

    public LocalDate getDate(dtoVaccinationFacility dto){
         int count=0;
         List<LocalDate> dateList=new ArrayList<>();
         LocalDate intial= LocalDate.now();
         LocalDate end=intial.plusDays(31);
         LocalDate temp=intial;
         for(int i=intial.getDayOfMonth();i<end.getDayOfMonth();i++){
                 dateList.add(temp);
                  temp.plusDays(1);
         }
         int index=Utils.showAndSelectIndex(dateList,"Select a date");
         return dateList.get(index);
    }



}
