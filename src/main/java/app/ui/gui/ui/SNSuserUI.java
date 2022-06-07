package app.ui.gui.ui;

import app.ui.console.ScheduleVaccinationUI;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SNSuserUI implements Initializable {

    private MainScene mainScene;

    private LoginUI loginUI;
    public Button btnVaccination;
    public Button btnBack;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    public void setMainApp(MainScene mainScene) {
        this.mainScene = mainScene;
    }

    public void VaccinatioPressed(ActionEvent event) {
        ScheduleVaccinationUI scheduleVaccinationUI=new ScheduleVaccinationUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        scheduleVaccinationUI.run();
        mainScene.bringBackWindow();
    }

    public void BackPressed(ActionEvent event) {
        loginUI.toLoginUI();
    }
}
