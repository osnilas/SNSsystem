package app.ui.gui.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUI implements Initializable {

    private MainScene mainScene;

    @FXML
    private Button btnLogin;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setMainApp(MainScene mainScene) {
        this.mainScene = mainScene;
    }

    @FXML
    public void loginBtnPress(ActionEvent event) {
        LoginUI loginUI=new LoginUI(this.mainScene);
        loginUI.toLoginUI();
    }


}