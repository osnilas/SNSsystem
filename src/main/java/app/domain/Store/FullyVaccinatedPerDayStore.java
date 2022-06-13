package app.domain.Store;

import app.domain.model.FullyVaccinatedPerDay;
import app.domain.model.VaccinationFacility;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FullyVaccinatedPerDayStore implements Serializable {
    private List<FullyVaccinatedPerDay> fullyVaccinatedPerDayList = new ArrayList<>();

    public void updateFullyVaccinatedPerDay (VaccinationFacility facility) {
        boolean flag = false;
        for (int i = 0; i < facility.getFullyVaccinatedPerDayList().size(); i++) {
            if (facility.getFullyVaccinatedPerDayList().get(i).checkIfSameDay(LocalDate.now())) {
                flag = true;
                facility.getFullyVaccinatedPerDayList().get(i).updateTotalNumberOfFullyVaccinated();
            }
        }
        if (!flag) {
            fullyVaccinatedPerDayList.add(new FullyVaccinatedPerDay());
        }
    }

    public List<FullyVaccinatedPerDay> getFullyVaccinatedPerDayList () {
        return fullyVaccinatedPerDayList;
    }
 }
