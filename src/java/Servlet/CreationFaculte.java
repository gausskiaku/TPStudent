/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Faculte;
import Dao.DaoFactory;
import Dao.FaculteDao;
import Form.FaculteForm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gauss
 */
public class CreationFaculte extends HttpServlet{
    
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE = "/creationFaculte.jsp";
    public static final String ATT_FACULTE = "faculte";
    public static final String ATT_FORM = "form";
    public static final String SESSION_FACULTES = "facultes";
//    public static final String NOM_FAC = "nom_faculte";
    private FaculteDao faculteDao;

    @Override
    public void init() throws ServletException {
        this.faculteDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFaculteDao();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
    
//       String nom = request.getParameter("nom_faculte");
       FaculteForm form = new FaculteForm(faculteDao);
        
       Faculte faculte = form.inscrireFaculte(request);
       
       request.setAttribute(ATT_FORM, form);
       request.setAttribute(ATT_FACULTE, faculte);
//       request.setAttribute("nom", nom);
//       req.setAttribute("faculte2", nom);
       if(form.getErreurs().isEmpty()){
       HttpSession session = request.getSession();
       Map<String, Faculte> facultes = (HashMap<String, Faculte>) session.getAttribute(SESSION_FACULTES);
       if(facultes == null){
       facultes = new HashMap<String, Faculte>();
       }
       facultes.put(faculte.getNom_faculte(), faculte);
       session.setAttribute(SESSION_FACULTES, facultes);
       
       }
       
       this.getServletContext().getRequestDispatcher(VUE).forward(request, resp);  

    }  
}
