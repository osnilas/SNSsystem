package app.ui.gui.ui;

import app.controller.ImportDataFromLegacySystemController;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ImportDataFromLegacySystemUI implements Initializable {

    public Button btnBack;
    private ImportDataFromLegacySystemController ctrl;

    private CoordinatorUI coordinatorUI;

    public Label txtFileName;
    public Button btnFile;
    public Button btnSave;
    public ListView<String> listData;
    public Button btnAsc;
    public Button btnDesc;

    private int flag=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ctrl = new ImportDataFromLegacySystemController();
    }

    public void setCoordinatorUI(CoordinatorUI coordinatorUI) {
        this.coordinatorUI = coordinatorUI;
    }
    public void filePressed(ActionEvent event) {
        listData.getItems().clear();
        FileChooser flChooser = FileChooserVaccinationStatisticsUI.createFileChooserVaccinationStatistics();
        File importFile = flChooser.showOpenDialog(txtFileName.getScene().getWindow());
        if (importFile != null) {
            txtFileName.setText(importFile.getName());
            try{
            ctrl.readData(importFile.getPath());
            }catch (Exception e){
                Utils.ExceptionWarning(e);
            }finally {
                try {
                    ctrl.sort();
                    updateList(ctrl.getInfo());
                }catch (Exception IGNORE){}

            }
        }
    }

    private void updateList(List<String> data){
        ctrl.sort();
        listData.getItems().clear();
        listData.getItems().add("SNS User Number  | name of the SNS user |  VaccineName | the vaccine type Short| Dose | LotNumber | ScheduledDateTime \t|  ArrivalDateTime |  NurseAdministrationDateTime | LeavingDateTime");
        listData.getItems().addAll(data);
    }

    public void savePressed(ActionEvent event) {
        ctrl.save();
        Utils.Information("Data saved successfully", "Saved",null);
        coordinatorUI.goBack();
    }

    public void ascPressed(ActionEvent event) {
        updateList(ctrl.getInfo());

    }

    public void descPressed(ActionEvent event) {
        List<String> data = ctrl.getInfo();
        Collections.reverse(data);
        updateList(data);
    }

    public void backPressed(ActionEvent event) {
        coordinatorUI.goBack();
    }
}
