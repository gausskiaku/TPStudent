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
public class Faculte {
    
    private String nom_faculte;
    private String mot_de_passe;

    public String getNom_faculte() {
        return nom_faculte;
    }

    public void setNom_faculte(String nom_faculte) {
        this.nom_faculte = nom_faculte;
    }

    public Faculte(String nom_faculte) {
        this.nom_faculte = nom_faculte;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Faculte() {
    }
    
}
