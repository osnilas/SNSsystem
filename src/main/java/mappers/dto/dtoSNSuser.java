package mappers.dto;

import app.domain.model.Person;

import java.util.Date;

public class dtoSNSuser extends Person {

    private String sex;
    private Date birth;
    private int SNSnumber;

    private final static String DEFAULT_SEX = "Not specified";

    public dtoSNSuser(String name, String sex, Date birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber) {
        super(email,name,address,email,phoneNumber,ccNumber);

        this.sex = sex;
        this.birth = birth;
        this.SNSnumber = SNSnumber;
    }

    public dtoSNSuser(String name, Date birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber) {
        super(email,name,address,email,phoneNumber,ccNumber);

        sex = DEFAULT_SEX;
        this.birth = birth;
        this.SNSnumber = SNSnumber;

    }

    @Override
    public String getName() {
        return super.getName();
    }

    public Date getBirth() {
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
    public String getAddress() {
        return super.getAddress();
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
        return "SNSuser{" +
                "name='" + getName() + '\'' +
                ", sex='" + getSex() + '\'' +
                ", birth=" + getBirth() +
                ", address='" + getAddress() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber=" + getPhoneNumber() +
                ", SNSnumber=" + getSNSnumber() +
                ", ccNumber=" + getCcNumber() +
                '}';
    }
}
