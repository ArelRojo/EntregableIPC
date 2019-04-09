/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import app.EntregableIPC;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class FXMLCitasController implements Initializable {

    @FXML
    private Button btAdd;
    @FXML
    private Button btDel;
    private TableView<Appointment> tvCitas;
    private EntregableIPC app;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     *
     * @param app
     */
    public void setApp(EntregableIPC app) {
        this.app = app;
    }

    /**
     *
     * @param stage
     * @param citas
     */
    public void initStage(Stage stage, ObservableList<Appointment> citas) {
        this.primaryStage = stage;
        this.tvCitas.setItems(citas);
    }
    
}
