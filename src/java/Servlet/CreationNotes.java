/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Etudiant;
import Bean.Notes;
import Connexion.Connexion;
import Dao.DaoFactory;
import Dao.EtudiantDao;
import Dao.NotesDao;
import Form.NotesForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Gauss
 */
public class CreationNotes extends HttpServlet{

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_NOTES = "notes";
    public static final String ATT_FORM = "form";
    public static final String SESSION_ETUDIANTS = "etudiants";
    public static final String APPLICATION_ETUDIANTS = "initEtudiants";
    public static final String SESSION_NOTESS = "notess";
    public static final String APPLICATION_NOTESS = "initNotess";
    public static final String VUE = "/creationNotes.jsp";
    private EtudiantDao etudiantDao;
    private NotesDao notesDao;
    @Override
    public void init() throws ServletException {
       this.etudiantDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getEtudiantDao();
       this.notesDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getNotesDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        NotesForm form = new NotesForm(etudiantDao, notesDao);
        Notes notes = form.creerNotes(req);
        req.setAttribute(ATT_NOTES, notes);
        req.setAttribute(ATT_FORM, form);
        
        if(form.getErreurs().isEmpty()){
        HttpSession session = req.getSession();
        Map<Long, Etudiant> etudiants = (HashMap<Long, Etudiant>) session.getAttribute(SESSION_ETUDIANTS);
        if(etudiants == null){
        etudiants = new HashMap<Long,Etudiant>();
        }
        etudiants.put(notes.getEtudiant().getMatricule_etu(), notes.getEtudiant());
        session.setAttribute(SESSION_ETUDIANTS, etudiants);
        
        
        List<Etudiant> l = etudiantDao.lister();
        if(l.isEmpty()){
        req.setAttribute("gauss", "videeeee");
        } else {
        req.setAttribute("gauss", "NON Videeeee");
        }
        
        
        Map<Long, Notes> notess = (HashMap<Long, Notes>) session.getAttribute(SESSION_NOTESS);
        if(notess == null){
        notess = new HashMap<Long, Notes>();
        }
        notess.put(notes.getId_notes(), notes);
        session.setAttribute(SESSION_NOTESS, notess);
        }
        if(req.getParameter("valider").equals("ok")){
        Connexion conn = new Connexion();
        Connection connexion = null;
        try {
//            connexion = daoFactory.getConnection();
            connexion = conn.getInstance();
            JasperDesign jasperDesign = JRXmlLoader.load(req.getSession().getServletContext().getRealPath("/releve.jrxml"));
            JRDesignQuery newquery = new JRDesignQuery();
            String sql = "SELECT * FROM T_Etudiant INNER JOIN T_Notes ON T_Etudiant.matricule_etu = T_Notes.matricule_etu \n" +
                "INNER JOIN T_Historique ON T_Etudiant.matricule_etu = T_Historique.matricule_etu AND T_Notes.id_notes = T_Historique.id_notes\n" +
                "WHERE T_Historique.annee_academique_his = '2014 - 2015'";
            
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
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }
    
    
}
