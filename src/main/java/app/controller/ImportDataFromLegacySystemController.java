package app.controller;

import app.domain.model.Company;

public class ImportDataFromLegacySystemController {


    private Company company;
    private App app;



    public ImportDataFromLegacySystemController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }


}
