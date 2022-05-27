package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationFacility;

import java.util.ArrayList;
import java.util.List;

public class SelectVaccinationFacilityController {

    private Company company;

    private App app;

    private VaccinationFacility facility;

    public SelectVaccinationFacilityController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public SelectVaccinationFacilityController(Company company) {
        this.company = company;
    }

    public List<String> getVaccinationFacilities(){
        List<VaccinationFacility> vaccinationFacilityList=this.company.getVaccinationFacilityList();
        List<String>vaccinationFacilityNameList=new ArrayList<>();
        for(int i=0;i<vaccinationFacilityList.size();i++){
            vaccinationFacilityNameList.add(vaccinationFacilityList.get(i).getName());
        }
        return vaccinationFacilityNameList;
    }

    public void setVaccinationFacilty(int index){
        this.facility=company.getVaccinationFacilityFromList(index);
    }
}
