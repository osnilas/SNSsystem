package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.regex.Pattern;

public class Employee {
    private AuthFacade auth;
    private int id;
    private String name;
    private Adress adress;
    private String email;
    private int phone;
    private String roleId;
    private Employee em;

    private static int count=0;

    public Employee(String name,Adress adress,int phone,String email, String roleId){
        count++;
        this.id=count;
        this.name=name;
        this.adress=adress;
        this.roleId=roleId;
        if(this.validateEmail(email)){
            this.email=email;
        }
        else{
            throw new IllegalArgumentException("Invalid email.");
        }
        if(this.validatePhone(phone)){
            this.phone=phone;
        }
        else{
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }

    public boolean validateEmail(String email){
        if(StringUtils.isBlank(email)&&checkFormat(email)){
            return true;
        }
        else{
            return false;
        }
    }

    //from the email class
    private boolean checkFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public boolean validatePhone(int phone){
        if(getDigits(phone)!= Constants.PHONE_NUMBER_LENGHT){
            return false;
        }
        else{
            return true;
        }

    }

    public int getDigits(int number){
        int count=0;
        while (number>0){
            number=number/10;
            count++;
        }
        return count;
    }
}
