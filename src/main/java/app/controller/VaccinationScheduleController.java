package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VaccinationScheduleController {

    private Company company;

    private VaccinationAppointment schedule;
    private App app;

    private VaccinationFacility facility;

    private SNSuser snSuser;

    private LocalDateTime date;


    private TypeVaccine typeVaccine;


    public VaccinationScheduleController() {
        this.app = App.getInstance();
        this.company = App.getInstance().getCompany();
    }

    public VaccinationScheduleController(Company company) {
        this.company = company;
        this.schedule = schedule;
    }

    public void setVaccinationFacility(int index) {
        this.facility = this.company.getVaccinationFacilityFromList(index);
    }

    public void record(){
        System.out.println(snSuser.getVaccinationRecord().size());
    }

    /**
     * @author João Veiga
     * @US 01 **ONLY**
     * @Description This method gets current session user email and get an SNS user with the same email.
     * @return Boolean if it found one or not.
     */
    public boolean SNSuserExistsEmail(){
        String email=this.company.getAuthFacade().getCurrentUserSession().getUserId().getEmail();
        if(this.company.SNSuserExistsEmail(email) !=null){
            this.snSuser=this.company.SNSuserExistsEmail(email);
            return true;
        }
        return false;
    }

    /**
     * @author João Veiga
     * @Description Creates a new Vaccination Appoiment and saves it on the controller.
     */
    public void createSchedule(){
        this.schedule =new VaccinationAppointment(snSuser.getSNSnumber(),date,typeVaccine);
    }

    /**
     * @author João Veiga
     * @Description Creates a new Vaccination Appoiment from a DTO and saves it on the controller.
     * @param dto
     */
    public void createScheduleFromDTO(dtoScheduleVaccine dto){
        this.typeVaccine=dto.getTypeVaccine();
        this.snSuser=company.SNSuserExistsNumber(dto.getSNSnumber());
        this.date=dto.getAppointmentDate();
        this.schedule=new VaccinationAppointment(dto.getSNSnumber(),dto.getAppointmentDate(),dto.getTypeVaccine());
    }

    /**
     * @author João Veiga
     * @Description This method saves the vaccine appoiment on the vaccination facility list of appoiments.
     */
    public void saveSchedule()  {
        facility.addSchedule(schedule);
    }

    /**
     * @author João Veiga
     * @Description This method gets vaccination facility list from company and fills a string list with the facility's name.
     * @return List<String> with vaccination facility name's.
     */
    public List<String> getVaccinationFacilities(){
        List<VaccinationFacility> vaccinationFacilityList=this.company.getVaccinationFacilityList();
        List<String>vaccinationFacilityNameList=new ArrayList<>();
        for(int i=0;i<vaccinationFacilityList.size();i++){
            vaccinationFacilityNameList.add(vaccinationFacilityList.get(i).getName());
        }
        return vaccinationFacilityNameList;
    }

    /**
     * @author João Veiga
     * @Description This method validates vaccine schedule, calls two sub methods(validateScheduleVaccineType and validateVaccinationRecord).
     * This method checks if SNS user has a vaccine already schedule and if it's on age range for vaccine and time since last dose.
     * @return Boolean if schedule is valid or not.
     */
    public boolean validateScheduleVaccine() {
        boolean flag;
        try{
            flag=validateScheduleVaccineType() && validateVaccinationRecord();
        }catch (Exception e){
            Utils.printText(e.getMessage());
            return false;
        }
        return flag;
    }

    private boolean validateScheduleVaccineType() throws Exception{
        boolean flagSNSuser=false;
        if(this.company.checkOtherCentersForVaccination(schedule.getTypeVaccine(),schedule.getSNSnumber())){
            flagSNSuser=true;
        }
        else{
            throw new Exception("User already has a schedule for same vaccine");
        }
        return flagSNSuser;
    }

    /**
     * @author João Veiga
     * @Description This method checks if it's possible create a new vaccine schedule intance.
     * @return Boolean if it's possible, or in other words, the date, vaccine type and vaccination facility isn't null.
     */
    public boolean validateCreationSchedule(){
        if(date==null || typeVaccine==null || facility==null || snSuser==null){
            return false;
        }
        return true;
    }

    /**
     * @author João Veiga
     * @Description This method checks if vaccination facility list in company is empty.
     * @return Boolean if vaccination facility list in company is empty or not.
     */
    public boolean checkIfVaccinationFaciltyListIsEmpty(){
        List<VaccinationFacility> list=this.company.getVaccinationFacilityList();
        if(list.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * @author João Veiga
     * @US 02 **ONLY**
     * @Description This method finds the SNS user through the SNS number.
     * @param SNSnumber
     * @return Boolean if it found one or not.
     */
    public boolean checkIfSNSuserExists(int SNSnumber){
            if(this.company.SNSuserExistsNumber(SNSnumber) !=null){
                this.snSuser=this.company.SNSuserExistsNumber(SNSnumber);
                return true;
            }
            return false;
    }

    /**
     * @author João Veiga
     * @Description This method validates the vaccine schedule by the vaccination record of the SNS user
     * This method first finds the SNS user lastest vaccination record with the same vaccine type as the schedule, then checks if SNS user is in the age and time to take the vaccine.
     * @return Boolean if it's valid or not.
     * @throws Exception if it's not valid, error message with more information.
     */
    private boolean validateVaccinationRecord() throws Exception {
        int dose=0;
        if(snSuser.getVaccinationRecord().size()==0){
            return true;
        }
        else{
            for(int i=0;i<snSuser.getVaccinationRecord().size();i++){
                if(snSuser.getVaccinationRecord().get(i).checkTypeVaccine(typeVaccine)) {
                    validateDoseTime(snSuser.getVaccinationRecord().get(i).getVaccine());
                    validateAgeGroup(snSuser.getVaccinationRecord().get(i).getVaccine());
                }
            }
        }
        return true;
    }

    /**
     * @author João Veiga
     * @Description This method validates the vaccine schedule by the vaccination record of the SNS user
     * This method first finds the SNS user lastest vaccination record with the same vaccine type as the schedule, then checks if SNS user is in the age to take the vaccine.
     * @return Boolean if it's valid or not.
     */
    public boolean validateAgeGroup(Vaccine vaccine) throws Exception {
        if(vaccine.getAgeGroup(snSuser.getAge()) !=-1){
            return true;
        }
        else {
            throw new Exception("Vaccine is't for user age ");
        }
    }
    /**
     * @author João Veiga
     * @Description This method validates the vaccine schedule by the vaccination record of the SNS user
     * This method first finds the SNS user lastest vaccination record with the same vaccine type as the schedule, then checks if SNS user is in time and age to take the vaccine.
     * @return Boolean if it's valid or not.
     */
    public boolean validateDoseTime(Vaccine vaccine) throws Exception {
        int ageGroupIndex= vaccine.getAgeGroup(snSuser.getAge());
        if(ageGroupIndex !=-1) {
            VaccinationRecord vaccinationRecordLatest = snSuser.getLatestVaccinationRecord(vaccine);
            if(vaccinationRecordLatest.getVaccine().getVaccineAdministration().getDoses().get(ageGroupIndex)==vaccinationRecordLatest.getNumberDosesTaken()){
                throw new Exception("SNS user has taken all doses of this vaccine");
            }
            Duration dayBetweenDosageTemp = Duration.between(vaccinationRecordLatest.getDate(), schedule.getAppointmentTime());
            int daysBetweendDosage = (int) Math.abs(dayBetweenDosageTemp.toDays());
            if (daysBetweendDosage > vaccine.getVaccineAdministration().getVaccineInterval().get(ageGroupIndex).get(vaccinationRecordLatest.getNumberDosesTaken() - 1)) {
                return true;
            }
            throw new Exception("Too soon to take next dose");
        }
        throw new Exception("SNS user is outside age range for a new dose");
    }

    /**
     * @author João Veiga
     * @Description This method check if the current session user is an SNS user.
     * @return Boolean if the current session user is an SNS user.
     */
    public boolean checkIfSNSuser(){
       return this.company.getAuthFacade().getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_SNS);
    }
    /**
     * @author João Veiga
     * @Description This method checks the vaccination facility schedule list and checks if the time slot has still an available slot.
     * @param day The day of the schedule
     * @param index Index of the time slot seleted
     * @return Boolean if the time slot has still an available slot.
     */
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

    /**
     * @author João Veiga
     * @Description This method generates and returns a date list of month duration starting the current day.
     * @return a date list of month duration starting the current day.
     */
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

    /**
     * @author João Veiga
     * @Description This method generates a time list of a certain date with a time difference betwen two times being the slot durantion.
     * @param date Day that will be generated the time list
     * @return a time list.
     */
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
    /**
     * @author João Veiga
     * @ALERT DO NOT USER OUTSIDE CONTROLLER (MVC PATTERN)
     * @Description This method generates a time list of a certain date with a time difference betwen two times being the slot durantion.
     * @param day Day that will be generated the time list
     * @return a time list.
     */
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

    /**
     * @author João Veiga
     * @Description Sets the vaccination schedule date and time on the controller class.
     * @param day Day selected
     * @param index Index of the time seleted from the time list
     */
    public void setDate(LocalDate day,int index){
        LocalDateTime time=getTimeSlots(day).get(index);
        this.date=time;
    }

    /**
     * @author João Veiga
     * @Description This method checks what type of facility it is.
     * @return 0 if the selected vaccination facility is a mass vaccination center, 1 if it's a health care center
     *
     */
    public int getTypeVaccinationFacility() {
        if (facility instanceof MassVaccinationCenter) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * @author João Veiga
     * @Description This method gets the mass vaccination center vaccine type name.
     * @return mass vaccination center vaccine type name.
     */
    public String getTypeVaccineFromMassVaccinationCenter(){
        MassVaccinationCenter center= (MassVaccinationCenter) facility;
        return center.getTypeOfVaccine().getName();
    }
    /**
     * @author João Veiga
     * @Description This method sets the mass vaccination center vaccine type as the schedule vaccine type.
     */
    public void setTypeVaccineMassVaccinationCenter(){
        MassVaccinationCenter center= (MassVaccinationCenter) facility;
        TypeVaccine type=center.getTypeOfVaccine();
        this.typeVaccine=type;
    }

    /**
     * @author João Veiga
     * @Description This method gets list with the name of the health care center vaccine type name
     * @return list with the name of the health care center vaccine type name
     */
    public List<String> getTypeVaccineFromHealthCareCenter() {
        HealthCareCenter center=(HealthCareCenter) facility;
        List<String> typeVaccineNameList = new ArrayList<>();
        for (int i = 0; i < center.getTypeVaccineList().size(); i++) {
            typeVaccineNameList.add(center.getTypeVaccineList().get(i).getName());
        }
        return typeVaccineNameList;
    }
    /**
     * @author João Veiga
     * @Description This method sets the health care center vaccine type as the schedule vaccine type.
     * @param index Index of the selected vaccine type of the list
     */
    public void setTypeVaccineHealthCareCenter(int index){
        HealthCareCenter center=(HealthCareCenter) facility;
        List<TypeVaccine> typeVaccineList = new ArrayList<>();
        typeVaccineList.addAll(center.getTypeVaccineList());
        this.typeVaccine=typeVaccineList.get(index);
    }


    /**
     * @author João Veiga
     * @Description This method creates a String with vaccination schedule information.
     * @return String with vaccination schedule information.
     */
    public String printSchedule(){
       return printScheduleInfo(facility.getName(),schedule.getAppointmentTime());
    }

    public String printScheduleInfo(String VaccinationCenter, LocalDateTime dateSheducle) {
        return "Schedule info:"+ "\nLocation: " + VaccinationCenter+
        "\nDate: " + dateSheducle.format(Constants.FORMATTER)+
        "\nTime: "+ dateSheducle.format(Constants.TIME_FORMATTER);
    }
}
