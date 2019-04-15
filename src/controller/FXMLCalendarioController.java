/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBAccess.ClinicDBAccess;
import app.EntregableIPC;
import java.awt.BorderLayout;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author lisa
 */
public class FXMLCalendarioController implements Initializable {

    private EntregableIPC app;
    private Stage primaryStage;
    
    @FXML
    private Button addButton;
    @FXML
     DatePicker datePicker;
    @FXML
    TextField tf_descripcion;
    @FXML
     ListView<Appointment> lv_citas;
    
    ClinicDBAccess list = ClinicDBAccess.getSingletonClinicDBAccess();
    private ObservableList<Appointment> citas = FXCollections.observableList(list.getAppointments());
   
    private LocalDate date;
    @FXML
    private TextField tf_doctor;
    @FXML
    private TextField tf_paciente;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setValue(LocalDate.now());
               
     
    }

     public void initStage(Stage stage, ObservableList<Appointment> calendario) {
        this.primaryStage = stage;
        this.lv_citas.setItems(calendario);
    }
  
    
     public void setApp(EntregableIPC app) {
        this.app = app;
    }

    @FXML
    private void addEvent(ActionEvent event) {

        
    }
    

  
}
