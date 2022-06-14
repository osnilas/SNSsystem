package algorithms.sort;

import app.domain.model.VaccinationAdminstrationRecord;
import app.ui.console.utils.Sort;

import java.util.List;

public class QuickSort implements Sort {
    @Override
    public void sortByArrivalTime(List<VaccinationAdminstrationRecord> list) {

    }


    public void sortByLeavingTime(List<VaccinationAdminstrationRecord> list,int left,int right) {
        if (left < right){
            VaccinationAdminstrationRecord pivot = list.get(right);
            int pos = left - 1;
            for (int i = left; i < right; i++)
                if (list.get(i).getLeavingDateTime().isBefore(pivot.getLeavingDateTime()))
                    Collections.swap(list, ++pos, i);
            Collections.swap(list, pos + 1, right);
            sortByLeavingTime(list, left, pos);
            sortByLeavingTime(list, pos + 1, right);
        }

    }
}
