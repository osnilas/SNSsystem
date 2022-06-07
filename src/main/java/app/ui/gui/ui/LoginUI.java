package app.ui.gui.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginUI {

    private final MainScene mainScene;


    public LoginUI(MainScene mainApp){
        this.mainScene=mainApp;
    }

    public MainScene getMainApp(){
        return this.mainScene;
    }

    public void toLoginUI(){
        try{
            this.mainScene.setSCENE_WIDTH(700);
            this.mainScene.setSCENE_HEIGHT(506);
            Login1UI loginUI=(Login1UI) this.mainScene.replaceSceneContent("/fxml/Login.fxml");
            loginUI.setMainApp(mainScene);
            loginUI.setLoginUI(this);
        }catch (Exception ex){
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
 }
