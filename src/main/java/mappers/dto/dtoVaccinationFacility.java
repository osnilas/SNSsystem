package mappers.dto;

import app.domain.model.TypeVaccine;
import app.domain.model.VaccinationSchedule;
import app.ui.console.utils.Utils;

import java.time.LocalTime;
import java.util.List;

public class dtoVaccinationFacility {

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

    private List<VaccinationSchedule> vaccinationScheduleList;

    private List<TypeVaccine> typeVaccineList;

    private TypeVaccine typeVaccine;

    public dtoVaccinationFacility(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalTime openingHours, LocalTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot,List<TypeVaccine> typeVaccineList,List<VaccinationSchedule> vaccinationScheduleList){
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

        this.vaccinationScheduleList=Utils.copyList(this.vaccinationScheduleList,vaccinationScheduleList);

        this.typeVaccineList= Utils.copyList(this.typeVaccineList,typeVaccineList);

        typeVaccine=null;
    }

    public dtoVaccinationFacility(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalTime openingHours, LocalTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot,TypeVaccine typeVaccine,List<VaccinationSchedule> vaccinationScheduleList){
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

        this.typeVaccineList= null;

        this.typeVaccine=typeVaccine;

        this.vaccinationScheduleList=Utils.copyList(this.vaccinationScheduleList,vaccinationScheduleList);
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

    public List<TypeVaccine> getTypeVaccineList() {
        return typeVaccineList;
    }

    public TypeVaccine getTypeVaccine() {
        return typeVaccine;
    }

    public List<VaccinationSchedule> getVaccinationScheduleList() {
        return vaccinationScheduleList;
    }
}
