package app.ui.console;

import app.controller.AddSNSfromCSVController;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoSNSuser;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.PatternSyntaxException;

import static java.time.LocalDate.parse;

public class AddUserFromCSVUI implements Runnable {

    private AddSNSfromCSVController ctlr;

    /**
     * @author João Veiga
     * Iniciates controller
     */
    public AddUserFromCSVUI() {
        ctlr = new AddSNSfromCSVController();
    }

    public void run() {
        file();
    }

    private void file() {
        String file;
        Utils.printText("\nAdd SNS users from CSV file UI:");
        file = Utils.readLineFromConsole("Insert CSV file name");
        if (file != null && file.endsWith(".csv")) {
            try {
                copyDataFromFile(file);
            } catch (FileNotFoundException | NoSuchElementException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyDataFromFile(String file) throws FileNotFoundException, ParseException, NoSuchElementException {
        boolean success = false;
        boolean flag = true;
        int count = 0;
        String split = null, uncutLine = null;
        Scanner in = new Scanner(new File(file));
        dtoSNSuser temp = null;
        if (in.hasNextLine()) {
            uncutLine = in.nextLine();
            if (uncutLine.contains(",")) {
                split = ",";
                in = new Scanner(new File(file));
            } else if (uncutLine.contains(";")) {
                split = ";";
            }
            if (split != null) {
                while (in.hasNextLine()) {
                    uncutLine = in.nextLine();
                    try{
                    String[] Line = uncutLine.split(split);
                    if (validateContents(Line)) {
                        String name = Line[0];
                        String sex = Line[1];
                        LocalDate birth = LocalDate.parse(Line[2], Constants.FORMATTER);
                        String address = Line[3];
                        int phoneNumber = Integer.parseInt(Line[4]);
                        String email = Line[5];
                        int SNSnumber = Integer.parseInt(Line[6]);
                        int ccNumber = Integer.parseInt(Line[7]);
                        if (Line[1].isBlank()) {
                            temp = new dtoSNSuser(name, birth, address, email, phoneNumber, SNSnumber, ccNumber);
                        } else {
                            if (Validate.validateSex(Line[1])) {
                                temp = new dtoSNSuser(name, sex, birth, address, email, phoneNumber, SNSnumber, ccNumber);
                            } else {
                                flag = false;
                            }
                        }
                        if (flag) {
                            success = ctlr.createSNSuser(temp);
                            if (!success) {
                                Utils.printText("User already exists or its unvalid");
                            }
                            Utils.printText(ctlr.printSNSuser());
                            if (Utils.confirm("Is it correct?(s/n)")) {
                                success = ctlr.saveSNSuser(temp);
                            } else {
                                Utils.printText("-----Not saved, registration aborted-----");
                            }
                            if (success) {
                                Utils.printText("-----------Registration done successfully-----------");
                                count++;
                            } else {
                                Utils.printText("-----------Registration failed-----------");
                            }
                        } else {
                            Utils.printText("-------Sex not valid----");
                        }
                    } else {
                        Utils.printText("Input:");
                        Utils.printText(uncutLine);
                        Utils.printText("-------Input not valid----");
                    }
                }catch (PatternSyntaxException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        in.close();
    }

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
}
