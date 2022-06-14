package app.ui.gui.ui;

import app.controller.CheckAndExportController;
import app.domain.model.FullyVaccinatedPerDay;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private DatePicker fromDateTextField;
    @FXML
    private DatePicker toDateTextField;
    private RoleUI roleUI;
    @FXML
    private ListView<String> listView;

    private List<String> fullyVaccinatedPerDayString = new ArrayList<>();
    private List<FullyVaccinatedPerDay> fullyVaccinatedPerDayList;
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
            clearList(fullyVaccinatedPerDayString);
            clearList(listView.getItems());
            fullyVaccinatedPerDayList = ctrl.getFullyVaccinatedListFromTo(fromDateTextField.getValue(), toDateTextField.getValue());
            fullyVaccinatedPerDayString.add("           Date               Number");

            for (int i = 0; i < fullyVaccinatedPerDayList.size(); i++) {

                fullyVaccinatedPerDayString.add(String.format("%s                %d", fullyVaccinatedPerDayList.get(i).getDay(), fullyVaccinatedPerDayList.get(i).getCount()));

            }

            listView.getItems().addAll(fullyVaccinatedPerDayString);

        }
    }

    @FXML
    void btnExportPressed(ActionEvent event) {
        FileChooser flChooser = FileChooserVaccinationStatisticsUI.createFileChooserVaccinationStatistics();
        File exportFile = flChooser.showSaveDialog(listView.getScene().getWindow());

    }

    public <E> void clearList(List<E> list) {
        list.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
