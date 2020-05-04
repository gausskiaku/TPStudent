/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Etudiant;
import Bean.Promotion;
import Connexion.Connexion;
import Dao.DaoFactory;
import Dao.EtudiantDao;
import Dao.PromotionDao;
import Form.EtudiantForm;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Gauss
 */
public class CreationEtudiant extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_ETUDIANT = "etudiant";
    public static final String ATT_FORM = "form";
    public static final String SESSION_PROMOTIONS = "promotions";
    public static final String APPLICATION_CLIENTS = "initClients";
    public static final String SESSION_ETUDIANTS = "etudiants";
    public static final String APPLICATION_ETUDIANTS = "initEtudiants";
    public static final String VUE = "/creationEtudiant.jsp";
    private PromotionDao promotionDao;
    private EtudiantDao etudiantDao;
    private DaoFactory daoFactory;

    @Override
    public void init() throws ServletException {
        this.promotionDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPromotionDao();
        this.etudiantDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getEtudiantDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EtudiantForm form = new EtudiantForm(promotionDao, etudiantDao);
        
        Etudiant etudiant = form.inscrireEtudiant(req);
        
        req.setAttribute(ATT_FORM, form);
        req.setAttribute(ATT_ETUDIANT, etudiant);
        
        if(form.getErreurs().isEmpty()){
        
            HttpSession session = req.getSession();
            Map<String, Promotion> promotions = (HashMap<String, Promotion>) session.getAttribute(SESSION_PROMOTIONS);
            
            if(promotions == null){
            promotions = new HashMap<String, Promotion>();
            }
            promotions.put(etudiant.getPromotion().getNom_promotion(), etudiant.getPromotion());
            session.setAttribute(SESSION_PROMOTIONS, promotions);
            
            Map<Long, Etudiant> etudiants = (HashMap<Long, Etudiant>) session.getAttribute(SESSION_ETUDIANTS);
            if(etudiants == null){
            etudiants = new HashMap<Long, Etudiant>();
            }
            etudiants.put(etudiant.getMatricule_etu(), etudiant);
            session.setAttribute(SESSION_ETUDIANTS, etudiants);
            
//            resp.sendRedirect("afficherEtudiant.jsp");
            
        }
        
        print(req, resp);
        this.getServletContext().getRequestDispatcher("/afficherEtudiant.jsp").forward(req, resp);
    }
    
    public void print (HttpServletRequest req, HttpServletResponse resp){
     Connexion conn = new Connexion();
        Connection connexion = null;
        try {
//            connexion = daoFactory.getConnection();
            connexion = conn.getInstance();
            JasperDesign jasperDesign = JRXmlLoader.load(req.getSession().getServletContext().getRealPath("/Récépissé.jrxml"));
            JRDesignQuery newquery = new JRDesignQuery();
            String sql = "SELECT * FROM T_Etudiant\n" +
            "INNER JOIN T_Promotion ON T_Etudiant.nom_promotion = T_Promotion.nom_promotion\n" +
            "INNER JOIN T_Departement ON T_Promotion.nom_departement = T_Departement.nom_departement\n" +
            "INNER JOIN T_Faculte ON T_Departement.nom_faculte = T_Faculte.nom_faculte\n" +
            " GROUP BY matricule_etu, nom_etu, postnom_etu, prenom_etu, sexe_etu, situation, T_Etudiant.nom_promotion, annee_academique, T_Promotion.nom_promotion,\n" +
            " T_Promotion.nom_departement, T_Departement.nom_departement, T_Departement.nom_faculte, T_Faculte.nom_faculte, T_Etudiant.id_app, T_Faculte.mot_de_passe\n" +
            " HAVING matricule_etu = (select IDENT_CURRENT('T_Etudiant'))";
            
            newquery.setText(sql);
            jasperDesign.setQuery(newquery);
//            req.setAttribute("A", "Connexion OK");
//            String report = "C:\\Users\\Gauss\\Documents\\NetBeansProjects\\TpGenie\\Récépissé.jrxml";
//            req.setAttribute("B", "Trouver fichier");
//            JasperReport jr = JasperCompileManager.compileReport(report);
//            req.setAttribute("C", "Suivant ok");
//            JasperPrint jp = JasperFillManager.fillReport(jr, null, connexion);
//            req.setAttribute("D", "TT pour print");
//            JasperViewer.viewReport(jp);
//            req.setAttribute("E", "Afficher");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, null, connexion);
            
            req.setAttribute("info","Affichage du pdf : ");
            resp.setContentType("application/pdf");
            resp.setContentLength(bytes.length);
            ServletOutputStream outputStream = resp.getOutputStream();
            
            outputStream.write(bytes, 0, bytes.length);
            
            outputStream.flush();
            outputStream.close();
            req.setAttribute("Oui", "Oui");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("Oui", "Non : "+e);
        }
    }
    
}
