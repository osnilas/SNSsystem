package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VaccinationAppointment implements Serializable {

    private int snsNumber;

    private LocalDateTime appointmentTime;

    private TypeVaccine typeVaccine;

    /**
     * @author Jo�o Veiga
     * @Description Construtor. Creates a vaccine appoiment with the following attributes:
     * @param snsNumber
     * @param appointmentTime Date and time of the appoiment
     * @param typeVaccine
     */
    public VaccinationAppointment(int snsNumber, LocalDateTime appointmentTime, TypeVaccine typeVaccine){

        this.snsNumber =snsNumber;
        this.appointmentTime=appointmentTime;
        this.typeVaccine =typeVaccine;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method checks if the input date&time is the same as the vaccine appointment.
     * @param appointmentTime Date and time
     * @return Boolean if the input date&time is the same as the vaccine appointment.
     */
    public boolean isAppointmentSameTime(LocalDateTime appointmentTime){
        if(this.appointmentTime.isEqual(appointmentTime)){
            return true;
        }
        return false;
    }

    public boolean isAppointmentToday(){
        LocalDate date=appointmentTime.toLocalDate();
        if(date.isEqual(LocalDate.now())){
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
