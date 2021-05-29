/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiController;

import Entity.Client;
import Entity.Produit;
import Entity.Produit_Facture;
import static GuiController.AjoutFactureClientExisteController.produit_facture;
import static GuiController.FactureController.clientFacture;
import static GuiController.HomeController.produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ClientService;
import services.FactureService;
import services.ProduitFactureService;
import services.ProduitService;
import services.ValidationChamps;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherFactureController implements Initializable {

    @FXML
    private TableView<Produit> tab;
    @FXML
    private TextField avance;
    @FXML
    private Label montant;

    private final ObservableList<Produit> l = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> etat;

    public static int idfactureDetail;
    @FXML
    private AnchorPane bck;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vbox;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField Cin;
    @FXML
    private TextField tel;
    @FXML
    private TextField adrsse;
    @FXML
    private Label labelDate;
    @FXML
    private Label typePaiement;
    @FXML
    private TableColumn<Produit, String> modele;

    @FXML
    private TableColumn<Produit, Integer> prix;

    @FXML
    private TextField modelee;
    private TextField Categorie;
    @FXML
    private TextField Prix;

    public float montantToatale = 0;
    /**
     * Initializes the controller class.
     */
    public Produit Produitselectionne;
    FactureService fs = new FactureService();
    ProduitService ps = new ProduitService();
    ClientService cs = new ClientService();
    ProduitFactureService produitFactureService = new ProduitFactureService();

    DetailFactureController detailFactureController = new DetailFactureController();
    @FXML
    private Label prenomValidation;
    @FXML
    private Label validationnom;
    @FXML
    private Label validationcin;
    @FXML
    private Label valdiationTel;
    @FXML
    private Label validationaddresse;
    @FXML
    private Label validationmodele;
    @FXML
    private Label validationprix;
    @FXML
    private TextField od_l;
    @FXML
    private TextField og_l;
    @FXML
    private TextField od_p;
    @FXML
    private TextField og_p;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            List<Produit> oo = ps.affciherProduitFacture(detailFactureController.factureDetail.getId());
            l.addAll(oo);
            for (Produit produit : oo) {
                montantToatale += produit.getPrix();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }

        modele.setCellValueFactory(new PropertyValueFactory<Produit, String>("modele"));
        prix.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("prix"));

        tab.setItems(l);

        /* Facture information*/
        labelDate.setText(detailFactureController.factureDetail.getDate());
        //observation.setText(detailFactureController.factureDetail.getObservation());
        final ObservableList<String> listeat = FXCollections.observableArrayList();
        listeat.add("Non Paye");
        listeat.add("Paye");
        etat.setItems(listeat);
        typePaiement.setText(detailFactureController.factureDetail.getEtat());
        etat.setValue(detailFactureController.factureDetail.getTypePaiment());
        avance.setText("" + (int) detailFactureController.factureDetail.getAvance());
        float montantrestant = montantToatale - detailFactureController.factureDetail.getAvance();
        if (montantrestant < 0) {
            montant.setText("0");
        } else {
            montant.setText("" + montantrestant);
        }
        od_l.setText(detailFactureController.factureDetail.getOd_loin());
        og_l.setText(detailFactureController.factureDetail.getOg_loin());
        od_p.setText(detailFactureController.factureDetail.getOd_pres());
        og_p.setText(detailFactureController.factureDetail.getOg_pres());

        /*Client information*/
        prenom.setText(clientFacture.getPrenomclient());
        nom.setText(clientFacture.getNomclient());
        tel.setText("" + clientFacture.getTel());
        Cin.setText("" + clientFacture.getCin());
        adrsse.setText(clientFacture.getAdresse());

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

    public void afficherAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert");
        //alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void select(MouseEvent event) {
        Produitselectionne = tab.getSelectionModel().getSelectedItem();
        Parent fxml;

        modelee.setText(Produitselectionne.getModele());
        Prix.setText("" + (int) Produitselectionne.getPrix());
    }

    @FXML
    private void ModifierClient(ActionEvent event) {
        try {
            //
            if (ValidationChamps.isTextFieldNotEmpty(nom, validationnom, "ce champ ne doit pas  etre  vide") && ValidationChamps.isTextFieldNotEmpty(prenom, prenomValidation, "ce champ ne doit pas  etre  vide") && ValidationChamps.isTextFieldNotNumber(Cin, validationcin) && ValidationChamps.isTextFieldNotNumber(tel, valdiationTel)) {

                Client c = new Client(nom.getText(), prenom.getText(), Cin.getText(), Integer.parseInt(tel.getText() + 0) / 10, adrsse.getText());

                if (!cs.modifier(c, clientFacture.getId())) {
                    clientFacture = c;
                    afficherAlert("Client est Modifié avec succes");
                } else {
                    afficherAlert("Erreur");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ModifierFacture(ActionEvent event) {
        try {
            int newAvance = 0;
            newAvance = Integer.parseInt(avance.getText()) + detailFactureController.factureDetail.getAvance();
            detailFactureController.factureDetail.setEtat(etat.getValue());
            detailFactureController.factureDetail.setAvance(newAvance);
            detailFactureController.factureDetail.setOd_loin(od_l.getText());
            detailFactureController.factureDetail.setOg_loin(og_l.getText());
            detailFactureController.factureDetail.setOd_pres(od_p.getText());
            detailFactureController.factureDetail.setOg_pres(og_p.getText());

            if (!fs.modifier(detailFactureController.factureDetail, detailFactureController.factureDetail.getId())) {
                afficherAlert("Facture est Modifié avec succes");
                avance.setText("" + newAvance);

                final ObservableList<Produit> l2 = FXCollections.observableArrayList();
                l2.addAll(ps.affciherProduitFacture(detailFactureController.factureDetail.getId()));
                tab.setItems(l2);
                montantToatale = 0;
                for (Produit produit : l2) {
                    montantToatale += produit.getPrix();

                }
                float montantRestant = 0;
                montantRestant = montantToatale - detailFactureController.factureDetail.getAvance();
                if (montantRestant < 0) {
                    montant.setText("0");
                } else {
                    montant.setText("" + montantRestant);
                }

            } else {
                afficherAlert("Erreur");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void SupprimerProduit(ActionEvent event) throws SQLException {
        if (Produitselectionne == null) {
            afficherAlert("Vous devez selectionneé la produit à supprimer !");

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez supprimer   " + Produitselectionne.getModele() + "?");

            ButtonType deleteGame = new ButtonType("Supprimer)");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(deleteGame, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == deleteGame) {
                if (!ps.supprimer(Produitselectionne.getId())) {
                    afficherAlert("Produit est supprimé avec succes");
                    final ObservableList<Produit> l2 = FXCollections.observableArrayList();
                    l2.addAll(ps.affciherProduitFacture(detailFactureController.factureDetail.getId()));
                    montantToatale = 0;
                    Produitselectionne = null;
                    modelee.setText("");
                    Prix.setText("");
                    for (Produit produit : l2) {
                        montantToatale += produit.getPrix();

                    }
                    float montantRestant = 0;
                    montantRestant = montantToatale - detailFactureController.factureDetail.getAvance();
                    if (montantRestant < 0) {
                        montant.setText("0");
                    } else {
                        montant.setText("" + montantRestant);
                    }
                    tab.setItems(l2);
                } else {
                    afficherAlert("Erreur");
                }
            }
        }
    }

    @FXML
    private void ModifierProduit(ActionEvent event) {
        try {
            if (ValidationChamps.isTextFieldNotEmpty(modelee, validationmodele, "ce champ ne doit pas  etre  vide") && ValidationChamps.isTextFieldNotEmptyNumber(Prix, validationprix)) {

                Produitselectionne = new Produit(Produitselectionne.getId(), modelee.getText(), Integer.parseInt(Prix.getText()));

                if (!ps.modifier(Produitselectionne, Produitselectionne.getId())) {
                    afficherAlert("Produit est Modifié avec succes");
                    ObservableList<Produit> l2 = FXCollections.observableArrayList();
                    l2.addAll(ps.affciherProduitFacture(detailFactureController.factureDetail.getId()));
                    tab.setItems(l2);
                    tab.refresh();
                    montantToatale = 0;
                    modelee.setText("");
                    Prix.setText("");
                    for (Produit produit : l2) {
                        montantToatale += produit.getPrix();

                    }
                    float montantRestant = 0;
                    montantRestant = montantToatale - detailFactureController.factureDetail.getAvance();
                    if (montantRestant < 0) {
                        montant.setText("0");
                    } else {
                        montant.setText("" + montantRestant);
                    }
                } else {
                    afficherAlert("Erreur");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerFacture(MouseEvent event) throws SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez supprimer cette facture?");

        ButtonType deleteGame = new ButtonType("Supprimer ");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(deleteGame, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == deleteGame) {
            List<Integer> listId = ps.recupererIdProduitFacture(detailFactureController.factureDetail.getId());
            if (fs.supprimer(detailFactureController.factureDetail.getId()));

            {
                for (Integer idp : listId) {
                    ps.supprimer(idp);
                }

                Parent fxml;

                try {

                    fxml = FXMLLoader.load(getClass().getResource("/Gui/DetailFacture.fxml"));
                    bck.getChildren().removeAll();
                    bck.getChildren().setAll(fxml);

                } catch (IOException ex) {
                    ex.getMessage();
                };
            }

        }
    }

    @FXML
    private void back(MouseEvent event) {
        affichage("/Gui/DetailFacture.fxml");
    }

    @FXML
    private void AjouterProduit(ActionEvent event) throws SQLException {
        if (ValidationChamps.isTextFieldNotEmpty(modelee, validationmodele, "ce champ ne doit pas  etre  vide") && ValidationChamps.isTextFieldNotEmptyNumber(Prix, validationprix)) {

            produit = new Produit(modelee.getText(), Integer.parseInt(Prix.getText()));
            int idProduit = ps.ajouterProduit(produit);
            if (idProduit != 0) {

                produit_facture = new Produit_Facture(detailFactureController.factureDetail.getId(), idProduit, 0);
                produitFactureService.ajouter(produit_facture);
                afficherAlert("produit ajouté avec succes");

                modelee.setText("");
                Prix.setText("");
                ObservableList<Produit> l2 = FXCollections.observableArrayList();

                l2.addAll(ps.affciherProduitFacture(detailFactureController.factureDetail.getId()));
                tab.setItems(l2);
                tab.refresh();
            } else {
                afficherAlert("verifier vous champs");

            }
        }

    }

}
