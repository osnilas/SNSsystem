package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSuser;

public class CheckVaccineAppointmentController {
    private Company company = App.getInstance().getCompany();

    private int snsUserIndex;
    private SNSuser snSuser;
    public boolean SNSuserExists (int number) {
        if (company.SNSuserExistsNumber(number) != null) {
            this.snSuser = company.SNSuserExistsNumber(number);
            return true;
        }
        return false;
    }

    public boolean checkAppointment (int index, SNSuser snSuser) {
        return company.checkAppointment(index, snSuser);
    }

    public SNSuser getSnSuser() {
        return snSuser;
    }

    public boolean checkAppointmentTime(int index, SNSuser snSuser) {
        snsUserIndex = company.getSnsUserAppointmentIndex(index, snSuser);
        return company.checkAppointmentTime(index, snsUserIndex);
    }
}
