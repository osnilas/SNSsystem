package app.controller;

import app.domain.model.Company;

import java.util.ArrayList;
import java.util.List;

public class ImportDataFromLegacySystemController {


    private Company company;
    private App app;



    public ImportDataFromLegacySystemController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    ArrayList<String> fileData = new ArrayList<>();

   int n = fileData .size();

    public void bubbleSort (ArrayList<String> fileData,int n,int[]arr){
        int temp;
        boolean flag;

        for (int i = 0; i < n-1; i++) {

            flag = false;

            for (int j = 0; j < n - 1; j++) {
                if (arr[j]>arr[j+1]){
                    swap(j,j+1,arr);
                    flag = true;
                }

            }
            if (!flag)
                break;

        }


    }

    public static void swap (int i, int j, int[] arr){

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    static int partition(int[] arr, int low, int high)
    {

        int pivot = arr[high];

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            if (arr[j] < pivot)
            {

                i++;
                swap(i, j,arr);
            }
        }
        swap(high,i+1,arr);
        return (i + 1);
    }

    static void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }




}
