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

    private VaccinationAppointment schedule;
    private App app;

    private SNSuser snSuser;



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

    public boolean saveSchedule(dtoScheduleVaccine dto,VaccinationFacility facility) throws Exception {
       return this.company.saveSchedule(schedule,facility,snSuser);
    }

    public List<VaccinationFacility> getVaccinationFacilityList(){
        return this.company.getVaccinationFacilityList();
    }

    public boolean validateScheduleVaccine(TypeVaccine typeVaccine) throws Exception {
        if(snSuser.checkIfHasVaccinationRecord()) {
            if (Objects.equals(snSuser.getVaccinationRecord().getVaccine().getTypeVaccine(), typeVaccine)) {
                throw new Exception("SNS user can't take this vaccine");
            }
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



    public boolean checkIfSNSuser(){
       return this.company.getAuthFacade().getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_SNS);
    }

    public String printSchedule(VaccinationFacility facility){
       return printScheduleInfo(facility.getName(),schedule.getAppointmentTime());
    }

    public String printScheduleInfo(String VaccinationCenter, LocalDateTime dateSheducle) {
        return "Schedule info:"+ "\nLocation: " + VaccinationCenter+
        "\nDate: " + dateSheducle.format(Constants.FORMATTER)+
        "\nTime: "+ dateSheducle.format(Constants.TIME_FORMATTER);
    }
}
