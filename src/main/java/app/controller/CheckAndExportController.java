package app.controller;

import app.domain.model.Company;
import app.domain.model.Coordinator;
import app.domain.model.FullyVaccinatedPerDay;
import app.domain.model.VaccinationFacility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckAndExportController {
    private Company company = App.getInstance().getCompany();
    private List<FullyVaccinatedPerDay> fullyVaccinatedPerDayList;
    private List<FullyVaccinatedPerDay> fullyVaccinatedPerDayListFromTo = new ArrayList<>();
    private VaccinationFacility facility;


    public List<FullyVaccinatedPerDay> getFullyVaccinatedListFromTo (LocalDate fromDate, LocalDate toDate) {
        setFacility();
        fullyVaccinatedPerDayList = facility.getFullyVaccinatedPerDayList();
        for (int i = 0; i < fullyVaccinatedPerDayList.size(); i++) {
            if ((getFullyVaccinatedDay(i).isEqual(fromDate) || getFullyVaccinatedDay(i).isAfter(fromDate)) && (getFullyVaccinatedDay(i).isBefore(toDate) || getFullyVaccinatedDay(i).isEqual(toDate))) {
                fullyVaccinatedPerDayListFromTo.add(fullyVaccinatedPerDayList.get(i));
            }
        }
        return fullyVaccinatedPerDayListFromTo;
    }


    public LocalDate getFullyVaccinatedDay (int i) {
        return fullyVaccinatedPerDayList.get(i).getDay();
    }

    public void setFacility() {
        Coordinator coordinator = company.getCoordinatorFacility(company.getAuthFacade().getCurrentUserSession().getUserId().getEmail());
        List<VaccinationFacility> facilities = Company.getVaccinationFacilityList();
        for (int i = 0; i < facilities.size(); i++) {
            if (coordinator.FacilitySame(facilities.get(i))) {
                this.facility = facilities.get(i);
            }
        }
    }

}
