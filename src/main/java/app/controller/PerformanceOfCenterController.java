package app.controller;

import app.domain.model.Arrival;
import app.domain.model.Exit;
import app.domain.shared.Constants;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class PerformanceOfCenterController {

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
