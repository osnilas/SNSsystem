package tests;

import app.controller.App;
import app.controller.CheckVaccineAppointmentController;
import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.SNSuser;
import app.domain.shared.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US004 {

    @Test
    void addSnsUserToWaitingList() {
        //Arrange
        SNSuser snSuser = Constants.SN_SUSER_TESTER;
        Arrival arrival = new Arrival(snSuser);
        Company company = App.getInstance().getCompany();
        company.addSnsUserToWaitingList(0, arrival);
        int test = company.getWaitingList(0).size();
        int expected = 1;

        //Act
        boolean result = test == expected;

        //Assert
        assertTrue(result);
    }


}