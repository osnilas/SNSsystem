package mappers.dto;

import app.domain.model.User;
import app.domain.shared.Constants;

import java.lang.ref.PhantomReference;
import java.time.LocalDate;

public class dtoSNSuser {

    private String name;
    private String residenceAddress;
    private String email;
    private int phoneNumber;
    private int ccNumber;

    private String sex;
    private LocalDate birth;
    private int SNSnumber;
    private String password;

    private final static String DEFAULT_SEX = "Not specified";

    public dtoSNSuser(String name, String sex, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber,String password) {
        this.name= name;
        this.residenceAddress =address;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.ccNumber=ccNumber;

        this.sex = sex;
        this.birth = birth;
        this.SNSnumber = SNSnumber;
        this.password=password;
    }

    public dtoSNSuser(String name, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber,String password) {
        this.name= name;
        this.residenceAddress =address;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.ccNumber=ccNumber;
        sex = DEFAULT_SEX;
        this.birth = birth;
        this.SNSnumber = SNSnumber;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public int getSNSnumber() {
        return SNSnumber;
    }

    public int getCcNumber() {
        return ccNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }


    public String getResidenceAddress() {
        return residenceAddress;
    }


    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public String getPassword() {
        return password;
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
