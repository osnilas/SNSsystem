package app.controller;

import app.domain.model.Company;
import app.domain.model.TypeVaccine;


/**
 * @author Pedro Nogueira
 * Controller of vaccine administration of US013
 */
public class RegisterTypeVaccineController {
    private Company company = App.getInstance().getCompany();

    private TypeVaccine typeVaccine;

    /**
     * @param name
     * @param description
     * @param code
     * @param vaccineTechnology
     * @author Pedro Nogueira
     * asks company to create a vaccine admnistration
     */
    public boolean createTypeVaccine(String name, String description, String code, String vaccineTechnology) {
        this.typeVaccine = company.createTypeVaccine(name, description, code, vaccineTechnology);
        return company.validateTypeVaccine(typeVaccine);
    }

    /**
     * @author Pedro Nogueira
     * Saves a vaccine administration in company
     * @return boolean of if the save was successful or not
     */
    public boolean saveTypeVaccine () { return company.saveTypeVaccine(typeVaccine); }

    public void save(){
        company.saveTypeVaccinesListFile();
    }

}

