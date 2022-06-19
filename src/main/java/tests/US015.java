package tests;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.VaccinationFacility;
import app.ui.console.utils.ReadFile;
import app.ui.gui.controller.CheckAndExportController;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class US015 implements ReadFile {

    @Test
    void testValid() throws IOException {
        Company company= App.getInstance().getCompany();
        VaccinationFacility vaccinationFacility = company.getVaccinationFacilityList().get(0);
        CheckAndExportController checkAndExportController = new CheckAndExportController();
        checkAndExportController.setFacilityTester(vaccinationFacility);
        List<String> test= new ArrayList<>();
        test.addAll(checkAndExportController.getFullyVaccinatedListFromTo(LocalDate.of(2022, 5,30), LocalDate.of(2020, 6,19 )));
        List<String> expected=readFile("Testing/statiscts.csv");
        assertIterableEquals(test,expected);
    }


    @Override
    public List<String> readFile(String file) throws IOException {
        List<String> fileData=new ArrayList<>();
        Scanner in = new Scanner(new File(file));
        int i=0;
        while (in.hasNext()){
            fileData.add(in.nextLine());
        }
        return fileData;
    }
}
