package app.domain.model;

import java.io.Serializable;

public class Coordinator extends Employee implements Serializable {

    private VaccinationFacility vaccinationFacility;

    public Coordinator(String name, String address, int phone, int cc, String email, String roleId, VaccinationFacility vaccinationFacility) {
        super(name, address, phone, cc, email, roleId);
        this.vaccinationFacility = vaccinationFacility;

    }
    public Coordinator(String name, String address, int phone, int cc, String email, String roleId) {
        super(name, address, phone, cc, email, roleId);
        this.vaccinationFacility=null;
    }

    public boolean FacilitySame(VaccinationFacility vaccinationFacility){
        return this.vaccinationFacility.equals(vaccinationFacility);
    }
}
