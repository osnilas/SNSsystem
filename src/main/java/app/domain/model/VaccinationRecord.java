package app.domain.model;

import java.util.List;

public class VaccinationRecord {

    private int dosesTaken;
    private Vaccine vaccine;
    private static List<VaccinationSchedule> record;

    private static int DOSES_TAKEN_DEFAULT=0;

    public VaccinationRecord(Vaccine vaccine){
        this.vaccine=vaccine;
        this.dosesTaken=DOSES_TAKEN_DEFAULT;
    }

    public void addVaccinationSchedule(VaccinationSchedule schedule){
        record.add(schedule);
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

    public void addVaccination(){
        dosesTaken++;
    }
}
