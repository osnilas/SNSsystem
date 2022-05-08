package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineAdministration;

import java.util.List;

public class VaccineAdministrationController {
    private Company company = App.getInstance().getCompany();

    private VaccineAdministration vaxAdm;

    public boolean createVaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Double> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
        this.vaxAdm = company.createVaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval);
        return company.validateVaccineAdministration(vaxAdm);
    }

    public boolean saveVaccineAdministration () {
       return company.saveVaccineAdministration(vaxAdm);
    }

    public void printVaccineAdministration () {
        company.printVaccineAdministration(vaxAdm);
    }

}
