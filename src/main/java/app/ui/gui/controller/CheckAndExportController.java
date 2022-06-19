package app.ui.gui.controller;

import app.controller.App;
import app.domain.model.*;

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


    /**
     * @author Pedro Nogueira
     * This method gets the list of fully vaccinated per day from the vaccination facility  and saves it in a list of strings
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<String> getFullyVaccinatedListFromTo(LocalDate fromDate, LocalDate toDate) {
        fullyVaccinatedPerDayList = facility.getFullyVaccinatedPerDayList();
        for (int i = 0; i < fullyVaccinatedPerDayList.size(); i++) {
            if ((getFullyVaccinatedDay(i).isEqual(fromDate) || getFullyVaccinatedDay(i).isAfter(fromDate)) && (getFullyVaccinatedDay(i).isBefore(toDate) || getFullyVaccinatedDay(i).isEqual(toDate))) {
                fullyVaccinatedPerDayListFromTo.add(fullyVaccinatedPerDayList.get(i));
            }
        }
        return getFullyVaccinatedPerDayListFromToString();
    }


    private List<String> getFullyVaccinatedPerDayListFromToString() {
        fullyVaccinatedPerDayListFromToString = new ArrayList<>();
        for (int i = 0; i < fullyVaccinatedPerDayListFromTo.size(); i++) {
            fullyVaccinatedPerDayListFromToString.add(fullyVaccinatedPerDayListFromTo.get(i).toString());
        }
        return fullyVaccinatedPerDayListFromToString;
    }

    public LocalDate getFullyVaccinatedDay(int i) {
        return fullyVaccinatedPerDayList.get(i).getDay();
    }

    /**
     * this method sets the vaccination facility as the one in the coordinator
     */
    public void setFacility() {
        Coordinator coordinator = company.getCoordinatorFacility(company.getAuthFacade().getCurrentUserSession().getUserId().getEmail());
        List<VaccinationFacility> facilities = Company.getVaccinationFacilityList();
        for (int i = 0; i < facilities.size(); i++) {
            if (coordinator.FacilitySame(facilities.get(i))) {
                this.facility = facilities.get(i);
            }
        }
    }

    /**
     * @author Pedro Nogueira
     * To be used in tests
     * @param facility
     */
    public void setFacilityTester(VaccinationFacility facility) {
        this.facility = facility;
    }

    /**
     * This method is used to export the data to a file.
     * @param exportFile
     * @return
     */
    public boolean save(File exportFile) {
        StringBuilder sb = new StringBuilder();
        sb.append("Date;Fully Vaccinated People\n");
        for (int i = 0; i < fullyVaccinatedPerDayListFromTo.size() / 2; i++) {
            sb.append(fullyVaccinatedPerDayListFromTo.get(i).getDay() + ";" + fullyVaccinatedPerDayListFromTo.get(i).getCount() + "\n");
        }

        return new FullyVaccinatedPerDay().save(exportFile.getAbsolutePath(), sb);
    }

    public void clear() {
        fullyVaccinatedPerDayListFromTo.clear();


    }
}
