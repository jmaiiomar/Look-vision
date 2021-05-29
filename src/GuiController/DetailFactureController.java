/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiController;

import Entity.Client;
import Entity.Facture;
import static GuiController.FactureController.clientFacture;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
public class DetailFactureController implements Initializable {

    @FXML
    private TableView<Facture> tab1;
    @FXML
    private TableColumn<Facture, Float> avance;
    @FXML
    private TableColumn<Facture, String> date;
    @FXML
    private TableColumn<Facture, String> type;
    @FXML
    private TableColumn<Facture, String> etat;

    private final ObservableList<Facture> l = FXCollections.observableArrayList();

    FactureService fs = new FactureService();
    FactureController factureController = new FactureController();
    @FXML
    private AnchorPane bck;

    public static Facture factureDetail;
    @FXML
    private TextField inputRecherche;
    @FXML
    private Label nomclient;
    @FXML
    private Label prenomclient;
    @FXML
    private TableColumn<Facture, String> od_l;
    @FXML
    private TableColumn<Facture, String> od_p;
    @FXML
    private TableColumn<Facture, String> og_p;
    @FXML
    private TableColumn<Facture, String> og_l;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Facture> oo = fs.affciherClientFacture(factureController.idClient);
            l.addAll(oo);
        } catch (SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }

        avance.setCellValueFactory(new PropertyValueFactory<Facture, Float>("avance"));
        date.setCellValueFactory(new PropertyValueFactory<Facture, String>("date"));
        etat.setCellValueFactory(new PropertyValueFactory<Facture, String>("etat"));
        type.setCellValueFactory(new PropertyValueFactory<Facture, String>("typePaiment"));
        od_l.setCellValueFactory(new PropertyValueFactory<Facture, String>("od_loin"));
        od_p.setCellValueFactory(new PropertyValueFactory<Facture, String>("og_pres"));
        og_p.setCellValueFactory(new PropertyValueFactory<Facture, String>("od_pres"));
        og_l.setCellValueFactory(new PropertyValueFactory<Facture, String>("og_loin"));

        tab1.setItems(l);

        
        /**infoClient*/
        nomclient.setText(clientFacture.getNomclient());
        prenomclient.setText(clientFacture.getPrenomclient());
    }

    @FXML
    private void select(MouseEvent event) {
        factureDetail = tab1.getSelectionModel().getSelectedItem();
        Parent fxml;

        try {

            fxml = FXMLLoader.load(getClass().getResource("/Gui/AfficherFacture.fxml"));
            bck.getChildren().removeAll();
            bck.getChildren().setAll(fxml);

        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void recherche(MouseEvent event) throws SQLException {
       final ObservableList<Facture> l2 = FXCollections.observableArrayList();

            l2.addAll(fs.recherche(inputRecherche.getText(),factureController.idClient));
              tab1.setItems(l2);
        
    }

    @FXML
    private void supprimerClient(MouseEvent event) throws SQLException {
        
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setHeaderText(null);
    alert.setContentText("Vous voulez supprimer   "+clientFacture.getPrenomclient()+" "+clientFacture.getNomclient()+"? (Tous les données relatives à ce client vont être supprimées)");

    ButtonType deleteGame = new ButtonType("Supprimer Client)");
    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    alert.getButtonTypes().setAll(deleteGame, buttonTypeCancel);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == deleteGame){
       ClientService cs=new ClientService();
        if(cs.supprimer(clientFacture.getId()));
        {
            Parent fxml;

        try {

            fxml = FXMLLoader.load(getClass().getResource("/Gui/Facture.fxml"));
            bck.getChildren().removeAll();
            bck.getChildren().setAll(fxml);

        } catch (IOException ex) {
            ex.getMessage();
        };
        }
        
        
    }
    }

    @FXML
    private void ajoutFactureClientExiste(ActionEvent event) {
        Parent fxml;

        try {

            fxml = FXMLLoader.load(getClass().getResource("/Gui/ajoutFactureClientExiste.fxml"));
            bck.getChildren().removeAll();
            bck.getChildren().setAll(fxml);

        } catch (IOException ex) {
            ex.getMessage();
        };
        
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
    private void back(MouseEvent event) {
        affichage("/Gui/Facture.fxml");
    }
    

}
