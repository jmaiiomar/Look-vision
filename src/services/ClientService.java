/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import BD.Database;
import Entity.Client;
import Iservice.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ClientService implements IService<Client> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ClientService() {
        cnx = Database.getInstance().getConnexion();
    }

    @Override
    public int ajouter(Client t) {
        int generatedKey = 0;
        try {

            String req = "INSERT INTO client (nom,prenom,tel,cin,adresse) values(?,?,?,?,?)";

            pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getNomclient());
            pst.setString(2, t.getPrenomclient());
            pst.setInt(3, t.getTel());
            pst.setString(4, t.getCin());
            pst.setString(5, t.getAdresse());
            pst.execute();
            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                generatedKey = rs.getInt(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedKey;
    }

    @Override
    public List<Client> affciher() throws SQLException {

        List<Client> arr = new ArrayList<Client>();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from client order by id desc");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String adresse = rs.getString("adresse");
            int tel = rs.getInt("tel");
            String cin = rs.getString("cin");

            Client p = new Client(id, nom, prenom, cin, tel, adresse);
            arr.add(p);

        }
        return arr;
    }

    @Override
    public boolean modifier(Client t, int id) throws SQLException {

        String req = "UPDATE `client` SET `nom` = '" + t.getNomclient() + "', `prenom` = '" + t.getPrenomclient() + "', `tel` = '" + t.getTel() + "', `cin` = '" + t.getCin() + "', `adresse` = '" + t.getAdresse() + "' WHERE `client`.`id` = '" + id + "' ";
        pst = cnx.prepareStatement(req);

        return pst.execute();
    }

    @Override
    public List recherche(String x) throws SQLException {
        ObservableList<Client> arr = FXCollections.observableArrayList();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from client where nom like '%" + x + "%' or prenom like '%" + x + "%' or tel like '%" + x + "%' or  cin like '%" + x + "%'or  adresse like '%" + x + "%'");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String adresse = rs.getString("adresse");
            int tel = rs.getInt("tel");
            String cin = rs.getString("cin");

            Client p = new Client(id, nom, prenom, cin, tel, adresse);
            arr.add(p);

        }
        return arr;
    }

    @Override
    public boolean supprimer(int id) throws SQLException {
       String req = "DELETE FROM `client` WHERE `client`.`id` = " + id + "";
        pst = cnx.prepareStatement(req);
        return pst.execute();   
    }

}
