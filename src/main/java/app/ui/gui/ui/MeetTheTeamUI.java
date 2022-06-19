package app.ui.gui.ui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MeetTheTeamUI implements Initializable {
    public Button btnBack;
    MainScene mainScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMainScene(MainScene mainScene) {
        this.mainScene = mainScene;
    }

    public void backPressed(ActionEvent event) {
        mainScene.toMainScene();
    }

    public void toMeetTheTeamUI() {

    }
}
