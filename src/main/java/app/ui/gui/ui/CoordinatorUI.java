package app.ui.gui.ui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorUI implements Initializable {

    public Button btnBack;
    private MainScene mainScene;
    private RoleUI roleUI;

    public Button btnVaccinationStatistics;
    public Button btnLegacySystem;
    public Button btnPerformance;

    public void setMainApp(MainScene mainScene) {
        this.mainScene = mainScene;
    }
    public void setRoleUI(RoleUI roleUI) {
        this.roleUI = roleUI;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void vaccinationStatisticsPressed(ActionEvent event) throws Exception {
        CheckVaccinationStatisticsUI checkVaccinationStatisticsUI
                = (CheckVaccinationStatisticsUI) this.mainScene.
                replaceSceneContent("/fxml/CheckVaccinationStatistics.fxml");
        checkVaccinationStatisticsUI.setRoleUI(roleUI);
    }

    public void LegacySystemPressed(ActionEvent event) {
    }

    public void performancePressed(ActionEvent event) {
    }

    public void backPressed(ActionEvent event) {
        roleUI.goBack();
    }
}
