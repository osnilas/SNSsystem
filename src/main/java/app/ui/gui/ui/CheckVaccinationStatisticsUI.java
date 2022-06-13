package app.ui.gui.ui;

import app.controller.CheckAndExportController;
import app.domain.Store.FullyVaccinatedPerDayStore;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CheckVaccinationStatisticsUI implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnExport;
    @FXML
    private DatePicker fromDateTextField;
    @FXML
    private DatePicker toDateTextField;
    @FXML
    private TextArea txtAreaVaccinationStatistics;
    private RoleUI roleUI;

    private FullyVaccinatedPerDayStore fullyVaccinatedPerDayStore;
    private CheckAndExportController ctrl = new CheckAndExportController();
    public void setRoleUI(RoleUI roleUI) {
        this.roleUI = roleUI;
    }

    @FXML
    void backPressed() {
        roleUI.toCoordinator();
    }

    @FXML
    void btnSearchPressed(ActionEvent event) {
        if (fromDateTextField.getValue().equals(toDateTextField.getValue()) || fromDateTextField.getValue().isAfter(toDateTextField.getValue())) {
            Utils.Warning("Date Error", "Invalid Date!", "End date must be after Start date!").show();
        } else if (ctrl.getFullyVaccinatedListFromTo(fromDateTextField.getValue(), toDateTextField.getValue()) == null) {
            Utils.Warning("Error", "Cannot show list", "List is empty!").show();
        } else {
            //txtAreaVaccinationStatistics.setText(String.join("\n", (CharSequence) IntStream.rangeClosed(1, ctrl.getFullyVaccinatedListFromTo(fromDateTextField.getValue(), toDateTextField.getValue()).size()).mapToObj(i ->  ctrl.getFullyVaccinatedListFromTo(fromDateTextField.getValue(), toDateTextField.getValue()).get(i)).collect(toList())));
            txtAreaVaccinationStatistics.setText(ctrl.getFullyVaccinatedListFromTo(fromDateTextField.getValue(), toDateTextField.getValue()).toString());
        }

    }

    @FXML
    void btnExportPressed(ActionEvent event) {
        FileChooser flChooser = FileChooserVaccinationStatisticsUI.createFileChooserVaccinationStatistics();
        File exportFile = flChooser.showSaveDialog(txtAreaVaccinationStatistics.getScene().getWindow());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
