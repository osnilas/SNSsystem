package app.controller;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Nogueira <1211613@isep.ipp.pt>
 * Controller of the appointment check of US004
 */

public class CheckVaccineAppointmentController {
    private final Company company = App.getInstance().getCompany();
    private SNSuser snSuser;
    private Arrival arrival;
    private int index;
    private int snsUserIndex;

    /**
     * @param number SNS User's number
     * @author Pedro Nogueira
     * Checks to see if the SNS User is in the system
     */
    public boolean snsUserExists(int number) {
        if (company.SNSuserExistsNumber(number) != null) {
            this.snSuser = company.SNSuserExistsNumber(number);
            this.snsUserIndex = company.getSnsUserAppointmentIndex(index, snSuser);
            return true;
        }
        return false;
    }

    /**
     * @author Pedro Nogueira
     * Checks to see if the SNS User has an appointment scheduled
     * @return boolean of whether they are (true) or not (false).
     */
    public boolean checkAppointment() {
        return company.checkAppointment(index, snSuser);
    }

    /**
     * @author Pedro Nogueira
     * Checks the SNS User's appointment day
     * @return boolean of whether the appointment day coincides with today.
     */
    public boolean checkAppointmentDay() {
        return company.checkAppointmentDay(index, snsUserIndex);
    }

    /**
     * @author Pedro Nogueira
     * Requests the registration of an SNS User's arrival from company.
     * @return boolean of arrival validation (not null)
     */
    public boolean requestSnsUserArrivalRegistration() {
        this.arrival = company.createArrival(snSuser);
        return company.validateArrival(arrival);
    }

    /**
     * @author Pedro Nogueira
     * Requests to save the arrival of an SNS User to the vaccination facility's waiting room.
     * @return boolean of the success of the operation
     */
    public boolean requestToSaveSnsUserArrival() {
        return company.saveSNSuserArrival(index, arrival);
    }

    /**
     * @author João Veiga
     * Getter for a list of the registered vaccination facilities.
     * @return a list of Strings of the vaccination facilities.
     */
    public List<String> getVaccinationFacilities() {
        List<VaccinationFacility> vaccinationFacilityList = Company.getVaccinationFacilityList();
        List<String> vaccinationFacilityNameList = new ArrayList<>();
        for (VaccinationFacility vaccinationFacility : vaccinationFacilityList) {
            vaccinationFacilityNameList.add(vaccinationFacility.getName());
        }
        return vaccinationFacilityNameList;
    }

    /**
     * @author Pedro Nogueira
     * @param index Index of the chosen vaccination facility.
     * Setter for the index.
     */
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
