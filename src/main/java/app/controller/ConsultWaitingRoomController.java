package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationAppointment;

import java.util.List;

public class ConsultWaitingRoomController {

    private Company company;

    public ConsultWaitingRoomController() {
        this.company = App.getInstance().getCompany();
    }

    public List<VaccinationAppointment> waitingList (int index) {
        return this.company.getwaitingList(index);
    }

}
