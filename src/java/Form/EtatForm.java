/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Dao.DaoFactory;
import java.sql.Connection;
import javax.servlet.ServletOutputStream;
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
public class EtatForm {
    private DaoFactory daoFactory;
    
    public void imprimer(HttpServletRequest request, HttpServletResponse response){
    Connection connexion = null;
        try {
            connexion = daoFactory.getConnection();
            JasperDesign jasperDesign = JRXmlLoader.load(request.getSession().getServletContext().getRealPath("Récépissé.jrxml"));
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
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            
            outputStream.flush();
            outputStream.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    }
    
}
