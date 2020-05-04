<%-- 
    Document   : user
    Created on : 7 sept. 2015, 08:27:16
    Author     : Gauss
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="Css/style.css" />
        <title>User</title>
    </head>
    <body>
        <div id="tout">
            <div id = "entete">
               <center>   <img src="images/logo2.gif"/> </center> 
            </div>
            <div id="menu">
                <c:import url="Menu.jsp"/>
            </div>
            <div id="corps">
                <form method="post" class="form-horizontal well" id="form1" action="<c:out value="user"/>">
                    
                    <fieldset>
                      <legend style="font-size: 2em;color: blue;"> User </legend>
                      <center><table border="0" style="width: 800px;">
                              <tr>
                                  <td>
                                      <label for="listeFacultes">Faculte :</label> </td>
                                  <td><select name="listeFacultes">
                                            <option></option>
                                            <option></option>
                                        </select>
                                  </td>
                              </tr>
                              
                              <tr>
                                  <td> <label for="password">Mot de passe : </label></td>
                                  <td> <input type="password" name="motDePasse" value="" size="30" /></td>
                              </tr>
                              <tr>
                                  <td> </td>
                                  <td>
                                      <button class="btn btn-primary" type="submit" name="valider"/> Enregistrement <i class="icon-white icon-ok-sign"></i></button>
                                      <input type="reset" class="btn" value="Effacer" name="valider" />
                                  </td>
                                  
                              </tr>
                             
                          </table></center>
                        
                    </fieldset>
                </form> 
            </div>
            
            
        </div>
        <form method="post" action="creationUser">
        
        
        <br />
        <input type="submit" value="Valider" name="valider" />
        <input type="submit" value="Effacer" name="effacer" />
        
        </form>
        
        
    </body>
</html>
