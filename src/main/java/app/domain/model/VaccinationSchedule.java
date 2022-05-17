package app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VaccinationSchedule {

    private int SNSnumber;

    private LocalDateTime appointmentTime;

    private LocalDate date;

    private String TypeVaccine;

    public VaccinationSchedule(int SNSnumber, LocalDateTime appointmentTime, LocalDate date , String TypeVaccine){

        this.SNSnumber=SNSnumber;
        this.appointmentTime=appointmentTime;
        this.date=date;
        this.TypeVaccine=TypeVaccine;
    }

}
