package tests;

import app.controller.App;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.ReadFile;
import app.ui.gui.ui.FileChooserLegacySystem;
import org.junit.jupiter.api.Test;
import org.testng.annotations.Ignore;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.ConstantBootstraps;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


//MUST DELETE ANY RECORD ON THE DATABASE BEFORE RUNNING THIS TEST

class US006 implements ReadFile {

    Company company=App.getInstance().getCompany();

    private String filePath = Constants.FILEPATH_REPORT_TEST;

    private String filePathInvalid = Constants.FILEPATH_REPORTINVALID_TEST_2;

    private VaccinationAdminstrationRecord vaccinationAdminstrationRecord=new VaccinationAdminstrationRecord(123456789,Constants.VACCINE_TESTER,1,"12345-65",LocalDateTime.now());



    @Test
    void generateDGSreportContentValid() {
        File file = new File(Constants.FILEPATH_REPORT);
        file.delete();
        company.getVaccinationFacilityList().get(0).getVaccinationAdminstrationRecordList().clear();
        company.getVaccinationFacilityList().get(0).getVaccinationAdminstrationRecordList().add(vaccinationAdminstrationRecord);
        company.getVaccinationFacilityList().get(0).getVaccinationAdminstrationRecordList().add(vaccinationAdminstrationRecord);
        company.generateDGSreportContent();

        List<String> exepted=new ArrayList<>();
        List<String> generated=new ArrayList<>();
        try {
            exepted.addAll(readFile(filePath));
            generated=readFile(Constants.FILEPATH_REPORT);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertIterableEquals(exepted,generated);
    }

    @Test
    void generateDGSreportContentInvalid() {
        File file = new File(Constants.FILEPATH_REPORT);
        file.delete();
        company.getVaccinationFacilityList().get(0).getVaccinationAdminstrationRecordList().clear();
        company.getVaccinationFacilityList().get(0).getVaccinationAdminstrationRecordList().add(vaccinationAdminstrationRecord);
        company.getVaccinationFacilityList().get(0).getVaccinationAdminstrationRecordList().add(vaccinationAdminstrationRecord);
        company.generateDGSreportContent();

        List<String> exepted=new ArrayList<>();
        List<String> generated=new ArrayList<>();
        try {
            exepted.addAll(readFile(filePathInvalid));
            generated.addAll(readFile(Constants.FILEPATH_REPORT));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertFalse(Arrays.equals(exepted.toArray(), generated.toArray()));
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