package app.controller;

import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.VaccinationAppointment;
import app.domain.model.VaccinationFacility;

import java.util.ArrayList;
import java.util.List;

import static app.domain.shared.Constants.EMPLOYEE_ARRAY_LIST;

public class ConsultWaitingRoomController {

    private Company company;

    public ConsultWaitingRoomController() {
        this.company = App.getInstance().getCompany();
    }

    public List<Arrival> waitingList (int index) {
        return this.company.getWaitingList(index);
    }

    public List<String> snsUsers (int index){
        return this.company.snsUsersInWaitingRoom(index);
    }


    public boolean snsUsersInWaitingRoom(int index) {
        this.company.snsUsersInWaitingRoom(index);
        return this.company.validateWaitingList(index);
    }

    public List<String> getVaccinationFacilities(){
        List<VaccinationFacility> vaccinationFacilityList=this.company.getVaccinationFacilityList();
        List<String>vaccinationFacilityNameList=new ArrayList<>();
        for(int i=0;i<vaccinationFacilityList.size();i++){
            vaccinationFacilityNameList.add(vaccinationFacilityList.get(i).getName());
        }
        return vaccinationFacilityNameList;
    }

}
