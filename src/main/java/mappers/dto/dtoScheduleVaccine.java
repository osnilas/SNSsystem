package mappers.dto;

import app.domain.model.TypeVaccine;
import app.domain.model.VaccinationSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class dtoScheduleVaccine {

    private int SNSnumber;

    private LocalDateTime appointmentDate;

    private TypeVaccine TypeVaccine;


    public dtoScheduleVaccine(int SNSnumber,LocalDateTime appointmentDate,TypeVaccine typeVaccine){
        this.SNSnumber=SNSnumber;
        this.appointmentDate=appointmentDate;
        this.TypeVaccine=typeVaccine;
    }

    public int getSNSnumber() {
        return SNSnumber;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public TypeVaccine getTypeVaccine() {
        return TypeVaccine;
    }

}
