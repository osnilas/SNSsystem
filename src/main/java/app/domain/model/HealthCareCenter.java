package app.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HealthCareCenter extends VaccinationFacility{

    private List<String> TypeVaccineList=new ArrayList<>();

    /**
     * @param name                           The Vaccination Center name
     * @param address                        The Vaccination Center adress
     * @param phoneNumber                    The Vaccination Center phone number
     * @param emailAddress                   The Vaccination Center email
     * @param faxNumber                      The Vaccination Center fax number
     * @param websiteAddress                 The Vaccination Center website
     * @param openingHours         The Vaccination Center opening and closing hours
     * @param closingHours         The Vaccination Center opening and closing hours
     * @param slotDuration                   The Vaccination Center slot duration
     * @param maximumNumberOfVaccinesPerSlot The Vaccination Center maximum number of vaccines per slot
     * @author JoÃ£o Veiga
     * Constructor
     * Creates an Employee with the following arttributes
     */
    public HealthCareCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalDateTime openingHours, LocalDateTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours,closingHours, slotDuration, maximumNumberOfVaccinesPerSlot);
    }

    public void addTypeVaccine(String TypeVaccine){
        TypeVaccineList.add(TypeVaccine);
    }
}
