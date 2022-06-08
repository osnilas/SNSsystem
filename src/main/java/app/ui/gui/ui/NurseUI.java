package app.ui.gui.ui;

import app.ui.console.ConsultWaitingRoomUI;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NurseUI implements Initializable {

    private MainScene mainScene;
    private RoleUI roleUI;
    public Button btnConsult;
    public Button btnVaccine;
    public Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMainApp(MainScene mainScene) {
        this.mainScene = mainScene;
    }
    public void setRoleUI(RoleUI roleUI) {
        this.roleUI = roleUI;
    }

    public void consultPressed(ActionEvent event) {
        ConsultWaitingRoomUI consultWaitingRoomUI=new ConsultWaitingRoomUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        consultWaitingRoomUI.run();
        mainScene.bringBackWindow();

    }

    public void vaccinePressed(ActionEvent event) {
        RegisterVaccineAdministrationUI registerVaccineAdministrationUI=new RegisterVaccineAdministrationUI(mainScene,this);
        registerVaccineAdministrationUI.toVaccineAdministrationScene1();

    }

    public void goBack(){
        roleUI.toNurse();
    }

    public void backPressed(ActionEvent event) {
        roleUI.goBack();
    }
}
