package app.ui.console;

import app.controller.AddSNSfromCSVController;
import app.controller.RecordVaccineAdministrationController;
import app.domain.model.Vaccine;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;

import java.util.List;

public class RecordVaccineAdministrationUI implements Runnable {
    private RecordVaccineAdministrationController ctlr;

    /**
     * @author Jo�o Veiga
     * Iniciates controller
     */
    public RecordVaccineAdministrationUI() {
        ctlr = new RecordVaccineAdministrationController();
    }


    @Override
    public void run() {
        try {
            RecordVaccineAdministration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RecordVaccineAdministration() throws Exception {

        int facility = Utils.showAndSelectIndex(ctlr.getVaccinationFacilities(), "Select a vaccination facility:");
        ctlr.setVaccinationFacility(facility);
        ctlr.checkIfWaitingListEmpty();
        List<String> optionsOfWaitingList = List.of("Get first in line", "Select a user");
        int op = Utils.showAndSelectIndex(optionsOfWaitingList, "Select a option:");

        switch (op) {
            case 0:
                ctlr.getUserFromWaitingList(0);
                break;
            case 1:
                boolean flag = true;
                do {
                    int snsNumber = Utils.readIntegerFromConsole("Insert SNS number: ");
                    if (Validate.validateSNS(snsNumber)) {
                        ctlr.getUserFromWaitingListSNSnumber(snsNumber);
                        flag = false;
                    }
                } while (flag);
                break;
            default:
                throw new Exception("No option chosen");
        }
        Utils.printText(ctlr.getAppoimentInfo());

        if (ctlr.getUserVaccinationRecord()) {
            Utils.printText(ctlr.getVaccineInfo());
        } else {
            int vaccine = Utils.showAndSelectIndex(ctlr.getVaccineList(), "Select a vaccine");
            ctlr.setVaccine(vaccine);
            Utils.printText(ctlr.getVaccineInfo());
        }
        String lotNumber = Utils.readLineFromConsole("Introduce vaccine lot number:");
        int recoveryTime = Utils.readIntegerFromConsole("Introduce recovery time:");
        ctlr.createVaccinationAdminstration(recoveryTime, lotNumber);
        ctlr.saveVaccinationAdminstration();
        ctlr.sendSMS(recoveryTime);

    }
}
