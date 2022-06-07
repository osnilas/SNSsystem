package app.ui.gui.ui;

import app.ui.console.*;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminUI implements Initializable {

    private MainScene mainScene;

    private LoginUI loginUI;
    public Button btnResCenter;
    public Button btnAddCSV;
    public Button btnResType;
    public Button btnResEm;
    public Button btnSpeVacc;
    public Button btnList;
    public Button btnListRole;
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

    public void resCenterPressed(ActionEvent event) {
        RegisterVaccinationCenterUI registerVaccinationCenterUI=new RegisterVaccinationCenterUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        registerVaccinationCenterUI.run();
        mainScene.bringBackWindow();
    }

    public void AddCSVPressed(ActionEvent event) {
        AddUserFromCSVUI addUserFromCSVUI=new AddUserFromCSVUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        addUserFromCSVUI.run();
        mainScene.bringBackWindow();
    }

    public void resEmPressed(ActionEvent event) {
        RegisterEmployeeUI registerEmployeeUI=new RegisterEmployeeUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        registerEmployeeUI.run();
        mainScene.bringBackWindow();
    }

    public void resTypePressed(ActionEvent event) {
        RegisterTypeVaccineUI registerTypeVaccineUI=new RegisterTypeVaccineUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        registerTypeVaccineUI.run();
        mainScene.bringBackWindow();
    }

    public void speVaccPressed(ActionEvent event) {
        VaccineAdministrationUI vaccineAdministrationUI=new VaccineAdministrationUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        vaccineAdministrationUI.run();
        mainScene.bringBackWindow();
    }

    public void listPressed(ActionEvent event) {
        ListingUsersUI listingUsersUI=new ListingUsersUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        listingUsersUI.run();
        mainScene.bringBackWindow();
    }

    public void listRolePressed(ActionEvent event) {
        RoleMenuUI roleMenuUI=new RoleMenuUI();
        Utils.ConsoleSwitch();
        mainScene.minimizeWindow();
        roleMenuUI.run();
        mainScene.bringBackWindow();
    }

    public void backPressed(ActionEvent event) {
        loginUI.toLoginUI();
    }
}
