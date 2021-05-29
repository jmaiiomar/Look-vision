/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiController;

import Entity.Client;
import Entity.Facture;
import Entity.Produit;
import Entity.Produit_Facture;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
public class HomeController implements Initializable {

    @FXML
    private Pane bck;
    @FXML
    private AnchorPane bckGolbale;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField tel;
    @FXML
    private TextField adrress;
    private TextField observation;
    @FXML
    private TextField avance;
    @FXML
    private ComboBox<String> Etat;
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker date;

    private TextField modelee;
    private TextField Categorie;
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;
    Client client = new Client();
    static Facture facture;
    static Produit produit;
    static Produit_Facture produit_facture;
    Collection<Produit> noDuplicates = new HashSet<Produit>();
    ClientService clientService = new ClientService();
    ProduitService produitService = new ProduitService();
    FactureService factureSerice = new FactureService();
    ProduitFactureService produitFactureService = new ProduitFactureService();
    ValidationChamps validation = new ValidationChamps();
    int idClient = 0;
    int idFacture = 0;
    @FXML
    private Label firstnameValidation;
    @FXML
    private Label lastnameValidation;
    @FXML
    private Label cinvalidation;
    @FXML
    private Label telvalidation;
    @FXML
    private Label addresseValidation;
    @FXML
    private Label avancevalidation;
    @FXML
    private Label etatValidation;
    @FXML
    private Label dateValidation;
    @FXML
    private Label prixValidation;
    @FXML
    private Label typeValidation;
    @FXML
    private TextField od_l;
    @FXML
    private TextField og_l;
    @FXML
    private TextField od_p;
    @FXML
    private TextField og_p;
    private TextField Quantite;
    private TextField PrixUnitaire;
    private Label quantiteValidation;
    @FXML
    private Text listProduitValidation;
    @FXML
    private TextField type_verre;
    @FXML
    private Label typeVerresValidation;
    @FXML
    private TextField PrixVerres;
    @FXML
    private TextField Monture;
    @FXML
    private Label MontureValidation;
    @FXML
    private TextField PrixMonture;
    @FXML
    private Label prixMontureValidation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<String> l = FXCollections.observableArrayList();
        l.add("Non Paye");
        l.add("Paye");
        Etat.setItems(l);
        final ObservableList<String> l2 = FXCollections.observableArrayList();
        l2.add("Espece");
        l2.add("cheque");
        l2.add("Virement Banquaire");
        l2.add("CNAM");
        l2.add("Banque Cofat");
        type.setItems(l2);
        // TODO
        //affichage("/Gui/Charts.fxml");

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
    private void ajouterProduit(ActionEvent event) {
//        if (ValidationChamps.isTextFieldNotEmpty(modelee, modeleValidation, "ce champ ne doit pas  etre  vide")  && ValidationChamps.isTextFieldNotEmptyNumber(PrixUnitaire, prixValidation)&& ValidationChamps.isTextFieldNotEmptyNumber(Quantite, quantiteValidation)) {

        if (!type_verre.getText().equals("") && !PrixVerres.getText().equals("")) {
      if (ValidationChamps.isTextFieldNotEmptyNumber(PrixVerres, prixValidation)) {

            produit = new Produit(type_verre.getText(), Integer.parseInt(PrixVerres.getText()));

            noDuplicates.add(produit);
            type_verre.setText("");
            PrixVerres.setText("");
        }
        }
        if (!Monture.getText().equals("") && !PrixMonture.getText().equals("")) {
      if (ValidationChamps.isTextFieldNotEmptyNumber(PrixMonture, prixMontureValidation)) {

            produit = new Produit(Monture.getText(), Integer.parseInt(PrixMonture.getText()));

            noDuplicates.add(produit);
            Monture.setText("");
            PrixMonture.setText("");
      }
        }

        vbox.getChildren().clear();
        for (Produit prod : noDuplicates) {
            ImageView img = new ImageView("/image/remove.png");
            img.setFitHeight(25);
            img.setFitWidth(25);
            HBox hb = new HBox();
            Label modelee = new Label("Produit  :  " + prod.getModele());
            Button btn = new Button("", img);
            double yy = 5;

            btn.getStyleClass().add("btns");

            hb.getChildren().addAll(modelee, btn);
            vbox.getChildren().addAll(hb);

            btn.setOnAction(
                    new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event
                ) {
                    vbox.getChildren().remove(hb);
                    noDuplicates.remove(prod);

                }
            }
            );

            //  }
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
    private void enablePageConfirmation(ActionEvent event) throws SQLException, ParseException {

        Collection<Integer> ListProduit = new HashSet<Integer>();
        String cinn = "";
        int tell = 0;
        if (ValidationChamps.isTextFieldNotEmpty(nom, firstnameValidation, "ce champ ne doit pas  etre  vide") && ValidationChamps.isTextFieldNotEmpty(prenom, lastnameValidation, "ce champ ne doit pas  etre  vide")) {
            String nomclient = nom.getText();
            String prenomclient = prenom.getText();
            String adresse = adrress.getText();

            client = new Client(nomclient, prenomclient, cinn, tell, adresse);
            if (!cin.getText().equals("")) {

                if (ValidationChamps.isTextFieldNotNumber(cin, cinvalidation)) {
                    cinn = cin.getText();
                    client.setCin(cinn);

                }
            }
            if (!tel.getText().equals("")) {

                if (ValidationChamps.isTextFieldNotNumber(tel, telvalidation)) {
                    tell = Integer.parseInt(tel.getText());
                    client.setTel(tell);
                }

            }

        }

        if (ValidationChamps.isTextFieldNotNumber(avance, avancevalidation) && ValidationChamps.isComboBoxNotEmpty(Etat, etatValidation, "vous devez choisire element") && ValidationChamps.isComboBoxNotEmpty(type, typeValidation, "vous devez choisire element") && ValidationChamps.isDatePickerNotEmpty(date, dateValidation, "ce champ ne doit pas  etre  vide")) {

            String dd = date.getValue().toString();
            facture = new Facture(Integer.parseInt(avance.getText() + 0) / 10, dd, client, Etat.getValue(), type.getValue(), og_l.getText(), od_l.getText(), og_p.getText(), od_p.getText());
        }

        if (idClient == 0 && client.getNomclient() != null && client.getPrenomclient() != null && facture != null && !noDuplicates.isEmpty()) {
            idClient = clientService.ajouter(client);

        }
        if (!noDuplicates.isEmpty()) {

            if (idClient != 0) {
                boolean x = clientService.modifier(client, idClient);
                if (facture != null && idFacture == 0) {
                    facture.getC().setId(idClient);
                    idFacture = factureSerice.ajouter(facture);

                }

                if (idFacture != 0) {

                    if (!noDuplicates.isEmpty()) {
                        for (Produit prod : noDuplicates) {
                            ListProduit.add(produitService.ajouterProduit(prod));
                        }
                        for (Integer idProduit : ListProduit) {
                            produit_facture = new Produit_Facture(idFacture, idProduit, 0);
                            produitFactureService.ajouter(produit_facture);

                        }
                        afficherAlert("Ajoute avec succes");

                        affichage("/Gui/Home.fxml");
                    }

                }
            }

        }
        listProduitValidation.setText("*Vous douvez ajouter au moins \n"
                + "une seul produit");

    }

}
