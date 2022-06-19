package app.ui.gui.ui;

import app.ui.console.utils.Utils;
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

    public Button btnMeet;
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


    public void meetPressed(ActionEvent event) throws Exception {
        mainScene.setSCENE_WIDTH(590);
        mainScene.setSCENE_HEIGHT(428);
        MeetTheTeamUI meetTheTeamUI= (MeetTheTeamUI) this.mainScene.replaceSceneContent("/fxml/MeetTheTeam.fxml");
        meetTheTeamUI.setMainScene(this.mainScene);
    }
}
