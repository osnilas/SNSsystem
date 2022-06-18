package algorithms.sort;

import app.domain.model.LegacySystemData;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {



    static void mergeByArrival(List <LegacySystemData> legacySystemDataList, int left, int middle, int right)
    {
        // Find sizes of two sublists to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        /* Create temp lists */
        List <LegacySystemData> Left=new ArrayList<>();
        List <LegacySystemData> Right=new ArrayList<>();

        /*Copy data to temp lists*/
        for (int i = 0; i < n1; ++i)
            Left.add(legacySystemDataList.get(left+i));

        for (int j = 0; j < n2; ++j)
            Right.add(legacySystemDataList.get(middle+1+j));

        /* Merge the temp lists */

        // Initial indexes of first and second sublists
        int i = 0, j = 0;

        // Initial index of merged sublists list
        int k = left;
        while (i < n1 && j < n2) {
            if ((Left.get(i).getArrivalTime().isBefore(Right.get(j).getArrivalTime())) || (Left.get(i).getArrivalTime().isEqual(Right.get(j).getArrivalTime()))){
                legacySystemDataList.set(k,Left.get(i));
                i++;
            }
            else {
                legacySystemDataList.set(k,Right.get(j));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of Left if any */
        while (i < n1) {
            legacySystemDataList.set(k,Left.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of Right if any */
        while (j < n2) {
            legacySystemDataList.set(k,Right.get(j));
            j++;
            k++;
        }
    }

    static void mergeByLeaving(List <LegacySystemData> legacySystemDataList, int left, int middle, int right)
    {
        // Find sizes of two sublists to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        /* Create temp lists */
        List <LegacySystemData> Left=new ArrayList<>();
        List <LegacySystemData> Right=new ArrayList<>();

        /*Copy data to temp lists*/
        for (int i = 0; i < n1; ++i)
            Left.add(legacySystemDataList.get(left+i));

        for (int j = 0; j < n2; ++j)
            Right.add(legacySystemDataList.get(middle+1+j));

        /* Merge the temp lists */

        // Initial indexes of first and second sublists
        int i = 0, j = 0;

        // Initial index of merged sublist list
        int k = left;
        while (i < n1 && j < n2) {
            if ((Left.get(i).getLeavingDateTime().isBefore(Right.get(j).getLeavingDateTime())) || (Left.get(i).getLeavingDateTime().isEqual(Right.get(j).getLeavingDateTime()))){
                legacySystemDataList.set(k,Left.get(i));
                i++;
            }
            else {
                legacySystemDataList.set(k,Right.get(j));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of Left if any */
        while (i < n1) {
            legacySystemDataList.set(k,Left.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of Right if any */
        while (j < n2) {
            legacySystemDataList.set(k,Right.get(j));
            j++;
            k++;
        }
    }

    // Main function that sorts List using
    // merge()
    public static void sort(List<LegacySystemData> list, int left, int right,int sortBy)
    {
        if (left < right) {
            // Find the middle point
            int middle =left+ (right-left)/2;

            // Sort first and second halves
            sort(list, left, middle,sortBy);
            sort(list, middle + 1, right,sortBy);

            // Merge the sorted halves
            switch (sortBy) {
                case 1:
                    mergeByArrival(list, left, middle, right);
                    break;
                case 2:
                    mergeByLeaving(list, left, middle, right);
                    break;
                default:
            }
        }
    }




}
