package app.domain.model;

import app.ui.console.utils.Utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HealthCareCenter extends VaccinationFacility{

    private List<TypeVaccine> TypeVaccineList;

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
    public HealthCareCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalTime openingHours, LocalTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot, List<TypeVaccine> list) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours,closingHours, slotDuration, maximumNumberOfVaccinesPerSlot);
        TypeVaccineList=new ArrayList<>();
        TypeVaccineList.addAll(list);
    }

    public void addTypeVaccine(TypeVaccine typeVaccine){
        TypeVaccineList.add(typeVaccine);
    }

    public List<TypeVaccine> getTypeVaccineList() {
        return TypeVaccineList;
    }

    @Override
    public String toString() {
        return super.toString()+
                "TypeVaccineList=" + TypeVaccineList +
                '}';
    }
}
