/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import DBAccess.ClinicDBAccess;
import controller.FXMLAddMedicoController;
import controller.FXMLAddPacienteController;
import controller.FXMLCitasController;
import controller.FXMLMedicosController;
import controller.FXMLPacientesController;
import controller.mainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointment;
import model.Clinic;
import model.Doctor;
import model.Patient;
import model.Person;

/**
 *
 * @author lisas
 */
public class EntregableIPC extends Application {
    
    public Stage stage;
    public VBox vbroot;
    private  mainController controladormenu;
    ClinicDBAccess clinicDBAccess = ClinicDBAccess.getSingletonClinicDBAccess();
    private final ObservableList<Patient> pacientes = FXCollections.observableList(clinicDBAccess.getPatients());
    private final ObservableList<Appointment> citas = FXCollections.observableList(clinicDBAccess.getAppointments());
    private ObservableList<Clinic> clinica;
    private ObservableList<Doctor> medico;
    private ObservableList<Person> paciente;
 
  
    
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
//        vbroot = (VBox)FXMLLoader.load(getClass().getResource(""));
        FXMLLoader miCargador= new FXMLLoader(getClass().getResource("/view/FXMLMain.fxml"));
        vbroot = (VBox) miCargador.load();
          controladormenu= miCargador.<mainController>getController();
          controladormenu.setApp(this);
          controladormenu.initStage(stage);
        Scene scene = new Scene(vbroot);
        stage.setTitle("Pantalla principal");
        stage.setScene(scene);
//        stage.initModality(Modality.APPLICATION_MODAL);
           stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void goToMain() throws Exception{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLMain.fxml"));
        this.vbroot.getChildren().remove(1);
        VBox panel = (VBox) loader.load();
        this.vbroot.getChildren().add(panel.getChildren().get(1));
        stage.setTitle("Pantalla principal");
        
    }

    public void goToPacientes() throws Exception{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLPacientes.fxml"));
       this.vbroot.getChildren().remove(1);
       this.vbroot.getChildren().add((BorderPane) loader.load());
        FXMLPacientesController pacienteController = loader.getController();
        pacienteController.setApp(this);
        pacienteController.initStage(stage, this.paciente);
        stage.setTitle("Pacientes");
    }

    public void goToMedicos()throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLMedicos.fxml"));
        this.vbroot.getChildren().remove(1);
        this.vbroot.getChildren().add((BorderPane) loader.load());
        FXMLMedicosController medicosController = loader.getController();
        medicosController.setApp(this);
        medicosController.initStage(stage, this.medico);
        stage.setTitle("Médicos");
    }

    public void goToCitas()throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLCitas.fxml"));
        this.vbroot.getChildren().remove(1);
        this.vbroot.getChildren().add((BorderPane) loader.load());
        FXMLCitasController citasController = loader.getController();
        citasController.setApp(this);
        citasController.initStage(stage, this.citas);
        stage.setTitle("Citas");
    }

    public void goToAddPaciente()throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLAddPaciente.fxml"));
        FXMLAddPacienteController addcontrolador = loader.getController();
        Parent root = (Parent)loader.load();
        Stage estageActual = new Stage();
        Scene scene = new Scene(root);
        estageActual.setScene(scene);
        estageActual.initModality(Modality.APPLICATION_MODAL);
        estageActual.showAndWait();
    }

    public void goToAddMedico()throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLAddMedico.fxml"));
        FXMLAddMedicoController addMcontrolador = loader.getController();
        Parent root = (Parent)loader.load();
        Stage estageActual = new Stage();
        Scene scene = new Scene(root);
        estageActual.setScene(scene);
        estageActual.initModality(Modality.APPLICATION_MODAL);
        estageActual.showAndWait();
        
    }
    
}