package app.controller;

import app.domain.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecordVaccineAdministrationController {

    private SNSuser snSuser;
    private VaccinationAppointment appointment;
    private VaccinationAdminstrationRecord adminstration;
    private VaccinationFacility facility;
    private Company company;
    private App app;

    public RecordVaccineAdministrationController(){
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public void createVaccinationAdminstration(int recoryTime,Vaccine vaccine,String lotNumber){
        this.adminstration=new VaccinationAdminstrationRecord(snSuser.getSNSnumber(),vaccine,lotNumber,appointment.getAppointmentTime(), LocalDateTime.now(),LocalDateTime.now().plusMinutes(recoryTime));
    }

    public void saveVaccinationAdminstration(){

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

    /**
     * @author João Veiga
     * @Description This method finds the SNS user through the SNS number.
     * @param SNSnumber
     * @return Boolean if it found one or not.
     */
    public boolean checkIfSNSuserExists(int SNSnumber){
        if(this.company.SNSuserExistsNumber(SNSnumber) !=null){
            this.snSuser=this.company.SNSuserExistsNumber(SNSnumber);
            return true;
        }
        return false;
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

}
