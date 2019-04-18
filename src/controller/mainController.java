/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import app.EntregableIPC;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author lisas
 */
public class mainController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem imPacientes;
    @FXML
    private MenuItem imMedicos;
    private EntregableIPC app;
    private Stage primaryStage;
    @FXML
    private VBox pagincio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     public void setApp(EntregableIPC app){
        this.app = app;
    }
     
     public void initStage(Stage stage){
        this.primaryStage = stage;
         primaryStage.setResizable(false);
    }
    @FXML
    private void irPrincipalPressed(ActionEvent event) throws Exception{
        this.app.goToMain();
    }

    @FXML
    private void imPacientesPressed(ActionEvent event) throws Exception {
        this.app.goToPacientes();
    }

    @FXML
    private void imMedicosPressed(ActionEvent event) throws Exception {
        this.app.goToMedicos();
    }

    @FXML
    private void CitasPressed(ActionEvent event) throws Exception {
        this.app.goToCalendario();
    }

    @FXML
    private void closeAction(ActionEvent event) {
        primaryStage.close();
    }

    
    
}
