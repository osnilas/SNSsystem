package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class VaccinationRecord implements Serializable {

    private Vaccine vaccine;
    private LocalDateTime date;
    private int numberDosesTaken;


    public VaccinationRecord(Vaccine vaccine, LocalDateTime date,int numberDose){

        this.vaccine=vaccine;
        this.date=date;
        this.numberDosesTaken =numberDose;
    }

    /**
     * @author João Veiga
     * @Description This method gets the vaccine adminstration index for the input age.
     * @param age
     * @return Vaccine adminstration index for the input age.
     */
    public int getAgeGroup(int age){
        int indexNumber=-1;
        for(int i=0;i<vaccine.getVaccineAdministration().getMaxAge().size();i++) {
            if (age >= vaccine.getVaccineAdministration().getMinAge().get(i) && age <= vaccine.getVaccineAdministration().getMaxAge().get(i)) {
                indexNumber = i;
            }
        }
        return indexNumber;
    }

    /**
     * @author João Veiga
     * @Description This method checks if the vaccination record vaccine's type is the same as the input.
     * @param typeVaccine
     * @return Boolean if the vaccination record vaccine's type is the same as the input.
     */
    public boolean checkTypeVaccine(TypeVaccine typeVaccine){
        if(typeVaccine.getCode().equalsIgnoreCase(vaccine.getTypeVaccine().getCode())){
            return true;
        }
        return false;
    }

    public int getNumberDosesTaken() {
        return numberDosesTaken;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }
}
