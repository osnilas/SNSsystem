package app.ui.gui.ui;

import app.controller.RecordVaccineAdministrationController;
import app.ui.console.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterVaccineAdministration2UI implements Initializable {

    public TextField txtSNSname;
    public TextField txtSNSage;
    public TextArea txtReactions;
    public TextField txtVaccineName;
    public TextField txtVaccineDose;
    public ComboBox CMBSNSuser;
    public Button btnVaccine;
    private boolean flag=false;
    private RegisterVaccineAdministrationUI registerVaccineAdministrationUI;
    private RecordVaccineAdministrationController vaccineAdministrationController;
    public Button btnCancel;

    public void setRegisterVaccineAdministrationUI(RegisterVaccineAdministrationUI registerVaccineAdministrationUI) {
        this.registerVaccineAdministrationUI = registerVaccineAdministrationUI;
        this.vaccineAdministrationController =registerVaccineAdministrationUI.getController();
    }

    public void setData(){
        if(vaccineAdministrationController.checkIfAlldataSet()){
            CMBSNSuser.disableProperty().setValue(true);
            List<String> appointment =vaccineAdministrationController.getAppoimentInfo(); ;
            txtSNSname.setText(appointment.get(0));
            txtSNSage.setText(appointment.get(1));
            txtReactions.setText("None");
            List<StringBuilder> vaccineInfo =vaccineAdministrationController.getVaccineInfo();
            txtVaccineName.setText(vaccineInfo.get(0).toString());
            txtVaccineDose.setText(vaccineInfo.get(1).toString());
            setBtnVaccine();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnVaccine.setDisable(true);
    }

    public void initComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList(vaccineAdministrationController.getWaitingList());
        //ObservableList<String> options = FXCollections.observableArrayList(new ArrayList<>());
        this.CMBSNSuser.setItems(options);
    }


    public void cancelPressed(ActionEvent event) {
        registerVaccineAdministrationUI.returnNurseUI();
    }

    public void SNSuserSelected(ActionEvent event)  {
        try {
            vaccineAdministrationController.getUserFromWaitingList(CMBSNSuser.getSelectionModel().getSelectedIndex());
        }catch (Exception e){
            Utils.ExceptionWarning(e);
            registerVaccineAdministrationUI.returnNurseUI();
        }

        List<String> appointment =vaccineAdministrationController.getAppoimentInfo(); ;
        txtSNSname.setText(appointment.get(0));
        txtSNSage.setText(appointment.get(1));
        txtReactions.setText("None");
        if(vaccineAdministrationController.getUserVaccinationRecord()){
            List<StringBuilder> vaccineInfo =vaccineAdministrationController.getVaccineInfo();
            txtVaccineName.setText(vaccineInfo.get(0).toString());
            txtVaccineDose.setText(vaccineInfo.get(1).toString());
        }else {
            registerVaccineAdministrationUI.toVaccineAdministrationScene3();
        }

    }

    public void setBtnVaccine() {
        btnVaccine.setDisable(false);
    }
    public void vaccinePressed(ActionEvent event) {
        registerVaccineAdministrationUI.toVaccineAdministrationScene4();
    }
}
