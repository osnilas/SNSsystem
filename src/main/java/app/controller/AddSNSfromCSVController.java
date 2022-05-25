package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSuser;
import app.ui.console.utils.Utils;
import mappers.dto.dtoSNSuser;

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

    public String printSNSuser() {
          return  this.company.printSNSuser(us);
    }

    public void printSNSuserList(){
        List<SNSuser> list=this.company.getSNSuserList();
        for(int i=0;i<list.size();i++){
            Utils.printText(list.get(i).toString());
        }
    }



}
