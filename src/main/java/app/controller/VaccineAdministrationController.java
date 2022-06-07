package app.controller;

import app.domain.model.Company;
import app.domain.model.TypeVaccine;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Pedro Nogueira
 * Controller of vaccine administration of US013
 */
public class VaccineAdministrationController {
    private Company company = App.getInstance().getCompany();

    private VaccineAdministration vaccineAdministration;

    private Vaccine vaccine;


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
    public boolean createVaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Integer> dosage, List<Integer> doses, ArrayList<ArrayList<Integer>> vaccineInterval, TypeVaccine typeVaccine) {
        this.vaccineAdministration = company.createVaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval);
        vaccine = company.createVaccine(vaccineAdministration, typeVaccine);
        if (company.validateVaccineAdministration(vaccineAdministration)) {
            return company.validateVaccine(vaccine);
        }
        return false;
    }

    /**
     * @author Pedro Nogueira
     * Saves a vaccine administration in company
     * @return boolean of if the save was successful or not
     */
    public boolean saveVaccineAdministration () {
        company.saveVaccine(vaccine);
       return company.saveVaccineAdministration(vaccineAdministration);
    }
    public void save(){
        company.saveVaccineAdministrationListFile();
    }
}
