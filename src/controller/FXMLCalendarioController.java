/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBAccess.ClinicDBAccess;
import app.EntregableIPC;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    AppointmentImplLocal agendaCita;
    Agenda agenda;
    @FXML
    private BorderPane borderpane;
    @FXML
    private SplitPane splitpane;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ListView<Appointment> lv_citas;
     private ObservableList<Appointment> citas;
  
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
         ClinicDBAccess miscitas = ClinicDBAccess.getSingletonClinicDBAccess();
        this.citas = FXCollections.observableList(miscitas.getAppointments());
       
        lv_citas.setItems(citas);
        lv_citas.setCellFactory(new Callback<ListView<Appointment>, ListCell<Appointment>>(){
            
             @Override
             public ListCell<Appointment> call(ListView<Appointment> param) {
                return new TextFieldListCell<>(new StringConverter<Appointment>() {
                    @Override
                    public String toString(Appointment object) {
                       return object.getAppointmentDateTime().toString();
                    }

                    @Override
                    public Appointment fromString(String string) {
                       return lv_citas.getItems().stream().filter(doctor -> doctor.getAppointmentDateTime().equals(string)).findFirst().orElse(null);
                    }
                } );
             }
        
        
        
        
        });
        
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
        agenda = new Agenda();
        
        this.vboxCalendar.getChildren().add(agenda);
        lLocalDateTimeTextField.localDateTimeProperty().bindBidirectional(agenda.displayedLocalDateTime());
        //Cargar citas
      lcitas = dao.getAppointments();
        agendaCita = new AppointmentImplLocal();
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
       
        
//        borderpane.prefWidthProperty().bind(primaryStage.widthProperty());
//        anchorpane.prefWidthProperty().bind(borderpane.widthProperty());
//
//           vboxCalendar.prefHeightProperty().bind(primaryStage.heightProperty());
//        primaryStage.setResizable(false);

    }
        @FXML
    private void deleteEvent(ActionEvent event){
         int i = lv_citas.getSelectionModel().getSelectedIndex(); //elemento seleccionado
        if (i > -1) {
            citas.remove(i);//lo borra de la lista
        }
        lv_citas.getSelectionModel().clearSelection();
        
        this.lv_citas.refresh();
       
        this.app.save();
        
         agendaCita = new AppointmentImplLocal();
        //agenda.displa
        agenda.appointments().clear();
        for (Appointment cita : lcitas) {
            agendaCita.withStartLocalDateTime(cita.getAppointmentDateTime())
                    .withEndLocalDateTime(cita.getAppointmentDateTime().plusMinutes(60))
                    .withDescription(cita.getPatient().getName() + " " + cita.getPatient().getSurname());
            agenda.appointments().add(agendaCita);
            agendaCita = new AppointmentImplLocal();
        }
         this.agenda.refresh();

        
    }

    @FXML
    private void addEvent(ActionEvent event) {
      
//     
    int resultHora = Integer.parseInt(ddHora.getValue());
    int resultMinuto = Integer.parseInt(ddMinuto.getValue());
       
      
       
        Appointment cita = new Appointment();
        
        cita.setDoctor(ddMedico.getValue());
        cita.setPatient(ddPaciente.getValue());
        cita.setAppointmentDateTime(LocalDateTime.of(datePicker.getValue(), LocalTime.of(resultHora, resultMinuto)));
            agendaCita = new AppointmentImplLocal();
            agendaCita.withStartLocalDateTime(cita.getAppointmentDateTime())
                    .withEndLocalDateTime(cita.getAppointmentDateTime().plusMinutes(60))
                    .withDescription(cita.getPatient().getName() + " " + cita.getPatient().getSurname());
            agenda.appointments().add(agendaCita);
            lcitas.add(cita);
            this.lv_citas.refresh();
            
            this.dao.saveDB();
            for(Appointment citaaux : lcitas){
                System.out.println(citaaux.getAppointmentDateTime());
            }
            
            this.agenda.refresh();
            
        
        
     
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
