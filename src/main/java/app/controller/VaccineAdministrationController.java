package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineAdministration;
import app.ui.console.VaccineAdministrationUI;


import java.util.List;


/**
 * @author Pedro Nogueira
 * Controller of vaccine administration of US013
 */
public class VaccineAdministrationController {
    private Company company = App.getInstance().getCompany();

    private VaccineAdministration vaccineAdministration;

    private VaccineAdministrationUI vaccineAdministrationUI;

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
        this.vaccineAdministration = company.createVaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval);
        return company.validateVaccineAdministration(vaccineAdministration);
    }

    /**
     * @author Pedro Nogueira
     * Saves a vaccine administration in company
     * @return boolean of if the save was successful or not
     */
    public boolean saveVaccineAdministration () {
       return company.saveVaccineAdministration(vaccineAdministration);
    }

}
