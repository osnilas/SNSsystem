package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;

import java.time.LocalDateTime;

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

    public boolean createVaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalDateTime openingHours,LocalDateTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot, String typeOfVaccine) {
        this.vc = this.company.createVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours,closingHours, slotDuration, maximumNumberOfVaccinesPerSlot, typeOfVaccine);
        return this.company.validateVaccinationCenter(vc);
    }

    public boolean saveVaccinationCenter() {
        return this.company.saveVaccinationCenter(vc);
    }


    public void printVaccinationCenter() {
        this.company.printVaccinationCenter(vc);
    }

}

