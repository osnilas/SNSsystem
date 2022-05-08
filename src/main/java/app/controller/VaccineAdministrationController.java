package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineAdministration;

import java.util.List;


/**
 * @author Pedro Nogueira
 * Controller of vaccine administration of US013
 */
public class VaccineAdministrationController {
    private Company company = App.getInstance().getCompany();

    private VaccineAdministration vaxAdm;

    /**
     * @param brand           The vaccine's brand
     * @param minAge          The minimum age for that vaccine
     * @param maxAge          The maximum age for that vaccine
     * @param dosage          The quantity of vaccine in each dose
     * @param doses           The number of doses
     * @param vaccineInterval The number of days in between each dose
     * @author Pedro Nogueira
     * asks company to create a vaccine admnistration
     */
    public boolean createVaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Double> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
        this.vaxAdm = company.createVaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval);
        return company.validateVaccineAdministration(vaxAdm);
    }

    /**
     * @author Pedro Nogueira
     * Saves a vaccine administration in company
     * @return boolean of if the save was sucessful or not
     */
    public boolean saveVaccineAdministration () {
       return company.saveVaccineAdministration(vaxAdm);
    }


    /**
     * @author Pedro Nogueira
     * Prints vaccine administration
     */
    public void printVaccineAdministration () {
        company.printVaccineAdministration(vaxAdm);
    }

}
