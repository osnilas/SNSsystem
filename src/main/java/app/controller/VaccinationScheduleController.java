package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VaccinationScheduleController {

    private Company company;

    private VaccinationAppointment schedule;
    private App app;

    private VaccinationFacility facility;

    private SNSuser snSuser;

    private LocalDateTime date;


    private TypeVaccine typeVaccine;


    public VaccinationScheduleController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public VaccinationScheduleController(Company company) {
        this.company = company;
        this.schedule = schedule;
    }

    public void setVaccinationFacility(int index) {
        this.facility = this.company.getVaccinationFacilityFromList(index);
    }

    public boolean SNSuserExistsEmail(){
        String email=this.company.getAuthFacade().getCurrentUserSession().getUserId().getEmail();
        if(this.company.SNSuserExistsEmail(email) !=null){
            this.snSuser=this.company.SNSuserExistsEmail(email);
            return true;
        }
        return false;
    }

    public void createSchedule(){
        this.schedule =new VaccinationAppointment(snSuser.getSNSnumber(),date,typeVaccine);
    }

    public void saveSchedule()  {
        facility.addSchedule(schedule);
    }

    public List<String> getVaccinationFacilities(){
        List<VaccinationFacility> vaccinationFacilityList=this.company.getVaccinationFacilityList();
        List<String>vaccinationFacilityNameList=new ArrayList<>();
        for(int i=0;i<vaccinationFacilityList.size();i++){
            vaccinationFacilityNameList.add(vaccinationFacilityList.get(i).getName());
        }
        return vaccinationFacilityNameList;
    }

    public boolean validateScheduleVaccine() throws Exception {
        return   validateScheduleVaccineType() && vaccinationRecord();
    }

    public boolean validateScheduleVaccineType() throws Exception{
        boolean flagSNSuser=false;
        if(this.company.checkOtherCentersForVaccination(schedule.getTypeVaccine(),schedule.getSNSnumber())){
            flagSNSuser=true;
        }
        else{
            throw new Exception("User already has a schedule for same vaccine");
        }
        return flagSNSuser;
    }

    public boolean validateCreationSchedule(){
        if(date==null || typeVaccine==null || facility==null){
            return false;
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
            if(this.company.SNSuserExistsNumber(SNSnumber) !=null){
                this.snSuser=this.company.SNSuserExistsNumber(SNSnumber);
                return true;
            }
            return false;
    }

    public boolean vaccinationRecord() throws Exception {
        int dose=0;
        if(snSuser.getVaccinationRecord().size()==0){
            return true;
        }
        else{
            for(int i=0;i<snSuser.getVaccinationRecord().size();i++){
                if(snSuser.getVaccinationRecord().get(i).checkTypeVaccine(typeVaccine)) {
                    validateAgeAndTimeDose(snSuser.getVaccinationRecord().get(i).getVaccine());
                }
            }
        }
        return true;
    }

    public boolean validateAgeAndTimeDose(Vaccine vaccine)throws Exception{
        if(!validateDoseTime(vaccine)){
            throw new Exception("Too soon to take next dose");
        }
        if(!validateAgeGroup(vaccine)){
            throw new Exception("Vaccine is't for user age ");
        }

        return validateAgeGroup(vaccine) && validateDoseTime(vaccine);
    }

    public boolean validateAgeGroup(Vaccine vaccine){
        if(vaccine.getAgeGroup(snSuser.getAge()) !=-1){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean validateDoseTime(Vaccine vaccine){
        int ageGroupIndex= vaccine.getAgeGroup(snSuser.getAge());
        VaccinationRecord vaccinationRecordLatest=snSuser.getLatestVaccinationRecord(vaccine);
        Duration dayBetweenDosageTemp= Duration.between(vaccinationRecordLatest.getDate(),schedule.getAppointmentTime());
        int daysBetweendDosage=(int) Math.abs(dayBetweenDosageTemp.toDays());
        return daysBetweendDosage>vaccine.getVaccineAdministration().getVaccineInterval().get(ageGroupIndex).get(vaccinationRecordLatest.getNumberDosesTaken()-1);

    }

    public boolean checkIfSNSuser(){
       return this.company.getAuthFacade().getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_SNS);
    }


    public boolean ValidateAppoimentTime(LocalDate day,int index ) {
        boolean flag = true;
        int count = 0;
        LocalDateTime date=getTimeSlots(day).get(index);
        List<VaccinationAppointment> scheduleList = facility.getVaccinationScheduleList();
        for (int i = 0; i < scheduleList.size(); i++) {
            if (scheduleList.get(i).isAppointmentSameTime(date)) {
                count++;
                if (count == facility.getMaximumNumberOfVaccinesPerSlot()) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public List<LocalDate> getDateList(){
        int count = 0;
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate inicial = LocalDate.now();
        LocalDate temp = inicial;
        LocalDate end = inicial.plusMonths(1);
        do {
            dateList.add(temp);
            temp = temp.plusDays(1);
        } while (temp.isBefore(end));
        return dateList;
    }

    public List<String> getTimeSlotsDTO(LocalDate date){
        boolean flag;
        LocalDateTime opening = LocalDateTime.of(date, facility.getOpeningHours());
        LocalDateTime closing = LocalDateTime.of(date, facility.getClosingHours());

        List<String> timeSlots = new ArrayList<>();
        LocalDateTime temp = opening;

        int index = 0;
        flag = true;
        do {
            timeSlots.add(temp.format(Constants.DATE_TIME_FORMATTER));
            temp = temp.plusMinutes(facility.getSlotDuration());
        } while (closing.isAfter(temp));
        return timeSlots;
    }

    public List<LocalDateTime> getTimeSlots(LocalDate day){
        boolean flag;
        LocalDateTime opening = LocalDateTime.of(day, facility.getOpeningHours());
        LocalDateTime closing = LocalDateTime.of(day, facility.getClosingHours());

        List<LocalDateTime> timeSlots = new ArrayList<>();
        LocalDateTime temp = opening;

        int index = 0;
        flag = true;
        do {
            timeSlots.add(temp);
            temp = temp.plusMinutes(facility.getSlotDuration());
        } while (closing.isAfter(temp));
        return timeSlots;
    }

    public void setDate(LocalDate day,int index){
        LocalDateTime time=getTimeSlots(day).get(index);
        this.date=time;
    }
    public int getTypeVaccineFromVaccinationFacility() {
        if (facility instanceof MassVaccinationCenter) {
            return 0;
        } else {
            return 1;
        }
    }

    public String getTypeVaccineFromVaccinationCenter(){
        MassVaccinationCenter center= (MassVaccinationCenter) facility;
        return center.getTypeOfVaccine().getName();
    }

    public void setTypeVaccineMassVaccinationCenter(){
        MassVaccinationCenter center= (MassVaccinationCenter) facility;
        TypeVaccine type=center.getTypeOfVaccine();
        this.typeVaccine=type;
    }



    public List<String> getTypeVaccineFromHealthCareCenter() {
        HealthCareCenter center=(HealthCareCenter) facility;
        List<String> typeVaccineNameList = new ArrayList<>();
        for (int i = 0; i < center.getTypeVaccineList().size(); i++) {
            typeVaccineNameList.add(center.getTypeVaccineList().get(i).getName());
        }
        return typeVaccineNameList;
    }

    public void setTypeVaccineHealthCareCenter(int index){
        HealthCareCenter center=(HealthCareCenter) facility;
        List<TypeVaccine> typeVaccineList = new ArrayList<>();
        typeVaccineList.addAll(center.getTypeVaccineList());
        this.typeVaccine=typeVaccineList.get(index);
    }




    public String printSchedule(){
       return printScheduleInfo(facility.getName(),schedule.getAppointmentTime());
    }

    public String printScheduleInfo(String VaccinationCenter, LocalDateTime dateSheducle) {
        return "Schedule info:"+ "\nLocation: " + VaccinationCenter+
        "\nDate: " + dateSheducle.format(Constants.FORMATTER)+
        "\nTime: "+ dateSheducle.format(Constants.TIME_FORMATTER);
    }
}
