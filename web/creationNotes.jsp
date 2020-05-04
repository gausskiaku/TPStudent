<%-- 
    Document   : creationNotes
    Created on : 4 sept. 2015, 15:07:11
    Author     : Gauss
--%>

<%@page import="Dao.PromotionDao"%>
<%@page import="Dao.DaoFactory"%>
<%@page import="Form.EtudiantForm"%>
<%@page import="Bean.Etudiant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ListIterator"%>
<%@page import="Dao.EtudiantDao"%>
<%@page import="java.util.List"%>
<%@page import="Dao.EtudiantDaoImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrement Notes</title>
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
            <form method="post" class="form-horizontal well" id="form1" action="<c:out value="creationNotes"/>">
            <legend style="font-size: 2em;color: blue;"> Information Notes</legend>
                    <fieldset>
                    <center><table border="0" style="width: 800px;">
                            <tr>
                                <td width="150">
                                    Decision
                                </td>
                                <td>
                                    <select name="decision">
                        <option value="">Choisissez la decision</option>
                        <option value="PGD">PDG</option>
                        <option value="D">D</option>
                        <option value="S">S</option>
                        <option value="A">A</option>
                        <option value="AA">AA</option>
                        <option value="ANAF">ANAF</option>
                        <option value="NAF">NAF</option>
                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="150">
                                   Poucentage 
                                </td>
                                <td>
                                    <input type="text" name="pourcentage" value="${notes.pourcentage}" size="30" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Nombre de cours reussi
                                </td>
                                <td>
                                   <input type="text" name="nbre_reussi" value="${notes.nbre_reussi}" size="30" /> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Nombre de cours echoué
                                </td>
                                <td>
                                    <input type="text" name="nbre_echec" value="${notes.nbre_echec}" size="30" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Session
                                </td>
                                <td>
                                    <select name="listeSessions">
                        <option value=""> Choisissez la session</option>
                        <option value="1er session">Première session</option>
                        <option value="2eme session">Deuxième session</option>
                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                   Matricule etudiant 
                                </td>
                                <td>
                                   <select name="listeEtudiants">
                                       <% 
                                       EtudiantDao etudiantDao = ((DaoFactory) getServletContext().getAttribute("daofactory")).getEtudiantDao();
                                       PromotionDao promotionDao = ((DaoFactory) getServletContext().getAttribute("daofactory")).getPromotionDao();
                                       EtudiantForm ee = new EtudiantForm(promotionDao, etudiantDao);
                                       List<Etudiant> list = ee.getEtudiant();
                                       Long etu ;
                                       for(Etudiant etudiant : list ){
                                       etu = etudiant.getMatricule_etu();
                                       out.write("<option value =\""+etu+"\">"+etu+"</option>");
                                       }
                                       %>
                        <%-- <option value="">Choisissez l'Etudiant</option>
                        <c:forEach items="${sessionScope.etudiants}" var="mapEtudiants" >
                            <option value="${mapEtudiants.key}">${mapEtudiants.value.matricule_etu}</option>
                        </c:forEach> --%>
                    </select> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    
                                </td>
                                <td>
                                    <p class="info">${form.resultat}</p>
                                   <button class="btn btn-primary"  type="submit" name="valider"  />Enregistrement<i class="icon-white icon-ok-sign"></i> </button>
                                <input type="reset" class="btn" value="Effacer" name="valider" /></td> 
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
