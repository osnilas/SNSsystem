package app.domain.model;

import app.ui.console.utils.Utils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HealthCareCenter extends VaccinationFacility implements Serializable {

    private List<TypeVaccine> TypeVaccineList;

    /**
     * @param name                           The health care center name
     * @param address                        The health care center adress
     * @param phoneNumber                    The health care center phone number
     * @param emailAddress                   The health care center email
     * @param faxNumber                      The health care center fax number
     * @param websiteAddress                 The health care center website
     * @param openingHours         The health care center opening and closing hours
     * @param closingHours         The health care center opening and closing hours
     * @param slotDuration                   The health care center slot duration
     * @param maximumNumberOfVaccinesPerSlot The health care center maximum number of vaccines per slot
     * @author JoÃ£o Veiga
     * @Description Constructor
     * Creates a health care center with the following arttributes
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
