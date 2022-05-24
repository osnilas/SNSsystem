package app.ui.console;

import app.controller.SelectVaccinationFacilityController;
import app.domain.model.VaccinationFacility;
import app.ui.console.utils.Utils;

import java.util.List;

public class SelectVaccinationFacilityUI implements Runnable {

    private SelectVaccinationFacilityController ctlr;

    private int index;

    public SelectVaccinationFacilityUI(){
        ctlr =new SelectVaccinationFacilityController();
    }

    @Override
    public void run() {
            Utils.showList(ctlr.getVaccinationFacilities(),"Select a vaccination facility");
            index=Utils.selectsIndex(ctlr.getVaccinationFacilities());
    }

    public int getIndex() {
        return index;
    }
}
