package app.domain.shared;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Validate {

    public boolean validateEmail(String email) {
        return StringUtils.isBlank(email) ? false : this.checkFormat(email);
    }

    //from the email class
    private boolean checkFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public boolean validatePhone(int phone) {
        if (getDigits(phone) != Constants.PHONE_NUMBER_LENGHT) {
            return false;
        } else {
            return true;
        }

    }

    public boolean validateName(String name) {
        if (name.isEmpty() || name == null || name.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateAdress(String Adress) {
        if (Adress.isEmpty() || Adress.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateCC(int cc) {
        if (getDigits(cc) != Constants.CC_LENGHT) {
            return false;
        }
        return true;
    }

    public int getDigits(int number) {
        int count = 0;
        while (number > 0) {
            number = number / 10;
            count++;
        }
        return count;
    }

}
