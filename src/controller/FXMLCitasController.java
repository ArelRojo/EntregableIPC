/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import app.EntregableIPC;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Appointment;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class FXMLCitasController implements Initializable {

    @FXML
    private Button btAdd;
    @FXML
    private Button btDel;
    private TableView<Appointment> tvCitas;
    private EntregableIPC app;
    private Stage primaryStage;
    @FXML
    private GridPane calendarView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     *
     * @param app
     */
    public void setApp(EntregableIPC app) {
        this.app = app;
    }

    /**
     *
     * @param stage
     * @param citas
     */
    public void initStage(Stage stage, ObservableList<Appointment> citas) {
        this.primaryStage = stage;
        this.tvCitas.setItems(citas);
    }

    @FXML
    private void b_AddCita(ActionEvent event) {
        try {
            this.app.goToAddCita();
        } catch (Exception e) {
        }
    }	
    
}
