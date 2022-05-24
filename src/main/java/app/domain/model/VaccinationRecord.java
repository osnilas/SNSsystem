package app.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class VaccinationRecord {

    private Vaccine vaccine;
    private LocalDateTime date;
    private int numberDosesTaken;


    public VaccinationRecord(Vaccine vaccine, LocalDateTime date,int numberDose){

        this.vaccine=vaccine;
        this.date=date;
        this.numberDosesTaken =numberDose;
    }

    public int getAgeGroup(int age){
        int indexNumber=-1;
        for(int i=0;i<vaccine.getVaccineAdministration().getMaxAge().size();i++) {
            if (age >= vaccine.getVaccineAdministration().getMinAge().get(i) && age <= vaccine.getVaccineAdministration().getMaxAge().get(i)) {
                indexNumber = i;
            }
        }
        return indexNumber;
    }
    public boolean checkTypeVaccine(TypeVaccine typeVaccine){
        if (Objects.equals(vaccine.getTypeVaccine(),typeVaccine)){
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
