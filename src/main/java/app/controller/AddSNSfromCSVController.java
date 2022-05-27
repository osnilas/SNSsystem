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
    /**
     * @Description Asks company to create an SNS user and stores the SNS user in the controller
     * @param dto DTO of the SNS user class
     * @return boolean of the validation the SNS user
     */
    public boolean createSNSuser(dtoSNSuser dto) {
        this.us = this.company.createSNSuser(dto);
        return this.company.validateSNSuser(us);
    }
    /**
     * @author João Veiga
     * @Description Saves SNS user in company, on List<SNSuser>
     * @return boolean of if the save was sucessful or not(it's not sucessful if the SNS user already exists or
     * another SNS has the same E-mail and/or Phone Number and/or Citizen Card Number and/or SNS User Number
     */
    public boolean saveSNSuser(dtoSNSuser dto) {
        return this.company.saveSNSuser(dto);
    }
    /**
     * @author João Veiga
     * @Description Prints SNS user
     */
    public String printSNSuser() {
          return  this.company.printSNSuser(us);
    }
}
