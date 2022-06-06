package app.ui.gui.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginUI {

    private final App mainApp;


    public LoginUI(App mainApp){
        this.mainApp=mainApp;
    }

    public App getMainApp(){
        return this.mainApp;
    }

    public void toLoginUI(){
        try{
            Login1UI loginUI=(Login1UI) this.mainApp.replaceSceneContent("/fxml/Login.fxml");
            loginUI.setMainApp(mainApp);
            loginUI.setLoginUI(this);
        }catch (Exception ex){
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
 }
