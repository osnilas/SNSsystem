package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VaccinationScheduleController {

    private Company company;

    private VaccinationSchedule schedule;
    private App app;

    private SNSuser snSuser;

    private VaccinationFacility facility;



    public VaccinationScheduleController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public VaccinationScheduleController(Company company) {
        this.company = company;
        this.schedule = schedule;
    }

    public void createSchedule(dtoScheduleVaccine dto){
        this.schedule=this.company.createSchedule(dto);
    }

    public boolean saveSchedule(dtoScheduleVaccine dto){
       return this.company.saveSchedule(schedule,facility,snSuser);
    }



    public void selectVaccinationFacility(){
        List<VaccinationFacility> list=getVaccinationFacilityList();
        Utils.showVaccinationFacility(list,"Select vaccination facility");
        this.facility=list.get(Utils.selectsIndex(list));
    }

    public TypeVaccine getTypeVaccineFromVaccinationFacility() throws Exception {
        if (facility instanceof VaccinationCenter) {
            return getTypeVaccineFromVaccinationCenter((VaccinationCenter) facility);
        } else {
            return getTypeVaccineFromHealthCareCenter((HealthCareCenter) facility);
        }
    }

    private TypeVaccine getTypeVaccineFromVaccinationCenter(VaccinationCenter center) throws Exception {
        String typeVaccine=center.getTypeOfVaccine().getName();
        if(Utils.confirm("Confirms type of Vaccine?")){
            return center.getTypeOfVaccine();
        }
        throw new Exception("Vaccine type not chosen");
    }

    private TypeVaccine getTypeVaccineFromHealthCareCenter(HealthCareCenter center){
        List<String> typeVaccineNameList=new ArrayList<>();
        for(int i=0;i<center.getTypeVaccineList().size();i++){
            typeVaccineNameList.add(center.getTypeVaccineList().get(i).getName());
        }
        Utils.printText("Recomend type Vaccine is"+ Constants.TYPE_VACCINE_RECOMMENDED.getName());
        int index=Utils.showAndSelectIndex(typeVaccineNameList,"Select type vaccine");
        return center.getTypeVaccineList().get(index);
    }



    public List<VaccinationFacility> getVaccinationFacilityList(){

        return this.company.getVaccinationFacilityList();
    }

    public boolean validateScheduleVaccine(TypeVaccine typeVaccine) throws Exception {
        if(Objects.equals(snSuser.getVaccinationRecord().getVaccine().getTypeVaccine(),typeVaccine)){
            throw  new Exception("SNS user can't take this vaccine");
        }
        return true;
    }


    public boolean checkIfVaccinationFaciltyListIsEmpty(){
        List<VaccinationFacility> list=this.company.getVaccinationFacilityList();
        if(list.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean checkIfSNSuserExists(int SNSnumber){
            if(this.company.SNSuserExists(SNSnumber) !=null){
                this.snSuser=this.company.SNSuserExists(SNSnumber);
                return true;
            }
            return false;
    }

    public boolean ValidateAppoimentTime(LocalDateTime date, VaccinationFacility center) {
        boolean flag=false;
        int count=0;
        List<VaccinationSchedule> scheduleList=center.getVaccinationScheduleList();
        for (int i=0;i<scheduleList.size();i++){
            if(scheduleList.get(i).getAppointmentTime().isEqual(date)){
                count++;
                if(count==center.getMaximumNumberOfVaccinesPerSlot()) {
                    flag = false;
                    break;
                }
            }
            flag=true;
        }
        return !flag;
    }

    public LocalDateTime getDateAppoiment(){
        LocalDate date=getDate(facility);

        LocalDateTime opening= LocalDateTime.of(date,facility.getOpeningHours());
        LocalDateTime closing=LocalDateTime.of(date,facility.getClosingHours());

        List <LocalDateTime> slotsPerDay=new ArrayList<>();
        LocalDateTime temp=opening;

        int index =0;
        boolean flag=true;
        do{
            slotsPerDay.add(temp);
            temp.plusMinutes(facility.getSlotDuration());
        }while (closing.isAfter(temp));
        do {
            index = Utils.showAndSelectIndex(slotsPerDay, "Select a time");
            if(ValidateAppoimentTime(slotsPerDay.get(index),facility)){
                Utils.printText("Slot"+ slotsPerDay.get(index) +"already taken");
                flag=false;
            }
        }while (flag);
        return slotsPerDay.get(index);
    }

    private LocalDate getDate(VaccinationFacility center){
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
