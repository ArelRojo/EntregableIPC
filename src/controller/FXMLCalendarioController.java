/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import app.DiaCelda;
import app.EntregableIPC;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Doctor;

/**
 * FXML Controller class
 *
 * @author thepu
 */
public class FXMLCalendarioController implements Initializable {

    private EntregableIPC app;
    private Stage primaryStage;
    @FXML
    private Button addButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField tf_descripcion;
    @FXML
    private TextField tf_doctor;
    @FXML
    private TextField tf_paciente;
    @FXML
    private TextField tf_paciente1;
    @FXML
    private Pane paneCalendar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Calendario.fxml"));
        BorderPane contenedor;
        try {
            contenedor = (BorderPane) loader.load();
            ObservableList<Node> os = contenedor.getChildren();
            SplitPane sp = (SplitPane) os.get(0);
            Pane panel = (Pane) sp.getItems().get(1);

            DatePicker datePicker = new DatePicker(LocalDate.now());
            datePicker.setShowWeekNumbers(false);
            datePicker.setDayCellFactory(cel -> new DiaCelda());

            DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
            Node popupContent = datePickerSkin.getPopupContent();
            panel.getChildren().add(popupContent);
          //  contenedor.setBottom(popupContent);
          this.primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLCalendarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setApp(EntregableIPC app) {
        this.app = app;
    }

    public void initStage(Stage stage, ObservableList<Doctor> medico) {
        this.primaryStage = stage;

    }

    @FXML
    private void addEvent(ActionEvent event) {
    }

}
