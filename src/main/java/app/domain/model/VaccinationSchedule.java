package app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VaccinationSchedule {

    private int SNSnumber;

    private LocalDateTime appointmentTime;

    private TypeVaccine TypeVaccine;

    public VaccinationSchedule(int SNSnumber, LocalDateTime appointmentTime, TypeVaccine TypeVaccine){

        this.SNSnumber=SNSnumber;
        this.appointmentTime=appointmentTime;
        this.TypeVaccine=TypeVaccine;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
}
