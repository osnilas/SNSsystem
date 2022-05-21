package mappers.dto;

import app.domain.model.User;
import app.domain.shared.Constants;

import java.time.LocalDate;

public class dtoSNSuser extends User {

    private String sex;
    private LocalDate birth;
    private int SNSnumber;

    private final static String DEFAULT_SEX = "Not specified";

    public dtoSNSuser(String name, String sex, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber) {
        super(email,name,address,email,phoneNumber,ccNumber);

        this.sex = sex;
        this.birth = birth;
        this.SNSnumber = SNSnumber;
    }

    public dtoSNSuser(String name, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber) {
        super(email,name,address,email,phoneNumber,ccNumber);

        sex = DEFAULT_SEX;
        this.birth = birth;
        this.SNSnumber = SNSnumber;

    }

    @Override
    public String getName() {
        return super.getName();
    }

    public LocalDate getBirth() {
        return birth;
    }

    public int getSNSnumber() {
        return SNSnumber;
    }

    @Override
    public int getCcNumber() {
        return super.getCcNumber();
    }

    @Override
    public int getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public String getResidenceAddress() {
        return super.getResidenceAddress();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "SNSuser:" +
                "name:'" + getName() +
                "  , sex:" + sex  +
                "  , birth:" + birth +
                "  , residence  address:'" + getResidenceAddress() +
                "  , email:" + getEmail()  +
                "  , phone number:" + getPhoneNumber() +
                "  , SNS number:" + SNSnumber +
                "  , citizen card number:" + getCcNumber();
    }
}
