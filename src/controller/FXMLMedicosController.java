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
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jfxtras.css.converters.IntegerConverter;
import model.Days;
import model.Doctor;
import model.ExaminationRoom;
import model.Patient;
import model.Person;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class FXMLMedicosController implements Initializable {

    //Declaramos los textfield
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidos;
    @FXML
    private TextField tfDNI;
    @FXML
    private TextField tfTelef;

    //Declaramos las tablas y las columnas
    @FXML
    private TableView<Doctor> tvMedicos;
    @FXML
    private TableColumn<Doctor, String> nomColumn;
    @FXML
    private TableColumn<Doctor, String> cognColumn;
    @FXML
    private TableColumn<Doctor, String> idColumn;
    @FXML
    private TableColumn<Doctor, String> roomColumn;
    @FXML
    private TableColumn<Doctor, String> telColumn;

    //Botones
    @FXML
    private Button btDel;
    @FXML
    private Button addButton;

    private EntregableIPC app;
    public Stage primaryStage;
    private ObservableList<Doctor> medico;
    private ObservableList<ExaminationRoom> rooms;
    private int pos;
    @FXML
    private ChoiceBox<ExaminationRoom> ddSalas;
    ClinicDBAccess dao;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClinicDBAccess mismedicos = ClinicDBAccess.getSingletonClinicDBAccess();
        this.medico = FXCollections.observableList(mismedicos.getDoctors());
        this.rooms = FXCollections.observableList(mismedicos.getExaminationRooms());
        tvMedicos.setItems(medico);
        idColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentifier()));
        nomColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        cognColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSurname()));
        telColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelephon()));
        roomColumn.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getExaminationRoom().getIdentNumber()));
        
        this.ddSalas.setConverter(new StringConverter<ExaminationRoom>() {
            @Override
            public String toString(ExaminationRoom object) {
                return object.getIdentNumber()+"";
            }

            @Override
            public ExaminationRoom fromString(String string) {
                Integer idsala = Integer.parseInt(string);
                return ddSalas.getItems().stream().filter(sala -> new Integer(sala.getIdentNumber()).equals(idsala)).findFirst().orElse(null);
            }
        });
//        horaIni.setCellValueFactory(new PropertyValueFactory<>("horaIniciodeTurno"));
//        horaIni.setCellFactory((TableColumn<Doctor, LocalTime> column) -> {
//            return new TableCell<Doctor, LocalTime>() {
//                
//                protected void updateItem(LocalTime item, boolean empty) {
//                   
//                    if (item == null || empty) {
//                        setText(null);
//                    } else {
//                        setText(item.format(DateTimeFormatter.ofPattern("hh:mm")));
//                    }
//                }
//            };
//
//        });
//        horaFin.setCellValueFactory(new PropertyValueFactory<>("horaIniciodeTurno"));
//        horaFin.setCellFactory((TableColumn<Doctor, LocalTime> column) -> {
//            return new TableCell<Doctor, LocalTime>() {
//                
//                protected void updateItem(LocalTime item, boolean empty) {
//                   
//                    if (item == null || empty) {
//                        setText(null);
//                    } else {
//                        setText(item.format(DateTimeFormatter.ofPattern("hh:mm")));
//                    }
//                }
//            };
//
//        });

//        fotoColumn.setCellValueFactory(new PropertyValueFactory<>("foto"));
//        fotoColumn.setCellFactory((TableColumn<Doctor, Image> column) -> {
//            return new TableCell<Doctor, Image>() {
//
//                protected void updateItem(Image item, boolean empty) {
//                    setText(null);
//                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//                    ImageView imageView = new ImageView(item);
//                    imageView.setFitHeight(40);
//                    imageView.setFitWidth(40);
//                    setGraphic(imageView);
//                }
//            };
//        });

   
        final ObservableList<Doctor> tablaDocSel = tvMedicos.getSelectionModel().getSelectedItems();
        tablaDocSel.addListener(selectorTablaDoctor);

        BooleanBinding noDoctorSelected = Bindings.isEmpty(tvMedicos.getSelectionModel().getSelectedItems());
        this.btDel.disableProperty().bind(noDoctorSelected);
    }

    public void setApp(EntregableIPC app) {
        this.app = app;
    }

    public void initStage(Stage stage, ClinicDBAccess clinicDBAccess) {
        this.primaryStage = stage;
        this.dao = clinicDBAccess;
        this.tvMedicos.setItems(medico);
        List<ExaminationRoom> lsala = dao.getExaminationRooms();
        ddSalas.getItems().addAll(lsala);
        tvMedicos.prefHeightProperty().bind(primaryStage.heightProperty());
       
        

    }

    @FXML
    private void AddMedico(ActionEvent event) {
//        ClinicDBAccess mispersonas = ClinicDBAccess.getSingletonClinicDBAccess();
//        this.medico = FXCollections.observableList(mispersonas.getDoctors()) ; 
//        tvMedicos.setItems(medico);

        Doctor persona = new Doctor();
        persona.setName(tfNombre.getText());
        persona.setSurname(tfApellidos.getText());
        persona.setIdentifier(tfDNI.getText());
        persona.setTelephon(tfTelef.getText());
        persona.setExaminationRoom(ddSalas.getSelectionModel().getSelectedItem());
        medico.add(persona);
        this.tvMedicos.refresh();
        this.app.save();
        

    }

    @FXML
    private void delMedico(ActionEvent event) {

        pos = tvMedicos.getSelectionModel().getSelectedIndex(); //elemento seleccionado
        if (pos > -1) {
            medico.remove(pos);//lo borra de la lista
        }
        tvMedicos.getSelectionModel().clearSelection();
        this.tvMedicos.refresh();
        this.app.save();
    }
    private final ListChangeListener<Doctor> selectorTablaDoctor
            = new ListChangeListener<Doctor>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Doctor> c) {
            ponerMedicoSeleccionado();
        }
    };

    public Doctor getTablaMedicosSeleccionados() {
        if (tvMedicos != null) {
            List<Doctor> tabla = tvMedicos.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Doctor competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;

            }

        }
        return null;

    }

    private void ponerMedicoSeleccionado() {
        final Doctor persona = getTablaMedicosSeleccionados();
        pos = medico.indexOf(persona);
        if (persona != null) {
            tfNombre.setText(persona.getName());
            tfApellidos.setText(persona.getSurname());
            tfDNI.setText(persona.getIdentifier());
            tfTelef.setText(persona.getTelephon());

        }
    }

}
