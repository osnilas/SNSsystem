package app.ui.console.utils;

import app.domain.model.TypeVaccine;
import app.domain.model.VaccinationFacility;
import app.domain.shared.Constants;
import app.ui.console.ScheduleVaccinationUI;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.*;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
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
        String[] cut=date.split("/");
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

    static public void ShowUserRoleDTO (List<UserRoleDTO> list,String header){
        System.out.println(header);

        int index = 0;
        for (int i=0; i<list.size() ;i++){

            index++;

            System.out.println(index + ". " + list.get(i).getDescription());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }

    public static List readFile(String filePath){
        File ficheiro =new File(filePath);
        List list=new ArrayList();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(ficheiro));
            try {
               list = (List) in.readObject();
            } finally {
                in.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
        return list;
    }

    public static void save(String filePath,List list){
        File ficheiro =new File(filePath);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ficheiro));
            try {
                out.writeObject(list);
            } finally {
                out.flush();
                out.close();
            }
        } catch (IOException ex) {
        }
    }

    public static void ConsoleSwitch(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Switching to Console");
        alert.setContentText("Graphical interface to this feature is not yet available");
        alert.setHeaderText("This window will be minimized and the program will run on console");
        alert.showAndWait();
    }

    public static void ExceptionWarning(Exception e){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Wasn't possible to do this action due to invalid data");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }

    public static Alert createConfirmation(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        return alert;
    }

    public static Alert Warning(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
        return alert;
    }

    public static Alert Information(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
        return alert;
    }


    public static boolean checkIfFileEmpty(String pathToFile){
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));
            if (br.readLine() == null) {
                System.out.println("No errors, and file empty");
            }
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
        return true;
    }

    public static boolean checkIfFileExists(String pathToFile){
        File file =new File(pathToFile);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public static void deleteFile(String pathToFile){
        File file =new File(pathToFile);
        file.delete();
    }

    public static String ReadProppeties(String property){
        Properties props = new Properties();
        try {


            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException e) {
        }
        String value = props.getProperty(property);
        return value;
    }
}
