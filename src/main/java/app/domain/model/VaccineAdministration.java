package app.domain.model;

import java.util.List;

public class VaccineAdministration {

    private String brand;
    private List<Integer> minAge;

    private List<Integer> maxAge;

    private List<Float> dosage;

    private List<Integer> doses;

    private List<Integer> vaccineInterval;


    public VaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Float> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
        this.brand = brand;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.dosage = dosage;
        this.doses = doses;
        this.vaccineInterval = vaccineInterval;
    }


    public String getBrand() { return brand; }

    public List<Integer> getMinAge() {
        return minAge;
    }

    public List<Integer> getMaxAge() { return maxAge; }

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
        return "VaccineAdministration{" +
                "brand: " + brand + '\'' +
                ", minAge: " + minAge +
                ", maxAge: " + maxAge +
                ", dosage: " + dosage +
                ", doses: " + doses +
                ", vaccineInterval: " + vaccineInterval +
                '}';
    }
}
