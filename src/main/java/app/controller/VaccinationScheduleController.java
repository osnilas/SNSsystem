package app.controller;

import app.domain.model.*;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;
import mappers.dto.dtoVaccinationFacility;

import java.time.LocalDateTime;
import java.util.List;

public class VaccinationScheduleController {

    private Company company;

    private VaccinationSchedule schedule;
    private App app;

    public VaccinationScheduleController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public VaccinationScheduleController(Company company) {
        this.company = company;
        this.schedule = schedule;
    }

    public boolean createSchedule(dtoScheduleVaccine dto, dtoVaccinationFacility dtoFacility){
        this.schedule=this.company.createVaccinationSchedule(dto);
        return this.company.ValidateCreationVaccinationSchedule(schedule,dtoFacility);
    }

    public boolean saveSchedule(Object facility,dtoScheduleVaccine dto){
         return this.company.saveSchedule(facility,dto);
    }

    public List<VaccinationCenter> getVaccinationCenterList(){
        return this.company.getVaccinationCenterList();
    }

    public List<HealthCareCenter> getHealthCareCenterList(){
        return this.company.getHealthCareCenterList();
    }

    public boolean checkIfVaccinationFaciltyListIsEmpty(){
        if(getHealthCareCenterList().isEmpty() && getVaccinationCenterList().isEmpty()){
            return false;
        }
        return true;
    }

    public boolean checkIfSNSuserExists(int SNSnumber){
            if(this.company.SNSuserExists(SNSnumber)){
                return true;
            }
            return false;

    }

    public boolean ValidateAppoimentTime(LocalDateTime date, dtoVaccinationFacility dto) {
    return this.company.ValidateAppoimentTime(date,dto);
    }
}
