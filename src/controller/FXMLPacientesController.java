/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBAccess.ClinicDBAccess;
import app.EntregableIPC;
import java.awt.Image;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointment;
import model.Patient;
import model.Patient;
import model.Person;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class FXMLPacientesController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDel;
    @FXML
    private TableColumn<Patient, String> dniColumn;
    @FXML
    private TableColumn<Patient, String> nameColumn;
    @FXML
    private TableColumn<Patient, LocalDate> ageColumn;
    @FXML
    private TableColumn<Patient, Image> pictureColumn;
    @FXML
    private TableColumn<Patient, String> phoneColumn;
    @FXML
    private TableView<Patient> tvPacientes;
    private EntregableIPC app;
    private Stage primaryStage;
    @FXML
    private TextField PacienteABuscar;
    private ObservableList<Patient> paciente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClinicDBAccess mispacientes = ClinicDBAccess.getSingletonClinicDBAccess();
        this.paciente = FXCollections.observableList(mispacientes.getPatients()) ; 
        tvPacientes.setItems(paciente);
        

        dniColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentifier()));
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        phoneColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelephon()));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("fechadenacimiento"));
        ageColumn.setCellFactory((TableColumn<Patient, LocalDate> column) -> {
            return new TableCell<Patient, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    }
                }
            };

        });

        pictureColumn.setCellValueFactory(new PropertyValueFactory<>("foto"));
        pictureColumn.setCellFactory((TableColumn<Patient, Image> column) -> {
            return new TableCell<Patient, Image>() {

                protected void updateItem(javafx.scene.image.Image item, boolean empty) {
                    setText(null);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    ImageView imageView = new ImageView(item);
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                    setGraphic(imageView);
                }
            };
        });
        BooleanBinding noPatientSelected = Bindings.isEmpty(tvPacientes.getSelectionModel().getSelectedItems());
        this.btnDel.disableProperty().bind(noPatientSelected);
    }

    public void setApp(EntregableIPC app) {
        this.app = app;
    }

    public void initStage(Stage stage, ObservableList<Patient> paciente) {
        this.primaryStage = stage;
        this.tvPacientes.setItems(paciente);
    }

    @FXML
    private void addPacientePressed(ActionEvent event) {
        try {
            this.app.goToAddPaciente();
        } catch (Exception e) {
        }
    }

    @FXML
    private void delPacientePressed(ActionEvent event) {
         ClinicDBAccess mispacientes = ClinicDBAccess.getSingletonClinicDBAccess();
         this.paciente = FXCollections.observableList(mispacientes.getPatients()) ; 
         tvPacientes.setItems(paciente);
            int i =  tvPacientes.getSelectionModel().getSelectedIndex(); //elemento seleccionado
            if(i>-1) paciente.remove(i);//lo borra de la lista
            tvPacientes.getSelectionModel().clearSelection();
    }

    @FXML
    private void buscarPaciente(ActionEvent event) {
    }

//    private class TableCellImpl extends TableCell<Patient, javafx.scene.image.Image> {
//
//        public TableCellImpl() {
//        }
//
//        @Override
//        protected void updateItem(javafx.scene.image.Image item, boolean empty) {
//            setText(null);
//            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//            ImageView imageView = new ImageView(item);
//            imageView.setFitHeight(40);
//            imageView.setFitWidth(40);
//            setGraphic(imageView);
//        }
//    }
}
