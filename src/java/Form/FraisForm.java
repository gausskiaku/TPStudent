/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Bean.Frais;
import Dao.DaoException;
import Dao.FraisDao;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gauss
 */
public class FraisForm {
    private static final String CHAMP_TYPE_FRAIS = "type_frais";
    private static final String CHAMP_MONTANT = "montant";
    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private FraisDao fraisDao;

    public FraisForm(FraisDao fraisDao) {
        this.fraisDao = fraisDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Frais creerFrais (HttpServletRequest request){
    
      String type_frais = request.getParameter("type_frais");
      String montant = request.getParameter("montant");
      
      Frais frais = new Frais();
      frais.setType_frais(type_frais);
      frais.setMontant(Integer.valueOf(montant).intValue());
        try {
            if(erreurs.isEmpty()){
            fraisDao.creer(frais);
            resultat = "Succes de la creation du frais";
            } else {
            resultat = "Echec de la creation du frais";
            }
        } catch (DaoException e) {
            setErreur("imprevu", "Erreur imprevue lors de la creation");
            resultat = "Echec de la creation du frais : une erreur imprevue est survenue, merci de reessayer dans quelques instants.";
            e.printStackTrace();
        }
    return frais; 
    }
    
    private void setErreur(String champ, String message){
    erreurs.put(champ, message);
    }
    
}
