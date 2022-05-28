package app.domain.model;

import app.domain.shared.Constants;

import java.lang.invoke.ConstantBootstraps;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    private final static String DEFAULT_SEX = "N/A";

    private String password;


    /**@author João Veiga
     * @Description Constructor,
     * Creates an SNS user with the following attributes:
     * @param name
     * @param sex
     * @param birth
     * @param residenceAddress
     * @param email
     * @param phoneNumber
     * @param SNSnumber
     * @param ccNumber
     * @param password
     */
    public SNSuser(String name, String sex, LocalDate birth, String residenceAddress, String email, int phoneNumber, int SNSnumber, int ccNumber,String password) {
        this.name= name;
        this.residenceAddress =residenceAddress;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.ccNumber=ccNumber;
        this.sex = sex;
        this.birth = birth;
        this.SNSnumber = SNSnumber;
        this.vaccinationRecord=new ArrayList<>();
        this.password=password;
    }

    /**@author João Veiga
     * @Description Constructor,
     * Creates an SNS user without specifying sex
     * @param name
     * @param birth
     * @param residenceAddress
     * @param email
     * @param phoneNumber
     * @param SNSnumber
     * @param ccNumber
     * @param password
     */
    public SNSuser(String name, LocalDate birth, String residenceAddress, String email, int phoneNumber, int SNSnumber, int ccNumber,String password) {
        this.name= name;
        this.residenceAddress =residenceAddress;
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

    /**@author João Veiga
     * @Description this method calculates the SNS user age by getting the year of birth of the SNS user and current year
     * @return SNS user age in Integer
     */
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

    public void setVaccinationRecord(VaccinationRecord record){
        vaccinationRecord.add(record);
    }

    public boolean checkIfHasVaccinationRecord(){
        if(vaccinationRecord.size()==0){
            return false;
        }
        return true;
    }

    /**@author João Veiga
     * @Description this method checks the SNS user Vaccination Record for all the records with the same vaccine as input and gets the latest record index from the List
     * @param vaccine
     * @return index of the lastest vaccination record of the vaccine taken by SNS user
     */
    public VaccinationRecord getLatestVaccinationRecord(Vaccine vaccine){
        int numberDosageTemp=-300,index=-1;
        for (int i=0;i<vaccinationRecord.size();i++){
            if(Objects.equals(vaccinationRecord.get(i).getVaccine(),vaccine)){
                if (vaccinationRecord.get(i).getNumberDosesTaken() > numberDosageTemp) {
                    numberDosageTemp = vaccinationRecord.get(i).getNumberDosesTaken();
                    index = i;
                }
            }
        }
        return vaccinationRecord.get(index);
    }

    /**@author João Veiga
     * @Description this method checks if the email is the same as the input
     * @param email
     * @return boolean, if its the same email returns true, otherwise false.
     */
    public boolean emailSame(String email){
        return this.email.equalsIgnoreCase(email);
    }
    /**@author João Veiga
     * this method checks if the sns number is the same as the input.
     * @param snsNumber
     * @return boolean, if its sns number is the same returns true, otherwise false.
            */
    public boolean SNSnumberSame(int snsNumber){
        return this.SNSnumber==snsNumber;
    }

    @Override
    public String toString() {
        return "SNSuser:" +
                "name:'" + name + '\'' +
                ", sex:'" + sex + '\'' +
                ", birth:" + birth.format(Constants.FORMATTER) +
                ", residence  address:'" + residenceAddress + '\'' +
                ", email:'" + email + '\'' +
                ", phone number:" + phoneNumber +
                ", SNS number:" + SNSnumber +
                ", citizen card number:" + ccNumber +
                ", password:" +password
                ;
    }

}
