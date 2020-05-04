/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Dao.DaoFactory;
import Form.EtatForm;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
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

public class CreationEtat extends HttpServlet{
    public static final String VUE = "/afficherEtudiant.jsp";
    private DaoFactory daoFactory;
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connexion = null;
        try {
            connexion = daoFactory.getConnection();
            JasperDesign jasperDesign = JRXmlLoader.load(req.getSession().getServletContext().getRealPath("Récépissé.jrxml"));
            JRDesignQuery newquery = new JRDesignQuery();
            String sql = "SELECT * FROM T_Etudiant \n" +
            "INNER JOIN T_Promotion ON T_Etudiant.nom_promotion = T_Promotion.nom_promotion\n" +
            "INNER JOIN T_Departement ON T_Promotion.nom_departement = T_Departement.nom_departement\n" +
            "INNER JOIN T_Faculte ON T_Departement.nom_faculte = T_Faculte.nom_faculte\n" +
            " GROUP BY matricule_etu, nom_etu, postnom_etu, prenom_etu, sexe_etu, situation, T_Etudiant.nom_promotion, annee_academique, T_Promotion.nom_promotion,\n" +
            " T_Promotion.nom_departement, T_Departement.nom_departement, T_Departement.nom_faculte, T_Faculte.nom_faculte\n" +
            " HAVING matricule_etu = MAX(matricule_etu)";
            
            newquery.setText(sql);
            jasperDesign.setQuery(newquery);
            
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, null, connexion);
            
            System.out.println("Affichage du pdf : ");
            resp.setContentType("application/pdf");
            resp.setContentLength(bytes.length);
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            
            outputStream.flush();
            outputStream.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connexion = null;
        try {
            connexion = daoFactory.getConnection();
            JasperDesign jasperDesign = JRXmlLoader.load(req.getSession().getServletContext().getRealPath("Récépissé.jrxml"));
            JRDesignQuery newquery = new JRDesignQuery();
            String sql = "SELECT * FROM T_Etudiant \n" +
            "INNER JOIN T_Promotion ON T_Etudiant.nom_promotion = T_Promotion.nom_promotion\n" +
            "INNER JOIN T_Departement ON T_Promotion.nom_departement = T_Departement.nom_departement\n" +
            "INNER JOIN T_Faculte ON T_Departement.nom_faculte = T_Faculte.nom_faculte\n" +
            " GROUP BY matricule_etu, nom_etu, postnom_etu, prenom_etu, sexe_etu, situation, T_Etudiant.nom_promotion, annee_academique, T_Promotion.nom_promotion,\n" +
            " T_Promotion.nom_departement, T_Departement.nom_departement, T_Departement.nom_faculte, T_Faculte.nom_faculte\n" +
            " HAVING matricule_etu = MAX(matricule_etu)";
            
            newquery.setText(sql);
            jasperDesign.setQuery(newquery);
            
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, null, connexion);
            
            System.out.println("Affichage du pdf : ");
            resp.setContentType("application/pdf");
            resp.setContentLength(bytes.length);
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            
            outputStream.flush();
            outputStream.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    
}
