package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VaccinationAdminstrationRecord implements Serializable {

    private int SNSuserNumber;
    private Vaccine vaccine;
    private String lotNumber;
    private LocalDateTime ScheduledDateTime;
    private LocalDateTime NurseAdministrationDateTime;
    private LocalDateTime LeavingDateTime;


    public VaccinationAdminstrationRecord(int SNSuserNumber,Vaccine vaccine,String lotNumber,LocalDateTime ScheduledDateTime,LocalDateTime NurseAdministrationDateTime,LocalDateTime LeavingDateTime){
        this.SNSuserNumber=SNSuserNumber;
        this.vaccine=vaccine;
        this.lotNumber=lotNumber;
        this.ScheduledDateTime=ScheduledDateTime;
        this.NurseAdministrationDateTime=NurseAdministrationDateTime;
        this.LeavingDateTime=LeavingDateTime;
    }

    public int getSNSuserNumber() {
        return SNSuserNumber;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public LocalDateTime getScheduledDateTime() {
        return ScheduledDateTime;
    }

    public LocalDateTime getNurseAdministrationDateTime() {
        return NurseAdministrationDateTime;
    }

    public LocalDateTime getLeavingDateTime() {
        return LeavingDateTime;
    }
}
