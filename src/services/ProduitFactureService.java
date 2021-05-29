/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import BD.Database;
import Entity.Produit_Facture;
import Iservice.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author asus
 */
public class ProduitFactureService implements IService<Produit_Facture> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProduitFactureService() {
        cnx = Database.getInstance().getConnexion();

    }

    @Override
    public int ajouter(Produit_Facture t) throws SQLException {
       
           int generatedKey = 0;
        String req = "INSERT INTO produit_facture (idproduit,idfacture,quantite) values(?,?,?)";

        pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(2, t.getIdFacture());
        pst.setInt(1, t.getIdProduit());
        pst.setInt(3, t.getQuantite());

        pst.execute();
         ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                generatedKey = rs.getInt(1);

            }
            return generatedKey;

    }

    @Override
    public List<Produit_Facture> affciher() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifier(Produit_Facture t, int id) throws SQLException {
        String req = "UPDATE `produit_facture` SET `quantite` = '" + t.getQuantite() + "' where id='" + id + "'";
        pst = cnx.prepareStatement(req);
        return pst.execute();
    }

  

    @Override
    public List<Produit_Facture> recherche(String x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
