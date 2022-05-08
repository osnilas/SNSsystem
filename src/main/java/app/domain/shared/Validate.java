package app.domain.shared;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;


public class Validate {
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

}
