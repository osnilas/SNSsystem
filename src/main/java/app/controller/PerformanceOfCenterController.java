package app.controller;

import algorithms.performance.BruteForce;
import app.domain.model.*;
import app.domain.shared.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PerformanceOfCenterController {

    private VaccinationFacility facility;
    private List<LocalDateTime> timeSlots = new ArrayList<>();

    private int[] numberUsersAtFacility;
    private List<VaccinationAdminstrationRecord> adminstrationRecordList = new ArrayList<>();
    private App app;
    private Company company;


    public PerformanceOfCenterController() {
        this.app = App.getInstance();
        this.company = App.getInstance().getCompany();
    }

    public void setFacility(VaccinationFacility facility) {
        Coordinator coordinator = company.getCoordinatorFacility(app.getCurrentUserSession().getUserId().getEmail());
        List<VaccinationFacility> facilities = company.getVaccinationFacilityList();
        for (int i = 0; i < facilities.size(); i++) {
            if (coordinator.FacilitySame(facilities.get(i))) {
                this.facility = facilities.get(i);
                this.adminstrationRecordList.addAll(facility.getVaccinationAdminstrationRecordList());
            }
        }
    }

    public void setTimeSlots(int timeIntervals) {
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), facility.getOpeningHours());
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), facility.getClosingHours());
        LocalDateTime temp = start;
        do {
            timeSlots.add(temp);
            temp = temp.plusMinutes(facility.getSlotDuration());
        } while (end.isAfter(temp));
        timeSlots.add(end);
        numberUsersAtFacility = new int[timeIntervals/Constants.TOTAL_MINUTES];
    }


    public void setNumberUsersAtFacility() {
        int countIn = 0,countOut = 0;
        for (int i = 0; i < timeSlots.size(); i++) {
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

    public List<String> getPerformanceData() throws Exception {
        List<String> performanceData = new ArrayList<>();
        List<Integer> data=performance();
        performanceData.add(String.valueOf(timeSlots.get(data.get(0))));
        performanceData.add(String.valueOf(timeSlots.get(data.get(1))));
        performanceData.add(String.valueOf(data.get(2)));
        return performanceData;
    }

    private List<Integer>  performance() throws Exception {
        BruteForce bruteForce = new BruteForce();
        bruteForce.MaxSubArray(numberUsersAtFacility);
        return bruteForce.getResults();
    }


    public int[] ArrivalsByInterval(int interval, List<Arrival> list){
        int nrOfArrivals, postion, index=0;
        int [] arrivals= new int[Constants.TOTAL_MINUTES/interval];
        for (int i=0; i<arrivals.length; i++){
            postion= convertToMinutes(Constants.OPENING_TIME_FOR_VACCINATION_FACILITY) + (i+1)*interval;
            nrOfArrivals=0;
            while (index < list.size() && convertToMinutes(splitDateFromTime(list.get(index).getTimeOfArrival())) < postion){
                nrOfArrivals++;
                index++;
            }
            arrivals[i] = nrOfArrivals;
        }
        return arrivals;
    }

    public int[] ExitsByInterval(int interval, List<Exit> list){
        int nrOfExits, postion, index=0;
        int [] exits= new int[Constants.TOTAL_MINUTES/interval];
        for (int i=0; i<exits.length; i++){
            postion= convertToMinutes(Constants.OPENING_TIME_FOR_VACCINATION_FACILITY) + (i+1)*interval;
            nrOfExits=0;
            while (index < list.size() && convertToMinutes(splitDateFromTime(list.get(index).getLeavingDateTime())) < postion){
                nrOfExits++;
                index++;
            }
            exits[i] = nrOfExits;
        }
        return exits;
    }

    public int convertToMinutes(LocalTime openingTime){
        int minutes = (openingTime.getHour() * 60) + (openingTime.getMinute());
        return minutes;
    }

    public LocalTime splitDateFromTime(LocalDateTime leavingDateTime){
        LocalTime exitTime = LocalTime.of(leavingDateTime.getHour(), leavingDateTime.getMinute());
        return exitTime;
    }

}
