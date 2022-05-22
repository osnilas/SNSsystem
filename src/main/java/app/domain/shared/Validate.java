package app.domain.shared;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class Validate {

    private static List<String> VaccineTypeList = Arrays.asList("Covid", "Malaria");

    /**
     * @author João Veiga
     * validates email.
     * Checks if its blank and its format is valid
     * @param email
     * @return boolean if its valid or not
     */
    public static boolean validateEmail(String email) {
        return !StringUtils.isBlank(email) && checkFormat(email);
    }

    /**
     * @author ISEP
     * Checks email format, format: *something*@something.something
     * @param email
     * @return boolean if email checks out format
     */
    private static boolean checkFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    /**
     * @author Afonso Cunha
     * Checks url format, format: www.*something*.something
     * @param websiteAdress
     * @return boolean if website adress checks out format
     */

    private static boolean checkUrlFormat(String websiteAdress) {
        String urlRegex = "[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
        Pattern pat = Pattern.compile(urlRegex);
        return pat.matcher(websiteAdress).matches();
    }

    /**
     * @author João Veiga
     * checks if phone number check out the phone number length of portuguese phone, 9 digits
     * @param phone
     * @return boolean if phone number check out requirements
     */
    public static boolean validatePhone(int phone) {
        if (getDigits(phone) != Constants.PHONE_NUMBER_LENGHT) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * @author João Veiga
     * checks if cc number checks out the cc number length of Portugal, 8 d
     * @param cc
     * @return boolean if cc number checks out requirements
     */
    public static boolean validateCC(int cc) {
        if (getDigits(cc) != Constants.CC_LENGHT) {
            return false;
        }
        return true;
    }

    /**
     * @author João Veiga
     * gives number of digits of a number
     * @param number
     * @return number of digits of a number
     */
    private static int getDigits(int number) {
        int count = 0;
        while (number > 0) {
            number = number / 10;
            count++;
        }
        return count;
    }

    public static boolean validateCode (String code) {
        if (code.length() != 5) {
            return false;
        } else {
            String regex = "[a-zA-Z0-9]{5}";
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(code).matches();
        }
    }

    public static boolean validateMinimumAge (List<Integer> age, int temp) {
        return age.get(temp) >= 1 && age.get(temp) <= 105;
    }

    public static boolean validateMaximumAge (List<Integer> minAge, List<Integer> maxAge, int temp) {
        return maxAge.get(temp) >= minAge.get(temp) && maxAge.get(temp) <= 105;
    }

    public static boolean validateDosage (List<Double> dosage, int temp) {
        return dosage.get(temp) > 0;
    }

    public static boolean validateDoses (List<Integer> doses, int temp) {
        return doses.get(temp) >= 1;
    }

    public static boolean validateVaccineInterval (List<Integer> vaccineInterval, int temp) {
        return vaccineInterval.get(temp) >= 1;
    }

    /**
     * @author Afonso Cunha
     * checks if the type of vaccine is on the type of vaccine list
     * @param typeOfVaccine
     * @return boolean true if the vaccine type exists
     */

    public static boolean validateTypeOfVaccine(String typeOfVaccine) {
        if (VaccineTypeList.contains(typeOfVaccine)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @author Afonso Cunha
     * checks if the slot duration is above 0
     * @param slotDuration
     * @return boolean true if the number is above 0
     */

    public static boolean validateSlotDuration(int slotDuration) {
        if (slotDuration > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @author Afonso Cunha
     * checks if the slot duration is above 0
     * @param maximumNumberOfVaccinesPerSlot
     * @return boolean true if the number is above 0
     */

    public static boolean validateMaximumNumberOfVaccinesPerSlot(int maximumNumberOfVaccinesPerSlot) {
        if (maximumNumberOfVaccinesPerSlot > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @author Afonso Cunha
     * Checks url format, format: www.*something*.something
     * @param websiteAdress
     * @return boolean if website adress checks out format
     */

    public static boolean validateWebsiteAdress(String websiteAdress) {
        return !StringUtils.isBlank(websiteAdress) && checkUrlFormat(websiteAdress);
    }

    public static boolean validateName(String name){
        if(name.isEmpty() && name.isBlank()){
            return false;
        }
        return true;
    }

    public static boolean validateAddress(String address) {
        if (address.isEmpty() && address.isBlank()) {
            return false;
        }
        return true;
    }

    public static boolean validateSNS (int sns){
        if(getDigits(sns)==Constants.SNS_LENGHT){
            return true;
        }
        return false;
    }

    public static boolean validateDate(String date){
        if(date!=null) {
            try {
                Date test = Constants.df.parse(date);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean validateTime(String time){
        if(time!=null) {
            try {
                LocalDateTime test = LocalDateTime.parse(time, Constants.TIME_FORMATTER);
            } catch (DateTimeException e) {
                return false;
            }
            return true;
        }return false;
    }

    public static boolean validateSex(String sex){
        if(sex!=null) {
            for (int i = 0; i < Constants.SexList.length; i++) {
                if (sex.equalsIgnoreCase(Constants.SexList[i])) {
                    return true;
                }

            }
            return false;
        }
        return false;
    }

}
