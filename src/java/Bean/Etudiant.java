/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import org.joda.time.DateTime;

/**
 *
 * @author Gauss
 */
public class Etudiant {
    private Long matricule_etu;
    private String nom_etu;
    private String postnom_etu;
    private String prenom_etu;
    
    private String sexe_etu;
    
    private String situation;
    private Promotion promotion = new Promotion();
    private String annee_academique;

    public String getAnnee_academique() {
        return annee_academique;
    }

    public void setAnnee_academique(String annee_academique) {
        this.annee_academique = annee_academique;
    }
    
    public Long getMatricule_etu() {
        return matricule_etu;
    }

    public void setMatricule_etu(Long matricule_etu) {
        this.matricule_etu = matricule_etu;
    }

    

    public String getNom_etu() {
        return nom_etu;
    }

    public void setNom_etu(String nom_etu) {
        this.nom_etu = nom_etu;
    }

    public String getPostnom_etu() {
        return postnom_etu;
    }

    public void setPostnom_etu(String postnom_etu) {
        this.postnom_etu = postnom_etu;
    }

    public String getPrenom_etu() {
        return prenom_etu;
    }

    public void setPrenom_etu(String prenom_etu) {
        this.prenom_etu = prenom_etu;
    }


    public String getSexe_etu() {
        return sexe_etu;
    }

    public void setSexe_etu(String sexe_etu) {
        this.sexe_etu = sexe_etu;
    }

    
    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    
    
    
}
