package app.domain.model;

import java.time.LocalDateTime;

public class VaccinationSchedule {

    private int SNSnumber;

    private LocalDateTime appointmentTime;

    private TypeVaccine typeVaccine;

    public VaccinationSchedule(int SNSnumber, LocalDateTime appointmentTime, TypeVaccine TypeVaccine){

        this.SNSnumber=SNSnumber;
        this.appointmentTime=appointmentTime;
        this.typeVaccine =TypeVaccine;
    }

    public app.domain.model.TypeVaccine getTypeVaccine() {
        return typeVaccine;
    }

    public int getSNSnumber() {
        return SNSnumber;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
}
