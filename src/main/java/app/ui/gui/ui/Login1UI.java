package app.ui.gui.ui;


import app.controller.AuthController;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Login1UI implements Initializable {

    private AuthController ctlr;
    private LoginUI loginUI;

    public TextField txtEmail;
    public Button btnLogin;
    public Button btnCancel;
    public PasswordField txtPwd;
    private MainScene mainScene;


    public void setMainApp(MainScene mainScene) {
        this.mainScene = mainScene;
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
        if(ctlr.doLogin(txtEmail.getText(),txtPwd.getText())){
            Welcome();
            redirectToRole();
            //RoleUI roleUI=new RoleUI(mainApp);
            //roleUI.toNurse();
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
        RoleUI roleUI=new RoleUI(mainScene);
        roleUI.setLoginUI(loginUI);
        List<UserRoleDTO> roleDTO=ctlr.getUserRoles();
        String role=roleDTO.get(0).getDescription();
        switch (role){
            case Constants.ROLE_SNS:
                roleUI.toSNS();
                break;
            case Constants.ROLE_NURSE:
                roleUI.toNurse();
                break;
            case Constants.ROLE_ADMIN:
                roleUI.toAdmin();
                break;
            case Constants.ROLE_RES:
                roleUI.toReceptionist();
                break;
            default:
        }
    }

    public void cancelPressed(ActionEvent event) {
        mainScene.toMainScene();
    }

    public void pwdPressed(KeyEvent keyEvent) {
    }
}
