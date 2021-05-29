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
public class Client {

    private int id;
    private String nomclient;
    private String prenomclient;
    private String cin;
    private int tel;
    private String adresse;

    public Client() {
    }

    public Client(int id, String nomclient, String prenomclient, String cin, int tel, String adresse) {
        this.id = id;
        this.nomclient = nomclient;
        this.prenomclient = prenomclient;
        this.cin = cin;
        this.tel = tel;
        this.adresse = adresse;
    }

    public Client(String nomclient, String prenomclient, String cin, int tel, String adresse) {
        this.nomclient = nomclient;
        this.prenomclient = prenomclient;
        this.cin = cin;
        this.tel = tel;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getPrenomclient() {
        return prenomclient;
    }

    public void setPrenomclient(String prenomclient) {
        this.prenomclient = prenomclient;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nomclient=" + nomclient + ", prenomclient=" + prenomclient + ", cin=" + cin + ", tel=" + tel + ", adresse=" + adresse + '}';
    }
    
    
}
