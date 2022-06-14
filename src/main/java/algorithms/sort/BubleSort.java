package algorithms.sort;

import app.domain.model.VaccinationAdminstrationRecord;

import java.util.Collections;
import java.util.List;

public class BubleSort {
    public static void sortByArrivalTime(List<VaccinationAdminstrationRecord> list) {
        int aux;
        boolean flag;

        for (int i = 0; i < list.size() - 1; i++) {

            flag = false;

            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getArrivalTime().isAfter(list.get(j + 1).getArrivalTime())) {
                    Collections.swap(list, j, j + 1);
                    flag = true;
                }

            }
            if (!flag)
                break;

        }
    }

    public static void sortByLeavingTime(List<VaccinationAdminstrationRecord> list) {
        int aux;
        boolean flag;

        for (int i = 0; i < list.size()- 1; i++) {

            flag = false;

            for (int j = 0; j < list.size()- i - 1; j++) {
                if (list.get(j).getLeavingDateTime().isAfter(list.get(j + 1).getLeavingDateTime())) {
                    Collections.swap(list, j, j + 1);
                    flag = true;
                }

            }
            if (!flag)
                break;

        }
    }

}
