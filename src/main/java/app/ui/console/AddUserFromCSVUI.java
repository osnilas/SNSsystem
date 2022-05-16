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
import java.util.Date;
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
                String name= Line[0];
                String sex= Line[1];
                Date birth=Constants.df.parse(Line[2]);
                String address=Line[3];
                int phoneNumber=Integer.parseInt(Line[4]);
                String email=Line[5];
                int SNSnumber=Integer.parseInt(Line[6]);
                int ccNumber=Integer.parseInt(Line[7]);
                if(Line[1]==null) {
                     temp = new dtoSNSuser(name,birth,address,email,phoneNumber,SNSnumber,ccNumber);
                }
                else{
                    if(Validate.validateSex(Line[2])) {
                         temp = new dtoSNSuser(name,sex,birth,address,email,phoneNumber,SNSnumber,ccNumber);
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
                    System.out.println("-------Input not valid----");
                }
            } else {
                System.out.println("Input:");
                System.out.println(uncutLine);
                System.out.println("-------Input not valid----");
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
        boolean name= Validate.validateName(Line[0]);
        boolean birth=Validate.validateDate(Line[2]);
        boolean address=Validate.validateAddress(Line[3]);
        boolean phoneNumber=Validate.validatePhone(Integer.parseInt(Line[4]));
        boolean email=Validate.validateEmail(Line[5]);
        boolean SNSnumber=Validate.validateSNS(Integer.parseInt(Line[6]));
        boolean ccNumber=Validate.validateCC(Integer.parseInt(Line[7]));

        return name && birth && address && phoneNumber && email && SNSnumber && ccNumber;
    }
}
