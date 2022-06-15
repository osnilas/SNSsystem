package app.ui.gui.ui;

import app.controller.CheckAndExportController;
import app.domain.model.FullyVaccinatedPerDay;
import app.domain.model.VaccinationFacility;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class CheckVaccinationStatisticsUI implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnExport;
    @FXML
    private Button clearButton;
    @FXML
    private DatePicker fromDateTextField = new DatePicker();
    @FXML
    private DatePicker toDateTextField = new DatePicker();
    private RoleUI roleUI;
    @FXML
    private ListView<String> listView;
    private CheckAndExportController ctrl = new CheckAndExportController();
    private List<String> fullyVaccinatedPerDayString = new ArrayList<>();
    private List<FullyVaccinatedPerDay> fullyVaccinatedPerDayList = new ArrayList<>();




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
            listView.getItems().clear();
            Utils.Warning("Date Error", "Invalid Date!", "End date must be after Start date!").show();

        } else if (fromDateTextField.getValue() == null|| toDateTextField.getValue() == null) {
            Utils.Warning("Date Error", "Invalid Date!", "Date must be picked!").show();

        } else if (ctrl.getFullyVaccinatedListFromTo(fromDateTextField.getValue(), toDateTextField.getValue()) == null) {
            listView.getItems().clear();
            Utils.Warning("Error", "Cannot show list", "List is empty!").show();

        } else {
            clear();
            listView.getItems().add("          Date              Number");
            listView.getItems().addAll(ctrl.getFullyVaccinatedListFromTo(fromDateTextField.getValue(), toDateTextField.getValue()));
            System.out.printf("getFullyVaccinatedListFromTo: %s\nlistView: %s\n", ctrl.getFullyVaccinatedListFromTo(fromDateTextField.getValue(), toDateTextField.getValue()), listView.getItems());
        }
    }

    @FXML
    void btnExportPressed(ActionEvent event) {
        if (!listView.getItems().isEmpty()) {
            FileChooser flChooser = FileChooserVaccinationStatisticsUI.createFileChooserVaccinationStatistics();
            File exportFile = flChooser.showSaveDialog(listView.getScene().getWindow());

            if (exportFile != null) {
                if (ctrl.save(exportFile)) {
                    Utils.Information("Vaccination Management System", Constants.EXPORT_MESSAGE, "Data succeddfully exported!.").show();
                } else {
                    Utils.Warning(Constants.APPLICATION_TITLE, Constants.EXPORT_MESSAGE, "There was a problem while exporting the data!").show();
                }
            }
        } else {
            Utils.Warning(Constants.APPLICATION_TITLE, Constants.EXPORT_MESSAGE, "No selected files!").show();
        }
    }

    public void clear () {
        listView.getItems().clear();
        System.out.println("listView: " + listView.getItems());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
