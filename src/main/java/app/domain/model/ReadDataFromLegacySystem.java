package app.domain.model;

import app.controller.App;
import app.domain.shared.Validate;
import app.ui.console.utils.ReadFile;
import app.ui.console.utils.ReadFileData;
import app.ui.console.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDataFromLegacySystem {

    String file;

    List<Vaccine>  vaccineList = new ArrayList<>();

    ReadFileData readFileData= new ReadFileData();
    List<String> fileData = new ArrayList<>();

    public ReadDataFromLegacySystem(String file,List<Vaccine> vaccineList) throws IOException {
        this.file=file;
        this.vaccineList.addAll(vaccineList);
        this.fileData.addAll(readFileData.readFile(file));
    }

    public List<VaccinationAdminstrationRecord> copyDataFromLegacySystem () throws  Exception{

        List<String> fileData = readFileData.readFile(file);
        ArrayList<VaccinationAdminstrationRecord> appointmentsFromLegacySystem = new ArrayList<>();
        String split = null;

        if (!fileData.get(0).contains(",")) {
            fileData.remove(0);
            split = ";";
        }
        else {
            split = ",";
        }

        for (int i = 0; i < fileData.size(); i++) {
            String[] line = fileData.get(i).split(split);
            if (!validateDataFromLegacySystem(line)) {
                int snsNumber = Integer.parseInt(line[0]);
                Vaccine vaccine = getVaccine(line[1]);
                int dosage = parseDose(line[2]);
                String lotNumber = line[3];
                LocalDateTime scheduleDateTime = Utils.parseDateTimeAmerican(line[4]);
                LocalDateTime arrivalDateTime = Utils.parseDateTimeAmerican(line[5]);
                LocalDateTime nurseAdministrationTime = Utils.parseDateTimeAmerican(line[6]);
                LocalDateTime leavingDateTime = Utils.parseDateTimeAmerican(line[7]);
                VaccinationAdminstrationRecord temp = new VaccinationAdminstrationRecord(snsNumber, vaccine, dosage, lotNumber, arrivalDateTime,scheduleDateTime, nurseAdministrationTime, leavingDateTime);
                System.out.println(temp.toString());
                appointmentsFromLegacySystem.add(temp);
            }
        }


        return appointmentsFromLegacySystem;
    }

    private int parseDose(String dose){
        switch (dose){
            case "Primeira":
                return 1;
            case "Segunda":
                return 2;
            case "Terceira":
                return 3;
            default:
        }
        return -1;
    }

    private Vaccine getVaccine(String vaccineName) {
        for(int i=0;i<vaccineList.size();i++){
            if(vaccineList.get(i).getName().equals(vaccineName)){
                return vaccineList.get(i);
            }
        }
        return null;
    }


    public boolean validateDataFromLegacySystem (String[] line){
        boolean snsNumber, vaccineName,dosage,lotNumber,scheduleDateTime,arrivalDateTime,nurseAdministrationTime,leavingDateTime;

        snsNumber = Validate.validateSNS(Integer.parseInt(line[0])) && validateSNSuser(Integer.parseInt(line[0]));
        vaccineName=Validate.validateName(line[1]);
        dosage=!line[2].isBlank();
        lotNumber=Validate.validateLotNumber(line[3]);
        scheduleDateTime=Validate.validateTimeFile(line[4]);
        arrivalDateTime=Validate.validateTimeFile(line[5]);
        nurseAdministrationTime=Validate.validateTimeFile(line[6]);
        leavingDateTime=Validate.validateTimeFile(line[7]);

        return snsNumber && vaccineName && dosage && lotNumber && scheduleDateTime && arrivalDateTime && nurseAdministrationTime && leavingDateTime;
    }

    private boolean validateSNSuser(int snsNumber){
        SNSuser snsUser;
        Company company = App.getInstance().getCompany();
        snsUser= company.SNSuserExistsNumber(snsNumber);
        if(snsUser==null){
            return false;
        }
        return true;
    }
}
