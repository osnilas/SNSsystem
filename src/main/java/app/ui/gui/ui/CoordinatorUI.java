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

    public void LegacySystemPressed(ActionEvent event) throws Exception {
        mainScene.setSCENE_WIDTH(1000);
        mainScene.setSCENE_HEIGHT(700);
        ImportDataFromLegacySystemUI importDataFromLegacySystemUI = (ImportDataFromLegacySystemUI) this.mainScene.replaceSceneContent("/fxml/ImportDataFromLegacySystem.fxml");
        importDataFromLegacySystemUI.setCoordinatorUI(this);
    }

    public void performancePressed(ActionEvent event) throws Exception {
        mainScene.setSCENE_WIDTH(623);
        mainScene.setSCENE_HEIGHT(464);
        PerformanceOfCenterUI performanceOfCenterUI = (PerformanceOfCenterUI) this.mainScene.replaceSceneContent("/fxml/PerformanceEvaluator.fxml");
        performanceOfCenterUI.setCoordinatorUI(this);
        performanceOfCenterUI.set();
    }

    public void goBack(){
        roleUI.toCoordinator();
    }

    public void backPressed(ActionEvent event) {
        roleUI.goBack();
    }
}
