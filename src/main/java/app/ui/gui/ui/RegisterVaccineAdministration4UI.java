package app.ui.gui.ui;

import app.controller.RecordVaccineAdministrationController;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterVaccineAdministration4UI implements Initializable {

    public TextField txtLot1;
    public TextField txtLot2;
    public TextField txtRecovery;
    public Button btnDone;
    private int flag=0;
    private RegisterVaccineAdministrationUI registerVaccineAdministrationUI;
    private RecordVaccineAdministrationController vaccineAdministrationController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setRegisterVaccineAdministrationUI(RegisterVaccineAdministrationUI registerVaccineAdministrationUI) {
        this.registerVaccineAdministrationUI = registerVaccineAdministrationUI;
        this.vaccineAdministrationController =registerVaccineAdministrationUI.getController();
    }

    public void lot1Written(KeyEvent keyEvent) {
        flag++;
    }

    public void lot2Written(KeyEvent keyEvent) {
        flag++;
    }

    private boolean validateNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void recoveryWritten(KeyEvent keyEvent) {
        flag++;
    }
    private boolean validateData(){
        Alert alert;
        boolean flag2=true;
        if(txtLot1.getText().length()!=5){
            System.out.println("bransh");
        }
        if (txtLot1.getText().length() != 5 || txtLot2.getText().length() != 2 || !validateNumber(txtLot2.getText())) {
            alert=Utils.Warning("Warning","Lot number not valid", "Lot number must have five alphanumeric characters an Hyphen and two numerical characters ");
            alert.showAndWait();
            txtLot1.clear();
            txtLot1.requestFocus();
            txtLot2.clear();
            txtLot2.requestFocus();
            flag2=false;
        }
        if ( !validateNumber(txtRecovery.getText())) {
            alert=Utils.Warning("Warning","Recovery number not valid", "Recovery number must be a number");
            alert.showAndWait();
            txtRecovery.clear();
            txtRecovery.requestFocus();
            flag2=false;
        }
        return flag2;
    }
    public void donePressed(ActionEvent event) {
        if(flag<3){
            Alert alert=Utils.Warning("Warning","Please fill all the fields",null);
            alert.showAndWait();
        }else{
            if(validateData()) {
                StringBuilder sb = new StringBuilder();
                sb.append(txtLot1.getText() + "-" + txtLot2.getText());
                vaccineAdministrationController.createVaccinationAdminstration(Integer.parseInt(txtRecovery.getText()), sb.toString());
                vaccineAdministrationController.saveVaccinationAdminstration();
                try {
                    vaccineAdministrationController.sendSMS(Integer.parseInt(txtRecovery.getText()));
                }catch (Exception e) {
                    Utils.ExceptionWarning(e);
                    registerVaccineAdministrationUI.returnNurseUI();
                    registerVaccineAdministrationUI.closePopUp();

                }
                registerVaccineAdministrationUI.returnNurseUI();
                registerVaccineAdministrationUI.closePopUp();

            }
        }
    }
}
