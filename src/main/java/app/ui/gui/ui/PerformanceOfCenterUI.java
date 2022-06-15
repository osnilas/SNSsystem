package app.ui.gui.ui;

import app.controller.PerformanceOfCenterController;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.IntStream;


import app.ui.console.utils.ReadFile;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;


import static java.util.stream.Collectors.toList;

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
        ctrl = new PerformanceOfCenterController();
        ctrl.setFacility();
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
            stringBuilder.append("Input:\n" + data.get(0) + "\n");
            stringBuilder.append("Sum:\n" + data.get(3) + "\n");
            stringBuilder.append("Time interval :\n" + data.get(1) + " " + data.get(2) + "\n");
            txtPerformance.setText(String.join("\n", stringBuilder.toString()));
            txtMinutes.clear();
        }catch (Exception ignored){
        }


    }

    public void minutesTyped(ActionEvent event) {
    }

    public void backPressed(ActionEvent event) {
        coordinatorUI.goBack();
    }
}
