package app.ui.gui.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainScene extends Application {

    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 500.0;
    private final double MINIMUM_WINDOW_HEIGHT = 400.0;
    private  double SCENE_WIDTH = 700;
    private  double SCENE_HEIGHT = 506;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        stage.setTitle("Vaccination management system");
        Image icon=new Image("Images/H082651ae82074da2bccb37051799d887P.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        toMainScene();
        this.stage.show();
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setSCENE_HEIGHT(double SCENE_HEIGHT) {
        this.SCENE_HEIGHT = SCENE_HEIGHT;
    }

    public void setSCENE_WIDTH(double SCENE_WIDTH) {
        this.SCENE_WIDTH = SCENE_WIDTH;
    }

    public void toMainScene() {
        try {
            setSCENE_WIDTH(604);
            setSCENE_HEIGHT(400);
            MainUI MainUI = (MainUI) replaceSceneContent("/fxml/Main.fxml");
            MainUI.setMainApp(this);
            //LoginUI loginUI = (LoginUI) replaceSceneContent("fxml/Login.fxml");
            //loginUI.setMainApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toConsole(){
        stage.close();
    }
    public void minimizeWindow(){stage.setIconified(true);}
    public void bringBackWindow(){stage.setIconified(false);}

    public Initializable replaceSceneContent(String fxml) throws Exception {
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
        Scene scene = new Scene(page, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add("/styles/Styles.css");
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
