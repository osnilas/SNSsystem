package app.ui.gui.ui;

import app.ui.gui.controller.PerformanceOfCenterController;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


import java.util.*;

public class PerformanceOfCenterUI implements Initializable {

    public DatePicker dateField;
    public Button btnAnalysis;
    public TextArea txtPerformance;
    public TextField txtMinutes;
    public Button btnBack;

    private CoordinatorUI coordinatorUI;

    private PerformanceOfCenterController ctrl;

    private int flag=0;

    public void setCoordinatorUI(CoordinatorUI coordinatorUI) {
        this.coordinatorUI = coordinatorUI;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void set() {
        try {
            ctrl = new PerformanceOfCenterController();
        } catch (Exception e) {
            Utils.ExceptionWarning(e);
            coordinatorUI.goBack();
        }
    }

    public void dateSelected(ActionEvent event) {
    }

    public void analysisSelected(ActionEvent event){
        List<String> data=new ArrayList<>();
        try{
            data=ctrl.getPerformanceData(dateField.getValue(),Integer.parseInt(txtMinutes.getText()));
        }catch (Exception e){
            Utils.ExceptionWarning(e);
            coordinatorUI.goBack();
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("The vaccination facility was least effective between :\n" + data.get(0) + " " + data.get(1) + "\n");
            txtPerformance.setText(stringBuilder.toString());
            txtMinutes.clear();
        }catch (Exception  exception){
            Utils.ExceptionWarning(exception);
        }
        ctrl.clear();


    }

    public void minutesTyped(ActionEvent event) {
    }

    public void backPressed(ActionEvent event) {
        coordinatorUI.goBack();
    }
}
