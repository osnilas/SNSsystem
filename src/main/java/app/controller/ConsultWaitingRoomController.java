package app.controller;

import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.VaccinationAppointment;

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
        return this.company.validateWaitingList(index);
    }

}
