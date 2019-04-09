/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Days;
import model.Doctor;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class FXMLAddMedicoController implements Initializable {

    @FXML
    private TextField tfDNI;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfTelef;
    @FXML
    private TextField tfDays;
    @FXML
    private TextField tfHoraIni;
    @FXML
    private TextField tfHoraFin;
    @FXML
    private TextField tfSala;
    @FXML
    private Button btAccept;
    @FXML
    private Button btCancel;
    @FXML
    private TextField tfSurname;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     *
     * @param medico
     */
    public void initStage(Doctor medico){
        this.tfDNI.setText("");
        this.tfName.setText("");
        this.tfSurname.setText("");
        this.tfTelef.setText("");
        this.tfDays.setText("");
        this.tfHoraIni.setText("");
        this.tfHoraFin.setText("");
        this.tfSala.setText("");
    }
   

    @FXML
    private void b_aceptar(ActionEvent event) {
        
    }
    
}
