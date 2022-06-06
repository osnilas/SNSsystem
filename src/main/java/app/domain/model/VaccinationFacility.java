package app.domain.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class VaccinationFacility implements Serializable {

    private String name;

    private String address;

    private int phoneNumber;

    private String emailAddress;

    private int faxNumber;

    private String websiteAddress;

    private LocalTime openingHours;

    private LocalTime closingHours;

    private int slotDuration;

    private int maximumNumberOfVaccinesPerSlot;

    private List<VaccinationAppointment> vaccinationScheduleList=new ArrayList<>();

    private List<VaccinationAdminstrationRecord> vaccinationAdminstrationRecordList=new ArrayList<>();

    private List<Arrival> waitingList=new ArrayList<>();

    /**@author João Veiga
     * @Description Constructor
     * Creates an Employee with the following arttributes
     * @param name                              The Vaccination Center name
     * @param address                           The Vaccination Center adress
     * @param phoneNumber                       The Vaccination Center phone number
     * @param emailAddress                      The Vaccination Center email
     * @param faxNumber                         The Vaccination Center fax number
     * @param websiteAddress                    The Vaccination Center website
     * @param openingHours            The Vaccination Center opening and closing hours
     * @param closingHours
     * @param slotDuration                      The Vaccination Center slot duration
     * @param maximumNumberOfVaccinesPerSlot     The Vaccination Center maximum number of vaccines per slot
     */
    public VaccinationFacility(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalTime openingHours, LocalTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot)  {

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

    public LocalTime getOpeningHours() {
        return openingHours;
    }

    public LocalTime getClosingHours() {
        return closingHours;
    }

    public int getSlotDuration() {
        return slotDuration;
    }

    public int getMaximumNumberOfVaccinesPerSlot() {
        return maximumNumberOfVaccinesPerSlot;
    }

    public List<VaccinationAppointment> getVaccinationScheduleList() {
        return vaccinationScheduleList;
    }

    public void addSchedule(VaccinationAppointment schedule){
        vaccinationScheduleList.add(schedule);
    }

    public void addVaccinationAdminstrationRecord(VaccinationAdminstrationRecord record){vaccinationAdminstrationRecordList.add(record);
    }
    public List<Arrival> getWaitingList() {
        return waitingList;
    }

    public List<VaccinationAdminstrationRecord> getVaccinationAdminstrationRecordList() {
        return vaccinationAdminstrationRecordList;
    }
}
