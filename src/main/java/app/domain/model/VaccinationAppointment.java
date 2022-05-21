package app.domain.model;

import java.time.LocalDateTime;

public class VaccinationAppointment {

    private int snsNumber;

    private LocalDateTime appointmentTime;

    private TypeVaccine typeVaccine;

    public VaccinationAppointment(int snsNumber, LocalDateTime appointmentTime, TypeVaccine typeVaccine){

        this.snsNumber =snsNumber;
        this.appointmentTime=appointmentTime;
        this.typeVaccine =typeVaccine;
    }

    public boolean isAppointmentSameTime(LocalDateTime appointmentTime){
        if(this.appointmentTime.isEqual(appointmentTime)){
            return true;
        }
        return false;
    }

    public TypeVaccine getTypeVaccine() {
        return typeVaccine;
    }

    public int getSNSnumber() {
        return snsNumber;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
}
