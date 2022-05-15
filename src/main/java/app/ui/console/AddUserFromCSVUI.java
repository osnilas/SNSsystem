package app.ui.console;

import app.controller.AddSNSfromCSVController;
import app.domain.model.SNSuser;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoSNSuser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddUserFromCSVUI implements Runnable {

    private AddSNSfromCSVController ctlr;

    private List<dtoSNSuser> SNSlist = new ArrayList<>();

    /**
     * @author João Veiga
     * Iniciates controller
     */
    public AddUserFromCSVUI() {
        ctlr = new AddSNSfromCSVController();
    }

    public void run() {
            boolean success = file();
    }

    private boolean file(){
        boolean success = false;
        int flag = 0;
        String file;
        System.out.println("\nAdd SNS users from CSV file UI:");
        file = Utils.readLineFromConsole("Insert CSV file name");
        if (file != null && file.endsWith(".csv")) {
            try {
                success = copy(file);
            } catch (FileNotFoundException | ParseException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    private boolean copy(String file) throws FileNotFoundException, ParseException {
        boolean success = false;
        boolean flag=true;
        int count=0;
        String split;
        Scanner in = new Scanner(new File(file));
        String uncutLine = in.nextLine();
        dtoSNSuser temp = null;
        if (uncutLine.contains(",")) {
            split=",";
            in.reset();
        }
        else{
            split=";";
        }
        while (in.hasNextLine()) {
            String[] Line = uncutLine.split(split);
            if (validateContents(Line)) {
                if(Line[2]==null) {
                     temp = new dtoSNSuser(Line[0], Constants.df.parse(Line[4]), Line[1], Line[4], Integer.parseInt(Line[3]), Integer.parseInt(Line[6]), Integer.parseInt(Line[7]));
                }
                else{
                    if(Validate.validateSex(Line[2])) {
                         temp = new dtoSNSuser(Line[0], Line[2], Constants.df.parse(Line[4]), Line[1], Line[4], Integer.parseInt(Line[3]), Integer.parseInt(Line[6]), Integer.parseInt(Line[7]));
                    }
                    else {
                        flag=false;
                    }
                }
                if (flag) {
                    SNSlist.add(temp);
                }
                else {
                    System.out.println("Input:");
                    System.out.println(uncutLine);
                    System.out.println("-------Last Input not valid----");
                }
            } else {
                System.out.println("Input:");
                System.out.println(uncutLine);
                System.out.println("-------Last Input not valid----");
            }
            uncutLine=in.nextLine();
        }
        for (int i = 0; i < SNSlist.size(); i++) {
            success = ctlr.createSNSuser(SNSlist.get(i));
            if (!success) {
                System.out.println("User already exists or its unvalid");
            }
        }
        if (success) {
            for (int i = 0; i < SNSlist.size(); i++) {
                System.out.println(SNSlist.get(i).toString());
                if (Utils.confirm("Is it correct?(s/n)")) {
                    success = ctlr.saveSNSuser(SNSlist.get(i));
                }
                if (success) {
                    System.out.println("-----------Registration done successfully-----------");
                    count++;
                }
            }
        }
        if(count!=0){
            success=true;
        }
        return success;
    }

    private boolean validateContents(String[] Line) {
        return (Validate.validateName(Line[0]) && Validate.validateAddress(Line[1]) && Validate.validatePhone(Integer.parseInt(Line[3])) && Validate.validateEmail(Line[4]) && Validate.validateBirth(Line[5]) && Validate.validateSNS(Integer.parseInt(Line[6])) && Validate.validateCC(Integer.parseInt(Line[7])));
    }
}
