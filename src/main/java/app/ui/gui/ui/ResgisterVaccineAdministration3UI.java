package app.ui.gui.ui;

import app.controller.RecordVaccineAdministrationController;
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
    private RecordVaccineAdministrationController controller;

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
        btnNext.setDisable(false);
        controller.setVaccine(cmbVaccine.getSelectionModel().getSelectedIndex());
    }

    public void nextPressed(ActionEvent event) {
        registerVaccineAdministrationUI.toVaccineAdministrationScene2();
        registerVaccineAdministrationUI.closePopUp();
    }
}
