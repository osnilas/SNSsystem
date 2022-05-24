package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void setVaccinationFacility(int index){
        this.facility=this.company.getVaccinationFacilityFromList(index);
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

    public boolean saveSchedule() throws Exception {
        boolean flagVaccination=false ,flagSNSuser=false;
        if(this.company.checkOtherCentersForVaccination(schedule.getTypeVaccine(),schedule.getSNSnumber())){
            flagSNSuser=true;
        }
        else{
                throw new Exception("User already has a schedule for same vaccine");
        }


        if(flagSNSuser){
            facility.addSchedule(schedule);
        }
        else{
            throw new Exception("SYSTEM ERROR VACCINATION FACILITY NOT FOUND");
        }
        return flagSNSuser;
    }

    public List<VaccinationFacility> getVaccinationFacilityList(){
        return this.company.getVaccinationFacilityList();
    }

    public boolean validateScheduleVaccine() throws Exception {
        return vaccinationRecord();
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
                if(Objects.equals(snSuser.getVaccinationRecord().get(i).getVaccine().getTypeVaccine(),schedule.getTypeVaccine())){
                    throw new Exception("SNS user can't schedule this vaccine again");
                }
                else if(!validateAgeAndTimeDose(snSuser.getVaccinationRecord().get(i).getVaccine())){
                    throw new Exception("SNS user can´t take this vaccine");
                }
            }
        }
        return true;
    }

    public boolean validateAgeAndTimeDose(Vaccine vaccine){
        return validateAgeGroup(vaccine);
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
        return daysBetweendDosage<vaccine.getVaccineAdministration().getVaccineInterval().get(ageGroupIndex).get(vaccinationRecordLatest.getNumberDosesTaken()-1);

    }

    public boolean checkIfSNSuser(){
       return this.company.getAuthFacade().getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_SNS);
    }


    private boolean ValidateAppoimentTime(LocalDateTime date, VaccinationFacility center) {
        boolean flag = false;
        int count = 0;
        List<VaccinationAppointment> scheduleList = center.getVaccinationScheduleList();
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
    public void getDateAppoiment() {
        LocalDate date = null;
        LocalDateTime dateTime = null;
        boolean flag = true;
        do {
            try {
                date = getDate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (date == null);
        do {
            try {
                dateTime = getTime(facility, date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (dateTime == null);
        this.date=dateTime;
    }

    private LocalDateTime getTime(VaccinationFacility facility, LocalDate date) throws Exception {
        Boolean flag;
        LocalDateTime opening = LocalDateTime.of(date, facility.getOpeningHours());
        LocalDateTime closing = LocalDateTime.of(date, facility.getClosingHours());

        List<LocalDateTime> timeSlots = new ArrayList<>();
        LocalDateTime temp = opening;

        int index = 0;
        flag = true;
        do {
            timeSlots.add(temp);
            temp = temp.plusMinutes(facility.getSlotDuration());
        } while (closing.isAfter(temp));

        do {
            index = Utils.showAndSelectIndex(timeSlots, "Select a time");
            if (index == -1) {
                throw new Exception("Time not chosen");
            }
            if (!ValidateAppoimentTime(timeSlots.get(index), facility)) {
                Utils.printText("Slot:\n" + timeSlots.get(index).format(Constants.DATE_TIME_FORMATTER) + " already full");
                flag = !ValidateAppoimentTime(timeSlots.get(index), facility);
            }
            flag = ValidateAppoimentTime(timeSlots.get(index), facility);
        } while (!flag);
        return timeSlots.get(index);
    }

    private LocalDate getDate() throws Exception {
        int count = 0;
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate inicial = LocalDate.now();
        LocalDate temp = inicial;
        LocalDate end = inicial.plusMonths(1);
        do {
            dateList.add(temp);
            temp = temp.plusDays(1);
        } while (temp.isBefore(end));
        Utils.showDate(dateList, "Select a date");
        int index = Utils.selectsIndex(dateList);
        if (index == -1) {
            throw new Exception("No date chosen");
        }
        return dateList.get(index);
    }
    public void getTypeVaccineFromVaccinationFacility() throws Exception {
        if (facility instanceof MassVaccinationCenter) {
            this.typeVaccine= getTypeVaccineFromVaccinationCenter((MassVaccinationCenter) facility);
        } else {
            this.typeVaccine= getTypeVaccineFromHealthCareCenter((HealthCareCenter) facility);
        }
    }

    private TypeVaccine getTypeVaccineFromVaccinationCenter(MassVaccinationCenter center) throws Exception {
        String typeVaccine = center.getTypeOfVaccine().getName();
        Utils.printText("Vaccine of this vaccination center:");
        Utils.printText(typeVaccine);
        Utils.printText("The DGS recommends:" + Constants.TYPE_VACCINE_RECOMMENDED.getName());
        if (Utils.confirm("Confirms type of Vaccine?")) {
            return center.getTypeOfVaccine();
        }
        throw new Exception("Vaccine type not chosen");
    }

    private TypeVaccine getTypeVaccineFromHealthCareCenter(HealthCareCenter center) {
        List<TypeVaccine> typeVaccineNameList = new ArrayList<>();
        for (int i = 0; i < center.getTypeVaccineList().size(); i++) {
            typeVaccineNameList.addAll(center.getTypeVaccineList());
        }
        Utils.showTypeVaccinne(typeVaccineNameList, "Select vaccine");
        Utils.printText("The DGS recommends:" + Constants.TYPE_VACCINE_RECOMMENDED.getName());
        int index = Utils.selectsIndex(typeVaccineNameList);
        return center.getTypeVaccineList().get(index);
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
