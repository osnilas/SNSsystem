package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VaccineCard implements Serializable {

    private Vaccine vaccine;
    private LocalDateTime date;
    private int numberDosesTaken;


    public VaccineCard(Vaccine vaccine, LocalDateTime date, int numberDose){

        this.vaccine=vaccine;
        this.date=date;
        this.numberDosesTaken =numberDose;
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

    public void updateNumberDosesTaken(){
        this.date=LocalDateTime.now();
        numberDosesTaken++;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof VaccineCard)) {
            return false;
        }
        VaccineCard vaccineCard = (VaccineCard) o;
        return this.vaccine.equals(vaccineCard.vaccine) && this.date.equals(vaccineCard.date)
                && this.numberDosesTaken == vaccineCard.numberDosesTaken;
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
