package algorithms.sort;

import app.domain.model.LegacySystemData;
import app.domain.model.VaccinationAdminstrationRecord;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.Random;
import java.util.Collections;
import java.util.List;

public class QuickSort {



    static int partitionArrival(List<LegacySystemData> list, int low, int high)
    {

        // pivot
        LegacySystemData pivot;
         pivot = list.get(high);

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            // If current element is smaller
            // than the pivot
            if (list.get(j).getArrivalTime().isBefore(pivot.getArrivalTime()))
            {

                // Increment index of
                // smaller element
                i++;
                Collections.swap(list,i,j);
            }
        }
        Collections.swap(list, i + 1, high);
        return (i + 1);
    }

    static int partitionLeaving(List<LegacySystemData> list, int low, int high)
    {

        // pivot
        LegacySystemData pivot;
        pivot = list.get(high);

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            // If current element is smaller
            // than the pivot
            if (list.get(j).getLeavingDateTime().isBefore(pivot.getLeavingDateTime()))
            {

                // Increment index of
                // smaller element
                i++;
                Collections.swap(list,i,j);
            }
        }
        Collections.swap(list, i + 1, high);
        return (i + 1);
    }

    /* The main function that implements QuickSort
              arr[] --> Array to be sorted,
              low --> Starting index,
              high --> Ending index
     */
    public static void quickSort(List<LegacySystemData> list, int low, int high)
    {
        if (low < high)
        {
            int pi=-5;
            String sortBy = Utils.ReadProppeties("Import.View");
            switch (sortBy) {
                case"Arrival":
                    pi = partitionArrival(list, low, high);
                break;
                case "Leaving":
                     pi = partitionLeaving(list, low, high);
                     break;
                default:
            }
            // Separately sort elements before
            // partition and after partition
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }




}
