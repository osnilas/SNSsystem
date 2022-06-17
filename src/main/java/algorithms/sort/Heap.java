package algorithms.sort;

import app.domain.model.LegacySystemData;

import java.util.Collections;
import java.util.List;

public class Heap {


    public static void sort(List<LegacySystemData> arr, int flag) {
        int n = arr.size();

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            switch (flag) {
                case 1:
                    heapifyLeaving(arr, i, 0);
                    break;
                case 2:
                    heapifyArrival(arr, i, 0);
                    break;
            }
        }

        // Heap sort
        for (int i = n - 1; i >= 0; i--) {
            LegacySystemData temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);

            // Heapify root element
            switch (flag) {
                case 1:
                    heapifyLeaving(arr, i, 0);
                    break;
                case 2:
                    heapifyArrival(arr, i, 0);
                    break;
            }
        }
    }

    static void heapifyLeaving(List<LegacySystemData> arr, int n, int i) {
        // Find largest among root, left child and right child
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr.get(l).getLeavingDateTime().isAfter(arr.get(largest).getLeavingDateTime())) {
            largest = l;

            if (r < n && arr.get(r).getLeavingDateTime().isAfter(arr.get(largest).getLeavingDateTime()))
                largest = r;

            // Swap and continue heapifying if root is not largest
            if (largest != i) {
                Collections.swap(arr, i, largest);
                heapifyLeaving(arr, n, largest);
            }
        }
    }

        static void heapifyArrival (List < LegacySystemData > arr,int n, int i){
            // Find largest among root, left child and right child
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;

            if (l < n && arr.get(l).getLeavingDateTime().isAfter(arr.get(largest).getLeavingDateTime())) {
                largest = l;

                if (r < n && arr.get(r).getLeavingDateTime().isAfter(arr.get(largest).getLeavingDateTime()))
                    largest = r;

                // Swap and continue heapifying if root is not largest
                if (largest != i) {
                    Collections.swap(arr, i, largest);
                    heapifyArrival(arr, n, largest);
                }
            }
        }
    }

