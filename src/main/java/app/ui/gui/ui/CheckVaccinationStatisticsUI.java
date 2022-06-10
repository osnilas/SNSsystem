package app.ui.gui.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckVaccinationStatisticsUI implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnExport;

    @FXML
    private DatePicker fromDateTextField;

    @FXML
    private DatePicker toDateTextField;

    @FXML
    private TextArea txtAreaVaccinationStatistics;

    private RoleUI roleUI;


    public void setRoleUI(RoleUI roleUI) {
        this.roleUI = roleUI;
    }

    @FXML
    void backPressed() {
        roleUI.toCoordinator();
    }

    @FXML
    void btnExportPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
