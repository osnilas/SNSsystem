package app.ui.gui.ui;

import app.ui.gui.controller.RegisterVaccineAdministrationController;
import app.ui.console.utils.Utils;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterVaccineAdministrationUI {
    private final MainScene mainScene;
    private final NurseUI nurseUI;
    private final RegisterVaccineAdministrationController ctlr;

    private Stage popUpWindow=new Stage();

    public RegisterVaccineAdministrationUI(MainScene mainScene, NurseUI nurseUI) {
        this.mainScene = mainScene;
        this.nurseUI = nurseUI;
        ctlr = new RegisterVaccineAdministrationController();
    }

    public MainScene getMainScene() {
        return mainScene;
    }

    public RegisterVaccineAdministrationController getController() {
        return ctlr;
    }

    public void returnNurseUI() {
        nurseUI.goBack();
    }

    public void toVaccineAdministrationScene1() {
        try {
            mainScene.setSCENE_WIDTH(468);
            mainScene.setSCENE_HEIGHT(293);
            RegisterVaccineAdministration1UI registerVaccineAdministration1UI = (RegisterVaccineAdministration1UI) mainScene.replaceSceneContent("/fxml/VaccineAdministrationScene1.fxml");
            registerVaccineAdministration1UI.setRegisterVaccineAdministrationUI(this);
            registerVaccineAdministration1UI.initComboBox();
        } catch (Exception ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toVaccineAdministrationScene2() {
        try {
            mainScene.setSCENE_WIDTH(827);
            mainScene.setSCENE_HEIGHT(642);
            RegisterVaccineAdministration2UI registerVaccineAdministration2UI = (RegisterVaccineAdministration2UI) mainScene.replaceSceneContent("/fxml/VaccineAdministrationScene2.fxml");
            registerVaccineAdministration2UI.setRegisterVaccineAdministrationUI(this);
            registerVaccineAdministration2UI.initComboBox();
            registerVaccineAdministration2UI.setData();
        } catch (Exception ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toVaccineAdministrationScene2FreshController() {
        try {
            ctlr.clear();
            mainScene.setSCENE_WIDTH(827);
            mainScene.setSCENE_HEIGHT(642);
            RegisterVaccineAdministration2UI registerVaccineAdministration2UI = (RegisterVaccineAdministration2UI) mainScene.replaceSceneContent("/fxml/VaccineAdministrationScene2.fxml");
            registerVaccineAdministration2UI.setRegisterVaccineAdministrationUI(this);
            registerVaccineAdministration2UI.initComboBox();
            registerVaccineAdministration2UI.setData();
        } catch (Exception ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toVaccineAdministrationScene3() {
        try {
            popUpWindow.setTitle("Vaccine Administration");
            popUpWindow.show();
            popUpWindow.setResizable(false);
            ResgisterVaccineAdministration3UI registerVaccineAdministration3UI = (ResgisterVaccineAdministration3UI) replacePopUp("/fxml/VaccineAdministrationScene3.fxml");
            registerVaccineAdministration3UI.RegisterVaccineAdministration3UI(this);
            registerVaccineAdministration3UI.intComboBox();
            Utils.Information("Vaccine","It's SNS user first dose", "Please select the vaccine").showAndWait();
        } catch (Exception ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toVaccineAdministrationScene4() {
        try {
            popUpWindow.show();
            RegisterVaccineAdministration4UI registerVaccineAdministration4UI = (RegisterVaccineAdministration4UI) replacePopUp("/fxml/VaccineAdministrationScene4.fxml");
            registerVaccineAdministration4UI.setRegisterVaccineAdministrationUI(this);
        } catch (Exception ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closePopUp() {
        popUpWindow.close();
    }

    private Initializable replacePopUp(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainScene.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainScene.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        scene.getStylesheets().add("/styles/Styles.css");
        this.popUpWindow.setScene(scene);
        this.popUpWindow.sizeToScene();
        return (Initializable) loader.getController();
    }


}
