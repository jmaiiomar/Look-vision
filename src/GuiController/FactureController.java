/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiController;

import Entity.Client;
import Entity.Facture;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ClientService;
import services.FactureService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FactureController implements Initializable {

    @FXML
    private AnchorPane facturePane;
    @FXML
    private TableView<Client> tab;
    @FXML
    private TableColumn<Client, String> prenom;
    @FXML
    private TableColumn<Client, String> nom;
    @FXML
    private TableColumn<Client, Integer> cin;
    @FXML
    private TableColumn<Client, String> addresse;
    private final ObservableList<Client> l = FXCollections.observableArrayList();

    ClientService cs = new ClientService();
    @FXML
    private TableColumn<Client, Integer> tel;

    
    public static int idClient;
    public static Client clientFacture;
    @FXML
    private TextField inputRecherche;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<Client> oo = cs.affciher();
            l.addAll(oo);
        } catch (SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("nomclient"));
        nom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenomclient"));
        cin.setCellValueFactory(new PropertyValueFactory<Client, Integer>("cin"));
        tel.setCellValueFactory(new PropertyValueFactory<Client, Integer>("tel"));
        addresse.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));

        tab.setItems(l);

    }
        

    @FXML
    private void select(MouseEvent event) {
       clientFacture = tab.getSelectionModel().getSelectedItem();
        idClient=clientFacture.getId();
        
              Parent fxml;
        try {

            fxml = FXMLLoader.load(getClass().getResource("/Gui/DetailFacture.fxml"));
            facturePane.getChildren().removeAll();
            facturePane.getChildren().setAll(fxml);

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void recherche(MouseEvent event) throws SQLException {
       
           final ObservableList<Client> l2 = FXCollections.observableArrayList();

            l2.addAll(cs.recherche(inputRecherche.getText()));
              tab.setItems(l2);
    }

}
