package app.domain.model;

import java.util.List;

public class VaccineAdministration {

    private String brand;
    private List<Integer> minAge;

    private List<Integer> maxAge;

    private List<Double> dosage;

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

    public VaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Double> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
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

    public List<Double> getDosage() {
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
        int x = 0;
        System.out.printf("Brand: %s%n", brand);
        System.out.println();
        for (int i = 0 ; i < minAge.size() ; i++) {
            System.out.printf("Age Range: %d - %d%n", minAge.get(i), maxAge.get(i));
            System.out.printf("Dosage: %.2f ml%n", dosage.get(i));
            System.out.printf("Doses: %d%n", doses.get(i));
            if (doses.get(i) > 1) {
                for (int j = 0; j < doses.get(i) - 1; j++) {
                    x += j;
                    System.out.printf("Vaccine interval between doses %d and %d: %d days%n", j+1, j+2, vaccineInterval.get(x));
                }
                x+=1;
            }
            System.out.println();
        }
        return null;
    }
}
