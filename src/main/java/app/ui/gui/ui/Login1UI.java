package app.ui.gui.ui;


import app.controller.AuthController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Login1UI implements Initializable {

    private AuthController ctlr;
    private LoginUI loginUI;

    public TextField txtEmail;
    public Button btnLogin;
    public Button btnCancel;
    public PasswordField txtPwd;
    private App mainApp;


    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }
    private void createController(){
        this.ctlr=new AuthController();
    }
    public void setLoginUI(LoginUI loginUI){
        this.loginUI=loginUI;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void emailPressed(KeyEvent keyEvent) {
    }

    public void loginPressed(ActionEvent event) {
        createController();
        System.out.println(txtEmail.getText());
        if(ctlr.doLogin(txtEmail.getText(),txtPwd.getText())){
            Welcome();

        }else{
            Failed();
            clearTxtFields();
        }
    }

    private void clearTxtFields(){
        txtPwd.clear();
        txtEmail.clear();
    }

    private void Failed(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setContentText("Invalid UserId and/or Password");
        alert.setHeaderText("Credentials not vaild");
        alert.showAndWait();
    }

    private void Welcome(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setContentText("Welcome back");
        alert.setHeaderText("Login successful");
        alert.showAndWait();
    }

    private void redirectToRole(){

    }

    public void cancelPressed(ActionEvent event) {
        mainApp.toMainScene();
    }

    public void pwdPressed(KeyEvent keyEvent) {
    }
}
