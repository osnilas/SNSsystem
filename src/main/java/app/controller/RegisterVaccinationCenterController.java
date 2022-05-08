package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;

public class RegisterVaccinationCenterController {

    private Company company;

    private VaccinationCenter vc;

    private App app;

    public RegisterVaccinationCenterController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public RegisterVaccinationCenterController(Company company) {
        this.company = company;
        this.vc = vc;
    }

  /* public boolean createVaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, String openingAndClosingHours, int slotDuration, int maximumNumberOfVacinesPerSlot, String typeOfVaccine) {
        this.vc = this.company.createVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingAndClosingHours, slotDuration, maximumNumberOfVacinesPerSlot, typeOfVaccine);
        return this.company.validateVaccinationCenter(vc);
    }

    public boolean saveVaccinationCenter() {
        return this.company.saveVaccinationCenter(vc);
    }


    public void printVaccinationCenter() {
        this.company.printVaccinationCenter(vc);
    } */

}
