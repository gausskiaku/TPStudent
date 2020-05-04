/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Bean.Etudiant;
import Bean.Notes;
import Dao.DaoException;
import Dao.EtudiantDao;
import Dao.NotesDao;
import Servlet.CreationEtudiant;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gauss
 */
public class NotesForm {
    private static final String CHAMP_DECISION = "decision";
    private static final String CHAMP_POURCENTAGE ="pourcentage";
    private static final String CHAMP_REUSSI = "nbre_reussi";
    private static final String CHAMP_ECHEC = "nbre_echec";
    private static final String CHAMP_LISTE_SESSIONS = "listeSessions";
    private static final String CHAMP_LISTE_ETUDIANTS = "listeEtudiants";
    private static final String SESSION_ETUDIANTS = "etudiants";
    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private EtudiantDao etudiantDao;
    private NotesDao notesDao;
    
    
    public NotesForm(EtudiantDao etudiantDao, NotesDao notesDao) {
        this.etudiantDao = etudiantDao;
        this.notesDao = notesDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Notes creerNotes (HttpServletRequest request){
    Etudiant etudiant;
    String idEtudiant = request.getParameter(CHAMP_LISTE_ETUDIANTS);
    Long id = null;
        try {
            id = Long.parseLong(idEtudiant.trim());
        } catch (NumberFormatException e) {
            setErreur("Oui", "Etudiant inconnu, merci d'itiliser le formulaire prevu a cet effet.");
            }
        etudiant = etudiantDao.trouver(id);
//        HttpSession session = request.getSession();
//        etudiant = ((Map<Long, Etudiant>) session.getAttribute(SESSION_ETUDIANTS)).get(id);
        
        String decision = request.getParameter(CHAMP_DECISION);
        String pourcentage = request.getParameter(CHAMP_POURCENTAGE);
        String nbre_reussi = request.getParameter(CHAMP_REUSSI);
        String nbre_echec = request.getParameter(CHAMP_ECHEC);
        String getsession = request.getParameter(CHAMP_LISTE_SESSIONS);
        String matricule_etu = request.getParameter(CHAMP_LISTE_ETUDIANTS);
        Notes notes = new Notes();
        try {
            notes.setDecision(decision);
            int p = Integer.parseInt(pourcentage);
            notes.setPourcentage(p);
            int nbre_reussi2 = Integer.parseInt(nbre_reussi);
            notes.setNbre_reussi(nbre_reussi2);
            int nbre_echec2 = Integer.parseInt(nbre_echec);
            notes.setNbre_echec(nbre_echec2);
            notes.setSession(getsession);
            notes.setEtudiant(etudiant);
            if(erreurs.isEmpty()){
            notesDao.creer(notes);
            resultat = "Succes de la creation de notes";
            } else {
            resultat = "Echec de la creation de notes";
            }
        } catch (DaoException e) {
            setErreur("imprevu", "Erreur imprevue lors de la creation");
            resultat = "Echec de creation de notes : une erreur imprevue est survenue, merci de reessayer dans quelques instants.";
            e.printStackTrace();
        }
        return notes;
    
    }
    
    private void setErreur (String champ, String message){
    erreurs.put(champ, message);
    }
}
