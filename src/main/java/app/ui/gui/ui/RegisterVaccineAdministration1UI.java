package app.ui.gui.ui;

import app.controller.RecordVaccineAdministrationController;
import app.controller.VaccineAdministrationController;
import app.ui.console.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegisterVaccineAdministration1UI implements Initializable {

    public ComboBox<String> cmbFacilty;
    private boolean flag=false;
    public Button btnNext;
    public Button btnCancel;
    private RegisterVaccineAdministrationUI registerVaccineAdministrationUI;
    private RecordVaccineAdministrationController vaccineAdministrationController;

    public void setRegisterVaccineAdministrationUI(RegisterVaccineAdministrationUI registerVaccineAdministrationUI) {
        this.registerVaccineAdministrationUI = registerVaccineAdministrationUI;
        this.vaccineAdministrationController =registerVaccineAdministrationUI.getController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList(vaccineAdministrationController.getVaccinationFacilities());
        //ObservableList<String> options = FXCollections.observableArrayList(new ArrayList<>());
        this.cmbFacilty.setItems(options);
    }

    public void cmbFacilitySelected(ActionEvent event) {
        this.flag=true;
    }

    public void nextPressed(ActionEvent event)  {

        if(this.flag){
            this.registerVaccineAdministrationUI.getController().setVaccinationFacility(this.cmbFacilty.getSelectionModel().getSelectedIndex());
            try{
               if(vaccineAdministrationController.checkIfWaitingListEmpty()){
                   this.registerVaccineAdministrationUI.toVaccineAdministrationScene2();
               };
            }catch (Exception e){
                Utils.ExceptionWarning(e);
                registerVaccineAdministrationUI.returnNurseUI();
            }
        }
        else{
            Alert alert=Utils.Warning("Warning","Please select a facility",null);
            alert.showAndWait();
        }
    }

    public void cancelPressed(ActionEvent event) {
        registerVaccineAdministrationUI.returnNurseUI();
    }
}