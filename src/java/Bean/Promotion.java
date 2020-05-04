/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Gauss
 */
public class Promotion {
    private String nom_promotion;
    private Departement departement = new Departement();

    public String getNom_promotion() {
        return nom_promotion;
    }

    public void setNom_promotion(String nom_promotion) {
        this.nom_promotion = nom_promotion;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
    
    
}
