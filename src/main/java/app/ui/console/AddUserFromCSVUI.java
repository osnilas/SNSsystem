package app.ui.console;

import app.controller.AddSNSfromCSVController;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.ReadFile;
import app.ui.console.utils.Utils;
import mappers.dto.dtoSNSuser;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.PatternSyntaxException;

import static java.time.LocalDate.parse;

public class AddUserFromCSVUI implements Runnable, ReadFile {

    private AddSNSfromCSVController ctlr;

    /**
     * @author João Veiga
     * Iniciates controller
     */
    public AddUserFromCSVUI() {
        ctlr = new AddSNSfromCSVController();
    }


    public void run() {
        String file;
        Utils.printText("\nAdd SNS users from CSV file UI:");
        file = Utils.readLineFromConsole("Insert CSV file name");
        if (file != null && file.endsWith(".csv")) {
            try {
                copyDataFromFile(file);
            } catch (Exception  e) {
                e.printStackTrace();
            }
        }
    }

    private void copyDataFromFile(String file) throws Exception {
        boolean success = false;
        boolean flag = true;
        int count = 0;
        dtoSNSuser temp = null;
        List<String> fileData = readFile(file);
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
            if (!validateContents(line)) {
                throw new Exception("CSV file information format not valid");
            }
        }
        for (int i = 0; i < fileData.size(); i++) {
            String[] line = fileData.get(i).split(split);
            String name = line[0];
            String sex = line[1];
            LocalDate birth = LocalDate.parse(line[2], Constants.FORMATTER);
            String address = line[3];
            int phoneNumber = Integer.parseInt(line[4]);
            String email = line[5];
            int SNSnumber = Integer.parseInt(line[6]);
            int ccNumber = Integer.parseInt(line[7]);
            String password = Utils.generatePwd(Constants.PWD_LENGTH);
            if (line[1].isBlank()) {
                temp = new dtoSNSuser(name, birth, address, email, phoneNumber, SNSnumber, ccNumber, password);
            } else {
                temp = new dtoSNSuser(name, sex, birth, address, email, phoneNumber, SNSnumber, ccNumber, password);
            }
            if (flag) {
                success = ctlr.createSNSuser(temp);
                if (!success) {
                    Utils.printText("User already exists or its invalid");
                }
                success = ctlr.saveSNSuser(temp);
                if (success) {
                    Utils.printText("-----------Registration done successfully-----------");
                    count++;
                } else {
                    Utils.printText("-----------Registration failed-----------");
                }
            } else {
                throw new Exception("CSV file information format not valid");
            }
        }
    }
    /**
     * @author João Veiga
     * @Description This method validates all the mandatory attributes of an SNS user
     * @param Line Line from a CSV file, already split
     * @return Boolean, true if all attributes are valid, false if at least one is invalid
     */
    private boolean validateContents(String[] Line) {
        boolean name, birth, address, phoneNumber, email, SNSnumber, ccNumber;
        try {
            name = Validate.validateName(Line[0]);
            birth = Validate.validateDate(Line[2]);
            address = Validate.validateAddress(Line[3]);
            phoneNumber = Validate.validatePhone(Integer.parseInt(Line[4]));
            email = Validate.validateEmail(Line[5]);
            SNSnumber = Validate.validateSNS(Integer.parseInt(Line[6]));
            ccNumber = Validate.validateCC(Integer.parseInt(Line[7]));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return name && birth && address && phoneNumber && email && SNSnumber && ccNumber;
    }

    @Override
    public List<String> readFile(String file) throws FileNotFoundException {
        List<String> fileData=new ArrayList<>();
        Scanner in = new Scanner(new File(file));
        while (in.hasNextLine()){
            fileData.add(in.nextLine());
        }
        return fileData;
    }
}
