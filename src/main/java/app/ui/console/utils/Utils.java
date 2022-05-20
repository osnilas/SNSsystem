package app.ui.console.utils;

import app.domain.model.TypeVaccine;
import app.domain.model.VaccinationFacility;
import app.domain.shared.Constants;
import app.ui.console.ShowTextUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }

    static public void showDate(List<LocalDate> dateList,String header){
        System.out.println(header);

        int index=0;
        for(int i=0;i<dateList.size();i++){
            index++;

            System.out.println(index+". "+dateList.get(i).format(Constants.FORMATTER));
        }
        System.out.println("");
        System.out.println("0-Cancel");
    }


    static public void showVaccinationFacility(List<VaccinationFacility> list, String header) {
        System.out.println(header);

        int index = 0;
        for (int i=0; i<list.size() ;i++){

            index++;

            System.out.println(index + ". " + list.get(i).getName());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }

    static public void showTypeVaccinne(List<TypeVaccine> list, String header) {
        System.out.println(header);

        int index = 0;
        for (int i=0; i<list.size() ;i++){

            index++;

            System.out.println(index + ". " + list.get(i).getName());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }


    static public Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    static public LocalDate createDate(String date){
        String[] cut=date.split("-");
        LocalDate result=LocalDate.of(Integer.parseInt(cut[2]),Integer.parseInt(cut[1]),Integer.parseInt(cut[0]));
        return result;
    }

    static public LocalTime createTime(LocalDate date, String time){
        String[] cut=time.split(":");
        LocalTime result=LocalTime.of(Integer.parseInt(cut[0]),Integer.parseInt(cut[1]));
        return result;
    }

    static public List copyList(List store, List toBeCopied){
        if(!store.isEmpty()&&!toBeCopied.isEmpty()) {
            for (int i = 0; i < toBeCopied.size(); i++) {
                store.add(toBeCopied);
            }
            return store;
        }else{
            return null;
        }
    }

    static public void printText(String text){
        System.out.println(text);
    }


    /**@author Jose Martinez from stackoverFlow
     * @link https://stackoverflow.com/a/51823845
     * This method generates a password with 7 alphanumeric characters,
     * including three capital letters and two digits.
     * The code was modifed to accomedate clients requirements
     * @param lenght of password
     * @return password with 7 alphanumeric characters, including three capital letters and two digits
     */
    static public String generatePwd(int lenght) {

        char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] NUMBERS = "0123456789".toCharArray();
        char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random rand = new SecureRandom();

        char[] password = new char[lenght];

        //get the requirements out of the way
        password[0] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[3] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[4] = NUMBERS[rand.nextInt(NUMBERS.length)];

        //populate rest of the password with random chars
        for (int i = 5; i < lenght; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        //shuffle it up
        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }
        return new String(password);
    }
}
