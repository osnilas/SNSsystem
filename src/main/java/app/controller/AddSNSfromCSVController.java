package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSuser;
import mappers.dto.dtoSNSuser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddSNSfromCSVController {

    private Company company;

    private SNSuser us;
    private App app;



    public AddSNSfromCSVController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public AddSNSfromCSVController(Company company) {
        this.company = company;
    }

    public boolean createSNSuser(dtoSNSuser dto) {
        this.us = this.company.createSNSuser(dto);
        return this.company.validateSNSuser(us);
    }



    public boolean saveSNSuser(dtoSNSuser dto) {
        return this.company.saveSNSuser(dto);
    }

    public void printSNSuser() {
            this.company.printSNSuser(us);
    }



}
