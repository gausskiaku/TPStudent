<%-- 
    Document   : creationFaculte
    Created on : 1 sept. 2015, 09:52:53
    Author     : Gauss
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrer Faculté</title>
        <link type="text/css" rel="stylesheet" href="Css/style.css" />
    </head>
    <body>
        <div id="tout">
            <div id="entete">
               <center>   <img src="images/logo2.gif"/> </center> 
            </div>
            <div id="menu">
                <c:import url="Menu.jsp" />
            </div>
            
            <div id="corps">
                <form class="form-horizontal well" method="post" id="form1" action="<c:out value="creationFaculte" />">
                                   
                    <legend style="font-size: 2em;color: blue;"> Information Faculte</legend>
                    <fieldset> 
                    <center><table border="0" style="width: 800px;">
                    <tr>
                        <td width="150"> Nom de la Faculte </td>
                        <td><input type="text" name="nom_faculte" value="<c:out value="${faculte.nom_faculte}" /> " size="30" />
                    <p class="erreur"> ${form.erreurs['nom_faculte']} </p>
                    </td>
                    </tr>
                    <tr>
                    <td width="150">Mot de passe de connexion</td>
                    <td><input type="password" name="mot_de_passe" value="<c:out value="${faculte.mot_de_passe}"/>" size="30"/></td>
                    <p class="info">${form.resultat}</p>
                    </tr>
                    <tr>
                        <td width="150"></td>
                        <td>
                                <button class="btn btn-primary"  type="submit" name="valider" value="Remettre à zero" />Enregistrement<i class="icon-white icon-ok-sign"></i> </button>
                            <input type="reset" class="btn" value="Effacer" name="valider" />
                            
                            </td>
                    </tr>
                    </table></center>
                   
                </fieldset>
            </form>
            </div>
            <div id="bas">
                
            </div>
        </div>
    </body>
</html>
