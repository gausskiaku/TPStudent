<%-- 
    Document   : pdf
    Created on : 10 sept. 2015, 09:26:28
    Author     : Gauss
--%>

<%@page import="net.sf.jasperreports.view.JasperViewer"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="Dao.DaoFactory"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="net.sf.jasperreports.engine.design.JRDesignQuery"%>
<%@page import="net.sf.jasperreports.engine.xml.JRXmlLoader"%>
<%@page import="net.sf.jasperreports.engine.design.JasperDesign"%>
<%@page import="java.sql.Connection"%>
<%@page import="Connexion.Connexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


        <% 
            try {
        
       Connexion conn = new Connexion();
        Connection connexion = null;
        try {
//            connexion = daoFactory.getConnection();
            connexion = conn.getInstance();
            JasperDesign jasperDesign = JRXmlLoader.load(request.getSession().getServletContext().getRealPath("/Récépissé.jrxml"));
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
            
            request.setAttribute("info","Affichage du pdf : ");
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            
            outputStream.write(bytes, 0, bytes.length);
            
            outputStream.flush();
            outputStream.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
            
        } catch (Exception e) {
            out.println(e.getMessage());
            
        }
        
        %>
   