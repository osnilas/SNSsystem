package app.domain.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class VaccinationCenter extends VaccinationFacility {

    private TypeVaccine typeOfVaccine;

    /**@author JoÃ£o Veiga
     * Constructor
     * Creates an Employee with the following arttributes
     * @param name                              The Vaccination Center name
     * @param address                           The Vaccination Center adress
     * @param phoneNumber                       The Vaccination Center phone number
     * @param emailAddress                      The Vaccination Center email
     * @param faxNumber                         The Vaccination Center fax number
     * @param websiteAddress                    The Vaccination Center website
     * @param openingHours                      The Vaccination Center opening  hours
     * @param closingHours                       The Vaccination closing hours
     * @param slotDuration                      The Vaccination Center slot duration
     * @param maximumNumberOfVaccinesPerSlot     The Vaccination Center maximum number of vaccines per slot
     * @param typeOfVaccine                     The Vaccination Center name
     */
    public VaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalTime openingHours, LocalTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot, TypeVaccine typeOfVaccine) {
        super(name,address,phoneNumber,emailAddress,faxNumber,websiteAddress,openingHours,closingHours,slotDuration,maximumNumberOfVaccinesPerSlot);

        this.typeOfVaccine = typeOfVaccine;


    }
    public TypeVaccine getTypeOfVaccine() {
        return typeOfVaccine;
    }


    @Override
    public String toString() {
        return "Vaccination Center{" +
                ", name=" + getName() +
                ", adress='" + getAddress() + '\'' +
                ", phone number=" + getPhoneNumber() +
                ", email='" + getAddress() + '\'' +
                ", fax number=" + getFaxNumber() +
                ", website adress='" + getWebsiteAddress() + '\'' +
                ", opening and closing hours=" + getOpeningHours() +
                ", opening and closing hours=" + getClosingHours() +
                ", slot duration='" + getSlotDuration() + '\'' +
                ", maximum number of vaccines per slot=" + getMaximumNumberOfVaccinesPerSlot() +
                ", type of vaccine='" + typeOfVaccine.toString() + '\'' +
                '}';
    }

}


