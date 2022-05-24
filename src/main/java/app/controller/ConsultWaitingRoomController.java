package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.SNSuser;

import java.util.ArrayList;

public class ConsultWaitingRoomController {

    private Company company;

    public ConsultWaitingRoomController() {
        this.company = App.getInstance().getCompany();
    }

 //   public ArrayList <SNSuser> waitingRoom () {
  //      return this.company.getwaitingRoom();
  //  }

}
