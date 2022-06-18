package app.ui.gui.ui;

import app.ui.gui.controller.RegisterVaccineAdministrationController;
import app.ui.console.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ResgisterVaccineAdministration3UI implements Initializable {
    public ComboBox<String> cmbVaccine;
    public Button btnNext;
    private RegisterVaccineAdministrationUI registerVaccineAdministrationUI;
    private RegisterVaccineAdministrationController controller;

    public void RegisterVaccineAdministration3UI(RegisterVaccineAdministrationUI registerVaccineAdministrationUI){
        this.registerVaccineAdministrationUI = registerVaccineAdministrationUI;
        this.controller =registerVaccineAdministrationUI.getController();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnNext.setDisable(true);
    }

    public void intComboBox(){
        ObservableList<String> options = FXCollections.observableArrayList(controller.getVaccineList());
        cmbVaccine.setItems(options);
    }

    public void vaccineSelected(ActionEvent event) {
        if(controller.setVaccine(cmbVaccine.getSelectionModel().getSelectedIndex())){
            btnNext.setDisable(false);
        }else{
            Utils.Warning("Vaccine not for user","Vaccine not for user","User out of age group").showAndWait();
        }
    }

    public void nextPressed(ActionEvent event) {
        registerVaccineAdministrationUI.toVaccineAdministrationScene2();
        registerVaccineAdministrationUI.closePopUp();
    }
}
