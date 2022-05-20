package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class VaccinationRecord {

    private Vaccine vaccine;
    private static List<VaccinationSchedule> record;


    public VaccinationRecord(){
        this.record=new ArrayList<>();
    }

    public void addVaccinationSchedule(VaccinationSchedule schedule){
        this.record.add(schedule);
    }

    public void addVaccine(Vaccine vaccine){
        this.vaccine=vaccine;
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

    public Vaccine getVaccine() {
        return vaccine;
    }
}
