package app.controller;

import app.domain.Store.FullyVaccinatedPerDayStore;

import java.time.LocalDate;
import java.util.List;

public class CheckAndExportController {
    private FullyVaccinatedPerDayStore fullyVaccinatedPerDayStore;

    public void getFullyVaccinatedListFromTo (LocalDate fromDate, LocalDate toDate) {
        for (int i = 0; i < fullyVaccinatedPerDayStore.getFullyVaccinatedPerDayList().size(); i++) {
            if (validateDate(i, "after", fromDate) && validateDate(i, "before", toDate)) {

            }
        }

    }


    public boolean validateDate (int i, String type, LocalDate referenceDate) {
        if (type.equals("after")) {
            return getFullyVaccinatedDay(i).isAfter(referenceDate) || getFullyVaccinatedDay(i).isEqual(referenceDate);
        } else if (type.equals("before")) {
            return getFullyVaccinatedDay(i).isBefore(referenceDate) || getFullyVaccinatedDay(i).isEqual(referenceDate);
        }
        return false;
    }

    public LocalDate getFullyVaccinatedDay (int i) {
        return fullyVaccinatedPerDayStore.getFullyVaccinatedPerDayList().get(i).getDay();
    }

}
