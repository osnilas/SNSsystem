package app.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class LegacySystemData {

    private int SNSuserNumber;
    private Vaccine vaccine;
    private int dose;
    private  String lotNumber;
    private LocalDateTime arrivalTime;
    private LocalDateTime ScheduledDateTime;
    private LocalDateTime  NurseAdministrationDateTime;
    private LocalDateTime LeavingDateTime;


    public LegacySystemData(int SNSuserNumber,Vaccine vaccine,int dose,String lotNumber,LocalDateTime arrivalTime,LocalDateTime ScheduledDateTime,LocalDateTime NurseAdministrationDateTime,LocalDateTime LeavingDateTime){
        this.SNSuserNumber=SNSuserNumber;
        this.vaccine=vaccine;
        this.dose=dose;
        this.lotNumber=lotNumber;
        this.arrivalTime=arrivalTime;
        this.ScheduledDateTime=ScheduledDateTime;
        this.NurseAdministrationDateTime=NurseAdministrationDateTime;
        this.LeavingDateTime=LeavingDateTime;
    }

    public int getSNSuserNumber() {
        return SNSuserNumber;
    }

    public int getDose() {
        return dose;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getLeavingDateTime() {
        return LeavingDateTime;
    }

    public LocalDateTime getNurseAdministrationDateTime() {
        return NurseAdministrationDateTime;
    }

    public LocalDateTime getScheduledDateTime() {
        return ScheduledDateTime;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

}