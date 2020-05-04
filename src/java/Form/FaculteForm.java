/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Bean.Faculte;
import Dao.FaculteDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

/**
 *
 * @author Gauss
 */
public class FaculteForm {
    public static final String CHAMP_FACULTE = "nom_faculte";
    public static final String CHAMP_MOT_DE_PASSE = "mot_de_passe";
    
    private FaculteDao faculteDao;
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public FaculteForm(FaculteDao faculteDao) {
        this.faculteDao = faculteDao;
    }
    
    
    public Faculte inscrireFaculte (HttpServletRequest request){
    
        String nom_faculte = request.getParameter(CHAMP_FACULTE);
        String mot_de_passe = request.getParameter(CHAMP_MOT_DE_PASSE);
        Faculte faculte = new Faculte();
        try {
            faculte.setNom_faculte(nom_faculte.trim());
            faculte.setMot_de_passe(mot_de_passe);
                        
            if(erreurs.isEmpty()){
            faculteDao.creer(faculte);
            resultat = "Succes d'enregistrement";
            } else {
              resultat = "Echec d'enregistrement";      
            }
        } catch (Exception e) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants. " + e;
            e.printStackTrace();
        }
    return faculte;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
    
    private void traiterFaculte (String faculte){
        try {
            validationFaculte(faculte);
        } catch (Exception e) {
            setErreur(CHAMP_FACULTE, e.getMessage());
        }
    }
    private void validationFaculte (String faculte) throws Exception{
    if(faculte != null){
        if (faculteDao.trouver(faculte) != null){
        throw new Exception("Cette faculte existe déjà, merci d'en trouver une autre");
        
        }
    }
    else {
    throw new Exception("Merci de saisir une faculte");
    }
    }
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {

        String valeur = request.getParameter( nomChamp );
    if ( valeur == null || valeur.trim().length() == 0 ) {
        return null;
    } else {
        return valeur.trim();
            }
    }
    
    public List<Faculte> getFaculte(){
//    Faculte faculte = new Faculte();
    List<Faculte> faculte = faculteDao.lister();
    
    return faculte;
    }
}
