<%-- 
    Document   : creationEtudiant
    Created on : 4 sept. 2015, 03:38:03
    Author     : Gauss
--%>

<%@page import="Bean.Promotion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Dao.DepartementDao"%>
<%@page import="Form.PromotionForm"%>
<%@page import="Dao.EtudiantDao"%>
<%@page import="Dao.PromotionDao"%>
<%@page import="Dao.DaoFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrer Etudiant</title>
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
                <form method="post" class="form-horizontal well" id="form1" action="<c:out value="creationEtudiant" />">
                    <legend style="font-size: 2em;color: blue;"> Information Etudiant</legend>
                        <fieldset>
                            <center><table border="0" style="width: 800px;">
                                <tr>
                                    <td width="150">
                                        Nom de l'Etudiant : 
                                    </td>
                                    <td width="150">
                                        <input type="text" name="nom_etu" value="${etudiant.nom_etu}" size="130" />
                                    </td>
                                </tr>
                                <tr><td width="155">PostNom de l'Etudiant: </td>
                                    <td><input type="text" name="postnom_etu" value="${etudiant.postnom_etu}" size="30" /></td>
                                </tr>
                                <tr>
                        <td width="150">Prenom de l'Etudiant :</td>
                        <td><input type="text" name="prenom_etu" value="${etudiant.prenom_etu}" size="30" />
                        </td>
                        <tr>
                        <td width="150">Sexe de l'Etudiant : </td>
                        <td> <select name="listeSexes">
                            <option value="Masculin">Masculin</option>
                            <option value="Feminin">Feminin</option>
                        </td>
                            </tr>
                        
                        <tr>
                        <td width="150">Promotion : </td>
                        <td><select name="listePromotions">
                                <% 
                                try{
                        PromotionDao promotionDao = ((DaoFactory) getServletContext().getAttribute("daofactory")).getPromotionDao();
                        DepartementDao departementDao = ((DaoFactory) getServletContext().getAttribute("daofactory")).getDepartementDao();
                        PromotionForm pp = new PromotionForm(departementDao, promotionDao);
                        List<Promotion> list = pp.getPromotion();
                        String pro = "";
                        for(Promotion promotion : list){
                        pro = promotion.getNom_promotion();
                        out.write("<option value=\""+pro+"\">"+pro+"</option>");
                        }
                        }catch(Exception e){
                        out.println(e);
                        }
                                %>
                            <option value="">Choisissez une promotion</option>
                            <c:forEach items="${sessionScope.promotions}" var ="mapPromotions">
                                <option value="${mapPromotions.key}">${mapPromotions.value.nom_promotion}</option>
                            </c:forEach>
                            </select></td>
                        </tr>
                        <tr>
                        <td width="150">Année Academique : </td>
                        <td><select name="listeAnnees">
                            <option value="2014 - 2015">2014 - 2015</option>
                            <option value="2015 - 2016">2015 - 2016</option>
                            <option value="2016 - 2017">2016 - 2017</option>
                            <option value="2017 - 2018">2017 - 2018</option>
                            <option value="2018 - 2019">2018 - 2019</option>
                        </select>
                        </td>
                        <tr>
                            <td></td>
                            <td>
                            <button class="btn btn-primary"  type="submit" name="valider" value="Remettre à zero" />Enregistrement<i class="icon-white icon-ok-sign"></i> </button>
                            <input type="reset" class="btn" value="Effacer" name="Remettre à zero" />
                            
                            </td>
                        </tr>
                                </table></center>
                        </fieldset>

                 <p class="info">${form.resultat}</p>
                    
                </form>
            </div>
            <div id="bas">
                
            </div>
        </div>
      
    </body>
</html>
