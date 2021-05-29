/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import BD.Database;
import Entity.Produit;
import GuiController.AfficherFactureController;
import Iservice.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ProduitService implements IService<Produit> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProduitService() {
        cnx = Database.getInstance().getConnexion();

    }

    @Override
    public int ajouter(Produit t) throws SQLException {
        String req = "INSERT INTO produit (categorie,modele,prix,quantite) values(?,?,?,?)";

        pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, t.getCategory());
        pst.setString(2, t.getModele());
        pst.setFloat(3, t.getPrix());
        pst.setInt(4, t.getQuantite());

        pst.execute();
        ResultSet rs = pst.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);

        }

        return generatedKey;
    }

    public int ajouterProduit(Produit t) throws SQLException {
        String req = "INSERT INTO produit (categorie,modele,prix,quantite) values(?,?,?,?)";
        pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, t.getCategory());
        pst.setString(2, t.getModele());
        pst.setFloat(3, t.getPrix());
        pst.setInt(4, t.getQuantite());

        pst.execute();
        ResultSet rs = pst.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);

        }

        return generatedKey;
    }

    @Override
    public List<Produit> affciher() throws SQLException {
        List<Produit> arr = new ArrayList<Produit>();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from produit");
        while (rs.next()) {
            String categorie = rs.getString("categorie");
            String modele = rs.getString("modele");
            int prix = rs.getInt("prix");
            int quantite = rs.getInt("quantite");
            int id = rs.getInt("id");
            Produit p = new Produit(id, categorie, modele, prix, quantite);
            arr.add(p);

        }
        return arr;
    }

    @Override
    public boolean modifier(Produit t, int id) throws SQLException {

        String req = "UPDATE `produit` SET `categorie` = '" + t.getCategory() + "', `modele` = '" + t.getModele() + "', `prix` = '" + t.getPrix() + "',`quantite` = '" + t.getQuantite() + "' WHERE `produit`.`id` = '" + t.getId() + "'";
        pst = cnx.prepareStatement(req);
        return pst.execute();
    }

    @Override
    public ObservableList<Produit> recherche(String x) throws SQLException {
        ObservableList<Produit> arr = FXCollections.observableArrayList();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from produit where categorie like '%" + x + "%' or modele like '%" + x + "%' or prix like '%" + x + "%' or  quantite like '%" + x + "%'");
        while (rs.next()) {
            String categorie = rs.getString("categorie");
            String modele = rs.getString("modele");
            float prix = rs.getFloat("prix");
            int quantite = rs.getInt("quantite");
            Produit p = new Produit(categorie, modele, prix, quantite);
            arr.add(p);

        }
        return arr;

    }

    public ObservableList<Produit> affciherProduitFacture(int idfacture) throws SQLException {
        ObservableList<Produit> arr = FXCollections.observableArrayList();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("SELECT p.*,pf.id FROM produit p INNER JOIN produit_facture pf ON p.id = pf.idproduit  WHERE pf.idfacture='" + idfacture + "'");
        while (rs.next()) {
            String categorie = rs.getString("p.categorie");
            String modele = rs.getString("p.modele");
            int prix = rs.getInt("p.prix");
            ///ici je prend le la quantite du table prouit_facture et je le met dans constructeur de Produit pour que je fais pas hasmap ...(ne5o f quantite te3 table produit_facture ou n7ot fehe f produit ili hye mch nefsha t3agriba ye3ni :)
            //nefs chy hna m3e id
            int idProd = rs.getInt("p.id");
            int quantite = rs.getInt("p.quantite");

            Produit p = new Produit(idProd, categorie, modele, prix, quantite);
            arr.add(p);

        }
        return arr;
    }

    public Produit getById(int id) throws SQLException {
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from produit where id='" + id + "'");
        Produit p = null;
        while (rs.next()) {
            String categorie = rs.getString("categorie");
            String modele = rs.getString("modele");
            int prix = rs.getInt("prix");
            int quantite = rs.getInt("quantite");
            int idd = rs.getInt("id");
            p = new Produit(idd, categorie, modele, prix, quantite);
        }
        return p;
    }

    @Override
    public boolean supprimer(int id) throws SQLException {
        String req = "DELETE FROM `produit` WHERE `produit`.`id` = " + id + "";
        pst = cnx.prepareStatement(req);
        return pst.execute();
    }

    public ObservableList<Integer> recupererIdProduitFacture(int idfacture) throws SQLException {
        ObservableList<Integer> arr = FXCollections.observableArrayList();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from produit_facture where idfacture='" + idfacture + "'");
        while (rs.next()) 
        {
            int quantite = rs.getInt("idproduit");
            arr.add(quantite);

        }
        System.out.println("array: " + arr);
        return arr;
    }
    

}
/*

String req = "DELETE FROM `produit` WHERE `produit`.`id` = '" + idd + "'";
            pst = cnx.prepareStatement(req);
            pst.execute();
*/
