package app.controller;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class CheckVaccineAppointmentController {
    private final Company company = App.getInstance().getCompany();
    private SNSuser snSuser;
    private Arrival arrival;
    private int index;
    private int snsUserIndex;


    public boolean SNSuserExists(int number) {
        if (company.SNSuserExistsNumber(number) != null) {
            this.snSuser = company.SNSuserExistsNumber(number);
            this.snsUserIndex = company.getSnsUserAppointmentIndex(index, snSuser);
            return true;
        }
        return false;
    }

    public boolean checkAppointment() {
        return company.checkAppointment(index, snSuser);
    }

    public boolean checkAppointmentDay() {
        return company.checkAppointmentDay(index, snsUserIndex);
    }

    public boolean requestSnsUserArrivalRegistration() {
        this.arrival = company.createArrival(snSuser);
        return company.validateArrival(arrival);
    }

    public boolean requestToSaveSnsUserArrival() {
        return company.saveSNSuserArrival(index, arrival);
    }

    public List<String> getVaccinationFacilities() {
        List<VaccinationFacility> vaccinationFacilityList = Company.getVaccinationFacilityList();
        List<String> vaccinationFacilityNameList = new ArrayList<>();
        for (int i = 0; i < vaccinationFacilityList.size(); i++) {
            vaccinationFacilityNameList.add(vaccinationFacilityList.get(i).getName());
        }
        return vaccinationFacilityNameList;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public VaccinationAppointment getSnsUserAppointment() {
        return company.getSnsUserAppointment(index, snsUserIndex);
    }

    public Arrival getArrival() {
        return arrival;
    }
}
