/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import BD.Database;
import Entity.Client;
import Entity.Facture;
import Iservice.IService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class FactureService implements IService<Facture> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public FactureService() {
        cnx = Database.getInstance().getConnexion();

    }

    @Override
    public int ajouter(Facture t) throws SQLException {
        
        String req = "INSERT INTO facture (date,avance,idClient,etat,typepaiment,og_loin,od_loin,og_pres,od_pres) values(?,?,?,?,?,?,?,?,?)";
        pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, t.getDate());
        pst.setFloat(2, t.getAvance());
        pst.setInt(3, t.getC().getId());
        pst.setString(4, t.getEtat());
        pst.setString(5, t.getTypePaiment());
        pst.setString(6, t.getOg_loin());
        pst.setString(7, t.getOd_loin());
        pst.setString(8, t.getOg_pres());
        pst.setString(9, t.getOd_pres());
        pst.execute();
        ResultSet rs = pst.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);

        }

        return generatedKey;

    }

    @Override
    public List<Facture> affciher() throws SQLException {
        List<Facture> arr = new ArrayList<>();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("SELECT f.*,c.nom,c.prenom FROM facture f INNER JOIN client c ON f.idClient = c.id");
        while (rs.next()) {

            int id = rs.getInt("f.id");
            int avance = rs.getInt("f.avance");
            String d = rs.getString("f.date");
            String ClientName = rs.getString("c.nom");
            String ClientLastName = rs.getString("c.prenom");
            String etat = rs.getString("f.etat");
            String typePaiement = rs.getString("f.typepaiment");
            String Og_loin = rs.getString("f.og_loin");
            String Od_loin = rs.getString("f.od_loin");
            String Oj_pres = rs.getString("f.og_pres");
            String Od_pres = rs.getString("f.od_pres");
            Client c = new Client();
            c.setNomclient(ClientName);
            c.setPrenomclient(ClientLastName);
            Facture f = new Facture(id, avance, d, c, etat, typePaiement, Og_loin, Od_loin, Od_pres, Od_pres);
            arr.add(f);

        }
        return arr;
    }

    @Override
    public boolean modifier(Facture t, int id) throws SQLException {
        String req = "UPDATE `facture` SET `avance` = '" + t.getAvance() + "', `etat` = '" + t.getEtat() + "', `od_loin` = '" + t.getOd_loin()+ "', `og_loin` = '" + t.getOg_loin()+ "', `od_pres` = '" + t.getOd_pres()+ "', `og_pres` = '" + t.getOg_pres()+ "' WHERE `facture`.`id` = '" + id + "'";
        pst = cnx.prepareStatement(req);

        return pst.execute();
    }

    public List<Facture> recherche(String x,int idc) throws SQLException {
        ObservableList<Facture> arr = FXCollections.observableArrayList();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from facture where idClient='"+idc+"' and ( avance like '%" + x + "%' or date like '%" + x + "%' or  etat like '%" + x + "%'or  typepaiment like '%" + x + "%')");
        while (rs.next()) {

            int idf = rs.getInt("id");
            int avance = rs.getInt("avance");
            String d = rs.getString("date");
            int idclient = rs.getInt("idClient");

            String etat = rs.getString("etat");
            String typePaiement = rs.getString("typepaiment");
            
            Client c = new Client();
            c.setId(idclient);
            String Og_loin = rs.getString("og_loin");
            String Od_loin = rs.getString("od_loin");
            String Oj_pres = rs.getString("og_pres");
            String Od_pres = rs.getString("od_pres");
            Facture f = new Facture(idf, avance,  d, c, typePaiement, etat, Og_loin, Od_loin, Od_pres, Od_pres);

            //Facture f = new Facture(idf, observation, avance, montant, d, c, typePaiement, etat);
            arr.add(f);

        }
        return arr;

    }

    public HashMap<Integer, Integer> StatistiqueFacturePaye() throws SQLException {
        HashMap<Integer, Integer> arr = new HashMap<Integer, Integer>();

        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("SELECT (SELECT COUNT(*) total FROM `facture` where etat='non paye') nonpaye,(SELECT COUNT(*) total FROM `facture` where etat='paye') paye FROM `facture` ");
        while (rs.next()) {

            int nonpaye = rs.getInt("nonpaye");
            int paye = rs.getInt("paye");
            arr.put(nonpaye, paye);
        }
        return arr;

    }

    public List<Facture> affciherClientFacture(int id) throws SQLException {
        List<Facture> arr = new ArrayList<>();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `facture` WHERE idClient='" + id + "' order by id desc");
        while (rs.next()) {

            int idf = rs.getInt("id");
            int avance = rs.getInt("avance");
            String d = rs.getString("date");
            int idclient = rs.getInt("idClient");

            String etat = rs.getString("etat");
            String typePaiement = rs.getString("typepaiment");
            Client c = new Client();
            c.setId(idclient);
             String Og_loin = rs.getString("og_loin");
            String Od_loin = rs.getString("od_loin");
            String Oj_pres = rs.getString("og_pres");
            String Od_pres = rs.getString("od_pres");
            Facture f = new Facture(idf, avance, d, c, typePaiement, etat, Og_loin, Od_loin, Od_pres, Od_pres);
            arr.add(f);

        }
        return arr;
    }

    public Facture getFactureByeId(int id) throws SQLException {

        Facture f = new Facture();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `facture` WHERE id='" + id + "'");
        while (rs.next()) {

            int idf = rs.getInt("id");
            int avance = rs.getInt("avance");
            String d = rs.getString("date");
            int idclient = rs.getInt("idClient");

            String etat = rs.getString("etat");
            String typePaiement = rs.getString("typepaiment");
            Client c = new Client();
            c.setId(idclient);
 String Og_loin = rs.getString("f.og_loin");
            String Od_loin = rs.getString("f.od_loin");
            String Oj_pres = rs.getString("f.og_pres");
            String Od_pres = rs.getString("f.od_pres");
             f = new Facture(idf, avance, d, c, etat, typePaiement, Og_loin, Od_loin, Od_pres, Od_pres);
        }
        return f;
    }

    @Override
    public boolean supprimer(int id) throws SQLException {
        String req = "DELETE FROM `facture` WHERE `facture`.`id` = " + id + "";
        pst = cnx.prepareStatement(req);
        return pst.execute();
    }

    @Override
    public List<Facture> recherche(String x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
