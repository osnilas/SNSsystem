package app.domain.shared;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import static app.domain.shared.Constants.df;


public class Validate {


    public static boolean validateName(String name){
        if(name.isBlank() || name.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean validateSex(String sex){
        boolean flag= false;
        for(int i=0;i<Constants.SexList.length;i++){
            if(sex.equalsIgnoreCase(Constants.SexList[i])){
                flag= true;
            }
        }
        return flag;
    }

    public static boolean validateAddress(String address){
        return validateName(address);
    }

    public static boolean validateBirth(String birth){
        try {
            df.setLenient(false);
            df.parse(birth);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

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

    public static boolean validateSNS(int sns){
        if(getDigits(sns) != Constants.SNS_LENGHT ){
            return false;
        }
        return true;
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

    public static boolean validateMinimumAge (List<Integer> age, int temp) {
            if (age.get(temp) < 1 || age.get(temp) > 105) {
                return false;
            }
        return true;
    }

    public static boolean validateMaximumAge (List<Integer> minAge, List<Integer> maxAge, int temp) {
        if (maxAge.get(temp) < minAge.get(temp) || maxAge.get(temp) > 105) {
            return false;
        }
        return true;
    }

    public static boolean validateDosage (List<Double> dosage, int temp) {
        if (dosage.get(temp) <= 0) {
            return false;
        }
        return true;
    }

    public static boolean validateDoses (List<Integer> doses, int temp) {
        if (doses.get(temp) < 1) {
            return false;
        }
        return true;
    }

    public static boolean validateVaccineInterval (List<Integer> vaccineInterval, int temp) {
        if (vaccineInterval.get(temp) < 1) {
            return false;
        }
        return true;
    }

    /**
     * @author Afonso Cunha
     * checks if the type of vaccine is on the type of vaccine list
     * @param typeOfVaccine
     * @return boolean true if the vaccine type exists
     */
/**
    public static boolean validateTypeOfVaccine(String typeOfVaccine) {
        if (VaccineTypeList.contains(typeOfVaccine)){
            return true;
        }else{
            return false;
        }
    }
*/

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
/**
    public static boolean validateWebsiteAdress(String websiteAdress) {
        return !StringUtils.isBlank(websiteAdress) && checkUrlFormat(websiteAdress);
    }
*/

}
