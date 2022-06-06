package app.ui.gui.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUI implements Initializable {

    private App mainApp;

    @FXML
    private Button btnLogin;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void loginBtnPress(ActionEvent event) {
        this.mainApp.setSCENE_WIDTH(700);
        this.mainApp.setSCENE_HEIGHT(506);
        LoginUI loginUI=new LoginUI(this.mainApp);
        loginUI.toLoginUI();
    }
}
