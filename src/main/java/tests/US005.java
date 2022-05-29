package tests;

import app.controller.App;
import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.SNSuser;
import app.domain.shared.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US005 {

    @Test
    void snsUsersInWaitingRoom() {
        //Arrange
        Company company = App.getInstance().getCompany();
        int index = 1;
        SNSuser snSuser = Constants.SN_SUSER_TESTER_FULL;
        Arrival arrival = new Arrival(snSuser);
        company.addSnsUserToWaitingList(1,arrival);

        //Act
        company.snsUsersInWaitingRoom(1);
        int test = company.getSNSusers().size();
        int expected = 1;
        //Assert
        assertEquals(expected,test);

    }


}