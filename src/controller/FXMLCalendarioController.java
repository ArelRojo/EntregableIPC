/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBAccess.ClinicDBAccess;
import app.DiaCelda;
import app.EntregableIPC;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jfxtras.scene.control.LocalDateTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroupImpl;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;
import model.Appointment;
import model.Doctor;
import model.Patient;

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

    ClinicDBAccess dao;
    @FXML
    private VBox vboxCalendar;
    @FXML
    private Button btnSemanaAnterior;
    @FXML
    private Button btnSemanaSiguiente;

    LocalDateTimeTextField lLocalDateTimeTextField;
    @FXML
    private ChoiceBox<Doctor> ddMedico;
    @FXML
    private ChoiceBox<Patient> ddPaciente;
    @FXML
    private ChoiceBox<String> ddHora;
    @FXML
    private ChoiceBox<String> ddMinuto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ddMedico.setConverter(new StringConverter<Doctor>(){
            @Override
            public String toString(Doctor object) {
                return object.getName();
            }

            @Override
            public Doctor fromString(String string) {
                return ddMedico.getItems().stream().filter(doctor -> doctor.getName().equals(string)).findFirst().orElse(null);
            }
        
        });
        
        this.ddPaciente.setConverter(new StringConverter<Patient>(){
            @Override
            public String toString(Patient object) {
                return object.getName();
            }

            @Override
            public Patient fromString(String string) {
                return ddPaciente.getItems().stream().filter(paciente -> paciente.getName().equals(string)).findFirst().orElse(null);
            }
        
        });
        
        for(int i = 0; i<24; i++){
            if(i <10)
            this.ddHora.getItems().add("0"+i);
            else
                this.ddHora.getItems().add(""+i);
        }
        this.ddMinuto.getItems().add("00");
        this.ddMinuto.getItems().add("15");
        this.ddMinuto.getItems().add("30");
        this.ddMinuto.getItems().add("45");
        
        
        
       

    }

    public void setApp(EntregableIPC app) {
        this.app = app;
    }

    public void initStage(Stage stage, ClinicDBAccess clinicDBAccess) {
         
        lLocalDateTimeTextField = new LocalDateTimeTextField();
        this.primaryStage = stage;
        this.dao = clinicDBAccess;
        // TODO
        Agenda agenda = new Agenda();
        this.vboxCalendar.getChildren().add(agenda);
        lLocalDateTimeTextField.localDateTimeProperty().bindBidirectional(agenda.displayedLocalDateTime());
        //Cargar citas
        List<Appointment> lcitas = dao.getAppointments();

        AppointmentImplLocal agendaCita = new AppointmentImplLocal();
        //agenda.displa

        for (Appointment cita : lcitas) {
            agendaCita.withStartLocalDateTime(cita.getAppointmentDateTime())
                    .withEndLocalDateTime(cita.getAppointmentDateTime().plusMinutes(60))
                    .withDescription(cita.getPatient().getName() + " " + cita.getPatient().getSurname());
            agenda.appointments().add(agendaCita);
            agendaCita = new AppointmentImplLocal();
        }
        
        this.ddMedico.getItems().addAll(this.dao.getDoctors());
        this.ddPaciente.getItems().addAll(this.dao.getPatients());
        this.dao.get

    }

    @FXML
    private void addEvent(ActionEvent event) {
    }

    @FXML
    private void lastWeek(ActionEvent event) {
        lLocalDateTimeTextField.setLocalDateTime(lLocalDateTimeTextField.getLocalDateTime().minusDays(7));
        
    }

    @FXML
    private void nextWeek(ActionEvent event) {
         lLocalDateTimeTextField.setLocalDateTime(lLocalDateTimeTextField.getLocalDateTime().plusDays(7));
    }

}
