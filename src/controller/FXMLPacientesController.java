/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * FXML Controller class
 *
 * @author lisas
 */
package controller;
import DBAccess.ClinicDBAccess;
import app.EntregableIPC;
import java.awt.Image;
import java.net.URL;

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

import model.Patient;



public class FXMLPacientesController implements Initializable {
    //botones
    private Button btnDel;
    @FXML
    private Button addButton;
    @FXML
    private Button btDel;
    //tabl
     @FXML
    private TableView<Patient> tvPacientes;
     @FXML
    private TableColumn<Patient, String> nomColumn;
    @FXML
    private TableColumn<Patient, String> cognColumn;
    @FXML
    private TableColumn<Patient, String> idColumn;
    @FXML
    private TableColumn<Patient, String> telColumn;
    @FXML
    private TableColumn<Patient, Image> fotoColumn;
    //textfields
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidos;
    @FXML
    private TextField tfDNI;
    @FXML
    private TextField tfTelef;
    
   
    private EntregableIPC app;
    private Stage primaryStage;
    private ObservableList<Patient> paciente;
 

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClinicDBAccess mispacientes = ClinicDBAccess.getSingletonClinicDBAccess();
        this.paciente = FXCollections.observableList(mispacientes.getPatients()) ; 
        tvPacientes.setItems(paciente);
        

        idColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentifier()));
        nomColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        cognColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSurname()));
        telColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelephon()));
        fotoColumn.setCellValueFactory(new PropertyValueFactory<>("foto"));
        fotoColumn.setCellFactory((TableColumn<Patient, Image> column) -> {
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
    private void AddPaciente(ActionEvent event) {
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
}
