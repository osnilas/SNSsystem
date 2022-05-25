package app.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SNSuser {


    private String name;
    private String residenceAddress;
    private String email;
    private int phoneNumber;
    private int ccNumber;
    private String sex;
    private LocalDate birth;
    private int SNSnumber;

    private List<VaccinationRecord> vaccinationRecord;

    private final static String DEFAULT_SEX = "Not specified";

    private String password;

    public SNSuser(String name, String sex, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber,String password) {
        this.name= name;
        this.residenceAddress =address;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.ccNumber=ccNumber;
        this.sex = sex;
        this.birth = birth;
        this.SNSnumber = SNSnumber;
        this.vaccinationRecord=new ArrayList<>();
        this.password=password;
    }

    public SNSuser(String name, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber,String password) {
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

    public String getSex() {
        return sex;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getCcNumber() {
        return ccNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        int years = now.getYear() - birth.getYear();
        return years;
    }

    public int getSNSnumber() {
        return SNSnumber;
    }

    public  List <VaccinationRecord> getVaccinationRecord() {
        return vaccinationRecord;
    }

    public boolean checkIfHasVaccinationRecord(){
        if(vaccinationRecord.size()==0){
            return false;
        }
        return true;
    }

    public VaccinationRecord getLatestVaccinationRecord(Vaccine vaccine){
        int numberDosageTemp=-1,index=-1;
        for (int i=0;i<vaccinationRecord.size();i++){
            if(vaccinationRecord.get(i).getNumberDosesTaken()<numberDosageTemp){
                numberDosageTemp=vaccinationRecord.get(i).getNumberDosesTaken();
                index=i;
            }
        }
        return vaccinationRecord.get(index);
    }

    public boolean emailSame(String email){
        return this.email.equalsIgnoreCase(email);
    }

    public boolean SNSnumberSame(int snsNumber){
        return this.SNSnumber==snsNumber;
    }

    @Override
    public String toString() {
        return "SNSuser:" +
                "name:'" + name + '\'' +
                ", sex:'" + sex + '\'' +
                ", birth:" + birth +
                ", residence  address:'" + name + '\'' +
                ", email:'" + email + '\'' +
                ", phone number:" + phoneNumber +
                ", SNS number:" + SNSnumber +
                ", citizen card number:" + ccNumber +
                ", password:" +password
                ;
    }

}
