package app.ui.gui.ui;

import app.ui.console.CheckVaccineAppointmentUI;
import app.ui.console.ConsultWaitingRoomUI;
import app.ui.console.ScheduleVaccinationUI;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionistUI implements Initializable {

    private MainScene mainScene;
    private RoleUI roleUI;
    public Button btnCheck;
    public Button btnVaccine;
    public Button btnBack;


    public void setMainApp(MainScene mainScene) {
        this.mainScene = mainScene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setRoleUI(RoleUI roleUI) {
        this.roleUI = roleUI;
    }

    public void checkPressed(ActionEvent event) {
        CheckVaccineAppointmentUI checkVaccineAppointmentUI=new CheckVaccineAppointmentUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        checkVaccineAppointmentUI.run();
        mainScene.bringBackWindow();
    }

    public void vaccinePressed(ActionEvent event) {
        ScheduleVaccinationUI scheduleVaccinationUI=new ScheduleVaccinationUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        scheduleVaccinationUI.run();
        mainScene.bringBackWindow();
    }

    public void backPressed(ActionEvent event) {
        roleUI.goBack();
    }
}
