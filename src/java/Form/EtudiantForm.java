/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Bean.Etudiant;
import Bean.Promotion;
import Dao.DaoException;
import Dao.EtudiantDao;
import Dao.PromotionDao;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author Gauss
 */
public class EtudiantForm {
//    private static final String CHAMP_MATRICULE = "matricule_etu";
    private static final String CHAMP_NOM = "nom_etu";
    private static final String CHAMP_POSTNOM = "postnom_etu";
    private static final String CHAMP_PRENOM = "prenom_etu";
    private static final String CHAMP_LISTE_SEXES = "listeSexes";
    private static final String CHAMP_LISTE_PROMOTIONS = "listePromotions";
    private static final String CHAMP_LISTE_ANNEE_ACADEMIQUE = "listeAnnees";
    private static final String SESSION_PROMOTIONS = "promotions";
    private static final String SESSION_ANNEES = "annees";
    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private PromotionDao promotionDao;
    private EtudiantDao etudiantDao;
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

    public EtudiantForm(PromotionDao promotionDao, EtudiantDao etudiantDao) {
        this.promotionDao = promotionDao;
        this.etudiantDao = etudiantDao;
    }
    
    
    public Etudiant inscrireEtudiant (HttpServletRequest request){
    Promotion promotion;
    
    String idPromotion = null; 
        try {
            idPromotion = request.getParameter(CHAMP_LISTE_PROMOTIONS);
            
        } catch (Exception e) {
            setErreur("Oui", "Promotion inconnu, merci d'utiliser le formulaire prevu à cet effet.");
        }
//        HttpSession session = request.getSession();
//        promotion = ((Map<String, Promotion>) session.getAttribute(SESSION_PROMOTIONS)).get(idPromotion);
        promotion = promotionDao.trouver(idPromotion);
        DateTime dt = new DateTime();
        String nom_etu = request.getParameter(CHAMP_NOM);
        String postnom_etu = request.getParameter(CHAMP_POSTNOM);
        String prenom_etu = request.getParameter(CHAMP_PRENOM);
        String listeSexes = request.getParameter(CHAMP_LISTE_SEXES);
        String annee_academique = request.getParameter(CHAMP_LISTE_ANNEE_ACADEMIQUE);
        
        
        Etudiant etudiant = new Etudiant();
        try {
            etudiant.setNom_etu(nom_etu);
            etudiant.setPostnom_etu(postnom_etu);
            etudiant.setPrenom_etu(prenom_etu);
            etudiant.setSexe_etu(listeSexes);
            etudiant.setAnnee_academique(annee_academique);
            etudiant.setPromotion(promotion);
            etudiant.setSituation("PreInscrit");
            
            if(erreurs.isEmpty()){
                etudiantDao.creer(etudiant);
        resultat = "Succès de l'enregistrement.";
        } else {
        resultat = "Echec de l'enregistrement.";
        }
        } catch (DaoException e) {
            setErreur("imprevu", "Erreur imprevue lors de la creation.");
            resultat = "Echec de la creation de l'etudiant : une erreur imprevue est survenue, merci de reessayer dans quelques instants.";
            e.printStackTrace();
        }
        
        // PDF
//           try {
////            Etudiant etudiant = new Etudiant();
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Gauss\\Documents\\NetBeansProjects\\TpGenie\\Etudiant.pdf"));
//            document.open();
//            document.add(new Paragraph("Votre Récépissé", FontFactory.getFont(FontFactory.TIMES_BOLD, Font.BOLD, BaseColor.ORANGE)));
//            document.add((new Paragraph("------------------------------------")));
//            document.add(new Paragraph("Vos informations : "));
//            document.add(new Paragraph("\t 1. Votre nom : " + etudiant.getNom_etu() ));
//            document.add(new Paragraph("\t 2. Votre postnom : " + etudiant.getPostnom_etu()));
//            document.add(new Paragraph("\t 3. Votre prenom : " + etudiant.getPrenom_etu() ));
//            document.add(new Paragraph("\t 4. Votre sexe : " + etudiant.getSexe_etu() ));
//            document.add(new Paragraph("\t 5. Promotion solicité : " + etudiant.getPromotion().getNom_promotion() ));
//            document.add(new Paragraph("\t 6. Année academique : " + etudiant.getAnnee_academique() ));
//            document.add(new Paragraph("\n Merci de choisir notre faculté "));
//            document.add(new Paragraph("NB : Votre inscrition se confirmera que lors du paiement de Frais academique et du frais technique" ));
//
//
//            document.close(); 
////            this.getServletContext().getRequestDispatcher("/afficherEtudiant.jsp").forward(req, resp);
//        } catch (Exception e) {
//        }
        
    return etudiant;
    }
    
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
    
    public List<Etudiant> getEtudiant(){
    List<Etudiant> etudiant = etudiantDao.lister();
    return etudiant;
    }
    
}
