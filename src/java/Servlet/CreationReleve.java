/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Connexion.Connexion;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class CreationReleve extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
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
    
    
    
    
}
