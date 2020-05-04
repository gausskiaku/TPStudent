<%-- 
    Document   : creationDepartement
    Created on : 3 sept. 2015, 09:01:02
    Author     : Gauss
--%>

<%@page import="Form.FaculteForm"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Dao.DaoFactory"%>
<%@page import="Dao.FaculteDaoImpl"%>
<%@page import="Bean.Faculte"%>
<%@page import="java.util.List"%>
<%@page import="Dao.DepartementDao"%>
<%@page import="Dao.FaculteDao"%>
<%@page import="Form.DepartementForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrer Departement</title>
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
                <form method="post" class="form-horizontal well" id="form1" action="<c:out value="creationDepartement" />">
                    <legend style="font-size: 2em;color: blue;"> Information Departement</legend>
                        <fieldset>
                            <center><table border="0" style="width: 800px;">
                                <tr>
                                    <td width="150">
                                        Nom du département : 
                                    </td>
                                    <td width="150">
                                        <input type="text" name="nom_departement" value="<c:out value="${departement.nom_departement}"/>" size="30" />
                    <span class="erreur"> ${form.erreurs['nom_departement']} </span>
                                    </td>
                    <c:set var="faculte" value="${departement.faculte}" scope="request" />

                                </tr>
                                <tr><td width="150">Nom faculte : </td>
                                    <td> <select name="listeFacultes">
                                           <%
                                            
                                            try{
                                                FaculteDao faculteDao = ((DaoFactory) getServletContext().getAttribute("daofactory")).getFaculteDao();
                                            FaculteForm ff = new FaculteForm(faculteDao);
                                           List<Faculte> list = ff.getFaculte();
                                           String fac = "";
                                           for(Faculte faculte : list){
                                            fac = faculte.getNom_faculte();
                                           out.write("<option value=\""+fac+"\">"+fac+"</option>");
                                           }
                                           }catch(Exception e){
                                                out.println("Probleme");
                                            }
                                            %>   
                                        </select> 
                                    <%--  <option value="">Choisissez une faculte</option>
                            <c:forEach items="${sessionScope.facultes}" var="mapFacultes">
                            <option value="${mapFacultes.key}">${mapFacultes.value.nom_faculte}</option>
                            </c:forEach>  
                            </select> --%>
                                    
                                 <%--   <select name="listeFacultes">
                                        <c:forEach items="${l}" var="item">
                                         <option value="">${item}</option>
                                        </c:forEach>
                                    </select> --%>
                                    
                                   
                                        <p class="erreur"> ${form.erreurs['nom_departement']} </p></td>
                                </tr>
                                <tr>
                            <td></td>
                            <td>
                                <%-- <p class="info"> ${form.resultat} </p> --%>
                                <button class="btn btn-primary"  type="submit" name="valider"  />Enregistrement<i class="icon-white icon-ok-sign"></i> </button>
                                <input type="reset" class="btn" value="Effacer" name="valider" />
                            
                            </td>
                        </tr>
                                </table></center>
                        </fieldset>

                 <p class="info">${form.resultat} </p>
                    
                </form>
            </div>
            <div id="bas">
                
            </div>
        </div>
    </body>
</html>
