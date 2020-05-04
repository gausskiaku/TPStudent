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
public class Notes {
    private Long id_notes;
    private String decision;
    private int pourcentage;
    private int nbre_reussi;
    private int nbre_echec;
    private String session;
    private Etudiant etudiant = new Etudiant();

    public Long getId_notes() {
        return id_notes;
    }

    public void setId_notes(Long id_notes) {
        this.id_notes = id_notes;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getNbre_reussi() {
        return nbre_reussi;
    }

    public void setNbre_reussi(int nbre_reussi) {
        this.nbre_reussi = nbre_reussi;
    }

    public int getNbre_echec() {
        return nbre_echec;
    }

    public void setNbre_echec(int nbre_echec) {
        this.nbre_echec = nbre_echec;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
    
}
