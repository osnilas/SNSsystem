package app.ui.gui.ui;

import app.domain.shared.Constants;
import app.ui.gui.controller.RegisterVaccineAdministrationController;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterVaccineAdministration4UI implements Initializable {

    public TextField txtLot1;
    public TextField txtLot2;

    public Button btnDone;
    private int flag=0;
    private RegisterVaccineAdministrationUI registerVaccineAdministrationUI;
    private RegisterVaccineAdministrationController vaccineAdministrationController;

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
        boolean flag2=true;
        if(txtLot1.getText().length()!= Constants.LOT_NUMBER_LENGTH_PART1){
            System.out.println("bransh");
        }
        if (txtLot1.getText().length() !=Constants.LOT_NUMBER_LENGTH_PART1  || txtLot2.getText().length() != Constants.LOT_NUMBER_LENGTH_PART2|| !validateNumber(txtLot2.getText())) {
            Utils.Warning("Warning","Lot number not valid", "Lot number must have five alphanumeric characters an Hyphen and two numerical characters ").showAndWait();
            txtLot1.clear();
            txtLot1.requestFocus();
            txtLot2.clear();
            txtLot2.requestFocus();
            flag2=false;
        }
        return flag2;
    }
    public void donePressed(ActionEvent event) {
        if(flag<3){
           Utils.Warning("Warning","Please fill all the fields",null).showAndWait();
           flag=0;

        }else{
            if(validateData()) {
                StringBuilder sb = new StringBuilder();
                sb.append(txtLot1.getText() + "-" + txtLot2.getText());
                vaccineAdministrationController.createVaccinationAdminstration( sb.toString());
                vaccineAdministrationController.saveVaccinationAdminstration();
                try {
                    vaccineAdministrationController.sendSMS();
                }catch (Exception e) {
                    Utils.ExceptionWarning(e);
                    registerVaccineAdministrationUI.returnNurseUI();
                    registerVaccineAdministrationUI.closePopUp();

                }
                 Utils.Information("Vaccine administration recorded","Vaccine administration recorded successfully",null).showAndWait();
                if(vaccineAdministrationController.getWaitingList().size()-1==0) {
                    Alert choice = Utils.createConfirmation("Confirmation", "Do you want to record another vaccine administration?", null);
                    choice.showAndWait();
                    if (choice.getResult().getText().equals("OK")) {
                        registerVaccineAdministrationUI.toVaccineAdministrationScene2FreshController();
                        registerVaccineAdministrationUI.closePopUp();
                    } else {
                        registerVaccineAdministrationUI.returnNurseUI();
                        registerVaccineAdministrationUI.closePopUp();
                    }
                }else {
                    registerVaccineAdministrationUI.returnNurseUI();
                    registerVaccineAdministrationUI.closePopUp();
                }
            }
        }
    }
}
