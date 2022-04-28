package app.domain.model;

import java.util.List;

public class VaccineAdministration {

    private List<Integer> age;

    private List<Float> dosage;

    private List<Integer> doses;

    private List<Integer> vaccineInterval;


    public VaccineAdministration(List<Integer> age, List<Float> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
        this.age = age;
        this.dosage = dosage;
        this.doses = doses;
        this.vaccineInterval = vaccineInterval;
    }

    public List<Integer> getAge() {
        return age;
    }

    public List<Float> getDosage() {
        return dosage;
    }

    public List<Integer> getDoses() {
        return doses;
    }

    public List<Integer> getVaccineInterval() {
        return vaccineInterval;
    }

    @Override
    public String toString() {
        return "SpecifyVaccineAdministration{" +
                "age=" + age +
                ", dosage=" + dosage +
                ", doses=" + doses +
                ", vaccineInterval=" + vaccineInterval +
                '}';
    }
}
