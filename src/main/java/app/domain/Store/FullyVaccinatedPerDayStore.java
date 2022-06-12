package app.domain.Store;

import app.domain.model.FullyVaccinatedPerDay;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FullyVaccinatedPerDayStore implements Serializable {
    private List<FullyVaccinatedPerDay> fullyVaccinatedPerDayList = new ArrayList<>();

    public void updateFullyVaccinatedPerDay () {
        boolean flag = false;
        for (int i = 0; i < fullyVaccinatedPerDayList.size(); i++) {
            if (fullyVaccinatedPerDayList.get(i).checkIfSameDay(LocalDate.now())) {
                flag = true;
                fullyVaccinatedPerDayList.get(i).updateTotalNumberOfFullyVaccinated();
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
