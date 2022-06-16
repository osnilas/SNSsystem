package app.ui.gui.controller;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Coordinator;
import app.domain.model.FullyVaccinatedPerDay;
import app.domain.model.VaccinationFacility;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckAndExportController {
    private Company company = App.getInstance().getCompany();
    private List<FullyVaccinatedPerDay> fullyVaccinatedPerDayListFromTo = new ArrayList<>();
    private VaccinationFacility facility;

    private List<String> fullyVaccinatedPerDayListFromToString;
    private List<FullyVaccinatedPerDay> fullyVaccinatedPerDayList = new ArrayList<>();

    public List<String> getFullyVaccinatedListFromTo (LocalDate fromDate, LocalDate toDate) {
        setFacility();
        fullyVaccinatedPerDayList = facility.getFullyVaccinatedPerDayList();
        for (int i = 0; i < fullyVaccinatedPerDayList.size(); i++) {
            if ((getFullyVaccinatedDay(i).isEqual(fromDate) || getFullyVaccinatedDay(i).isAfter(fromDate)) && (getFullyVaccinatedDay(i).isBefore(toDate) || getFullyVaccinatedDay(i).isEqual(toDate))) {
                fullyVaccinatedPerDayListFromTo.add(fullyVaccinatedPerDayList.get(i));
            }
        }
        return getFullyVaccinatedPerDayListFromToString();
    }

    public List<String> getFullyVaccinatedPerDayListFromToString () {
        fullyVaccinatedPerDayListFromToString = new ArrayList<>();
        for (int i = 0 ; i < fullyVaccinatedPerDayList.size() ; i++) {
            fullyVaccinatedPerDayListFromToString.add(fullyVaccinatedPerDayList.get(i).toString());
        }
        return fullyVaccinatedPerDayListFromToString;
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

    public boolean save(File exportFile) {
        return new FullyVaccinatedPerDay().save(exportFile, fullyVaccinatedPerDayListFromTo);
    }
}
