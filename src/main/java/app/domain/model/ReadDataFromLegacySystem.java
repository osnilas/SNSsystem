package app.domain.model;

import app.ui.console.utils.ReadFile;
import app.ui.console.utils.ReadFileData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDataFromLegacySystem {

    String file;

    DateTimeFormatter formatter;
    ReadFileData readFileData= new ReadFileData();
    List<String> fileData = new ArrayList<>();

    public ReadDataFromLegacySystem(String file,DateTimeFormatter formatter) throws IOException {
        this.file=file;
        this.formatter=formatter;
        this.fileData.addAll(readFileData.readFile(file));
    }

    public List<String> copyDataFromLegacySystem (String file) throws  Exception{

        List<String> fileData = readFileData.readFile(file);
        System.out.println("Size:"+fileData.size() );
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
            int SNSnumber = Integer.parseInt(line[0]);
            String vaccineName = line[1];
            String dosage = line[2];
            String lotNumber = line[3];
            String scheduleDateTime = line[4];
            String arrivalDateTime = line [5];
            String nurseAdministrationTime = line [6];
            String leavingDateNumber = line [7];


        }
        return fileData;
    }





    public boolean validateDataFromLegacySystem (String[] line){

        return false;
    }

}
