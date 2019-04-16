/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.paint.Color;

/**
 *
 * @author thepu
 */
public class DiaCelda extends DateCell {
    
             String newline = System.getProperty("line.separator");
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
 
                // Show Weekends in blue color
                DayOfWeek day = DayOfWeek.from(item);
                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                    this.setTextFill(Color.ROSYBROWN);
                    this.setDisable(true);
                  
                 
                 
                 
                 this.setText(this.getText()+"\r");
                }
                else this.setText(this.getText()+"\rlibre");
            }
    
}
