package app.domain.model;

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


    ReadFileData readFileData= new ReadFileData();
    List<String> fileData = new ArrayList<>();

    public ReadDataFromLegacySystem(String file) throws IOException {
        this.file=file;
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
                throw new Exception("CSV file information format not valid");
            }
      }

        for (int i = 0; i < fileData.size(); i++) {
            String[] line = fileData.get(i).split(split);
            int snsNumber = Integer.parseInt(line[0]);
            String vaccineName = line[1];
            int dosage =parseDose(line [2]);
            String lotNumber = line[3];
            LocalDateTime scheduleDateTime = Utils.parseDateTimeAmerican(line[4]);
            LocalDateTime arrivalDateTime =Utils.parseDateTimeAmerican(line [5]);
            LocalDateTime nurseAdministrationTime =Utils.parseDateTimeAmerican(line [6]);
            LocalDateTime leavingDateTime =Utils.parseDateTimeAmerican(line [7]);
            VaccinationAdminstrationRecord temp=new VaccinationAdminstrationRecord(snsNumber,vaccineName,dosage,lotNumber,scheduleDateTime,arrivalDateTime,nurseAdministrationTime,leavingDateTime);
            appointmentsFromLegacySystem.add(temp);
        }


        return appointmentsFromLegacySystem;
    }

    private int parseDose(String dose){
        switch (dose){
            case "Primeira":
                return 1;
                break;
            case "Segunda":
                return 2;
                break;
            case "Terceira":
                return 3;
                break;
            default:

        }

    }



    public boolean validateDataFromLegacySystem (String[] line){
        boolean snsNumber, vaccineName,dosage,lotNumber,scheduleDateTime,arrivalDateTime,nurseAdministrationTime,leavingDateTime;

        snsNumber = Validate.validateSNS(Integer.parseInt(line[0]));
        vaccineName=Validate.validateName(line[1]);
        dosage=!line[2].isBlank();
        lotNumber=Validate.validateLotNumber(line[3]);
        scheduleDateTime=Validate.validateScheduleDateTime(line[4]);
        arrivalDateTime=Validate.validateArrivalDateTime(line[5]);
        //nurseAdministrationTime=;
        //leavingDateTime=;




        return false;
    }

}
