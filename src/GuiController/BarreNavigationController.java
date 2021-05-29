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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Amrouch
 */
public class BarreNavigationController implements Initializable {

    @FXML
    private Pane bck;
MenuController menu=new MenuController();
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       affichage(menu.pagename);
    }    

    @FXML
    private void afficherPageStatistique(MouseEvent event) {
        affichage("/Gui/Charts.fxml");
    }

    @FXML
    private void afficherPageFacutre(MouseEvent event) {
        affichage("/Gui/Facture.fxml");
    }

    @FXML
    private void afficherPageAjoutFacutre(MouseEvent event) {
                affichage("/Gui/Home.fxml");

    }
    
}
