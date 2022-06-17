package app.ui.gui.controller;

import algorithms.performance.BruteForce;
import algorithms.performance.Sum;
import app.controller.App;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class PerformanceOfCenterController {

    private LocalTime OpeningHours=LocalTime.of(8,0);
    private LocalTime ClosingHours=LocalTime.of(20,0);
    private List<LocalDateTime> timeSlots = new ArrayList<>();

    private  int[] numberUsersAtFacility= new int[1];
    private List<LegacySystemData> adminstrationRecordList = new ArrayList<>();
    private App app;
    private Company company;


    public PerformanceOfCenterController() {
        this.app = App.getInstance();
        this.company = App.getInstance().getCompany();
        this.adminstrationRecordList=this.company.getLegacySystemDataFromStore();
        if(adminstrationRecordList.size()==0){
            throw new IllegalArgumentException("No data to analyze");
        }
    }

    public void clear(){
        this.timeSlots.clear();
        this.numberUsersAtFacility = new int[1];
    }

    private void setTimeSlots(LocalDate date,int timeIntervals) {
        LocalDateTime start = LocalDateTime.of(date,OpeningHours);
        LocalDateTime end = LocalDateTime.of(date, ClosingHours);
        LocalDateTime temp = start;
        do {
            timeSlots.add(temp);
            temp = temp.plusMinutes(timeIntervals);
        } while (end.isAfter(temp));
        timeSlots.add(end);
        numberUsersAtFacility = new int[timeSlots.size()];
    }


    private void setNumberUsersAtFacility() {
        int countIn = 0,countOut = 0;
        for (int i = 0; i < timeSlots.size()-1; i++) {
            countIn = 0;
            countOut = 0;
            for (int j = 0; j < adminstrationRecordList.size(); j++) {
                if ((adminstrationRecordList.get(j).getArrivalTime().isAfter(timeSlots.get(i)) || adminstrationRecordList.get(j).getArrivalTime().isEqual(timeSlots.get(i)) ) && (adminstrationRecordList.get(j).getArrivalTime().isBefore(timeSlots.get(i + 1))) || adminstrationRecordList.get(j).getArrivalTime().isEqual(timeSlots.get(i)) ){
                    countIn++;
                }
                if ((adminstrationRecordList.get(j).getLeavingDateTime().isAfter(timeSlots.get(i)) || adminstrationRecordList.get(j).getLeavingDateTime().isEqual(timeSlots.get(i)) ) && (adminstrationRecordList.get(j).getLeavingDateTime().isBefore(timeSlots.get(i + 1))) || adminstrationRecordList.get(j).getLeavingDateTime().isEqual(timeSlots.get(i))){
                    countOut--;
                }
            }
            numberUsersAtFacility[i]=(countIn+ countOut);
        }
    }

    public List<String> getPerformanceData(LocalDate date,int timeInterval) throws Exception {
        setTimeSlots(date,timeInterval);
        setNumberUsersAtFacility();
        if(validateArray(numberUsersAtFacility)) {
            List<String> performanceData = new ArrayList<>();
            List<Integer> data = performance();
            performanceData.add(Arrays.toString(Arrays.copyOfRange(numberUsersAtFacility, data.get(0), data.get(1) + 1)));
            performanceData.add(String.valueOf(timeSlots.get(data.get(0)).format(Constants.DATE_TIME_FORMATTER_ALT)));
            performanceData.add(String.valueOf(timeSlots.get(data.get(1)+1).format(Constants.DATE_TIME_FORMATTER_ALT)));
            performanceData.add(String.valueOf(data.get(2)));
            return performanceData;
        }
        throw new IllegalArgumentException("No vaccination administration records found");

    }

    private List<Integer>  performance() throws Exception {
        int[] temp=numberUsersAtFacility.clone();
        BruteForce bruteForce = new BruteForce();
        Sum sum = new Sum();
        long start = System.nanoTime();
        bruteForce.MaxSubArray(numberUsersAtFacility);
        long end = System.nanoTime();
        System.out.println("Brute force time: " + (end - start)  + " nano seconds");

        start=System.nanoTime();
        sum.Max(numberUsersAtFacility);
        end=System.nanoTime();
        System.out.println("Sum time: " + (end - start) + " nano seconds");


        return bruteForce.getResults();
    }


    private boolean validateArray(int[] array) {
        int count= 0;
        for (int i=0; i<array.length; i++) {
            if (array[i] ==0) {
                count++;
            }
        }
        return count != array.length;
    }
}
