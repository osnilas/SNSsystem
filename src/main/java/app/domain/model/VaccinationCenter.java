package app.domain.model;

public class VaccinationCenter {
    private String name;

    private String address;

    private int phoneNumber;

    private String emailAddress;

    private int faxNumber;

    private String websiteAddress;

    private String openingAndClosingHours;

    private int slotDuration;

    private int maximumNumberOfVacinesPerSlot;

    private String typeOfVaccine;


    public VaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, String openingAndClosingHours, int slotDuration, int maximumNumberOfVacinesPerSlot, String typeOfVaccine) {

        this.name = name;

        this.address = address;

        this.phoneNumber = phoneNumber;

        this.emailAddress = emailAddress;

        this.faxNumber = faxNumber;

        this.websiteAddress = websiteAddress;

        this.openingAndClosingHours = openingAndClosingHours;

        this.slotDuration = slotDuration;

        this.maximumNumberOfVacinesPerSlot = maximumNumberOfVacinesPerSlot;

        this.typeOfVaccine = typeOfVaccine;


    }

    public String getName() {
        return name;
    }

    public String getAdress() {
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

    public String getWebsiteAdress() {
        return websiteAddress;
    }

    public String getOpeningAndClosingHours() {
        return openingAndClosingHours;
    }

    public int getSlotDuration() {
        return slotDuration;
    }

    public int getMaximumNumberOfVacinesPerSlot() {
        return maximumNumberOfVacinesPerSlot;
    }

    public String getTypeOfVaccine() {
        return typeOfVaccine;
    }


    @Override
    public String toString() {
        return "Vaccination Center{" +
                ", name=" + name +
                ", adress='" + address + '\'' +
                ", phone number=" + phoneNumber +
                ", email='" + emailAddress + '\'' +
                ", fax number=" + faxNumber +
                ", website adress='" + websiteAddress + '\'' +
                ", opening and closing hours=" + openingAndClosingHours +
                ", slot duration='" + slotDuration + '\'' +
                ", maximum number of vaccines per slot=" + maximumNumberOfVacinesPerSlot +
                ", type of vaccine='" + typeOfVaccine + '\'' +
                '}';
    }

}
