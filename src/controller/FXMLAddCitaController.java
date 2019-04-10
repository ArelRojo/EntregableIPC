/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thepu
 */
public class FXMLAddCitaController implements Initializable {

    @FXML
    private TextField tfDNI;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPhone;
    @FXML
    private Button btAccept;
    @FXML
    private Button btCancel;
    @FXML
    private DatePicker dateCita;
    @FXML
    private TextField screen1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void displayCita(ActionEvent event) {
       LocalDate today = LocalDate.now();
        LocalDate cdate = dateCita.getValue();
        if(cdate == null || cdate.isBefore(today)){
        screen1.setText("Selecciona una fecha válida!");
        
        }else{
        String date = String.valueOf(cdate);
        screen1.setText("Fecha de la cita:" + date);
        }
    }

    
}
