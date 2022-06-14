package algorithms.sort;

import app.domain.model.VaccinationAdminstrationRecord;
import app.ui.console.utils.Sort;

import java.util.Collections;
import java.util.List;

public class BubleSort implements Sort {
    @Override
    public void sortByArrivalTime(List<VaccinationAdminstrationRecord> list) {
        int aux;
        boolean flag;

        for (int i = 0; i < list.size() - 1; i++) {

            flag = false;

            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getArrivalTime().isBefore(list.get(j + 1).getArrivalTime())) {
                    Collections.swap(list, j, j + 1);
                    flag = true;
                }

            }
            if (!flag)
                break;

        }
    }

    @Override
    public void sortByLeavingTime(List<VaccinationAdminstrationRecord> list) {
        int aux;
        boolean flag;

        for (int i = 0; i < list.size() - 1; i++) {

            flag = false;

            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getLeavingDateTime().isBefore(list.get(j + 1).getLeavingDateTime())) {
                    Collections.swap(list, j, j + 1);
                    flag = true;
                }

            }
            if (!flag)
                break;

        }
    }

}
