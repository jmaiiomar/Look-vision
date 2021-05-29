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
import static GuiController.FactureController.clientFacture;
import static GuiController.HomeController.produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ClientService;
import services.FactureService;
import services.ProduitFactureService;
import services.ProduitService;
import services.ValidationChamps;

/**
 * FXML Controller class
 *
 * @author Amrouch
 */
public class AjoutFactureClientExisteController implements Initializable {
    
    @FXML
    private AnchorPane bckGolbale;
    @FXML
    private Pane bck;
    @FXML
    private TextField avance;
    @FXML
    private Label avancevalidation;
    @FXML
    private ComboBox<String> Etat;
    @FXML
    private Label etatValidation;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Label typeValidation;
    @FXML
    private DatePicker date;
    @FXML
    private Label dateValidation;
    private TextField modelee;
    private Label modeleValidation;
    private TextField Categorie;
    private Label categorieValidation;
    private TextField Prix;
    @FXML
    private Label prixValidation;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vbox;
    @FXML
    private TextField od_l;
    @FXML
    private TextField og_l;
    @FXML
    private TextField od_p;
    @FXML
    private TextField og_p;
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
    private TextField Quantite;
    private Label quantiteValidation;
    private TextField PrixUnitaire;
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
    private void enablePageConfirmation(ActionEvent event) throws SQLException {
        
        Collection<Integer> ListProduit = new HashSet<Integer>();
        int cinn = 0;
        int tell = 0;
        client.setId(clientFacture.getId());
        idClient=clientFacture.getId();
        if (ValidationChamps.isTextFieldNotNumber(avance, avancevalidation)&& ValidationChamps.isComboBoxNotEmpty(Etat, etatValidation, "vous devez choisire element") && ValidationChamps.isComboBoxNotEmpty(type, typeValidation, "vous devez choisire element") && ValidationChamps.isDatePickerNotEmpty(date, dateValidation, "ce champ ne doit pas  etre  vide")) {
            
            String dd = date.getValue().toString();
            facture = new Facture(Integer.parseInt(avance.getText()+0)/10, dd, client, Etat.getValue(), type.getValue(), og_l.getText(), od_l.getText(), og_p.getText(), od_p.getText());
        }
        
     if (!noDuplicates.isEmpty()) {
        if (idClient != 0) {
            if (facture != null && idFacture == 0) {
                facture.getC().setId(idClient);
                idFacture = factureSerice.ajouter(facture);
                
            }
            if (idFacture != 0) {
                
               
                    for (Produit prod : noDuplicates) {
                        ListProduit.add(produitService.ajouterProduit(prod));
                    }
                    for (Integer idProduit : ListProduit) {
                        produit_facture = new Produit_Facture(idFacture, idProduit, 0);
                        produitFactureService.ajouter(produit_facture);
                        
                    }
                    afficherAlert("Ajoute avec succes");
                    
                    affichage("/Gui/ajoutFactureClientExiste.fxml");
                }
               

            }
        }
     listProduitValidation.setText("*Vous douvez ajouter au moins \n"
        + "une seul produit");
    }
    
}
