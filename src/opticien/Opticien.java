/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticien;

import BD.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class Opticien extends Application {

    @Override
    public void start(Stage stage) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/Gui/Menu.fxml"));
            Scene scene = new Scene(root, 1300,800 );
            stage.setResizable(false);
            Image img = new Image("/image/logoo.gif");
            stage.getIcons().add(img);
            stage.setTitle("Look Vision");
            stage.setScene(scene);
            stage.show();
            
                   

            
            
        } catch (IOException ex) {
            Logger.getLogger(Opticien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
