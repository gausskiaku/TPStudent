<%-- 
    Document   : creationPromotion
    Created on : 3 sept. 2015, 20:50:30
    Author     : Gauss
--%>

<%@page import="Bean.Departement"%>
<%@page import="java.util.List"%>
<%@page import="Dao.FaculteDao"%>
<%@page import="Form.DepartementForm"%>
<%@page import="Dao.DaoFactory"%>
<%@page import="Dao.DepartementDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrer Promontion</title>
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
            <form method="post" class="form-horizontal well" id="form1" action="<c:out value="creationPromotion" /> ">
               
                    <legend style="font-size: 2em;color: blue;"> Information Promotion </legend> 
                    <fieldset>
                        <center><table border="0" style="width: 800px;">
                    <tr> 
                        <td width="150">Nom promotion </td>
                        <td><input type="text" name="nom_promotion" value="${promotion.nom_promotion}" size="30" /></td>
                    </tr>
                    <tr>
                        <td width="150">Departement</td>
                        <td><select name="listeDepartements">
                                <% 
                                try {
                                DepartementDao departementDao = ((DaoFactory) getServletContext().getAttribute("daofactory")).getDepartementDao();
                                FaculteDao faculteDao = ((DaoFactory) getServletContext().getAttribute("daofactory")).getFaculteDao();
                                DepartementForm dd = new DepartementForm(faculteDao, departementDao);
                                List<Departement> list = dd.getDepartement();
                                String dep = "";
                                for(Departement departement : list){
                                dep = departement.getNom_departement();
                                out.write("<option value=\""+dep+"\">"+dep+"</option>");
                                }
                                } catch (Exception e){
                                out.println(e);
                                }
                                %>
                      <%--  <option value="">Choisissez un departement</option>
                        <c:forEach items="${sessionScope.departements}" var="mapDepartements" >
                            <option value="${mapDepartements.key}">${mapDepartements.value.nom_departement}</option>
                        </c:forEach> --%>
                    </select></td>
                    <c:set var="departement" value="${promotion.departement}" />
                    <p class="erreur"> ${form.erreurs['nom_promotion']}</p>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    <br />
                    <p class="info"> ${form.resultat} </p>
                    <tr>
                        <td></td>
                        <td><button class="btn btn-primary"  type="submit" name="valider"  />Enregistrement<i class="icon-white icon-ok-sign"></i> </button>
                                <input type="reset" class="btn" value="Effacer" name="valider" /></td>
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
