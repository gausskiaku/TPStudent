/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import org.joda.time.DateTime;

/**
 *
 * @author Gauss
 */
public class Frais implements Serializable{
    private Long id_frais;
    private int montant;
    private String type_frais;
//    private DateTime date_paiement;

    public Long getId_frais() {
        return id_frais;
    }

    public void setId_frais(Long id_frais) {
        this.id_frais = id_frais;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getType_frais() {
        return type_frais;
    }

    public void setType_frais(String type_frais) {
        this.type_frais = type_frais;
    }

//    public DateTime getDate_paiement() {
//        return date_paiement;
//    }
//
//    public void setDate_paiement(DateTime date_paiement) {
//        this.date_paiement = date_paiement;
//    }
    
    
}
