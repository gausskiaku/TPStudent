<%-- 
    Document   : creationFrais
    Created on : 3 sept. 2015, 18:53:58
    Author     : Gauss
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrer les frais</title>
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
                <form method="post"  class="form-horizontal well" id="form1" action="<c:out value="creationFrais"/>">
                
                    <legend style="font-size: 2em;color: blue;"> Information frais </legend>
                    <fieldset>
                        <center><table border="0" style="width: 800px;">
                   <tr> 
                   <td>Type frais </td>
                   <td><input type="text" name="type_frais" value="${frais.type_frais}" size="30" />
                    </td>
                   </tr>
                   <tr>
                       <td> Montant </td>
                       <td><input type="text" name="montant" value="${frais.montant}" size="30" /></td>
                    </tr>
                    <tr>
                       <td> </td>
                       <td>
                           <button class="btn btn-primary"  type="submit" name="valider" />Enregistrement<i class="icon-white icon-ok-sign"></i> </button>
                            <input type="reset" class="btn" value="Effacer" name="valider" />
                       </td>
                    </tr>
                    <p class="info">${form.resultat}</p>
                    
                            </table></center>
                </fieldset>
            
            </form>  
            </div>
            <div id="bas">
                
            </div>
        </div>
        
                   
               
    </body>
</html>
