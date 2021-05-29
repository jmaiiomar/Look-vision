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
public class Produit_Facture {
    private int id;
    private int idFacture;
    private int idProduit;
    private int quantite;

    public Produit_Facture() {
    }

    public Produit_Facture(int idFacture, int idProduit, int quantite) {
        this.idFacture = idFacture;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit_Facture{" + "id=" + id + ", idFacture=" + idFacture + ", idProduit=" + idProduit + ", quantite=" + quantite + '}';
    }
    
    
    
    
}
