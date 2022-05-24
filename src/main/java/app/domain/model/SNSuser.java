package app.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SNSuser extends User {


    private String sex;
    private LocalDate birth;
    private int SNSnumber;

    private List<VaccinationRecord> vaccinationRecord;

    private final static String DEFAULT_SEX = "Not specified";

    public SNSuser(String name, String sex, LocalDate birth, String address, String email, int phoneNumber, int SNSnumber, int ccNumber) {
        super(email,name,address,email,phoneNumber,ccNumber);

        this.sex = sex;
        this.birth = birth;
        this.SNSnumber = SNSnumber;
        this.vaccinationRecord=new ArrayList<>();
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

    public boolean SNSnumberSame(int snsNumber){
        return this.SNSnumber==snsNumber;
    }

    @Override
    public String toString() {
        return "SNSuser:" +
                "name:'" + getName() + '\'' +
                ", sex:'" + sex + '\'' +
                ", birth:" + birth +
                ", residence  address:'" + getResidenceAddress() + '\'' +
                ", email:'" + getEmail() + '\'' +
                ", phone number:" + getPhoneNumber() +
                ", SNS number:" + SNSnumber +
                ", citizen card number:" + getCcNumber() +
                '}';
    }

}
