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

    /**
     * @author Filipe Magalhães
     * @Description Asks company to fill a string array with the SNS users in the waiting room
     * @param index -index of the vaccination facility chosen
     * @return boolean of the validation the SNS user
     */

    public List<String> snsUsers (int index){
        return this.company.snsUsersInWaitingRoom(index);
    }



    /**
     * @author Filipe Magalhães
     * @Description Checks if the waitig room list is empty
     * @param index -index of the vaccination facility list
     * @return boolean of the validation of the waiting room list
     */


    public boolean snsUsersInWaitingRoom(int index) {
        return this.company.validateWaitingList(index);
    }

    /**
     * @author Filipe Magalhães
     * @Description gets the name of the vaccination facilities and adds them to a String List
     * @return list with the names of all registered vaccination facilities
     */


    public List<String> getVaccinationFacilities(){
        List<VaccinationFacility> vaccinationFacilityList=this.company.getVaccinationFacilityList();
        List<String>vaccinationFacilityNameList=new ArrayList<>();
        for(int i=0;i<vaccinationFacilityList.size();i++){
            vaccinationFacilityNameList.add(vaccinationFacilityList.get(i).getName());
        }
        return vaccinationFacilityNameList;
    }

}
