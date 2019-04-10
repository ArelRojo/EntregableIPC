/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBAccess.ClinicDBAccess;
import app.EntregableIPC;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Days;
import model.Doctor;
import model.ExaminationRoom;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class FXMLMedicosController implements Initializable {

    @FXML
    private Button btAdd;
    @FXML
    private Button btDel;
    @FXML
    private TableView<Doctor> tvMedicos;
    @FXML
    private TableColumn<Doctor, String> doctorName;
    @FXML
    private TableColumn<Doctor, String> doctorSurname;
    @FXML
    private TableColumn<Doctor, String> doctorDNI;
    @FXML
    private TableColumn<Doctor, LocalTime> horaIni;
    @FXML
    private TableColumn<Doctor, LocalTime> horaFin;
    @FXML
    private TableColumn<Doctor,LocalDate > dayColumn;
    @FXML
    private TableColumn<Doctor, ArrayList<Days>> diasSemana;
    @FXML
    private TableColumn<Doctor, ExaminationRoom> salaColumn;
    private EntregableIPC app;
    public Stage primaryStage;
    @FXML
    private TextField tf_buscarMedico;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        doctorDNI.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentifier()));
        doctorName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        doctorSurname.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSurname()));
        horaIni.setCellValueFactory(new PropertyValueFactory<>("horaIniciodeTurno"));
        horaIni.setCellFactory((TableColumn<Doctor, LocalTime> column) -> {
            return new TableCell<Doctor, LocalTime>() {
                
                protected void updateItem(LocalTime item, boolean empty) {
                   
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("hh:mm")));
                    }
                }
            };

        });
        horaFin.setCellValueFactory(new PropertyValueFactory<>("horaIniciodeTurno"));
        horaFin.setCellFactory((TableColumn<Doctor, LocalTime> column) -> {
            return new TableCell<Doctor, LocalTime>() {
                
                protected void updateItem(LocalTime item, boolean empty) {
                   
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("hh:mm")));
                    }
                }
            };

        });
//        diasSemana.setCellValueFactory((TableColumn.CellDataFeatures<Doctor, ArrayList<Days>> c) -> {
//            return new SimpleStringProperty(c.getValue().getVisitDays());
//        });
        
        BooleanBinding noPatientSelected = Bindings.isEmpty(tvMedicos.getSelectionModel().getSelectedItems());
        this.btDel.disableProperty().bind(noPatientSelected);
    }    
    public void setApp(EntregableIPC app) {
        this.app = app;
    }
    
    public void initStage(Stage stage, ObservableList<Doctor> medico) {
        this.primaryStage = stage;
        this.tvMedicos.setItems(medico);
        
    }

    @FXML
    private void buscarMedicoPressed(ActionEvent event) {
    }

    @FXML
    private void AddMedico(ActionEvent event) {
        try {
            this.app.goToAddMedico();
        } catch (Exception e) {
        }
    }
    private ObservableList<Doctor> medico;

    @FXML
    private void delMedico(ActionEvent event) {
        ClinicDBAccess mismedicos = ClinicDBAccess.getSingletonClinicDBAccess();
         this.medico = FXCollections.observableList(mismedicos.getDoctors()) ; 
         tvMedicos.setItems(medico);
            int i =  tvMedicos.getSelectionModel().getSelectedIndex(); //elemento seleccionado
            if(i>-1) medico.remove(i);//lo borra de la lista
            tvMedicos.getSelectionModel().clearSelection();
    }
}
