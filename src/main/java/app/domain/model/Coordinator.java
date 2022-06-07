package app.domain.model;

public class Coordinator extends User {

    private VaccinationFacility vaccinationFacility;

    public Coordinator(String id, String name, String address, String email, int phoneNumber, int ccNumber) {
        super(id, name, address, email, phoneNumber, ccNumber);
        vaccinationFacility=null;
    }

    public Coordinator(String id, String name, String address, String email, int phoneNumber, int ccNumber, VaccinationFacility vaccinationFacility) {
        super(id, name, address, email, phoneNumber, ccNumber);
        this.vaccinationFacility=vaccinationFacility;
    }


    public boolean FacilitySame(VaccinationFacility vaccinationFacility){
        return this.vaccinationFacility.equals(vaccinationFacility);
    }
}
