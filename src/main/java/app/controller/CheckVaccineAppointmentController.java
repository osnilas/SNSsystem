package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSuser;

public class CheckVaccineAppointmentController {

    private Company company;

    public SNSuser SNSuserExists (int number) {
        return company.SNSuserExists(number);
    }

    public boolean checkAppointment (int index, SNSuser snSuser) {
        return company.checkAppointment(index, snSuser);
    }

}
