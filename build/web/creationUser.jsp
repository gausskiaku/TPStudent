<%-- 
    Document   : CreationUser
    Created on : 7 sept. 2015, 21:33:16
    Author     : Gauss
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pour l'admnistracteur</title>
        <link type="text/css" rel="stylesheet" href="Css/style.css" />
    </head>
    <body>
        <div id="tout">
            <div id="entete">
                <center>   <img src="images/logo2.gif"/> </center>
            </div>
            <div id="menu">
                
                
            </div>
            <div id="corps">
                <center><form method="post" class="form-horizontal well" id="form1" action="<c:out value="creationUser"/>">
                       <center> <p style="font-size: 2em; color: white; font-size: 30px"> BIENVENU CHER ADMNISTRATEUR </p>
                    <fieldset>
                         <legend style="font-size: 2em; color: orange; font-size: 27px"> Admnistration </legend>
                         <center><table border="0" style="margin-top: 25px;width: 510px;border: 0px solid yellow;height: 200px;background:white ;" class="table table-bordered table-striped tablecondensed">
                                 <tr class="success">  <td style="color: black;margin-left: 100px; font-size: 21px" >Pour ajouter une nouvelle faculté : <a style="color: red" href="<c:url value="creationFaculte.jsp"/>">Cliquer ici</a></td>  </tr>
                                 <tr>  <td style="color: black;margin-left: 100px; font-size: 21px" >Pour ajouter un nouveau departement : <a style="color: red" href="<c:url value="creationDepartement.jsp"/>">Cliquer ici</a></td></tr>
                                 <tr>  <td style="color: black;margin-left: 100px; font-size: 21px" >Pour ajouter une nouvelle promotion : <a style="color: red" href="<c:url value="creationFaculte.jsp"/>">Cliquer ici</a></tr>
                                 
                             </table></center>
                    </fieldset></center>
                        
                    
                    </form></center>
            </div>
        </div>
    </body>
</html>
