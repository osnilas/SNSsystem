package tests;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.LegacySystemData;
import app.domain.model.ReadDataFromLegacySystem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class US017 {

    ReadDataFromLegacySystem readDataFromLegacySystem = new ReadDataFromLegacySystem();

    @Test
    void copyDataFromLegacySystemValid() throws Exception {
        //Arrange
        ArrayList<LegacySystemData> test = new ArrayList<>();
        test = (ArrayList<LegacySystemData>) readDataFromLegacySystem.copyDataFromLegacySystem();
        //Act
        ArrayList<LegacySystemData> expected = new ArrayList<>();
        //Assert
       assertEquals(test,expected);
    }

    @Test
    void copyDataFromLegacySystemInvalid() throws Exception {
    ArrayList<LegacySystemData> test = new ArrayList<>();
       assertFalse(test.size()==0);
    }
}