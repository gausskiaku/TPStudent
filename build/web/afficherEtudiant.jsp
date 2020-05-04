<%-- 
    Document   : afficherEtudiant
    Created on : 6 sept. 2015, 03:56:32
    Author     : Gauss
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Affichage de l'Etudiant</title>
        <link type="text/css" rel="stylesheet" href="Css/style.css" />
    </head>
    <body>
        
              <form method="post" action="creationEtudiant">
                  <p class="info" style="font-size: 2em;color: orange;">${form.resultat}</p>
        
        <p>Nom : ${etudiant.nom_etu}</p>
        <p>PostNom : ${etudiant.postnom_etu}</p>
        <p>Prenom : ${etudiant.prenom_etu}</p>
        <p>Sexe : ${etudiant.sexe_etu}</p>
        <p>Promotion : ${etudiant.promotion.nom_promotion}</p>
        <p>Année academique : ${etudiant.annee_academique}</p>
        <p><a href="pdf.jsp">Retourner a la page</a></p>
        <p>${Oui}</p>
        <p>${info}</p>
        <p>${B}</p>
        <p>${C}</p>
        <p>${D}</p>
        <p>${E}</p>
        <input type="submit" value="Valider" name="valider" />
        
        </form>     
        
    </body>
</html>
