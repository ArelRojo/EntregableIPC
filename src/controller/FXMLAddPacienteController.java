/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author lisas
 */
public class FXMLAddPacienteController implements Initializable {

    @FXML
    private TextField tfDNI;
    @FXML
    private TextField tfName;
    private TextField tfAge;
  
    @FXML
    private Button btAccept;
    @FXML
    private Button btCancel;
    @FXML
    private TextField tfPhone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        public void initStage(Patient paciente){
        this.tfDNI.setText("");
        this.tfName.setText("");
        this.tfAge.setText("");
        this.tfPhone.setText("");
    }

    
}
