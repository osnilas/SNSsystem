package app.ui.console;

import app.controller.SelectVaccinationFacilityController;
import app.domain.model.VaccinationFacility;
import app.ui.console.utils.Utils;

import java.util.List;

public class SelectVaccinationFacilityUI implements Runnable {

    private SelectVaccinationFacilityController ctlr;

    private int index=-1;

    public SelectVaccinationFacilityUI(){
        ctlr =new SelectVaccinationFacilityController();
    }

    @Override
    public void run() {
        do {
            List<String> list = ctlr.getVaccinationFacilities();
            Utils.showList(list, "Select a vaccination facility");
            index = Utils.selectsIndex(ctlr.getVaccinationFacilities());
        }while (index==-1);
    }

    public int getIndex() {
        return index;
    }
}
