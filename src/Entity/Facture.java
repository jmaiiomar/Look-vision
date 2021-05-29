/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author asus
 */
public class Facture {

    private int id;
    private int avance;
    private String date;
    private Client c;
    private String client;
    private String etat;
    private String typePaiment;
    private String og_loin; 
    private String od_loin; 
    private String og_pres; 
    private String od_pres; 
    public Facture() {
    }

    public Facture(int id, int avance,  String date, Client c, String etat, String typePaiment, String og_loin, String od_loin, String og_pres, String od_pres) {
        this.id = id;
        this.avance = avance;
        this.date = date;
        this.c = c;
        this.etat = etat;
        this.typePaiment = typePaiment;
        this.og_loin = og_loin;
        this.od_loin = od_loin;
        this.og_pres = og_pres;
        this.od_pres = od_pres;
    }

    public Facture(int avance, String date, Client c, String etat, String typePaiment, String og_loin, String od_loin, String og_pres, String od_pres) {
        this.avance = avance;
        this.date = date;
        this.c = c;
        this.client = client;
        this.etat = etat;
        this.typePaiment = typePaiment;
        this.og_loin = og_loin;
        this.od_loin = od_loin;
        this.og_pres = og_pres;
        this.od_pres = od_pres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvance() {
        return avance;
    }

    public void setAvance(int avance) {
        this.avance = avance;
    }

   

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTypePaiment() {
        return typePaiment;
    }

    public void setTypePaiment(String typePaiment) {
        this.typePaiment = typePaiment;
    }

    public String getOg_loin() {
        return og_loin;
    }

    public void setOg_loin(String og_loin) {
        this.og_loin = og_loin;
    }

    public String getOd_loin() {
        return od_loin;
    }

    public void setOd_loin(String od_loin) {
        this.od_loin = od_loin;
    }

    public String getOg_pres() {
        return og_pres;
    }

    public void setOg_pres(String og_pres) {
        this.og_pres = og_pres;
    }

    public String getOd_pres() {
        return od_pres;
    }

    public void setOd_pres(String od_pres) {
        this.od_pres = od_pres;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", avance=" + avance +  ", date=" + date + ", c=" + c + ", client=" + client + ", etat=" + etat + ", typePaiment=" + typePaiment + ", og_loin=" + og_loin + ", od_loin=" + od_loin + ", og_pres=" + og_pres + ", od_pres=" + od_pres + '}';
    }
    

}