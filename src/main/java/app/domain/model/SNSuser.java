package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class SNSuser implements Serializable {


    private String name;
    private String residenceAddress;
    private String email;
    private int phoneNumber;
    private int ccNumber;
    private String sex;
    private LocalDate birth;
    private int SNSnumber;

    private List<VaccineCard> vaccineCards;

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
        this.vaccineCards =new ArrayList<>();
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

    public  List <VaccineCard> getVaccineCards() {
        return vaccineCards;
    }

    public void setVaccineCards(VaccineCard record){
        vaccineCards.add(record);
    }

    public boolean checkIfHasVaccineCard(){
        if(vaccineCards.size()==0){
            return false;
        }
        return true;
    }

    public boolean checkIfTookVaccine(Vaccine vaccine){
        for(int i = 0; i> vaccineCards.size(); i++){
         if(Objects.equals(vaccineCards.get(i).getVaccine(),vaccine)){
             return true;
            }
        }
        return false;
    }


    /**@author João Veiga
     * @Description this method checks the SNS user Vaccination Record for all the records with the same vaccine as input and gets the latest record index from the List
     * @param vaccine
     * @return index of the lastest vaccination record of the vaccine taken by SNS user
     */
    public VaccineCard getVaccinationRecord(Vaccine vaccine){
        int index=-1;
        for (int i = 0; i< vaccineCards.size(); i++){
            if(Objects.equals(vaccineCards.get(i).getVaccine(),vaccine)){
                index=i;

            }
        }
        return vaccineCards.get(index);
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

    public void updateVaccinationRecord(Vaccine vaccine, LocalDateTime administraitonTime){
        if(checkIfTookVaccine(vaccine)) {
            for(int i=0;i<vaccineCards.size();i++){
                if(vaccineCards.get(i).getVaccine().equals(vaccine)){
                    vaccineCards.get(i).updateNumberDosesTaken();
                }
            }
        }else{
            vaccineCards.add(new VaccineCard(vaccine,administraitonTime,1));
        }

    }

   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       SNSuser snSuser = (SNSuser) o;
       return SNSnumber == snSuser.SNSnumber;
   }
    @Override
    public String toString() {
        return "SNSuser:" +
                "name:'" + name + '\'' +
                ", sex:'" + sex + '\'' +
                ", birth:" + birth +
                ", residence  address:'" + residenceAddress + '\'' +
                ", email:'" + email + '\'' +
                ", phone number:" + phoneNumber +
                ", SNS number:" + SNSnumber +
                ", citizen card number:" + ccNumber +
                ", password:" +password +"\n"
                ;
    }

}
