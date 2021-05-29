/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Amrouch
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane bck;
    public static String pagename;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void affichage(String x) {
        Parent fxml;

        try {

            fxml = FXMLLoader.load(getClass().getResource(x));
            bck.getChildren().removeAll();
            bck.getChildren().setAll(fxml);

        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    @FXML
    private void afficherPageStatistique(MouseEvent event) {
        pagename="/Gui/Charts.fxml";
                affichage("/Gui/barreNavigation.fxml");

    }

    @FXML
    private void AfficherPageFacture(MouseEvent event) {
                pagename="/Gui/Facture.fxml";

                affichage("/Gui/barreNavigation.fxml");

    }

    @FXML
    private void AfficherPageAjoutFacture(MouseEvent event) {
                pagename="/Gui/Home.fxml";
               
        affichage("/Gui/barreNavigation.fxml");

    }
    
}
