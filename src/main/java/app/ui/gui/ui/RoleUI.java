package app.ui.gui.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleUI {

    private final MainScene mainScene;

    private LoginUI loginUI;

    public RoleUI(MainScene mainScene){
        this.mainScene=mainScene;
    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    public void toAdmin(){
        try {
            mainScene.setSCENE_WIDTH(648);
            mainScene.setSCENE_HEIGHT(437);
            AdminUI adminUI = (AdminUI) this.mainScene.replaceSceneContent("/fxml/AdminMenu.fxml");
            adminUI.setMainApp(mainScene);
            adminUI.setRoleUI(this);
        }catch (Exception ex){
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void toNurse(){try {
        mainScene.setSCENE_WIDTH(600);
        mainScene.setSCENE_HEIGHT(400);
        NurseUI nurseUI = (NurseUI) this.mainScene.replaceSceneContent("/fxml/NurseMenu.fxml");
        nurseUI.setMainApp(mainScene);
        nurseUI.setRoleUI(this);
    }catch (Exception ex){
        Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE,null,ex);
    }}

    public void toReceptionist(){
        try {
            mainScene.setSCENE_WIDTH(600);
            mainScene.setSCENE_HEIGHT(400);
            ReceptionistUI resUI = (ReceptionistUI) this.mainScene.replaceSceneContent("/fxml/ReceptionistMenu .fxml");
            resUI.setMainApp(mainScene);
            resUI.setRoleUI(this);
        }catch (Exception ex){
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void toSNS(){
        try {
            mainScene.setSCENE_WIDTH(604);
            mainScene.setSCENE_HEIGHT(400);
            SNSuserUI snSuserUI=(SNSuserUI)  this.mainScene.replaceSceneContent("/fxml/SNSuserMenu.fxml");
            snSuserUI.setMainApp(mainScene);
            snSuserUI.setRoleUI(this);
        }catch (Exception ex){
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void toCoordinator(){
        try {
            mainScene.setSCENE_WIDTH(600);
            mainScene.setSCENE_HEIGHT(400);
            CoordinatorUI coordinatorUI=(CoordinatorUI)  this.mainScene.replaceSceneContent("/fxml/CoordinatorMenu.fxml");
            coordinatorUI.setMainApp(mainScene);
            coordinatorUI.setRoleUI(this);
        } catch (Exception ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void goBack(){
        loginUI.toLoginUI();
    }
}
