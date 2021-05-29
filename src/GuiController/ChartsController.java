/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import services.FactureService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ChartsController implements Initializable {

    @FXML
    private PieChart PieChart;
    FactureService fs = new FactureService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            for (Map.Entry m : fs.StatistiqueFacturePaye().entrySet()) {

                ObservableList<PieChart.Data> pieChartData
                        = FXCollections.observableArrayList(
                                new PieChart.Data("Non Paye",(Integer) m.getKey()),
                                new PieChart.Data("Paye", (Integer) m.getValue()));
PieChart.setData(pieChartData);
        PieChart.setTitle("Factures Satatistiques");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
