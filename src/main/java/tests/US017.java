package tests;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.LegacySystemData;
import app.domain.model.ReadDataFromLegacySystem;
import app.domain.model.Vaccine;
import app.domain.shared.Constants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class US017 {


    US017() throws IOException {
    }

    private List<LegacySystemData> setUp() throws IOException {
        LegacySystemData test1 = new LegacySystemData(161599370, Constants.VACCINE_TESTER,1,"21C16-05",LocalDateTime.of(2022,5,30,18,15), LocalDateTime.of(2022,5,30,17,52) , LocalDateTime.of(2022,5,30,18,36) , LocalDateTime.of(2022,5,30,19,11));
        LegacySystemData test2 = new LegacySystemData(161595362, Constants.VACCINE_TESTER,1,"21C16-05",LocalDateTime.of(2022,5,30,11,30), LocalDateTime.of(2022,5,30,11,27) , LocalDateTime.of(2022,5,30,11,45) , LocalDateTime.of(2022,5,30,12,20));
        LegacySystemData test3 = new LegacySystemData(161598449, Constants.VACCINE_TESTER,1,"21C16-05",LocalDateTime.of(2022,5,30,16,45), LocalDateTime.of(2022,5,30,17,06) , LocalDateTime.of(2022,5,30,17,46) , LocalDateTime.of(2022,5,30,18,17));
        LegacySystemData test4 = new LegacySystemData(161593279, Constants.VACCINE_TESTER,1,"21C16-05",LocalDateTime.of(2022,5,30,8,15), LocalDateTime.of(2022,5,30,8,38) , LocalDateTime.of(2022,5,30,9,20) , LocalDateTime.of(2022,5,30,9,58));
        List<LegacySystemData> testList = new ArrayList<>();
        testList.add(test1);
        testList.add(test2);
        testList.add(test3);
        testList.add(test4);
        return testList;
    }

    @Test
    void copyDataFromLegacySystemValid() throws Exception {
         Company company= App.getInstance().getCompany();
         List<Vaccine> vaccineList = company.getVaccineList();
         ReadDataFromLegacySystem readDataFromLegacySystem = new ReadDataFromLegacySystem("Testing/10.csv",vaccineList);
        //Arrange
        List<LegacySystemData> test = new ArrayList<>();
        test.addAll(readDataFromLegacySystem.copyDataFromLegacySystem());
        //Act
        List<LegacySystemData> expected = setUp();

        //Assert
        assertIterableEquals(expected,test);
    }

    @Test
    void copyDataFromLegacySystemInvalid() throws Exception {
        Company company= App.getInstance().getCompany();
        List<Vaccine> vaccineList = company.getVaccineList();
        ReadDataFromLegacySystem readDataFromLegacySystem = new ReadDataFromLegacySystem("Testing/10.csv",vaccineList);
        //Arrange
        List<LegacySystemData> test = new ArrayList<>();
        test.addAll(readDataFromLegacySystem.copyDataFromLegacySystem());
       assertFalse(test.size()==0);
    }
}