/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author asus
 */
public class Produit {
    private int id;
    private String Category;
    private String modele;
    private int prix;
    private int quantite;

    public Produit() {
    }

    public Produit(int id, String Category, String modele, int prix, int quantite) {
        this.id = id;
        this.Category = Category;
        this.modele = modele;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(String Category, String modele, float prix, int quantite) {
        this.Category = Category;
        this.modele = modele;
        this.prix = (int)prix;
        this.quantite = quantite;
    }

    public Produit(int id, String modele, int prix) {
        this.id = id;
        this.modele = modele;
        this.prix = prix;
    }

    public Produit(String modele, int prix) {
        this.modele = modele;
        this.prix = prix;
    }
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
 public void setQuantite(int q) {
        this.quantite = q;
    }

    public int getQuantite() {
        return quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", Category=" + Category + ", modele=" + modele + ", prix=" + prix + ", quantite=" + quantite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.modele, other.modele)) {
            return false;
        }
        return true;
    }

   
    
    
    
    
    
}
