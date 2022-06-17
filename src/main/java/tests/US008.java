package tests;

import app.controller.App;
import app.ui.gui.controller.RecordVaccineAdministrationController;
import app.domain.model.*;
import app.domain.shared.Constants;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class US008 {

    RecordVaccineAdministrationController controller = new RecordVaccineAdministrationController();

    private static int SNSuserNumber=123456789;

    private static int CCNumber=23456789;
    private static int PhoneNumber=987654321;

    private static int indexListNoVaccine=0;
    private static int indexListVaccine=1;
    private static int indexListNoappoiment=2;

    private static final SNSuser SNSuserNoVaccine = new SNSuser("TesterNoVaccine","Male", LocalDate.of(1999,1,5),"Rua no vaccine","noVaccine@tester.com",PhoneNumber,SNSuserNumber,CCNumber,"pass");

    private static final VaccinationAppointment SNSuserNoVaccineAppoiment=new VaccinationAppointment(SNSuserNoVaccine.getSNSnumber(),LocalDateTime.now(),Constants.TYPE_VACCINE_TESTER1);

    private static final SNSuser SNSuserWithVaccine = new SNSuser("TesterWithVaccine","Male", LocalDate.of(1999,1,6),"Rua with vaccine","Vaccine@tester.com",PhoneNumber-1,SNSuserNumber-1,CCNumber-1,"pass");

    private static final VaccinationAppointment SNSuserWithVaccineAppoiment=new VaccinationAppointment(SNSuserWithVaccine.getSNSnumber(),LocalDateTime.now(),Constants.TYPE_VACCINE_TESTER1);

    private static final SNSuser SNSuserNotOnAppoiment = new SNSuser("TesterNotAppoiment","Male", LocalDate.of(1999,1,30),"Rua no apppoiment","notAppoiment@tester.com",PhoneNumber-6,SNSuserNumber-7,CCNumber-5,"pass");

    private static final VaccineCard vaccineCard=new VaccineCard(Constants.VACCINE_TESTER, LocalDateTime.now().minusMonths(1),1);

    void prep() {
        Company company = App.getInstance().getCompany();
        SNSuserWithVaccine.updateVaccinationRecord(vaccineCard.getVaccine(),vaccineCard.getDate());
        company.getSNSuserList().addAll(Arrays.asList(SNSuserNoVaccine,SNSuserWithVaccine));
        company.getVaccinationFacilityList().get(0).addSchedule(SNSuserNoVaccineAppoiment);
        company.getVaccinationFacilityList().get(0).addSchedule(SNSuserWithVaccineAppoiment);
        company.getVaccinationFacilityList().get(0).getWaitingList().add(new Arrival(SNSuserNoVaccine));
        company.getVaccinationFacilityList().get(0).getWaitingList().add(new Arrival(SNSuserWithVaccine));
        company.getVaccinationFacilityList().get(0).getWaitingList().add(new Arrival(SNSuserNotOnAppoiment));
        controller.setVaccinationFacility(0);
    }
    void clean() {
        Company company = App.getInstance().getCompany();
        controller=new RecordVaccineAdministrationController();
        company.getVaccinationFacilityList().get(0).getVaccinationScheduleList().clear();
        company.getVaccinationFacilityList().get(0).getWaitingList().clear();
    }

    @Test
    void firstDose() {
        prep();
        try {
            controller.getUserFromWaitingList(indexListNoVaccine);
        } catch (Exception ignored) {
        }

        assertFalse(controller.getUserVaccineCard());
        clean();
    }

    @Test
    void allReadyTookFirstDose() {
        prep();
        try {
            controller.getUserFromWaitingList(indexListVaccine);
        } catch (Exception ignored) {
        }
        controller.getUserVaccineCard();
        assertTrue(controller.getUserVaccineCard());
        clean();
    }

    @Test
    void notOnAppoiment() {
        prep();
        boolean flag=true;
        try {
            controller.getUserFromWaitingList(indexListNoappoiment);
        } catch (Exception ignored) {
            flag=false;
        }
        assertFalse(flag);
        clean();
    }

    @Test
    void withAppoiment() {
        prep();
        boolean flag=true;
        try {
            controller.getUserFromWaitingList(indexListNoVaccine);
        } catch (Exception ignored) {
            flag=false;
        }
        assertTrue(flag);
        clean();
    }

    @Test
    void checkVaccines(){
        prep();
        int expeted= App.getInstance().getCompany().getVaccineListByVaccineType(Constants.TYPE_VACCINE_TESTER1).size();
        try {
            controller.getUserFromWaitingList(indexListNoVaccine);
        } catch (Exception ignored) {
        }
        int generated=controller.getVaccineList().size();
        assertEquals(expeted,generated);
        clean();
    }

    @Test
    void ValidAdministration(){
        prep();
        try {
            controller.getUserFromWaitingList(indexListNoVaccine);
            controller.setVaccine(0);
            controller.createVaccinationAdminstration("US256-65");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertTrue(controller.saveVaccinationAdminstration());
        clean();
    }

    @Test
    void InvalidAdministrationNoLotNumber(){
        prep();
        try {
            controller.getUserFromWaitingList(indexListNoVaccine);
            controller.setVaccine(0);
            controller.createVaccinationAdminstration("");
        } catch (Exception e) {
        }
        assertFalse(controller.saveVaccinationAdminstration());
        clean();
    }


    @Test
    void InvalidAdministrationNoVaccine(){
        prep();
        try {
            controller.getUserFromWaitingList(indexListNoVaccine);
            controller.createVaccinationAdminstration("US256-65");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertFalse(controller.saveVaccinationAdminstration());
        clean();
    }

}
