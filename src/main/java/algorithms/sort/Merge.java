package algorithms.sort;

import app.domain.model.LegacySystemData;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Merge {

    static void mergeByArrival(List <LegacySystemData> arr, int left, int middle, int right)
    {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        /* Create temp arrays */
        List <LegacySystemData> L=new ArrayList<>();
        List <LegacySystemData> R=new ArrayList<>();

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L.add(arr.get(left+i));

        for (int j = 0; j < n2; ++j)
            R.add(arr.get(middle+1+j));

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = left;
        while (i < n1 && j < n2) {
            if ((L.get(i).getArrivalTime().isBefore(R.get(j).getArrivalTime())) || (L.get(i).getArrivalTime().isEqual(R.get(j).getArrivalTime()))){
                arr.set(k,L.get(i));
                i++;
            }
            else {
                arr.set(k,R.get(j));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr.set(k,L.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr.set(k,R.get(j));
            j++;
            k++;
        }
    }

    static void mergeByLeaving(List <LegacySystemData> arr, int left, int middle, int right)
    {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        /* Create temp arrays */
        List <LegacySystemData> L=new ArrayList<>();
        List <LegacySystemData> R=new ArrayList<>();

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L.add(arr.get(left+i));

        for (int j = 0; j < n2; ++j)
            R.add(arr.get(middle+1+j));

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = left;
        while (i < n1 && j < n2) {
            if ((L.get(i).getLeavingDateTime().isBefore(R.get(j).getLeavingDateTime())) || (L.get(i).getLeavingDateTime().isEqual(R.get(j).getLeavingDateTime()))){
                arr.set(k,L.get(i));
                i++;
            }
            else {
                arr.set(k,R.get(j));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr.set(k,L.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr.set(k,R.get(j));
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(List<LegacySystemData> list, int left, int right)
    {
        if (left < right) {
            // Find the middle point
            int middle =left+ (right-left)/2;

            // Sort first and second halves
            sort(list, left, middle);
            sort(list, middle + 1, right);

            // Merge the sorted halves
            String sortBy = Utils.ReadProppeties("Import.View");
            switch (sortBy) {
                case "Arrival":
                    mergeByArrival(list, left, middle, right);
                    break;
                case "Leaving":
                    mergeByLeaving(list, left, middle, right);
                    break;
                default:
            }
        }
    }

}
