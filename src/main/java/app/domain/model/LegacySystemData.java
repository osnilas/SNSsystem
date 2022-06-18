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



    /**@author Filipe Magalhães
     * Constructor
     * @param SNSuserNumber
     * @param vaccine
     * @param dose
     * @param lotNumber
     * @param arrivalTime
     * @param ScheduledDateTime
     * @param NurseAdministrationDateTime
     * @param LeavingDateTime
     */




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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LegacySystemData other = (LegacySystemData) obj;
        if (this.SNSuserNumber != other.SNSuserNumber) {
            return false;
        }
        if (this.vaccine != other.vaccine) {
            return false;
        }
        if (this.dose != other.dose) {
            return false;
        }
        if (this.lotNumber != other.lotNumber) {
            return false;
        }
        if (this.arrivalTime != other.arrivalTime) {
            return false;
        }
        if (this.ScheduledDateTime != other.ScheduledDateTime) {
            return false;
        }
        if (this.NurseAdministrationDateTime != other.NurseAdministrationDateTime) {
            return false;
        }
        if (this.LeavingDateTime != other.LeavingDateTime) {
            return false;
        }
        return true;
    }

}
