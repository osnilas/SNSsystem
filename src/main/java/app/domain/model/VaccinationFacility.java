package app.domain.model;

import java.time.LocalDateTime;

public class VaccinationFacility {

    private String name;

    private String address;

    private int phoneNumber;

    private String emailAddress;

    private int faxNumber;

    private String websiteAddress;

    private LocalDateTime openingHours;

    private LocalDateTime closingHours;

    private int slotDuration;

    private int maximumNumberOfVaccinesPerSlot;

    /**@author JoÃ£o Veiga
     * Constructor
     * Creates an Employee with the following arttributes
     * @param name                              The Vaccination Center name
     * @param address                           The Vaccination Center adress
     * @param phoneNumber                       The Vaccination Center phone number
     * @param emailAddress                      The Vaccination Center email
     * @param faxNumber                         The Vaccination Center fax number
     * @param websiteAddress                    The Vaccination Center website
     * @param openingAndClosingHours            The Vaccination Center opening and closing hours
     * @param slotDuration                      The Vaccination Center slot duration
     * @param maximumNumberOfVaccinesPerSlot     The Vaccination Center maximum number of vaccines per slot
     */
    public VaccinationFacility(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalDateTime openingHours, LocalDateTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot) {

        this.name = name;

        this.address = address;

        this.phoneNumber = phoneNumber;

        this.emailAddress = emailAddress;

        this.faxNumber = faxNumber;

        this.websiteAddress = websiteAddress;

        this.openingHours=openingHours;

        this.closingHours=closingHours;

        this.slotDuration = slotDuration;

        this.maximumNumberOfVaccinesPerSlot = maximumNumberOfVaccinesPerSlot;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getFaxNumber() {
        return faxNumber;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public LocalDateTime getOpeningHours() {
        return openingHours;
    }

    public LocalDateTime getClosingHours() {
        return closingHours;
    }

    public int getSlotDuration() {
        return slotDuration;
    }

    public int getMaximumNumberOfVaccinesPerSlot() {
        return maximumNumberOfVaccinesPerSlot;
    }

}
