/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBAccess.ClinicDBAccess;
import app.EntregableIPC;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import jfxtras.scene.control.LocalDateTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
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
    List<Appointment> lcitas;
    @FXML
    private Button delete_bt;
  
 

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
      lcitas = dao.getAppointments();

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
        primaryStage.setResizable(false);

    }

    @FXML
    private void addEvent(ActionEvent event) {
      
//       StringConverter sc = new NumberStringConverter();
//     String lbldate = datePicker.getValue().toString() + " " + ddHora + ":" + ddMinuto;
//     ddHora.setConverter(sc);
//     ddMinuto.setConverter(sc);
//       
//       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yy HH:mm");
//       LocalDateTime formatDateTime = LocalDateTime.parse((CharSequence)(lbldate), formatter);
//      
       
      
       
        Appointment cita = new Appointment();
        
        cita.setDoctor(ddMedico.getValue());
        cita.setPatient(ddPaciente.getValue());
//        cita.setAppointmentDateTime(formatDateTime);
   
        lcitas.add(cita);
        
        
     
    }

    @FXML
    private void lastWeek(ActionEvent event) {
        lLocalDateTimeTextField.setLocalDateTime(lLocalDateTimeTextField.getLocalDateTime().minusDays(7));
        
    }

    @FXML
    private void nextWeek(ActionEvent event) {
         lLocalDateTimeTextField.setLocalDateTime(lLocalDateTimeTextField.getLocalDateTime().plusDays(7));
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
    }

}
