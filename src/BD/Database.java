/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.sql.DataSource;

/**
 *
 * @author Pytrooooo
 */
public class Database {

    private String url = "jdbc:mysql://127.0.0.1/opticien";
    private String username = "root";
    private String password = "";
    private Connection connexion;
    private static Database instance;

    public void afficherAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        //alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private Database() {
        try {
            connexion = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            afficherAlert("Pas de connexion verifier votre serveur!");
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnexion() {
        return connexion;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
