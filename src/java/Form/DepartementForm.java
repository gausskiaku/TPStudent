/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Bean.Departement;
import Bean.Faculte;
import Dao.DaoException;
import Dao.DaoFactory;
import Dao.DepartementDao;
import Dao.DepartementDaoImpl;
import Dao.FaculteDao;
import Servlet.CreationFaculte;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gauss
 */
public class DepartementForm {
    private static final String CHAMP_LISTE_FACULTES = "listeFacultes";
    private static final String CHAMP_NOM_DEPARTEMENT = "nom_departement";
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private FaculteDao faculteDao;
    private DepartementDao departementDao;
   
    private static final String SESSION_FACULTES = "facultes";

    public DepartementForm(FaculteDao faculteDao, DepartementDao departementDao) {
        this.faculteDao = faculteDao;
        this.departementDao = departementDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Departement creerDepartement (HttpServletRequest request){
    Faculte faculte;
    String idAncienFaculte = null;
    
        try {
            idAncienFaculte = request.getParameter(CHAMP_LISTE_FACULTES);
        } catch (NullPointerException e) {
            setErreur("Oui", "Faculte inconnu, merci d'utiliser le formulaire prévu à cet effet. " + e);
        }
        faculte = faculteDao.trouver(idAncienFaculte);
        HttpSession session = request.getSession();
//        faculte = ( (Map<String, Faculte>)session.getAttribute(SESSION_FACULTES)).get(idAncienFaculte.trim());
//    FaculteForm faculteForm = new FaculteForm(faculteDao);
//    faculte = faculteForm.inscrireFaculte(request);
//    erreurs = faculteForm.getErreurs();
    
    Departement departement = new Departement();
    String nom_departement = request.getParameter(CHAMP_NOM_DEPARTEMENT);
        try {            
            departement.setFaculte(faculte);
            departement.setNom_departement(nom_departement.trim());
    if(erreurs.isEmpty()){
        departementDao.creer(departement);
        resultat = "Succes de la creation du departement";
    } else {
        resultat = "Echec de la creation du departement";
    }
        } catch (DaoException e) {
            setErreur("imprevu", "Erreur imprevue lors du creation.");
            resultat = "Echec de creation du departement : une erreur imprevue est survenue, merci de réessayer dans quelques instants. ";
            e.printStackTrace();
        }
    
    return departement;
    }
    
    private void setErreur (String champ, String message){
    erreurs.put(champ, message);
    }

   public List<Departement> getDepartement(){
   List<Departement> departement = departementDao.lister();
   return departement;
   }
    
    
    
}
