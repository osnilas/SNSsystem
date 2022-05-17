package app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class VaccinationScheduler {

    private int SNSnumber;

    private LocalDateTime appointmentTime;

    private LocalDate date;

    private String TypeVaccine;

    public VaccinationScheduler (int SNSnumber,LocalDateTime appointmentTime,LocalDate date ,String TypeVaccine){

        this.SNSnumber=SNSnumber;
        this.appointmentTime=appointmentTime;
        this.date=date;
        this.TypeVaccine=TypeVaccine;
    }

}
