package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VaccinationAdminstrationRecord implements Serializable {

    private int SNSuserNumber;
    private Vaccine vaccine;
    private int dose;
    private String lotNumber;
    private LocalDateTime NurseAdministrationDateTime;


    public VaccinationAdminstrationRecord(int SNSuserNumber,Vaccine vaccine,int dose,String lotNumber,LocalDateTime NurseAdministrationDateTime){
        this.SNSuserNumber=SNSuserNumber;
        this.vaccine=vaccine;
        this.dose=dose;
        this.lotNumber=lotNumber;
        this.NurseAdministrationDateTime=NurseAdministrationDateTime;
    }

    public int getSNSuserNumber() {
        return SNSuserNumber;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public int getDose() {
        return dose;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public LocalDateTime getNurseAdministrationDateTime() {
        return NurseAdministrationDateTime;
    }


    @Override
    public String toString() {
        return "VaccinationAdminstrationRecord{" +
                "SNSuserNumber=" + SNSuserNumber +
                ", vaccine=" + vaccine +
                ", dose=" + dose +
                ", lotNumber='" + lotNumber + '\'' +
                ", NurseAdministrationDateTime=" + NurseAdministrationDateTime +
                '}';
    }
}
