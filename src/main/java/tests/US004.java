package tests;

import app.controller.App;
import app.controller.CheckVaccineAppointmentController;
import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.SNSuser;
import app.domain.shared.Constants;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionService;

import static org.junit.jupiter.api.Assertions.*;

class US004 {

    @Test
    void addSnsUserToWaitingList() {
        //Arrange
        SNSuser snSuser = Constants.SN_SUSER_TESTER_FULL;
        Arrival arrival = new Arrival(snSuser);
        Company company = App.getInstance().getCompany();
        //Act
        int expected =company.getWaitingList(0).size();
        company.addSnsUserToWaitingList(0, arrival);
        int test = company.getWaitingList(0).size();
        expected = expected+1;
        //Assert
        assertEquals(test,expected);
    }


}