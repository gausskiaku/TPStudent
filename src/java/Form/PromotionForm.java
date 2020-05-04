/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Bean.Departement;
import Bean.Promotion;
import Dao.DaoException;
import Dao.DepartementDao;
import Dao.PromotionDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gauss
 */
public class PromotionForm {
    public static final String CHAMP_LISTE_DEPARTEMENTS = "listeDepartements";
    public static final String CHAMP_NOM_PROMOTION = "nom_promotion";
    public static final String SESSION_DEPARTEMENTS = "departements";
    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private DepartementDao departementDao;
    private PromotionDao promotionDao;

    public PromotionForm(DepartementDao departementDao, PromotionDao promotionDao) {
        this.departementDao = departementDao;
        this.promotionDao = promotionDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Promotion creerPromotion (HttpServletRequest request){
    Departement departement;
    String idDepartement = null;
        try {
            idDepartement = request.getParameter(CHAMP_LISTE_DEPARTEMENTS);
        } catch (Exception e) {
            setErreur("Oui", "Departement inconnu! " + e);
           
        }
//        HttpSession session = request.getSession();
//        departement = ((Map<String, Departement>) session.getAttribute(SESSION_DEPARTEMENTS)).get(idDepartement.trim());
        departement = departementDao.trouver(idDepartement);
        Promotion promotion = new Promotion();
        String nom_promotion = request.getParameter(CHAMP_NOM_PROMOTION);

        try {
            promotion.setDepartement(departement);
            promotion.setNom_promotion(nom_promotion.trim());
            
            if(erreurs.isEmpty()){
            promotionDao.creer(promotion);
            resultat = "Succès de la creation de promotion";
            } else {
            resultat = "Echec de la creation de la promotion";
            }
        } catch (DaoException e) {
            setErreur("imprevu", "Erreur imprevue lors de la creation.");
            resultat = "Echec de la creation de la promotion : Une erreur imprevue est survenue, merci de reessayer dans quelques instants.";
            e.printStackTrace();
        }
        return promotion;
    }
    
    private void setErreur (String champ, String message){
    erreurs.put(champ, message);
    }
    public List<Promotion> getPromotion(){
    List<Promotion> promotion = promotionDao.lister();
    
    return promotion;
    
    }
}
