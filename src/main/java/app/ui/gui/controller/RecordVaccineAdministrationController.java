package app.ui.gui.controller;

import app.controller.App;
import app.domain.model.*;
import Timer.SendSMSTask;
import app.ui.console.utils.Utils;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class RecordVaccineAdministrationController {

    private SNSuser snSuser;
    private VaccinationAppointment appointment;

    private VaccineCard vaccineCard;
    private VaccinationAdminstrationRecord adminstration;
    private VaccinationFacility facility;

    private Arrival arrival;

    private Vaccine vaccine;
    private Company company;
    private App app;

    public RecordVaccineAdministrationController(){
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    /**
     * @author Jo�o Veiga
     * @Description This method clears SNS user, appointment, vaccineCard, adminstration, vaccine attributes of the controller.
     */
    public void clear(){
        this.snSuser = null;
        this.appointment = null;
        this.vaccineCard = null;
        this.adminstration = null;
        this.vaccine = null;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method creates a Vaccination Administration
     * @param lotNumber
     */
    public void createVaccinationAdminstration(String lotNumber) {
        int recoryTime= Integer.parseInt(Utils.ReadProppeties("Recovery.time"));
        if (recoryTime != 0 && !lotNumber.isBlank()) {
            if (snSuser.checkIfTookVaccine(vaccine)) {
                this.adminstration = new VaccinationAdminstrationRecord(snSuser.getSNSnumber(), vaccine, snSuser.getVaccinationRecord(vaccine).getNumberDosesTaken() + 1, lotNumber, LocalDateTime.now());
            } else {
                this.adminstration = new VaccinationAdminstrationRecord(snSuser.getSNSnumber(), vaccine, 1, lotNumber, LocalDateTime.now());
            }
        } else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    /**
     * @author Jo�o Veiga
     * @Description This method saves a Vaccination Administration on the vaccination facilty selected.
     */
    public boolean saveVaccinationAdminstration(){
        if(validateVaccineAdministrationCreation()) {
            this.facility.addVaccinationAdminstrationRecord(adminstration);
            updateVaccineCard();
            deleteAppoiment();
            save();
        }
        return validateVaccineAdministration();
    }

    /**
     * @author Jo�o Veiga
     * @Description This method gets vaccination facility list from company and fills a string list with the facility's name.
     * @return List<String> with vaccination facility name's.
     */
    public List<String> getVaccinationFacilities(){
        List<VaccinationFacility> vaccinationFacilityList=this.company.getVaccinationFacilityList();
        List<String>vaccinationFacilityNameList=new ArrayList<>();
        for(int i=0;i<vaccinationFacilityList.size();i++){
            vaccinationFacilityNameList.add(vaccinationFacilityList.get(i).getName());
        }
        return vaccinationFacilityNameList;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method sets the vaccination facility selected from list from company.
     * @param index
     */
    public void setVaccinationFacility(int index) {
        this.facility = this.company.getVaccinationFacilityFromList(index);
    }

    /**
     * @author Jo�o Veiga
     * @Description This method check if waiting list empty or not.
     * @return boolean
     * @throws FileNotFoundException If waiting list is empty.
     */
    public boolean checkIfWaitingListEmpty() throws Exception {
        if(facility.getWaitingList().size()==0){
            throw new Exception("Waiting list is empty");
        }
        return true;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method creates a List of Strings with the name of the SNS user on the waiting list.
     * @return a List of Strings with the name of the SNS user on the waiting list.
     */
    public List <String> getWaitingList(){
        List<String> waitingList=new ArrayList<>();
        for(int i=0;i<facility.getWaitingList().size();i++){
            StringBuilder sb=new StringBuilder();
            sb.append((i+1)+"-"+facility.getWaitingList().get(i).getSnSuser().getName()+", SNS number: "+facility.getWaitingList().get(i).getSnSuser().getSNSnumber());
            waitingList.add(sb.toString());
        }
        return waitingList;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method gets the SNS user from the waiting list.
     * @param index
     * @throws Exception If user doesn't have an appointment.
     */
    public void getUserFromWaitingList(int index) throws Exception {
        this.snSuser=facility.getWaitingList().get(index).getSnSuser();
        this.arrival=facility.getWaitingList().get(index);
        getVaccinationAppointment();
    }

    /**
     * @author Jo�o Veiga
     * @Description This method gets the appointment information, SNS user's name and age.
     * @return List of Strings with the appointment information.
     */
    public List<String> getAppoimentInfo() {
        StringBuilder sb=new StringBuilder();
        List<String> appoimentInfo=new ArrayList<>();
        appoimentInfo.add(sb.append(snSuser.getName()).toString());
        sb.setLength(0);
        appoimentInfo.add(sb.append(snSuser.getAge()).toString());
        return appoimentInfo;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method gets the vaccine information, vaccine name and vaccine dose.
     * @return List of Strings with the vaccine information.
     */
    public List<StringBuilder> getVaccineInfo(){
        StringBuilder sb=new StringBuilder();
        int ageGroup=vaccine.getAgeGroup(snSuser.getAge());
        List<StringBuilder> vaccineInfo=new ArrayList<>();
        vaccineInfo.add(sb.append(vaccine.getName()));
        sb=new StringBuilder();
        vaccineInfo.add(sb.append(vaccine.getVaccineAdministration().getDosage().get(ageGroup)));
        return vaccineInfo;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method gets a list of Strings with the name of the vaccines saved on company that the SNS user can take.
     * @return List of Strings with the name of the vaccines saved on company.
     */
    public List<String> getVaccineList(){
        List<Vaccine> vaccineList=company.getVaccineListByVaccineType(appointment.getTypeVaccine());
        List<String> vaccineListString=new ArrayList<>();
        for (int i=0;i<vaccineList.size();i++){
            if(vaccineList.get(i).getTypeVaccine().equals(appointment.getTypeVaccine())){
                vaccineListString.add(vaccineList.get(i).getName());
            }
        }
        return vaccineListString;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method sets the vaccine from the list of vaccines saved on company.
     * @param index of the list of vaccines on the company
     */
    public boolean setVaccine(int index){
        List<Vaccine> vaccineList=company.getVaccineListByVaccineType(appointment.getTypeVaccine());
        this.vaccine=vaccineList.get(index);
        return vaccine.getAgeGroup(snSuser.getAge())!=-1;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method gets the vaccine card of the vaccine of this appointment from the SNS user.
     * @return boolean of if the SNS user has or not a vaccine card.
     */
    public boolean getUserVaccineCard() {
        if (snSuser.getVaccineCards().size() == 0) {
            return false;
        } else {
            for (int i = 0; i < snSuser.getVaccineCards().size(); i++) {
                if (snSuser.getVaccineCards().get(i).checkTypeVaccine(appointment.getTypeVaccine())) {
                    this.vaccineCard =snSuser.getVaccineCards().get(i);
                    this.vaccine=snSuser.getVaccineCards().get(i).getVaccine();
                }
            }
        }
        return true;
    }


    /**
     * @author Jo�o Veiga
     * @Description This method gets the vaccination appointment from the vaccination facilty with the same SNS number as the SNS number.
     * @throws Exception If SNS number doesn't have an appoiment.
     */
    private void getVaccinationAppointment() throws Exception {
        boolean flag=true;
        for(int i=0;i<facility.getVaccinationScheduleList().size();i++){
            if(facility.getVaccinationScheduleList().get(i).isAppointmentToday() && facility.getVaccinationScheduleList().get(i).getSNSnumber()==snSuser.getSNSnumber()){
                this.appointment=facility.getVaccinationScheduleList().get(i);
                flag=false;
            }
        }
        if(flag){
            throw new Exception("SNS user doesn't have a appointment");
        }
    }


    /**
     * @author Jo�o Veiga
     * @Description This method updates a vaccine card of the SNS user.
     */
    private void updateVaccineCard(){
        if(vaccineCard==null){
            snSuser.updateVaccinationRecord(vaccine,LocalDateTime.now());
        }else {
            vaccineCard.updateNumberDosesTaken();//US15
            if (vaccineCard.getNumberDosesTaken() == vaccine.getVaccineAdministration().getDoses().get(vaccine.getAgeGroup(snSuser.getAge()))) {
                company.updateTotalNumberOfFullyVaccinated(facility);
            }
        }
    }

    /**
     * @author Jo�o Veiga
     * @Description This method validates the vaccine administration attributes.
     * @return boolean of if the attributes are valid or not.
     */
    public boolean validateVaccineAdministration() {
        return snSuser!=null && facility!=null && appointment!=null && vaccine!=null;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method validates the vaccine administration attributes.
     * @return boolean of if the attributes are valid or not.
     */
    public boolean validateVaccineAdministrationCreation() {
        return snSuser!=null && facility!=null && appointment!=null && vaccine!=null && adminstration!=null;
    }

    /**
     * @author Jo�o Veiga
     * @Description This method creates a timer for a SMS to be sent in M minutes.
     * @throws FileNotFoundException If the SMS file doesn't exist.
     */
    public void sendSMS() throws FileNotFoundException {
        SendSMSTask sms=new SendSMSTask();
        sms.setMessage(Utils.ReadProppeties("SMS.Text"));
        int minutes= Integer.parseInt(Utils.ReadProppeties("Recovery.time"));
        long time=(long)minutes*60000;
        Timer timer=new Timer();
        timer.schedule(sms,time);
    }

    /**
     * @author Jo�o Veiga
     * @Description This deletes the vaccination appointment from the vaccination facility and the SNS user from the waiting list.
     */
    private void deleteAppoiment(){
        facility.getVaccinationScheduleList().remove(appointment);
        for(int i=0;i<facility.getWaitingList().size();i++){
            if(facility.getWaitingList().get(i).getSnSuser().getSNSnumber()==snSuser.getSNSnumber()){
                facility.getWaitingList().remove(i);
            }
        }
    }

    private void save(){
        this.company.saveVaccinationFacilityListFile();
        this.company.saveSNSusersListFile();
    }
}
