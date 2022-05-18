package app.domain.model;

import mappers.dto.dtoSNSuser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SNSuser extends Person{


    private String sex;
    private LocalDate birth;
    private int SNSnumber;

    private VaccinationRecord vaccinationRecord;

    private final static String DEFAULT_SEX = "Not specified";

    public SNSuser(String name, String sex, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber) {
        super(email,name,address,email,phoneNumber,ccNumber);

        this.sex = sex;
        this.birth = birth;
        this.SNSnumber = SNSnumber;
    }

    public SNSuser(String name, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber) {
        super(email,name,address,email,phoneNumber,ccNumber);

        sex = DEFAULT_SEX;
        this.birth = birth;
        this.SNSnumber = SNSnumber;

    }


    public int getAge() {
        LocalDate now = LocalDate.now();
        int years = now.getYear() - birth.getYear();
        return years;
    }

    public int getSNSnumber() {
        return SNSnumber;
    }

    @Override
    public String toString() {
        return "SNSuser{" +
                "name='" + getName() + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", address='" + getAddress() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber=" + getPhoneNumber() +
                ", SNSnumber=" + SNSnumber +
                ", ccNumber=" + getCcNumber() +
                '}';
    }
}
