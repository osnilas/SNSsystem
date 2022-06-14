package app.ui.console.utils;

import app.domain.model.VaccinationAdminstrationRecord;

import java.util.List;

public interface Sort {

    public void sortByArrivalTime(List<VaccinationAdminstrationRecord> list);
    public void sortByLeavingTime(List<VaccinationAdminstrationRecord> list);
}
