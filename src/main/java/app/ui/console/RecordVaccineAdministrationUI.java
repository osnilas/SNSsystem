package app.ui.console;

import app.controller.AddSNSfromCSVController;
import app.controller.RecordVaccineAdministrationController;
import app.ui.console.utils.Utils;

public class RecordVaccineAdministrationUI implements Runnable {
    private RecordVaccineAdministrationController ctlr;

    /**
     * @author João Veiga
     * Iniciates controller
     */
    public RecordVaccineAdministrationUI() {
        ctlr = new RecordVaccineAdministrationController();
    }

    @Override
    public void run() {

        int facility=Utils.showAndSelectIndex(ctlr.getVaccinationFacilities(),"Select a vaccination facility:");
        ctlr.setVaccinationFacility(facility);

        try {
            ctlr.getUserFromWaitingList();
        } catch (Exception e) {
            e.printStackTrace();
        }
       Utils.printText(ctlr.getAppoimentInfo());

        if(ctlr.getUserVaccinationRecord()){
            Utils.printText(ctlr.getVaccineInfo());
        }else{
            int vaccine=Utils.showAndSelectIndex(ctlr.getVaccineList(),"Select a vaccine");
            ctlr.setVaccine(vaccine);
            ctlr.getVaccineInfo();
        }
        String lotNumber=Utils.readLineFromConsole("Introduce vaccine lot number:");
        int recoveryTime=Utils.readIntegerFromConsole("Introduce recovery time:");
        ctlr.createVaccinationAdminstration(recoveryTime,lotNumber);
        ctlr.saveVaccinationAdminstration();
    }
}
