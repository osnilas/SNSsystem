package app.controller;

import app.domain.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecordVaccineAdministrationController {

    private SNSuser snSuser;
    private VaccinationAppointment appointment;

    private VaccinationRecord userRecord;
    private VaccinationAdminstrationRecord adminstration;
    private VaccinationFacility facility;

    private Vaccine vaccine;
    private Company company;
    private App app;

    public RecordVaccineAdministrationController(){
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public void createVaccinationAdminstration(int recoryTime,String lotNumber){
        this.adminstration=new VaccinationAdminstrationRecord(snSuser.getSNSnumber(),vaccine,lotNumber,appointment.getAppointmentTime(), LocalDateTime.now(),LocalDateTime.now().plusMinutes(recoryTime));
    }

    public void saveVaccinationAdminstration(){
        this.facility.addVaccinationAdminstrationRecord(adminstration);
        updateVaccinationRecord();
    }

    /**
     * @author João Veiga
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

    public void setVaccinationFacility(int index) {
        this.facility = this.company.getVaccinationFacilityFromList(index);
    }

    public void getUserFromWaitingList() throws Exception {
        this.snSuser=facility.getWaitingList().get(0).getSnSuser();
        getVaccinationAppointment();
        facility.getWaitingList().remove(0);
    }

    public String getAppoimentInfo() {
        String info;
        info = "SNS user info:\n" + "Name: " + snSuser.getName();
        return info;
    }

    public String getVaccineInfo(){
        int ageGroup=userRecord.getAgeGroup(snSuser.getAge());
        return "Vaccine: "+userRecord.getVaccine().getName()+"\nDose: "+userRecord.getVaccine().getVaccineAdministration().getDoses().get(ageGroup)+" mL";
    }

    public List<String> getVaccineList(){
        List<Vaccine> vaccineListFull=this.company.getVaccineList();
        List<String> vaccineList=new ArrayList<>();
        for (int i=0;i<vaccineListFull.size();i++){
            if(Objects.equals(vaccineListFull.get(i).getTypeVaccine(),appointment.getTypeVaccine())){
                vaccineList.add(vaccineListFull.get(i).getName());
            }
        }
        return vaccineList;
    }

    public void setVaccine(int index){
        List<Vaccine> vaccineListFull=this.company.getVaccineList();
        List<Vaccine> vaccineList=new ArrayList<>();
        for (int i=0;i<vaccineListFull.size();i++){
            if(Objects.equals(vaccineListFull.get(i).getTypeVaccine(),appointment.getTypeVaccine())){
                vaccineList.add(vaccineListFull.get(i));
            }
        }
        this.vaccine=vaccineList.get(index);
    }


    public boolean getUserVaccinationRecord() {
        if (snSuser.getVaccinationRecord().size() == 0) {
            return false;
        } else {
            for (int i = 0; i < snSuser.getVaccinationRecord().size(); i++) {
                if (snSuser.getVaccinationRecord().get(i).checkTypeVaccine(appointment.getTypeVaccine())) {
                    this.userRecord=snSuser.getVaccinationRecord().get(i);
                    this.vaccine=snSuser.getVaccinationRecord().get(i).getVaccine();
                }
            }
        }
        return true;
    }

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

    private void updateVaccinationRecord(){
        VaccinationRecord lastRecord=snSuser.getLatestVaccinationRecord(vaccine);
        VaccinationRecord newRecord=new VaccinationRecord(vaccine,LocalDateTime.now(),lastRecord.getNumberDosesTaken()+1);
        snSuser.addVaccinationRecord(newRecord);
    }
}
