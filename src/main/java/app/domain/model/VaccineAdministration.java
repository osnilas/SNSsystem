package app.domain.model;

import java.util.List;

public class VaccineAdministration {

    private String brand;
    private List<Integer> minAge;

    private List<Integer> maxAge;

    private List<Integer> dosage;

    private List<Integer> doses;

    private List<Integer> vaccineInterval;

    /**
     * @param brand           The vaccine's brand
     * @param minAge          The minimum age for that vaccine
     * @param maxAge          The maximum age for that vaccine
     * @param dosage          The quantity of vaccine in each dose
     * @param doses           The number of doses
     * @param vaccineInterval The number of days in between each dose
     * @author Pedro Nogueira
     * Constructor
     * Creates a vaccine admnistration
     */

    public VaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Integer> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
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

    public List<Integer> getDosage() {
        return dosage;
    }

    public List<Integer> getDoses() {
        return doses;
    }

    public List<Integer> getVaccineInterval() { return vaccineInterval; }

}
