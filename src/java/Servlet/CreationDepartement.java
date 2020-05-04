/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Departement;
import Bean.Faculte;
import Dao.DaoFactory;
import Dao.DepartementDao;
import Dao.FaculteDao;
import Form.DepartementForm;
import Form.FaculteForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
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
public class CreationDepartement extends HttpServlet {
    public static final String VUE = "/creationDepartement.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_DEPARTEMENT = "departement";
    public static final String ATT_FORM = "form";
    public static final String APPLICATION_FACULTES = "initFacultes";
    public static final String APPLICATION_DEPARTEMENTS = "initDepartements";
    public static final String SESSION_FACULTES = "facultes";
    public static final String SESSION_DEPARTEMENTS = "departements";
    public static final String ATT_MAP_FACULTES = "mapFac";
    public static final String ATT_MAP_DEPARTEMENTS = "mapDep";
    private FaculteDao faculteDao;
    private DepartementDao departementDao;
//    private Map<String, List> fac;

    @Override
    public void init() throws ServletException {
       this.faculteDao = ( (DaoFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getFaculteDao();
       this.departementDao = ( (DaoFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getDepartementDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
//        String nom = req.getParameter("nom_departement");
//        req.setAttribute("nom", nom);
        DepartementForm form = new DepartementForm(faculteDao, departementDao);
        Departement departement = form.creerDepartement(req);
        req.setAttribute(ATT_DEPARTEMENT, departement);
        req.setAttribute(ATT_FORM, form);
        
        
        
        FaculteForm ff = new FaculteForm(faculteDao);
        List<Faculte> fac = ff.getFaculte();
        if(fac.isEmpty()){
        req.setAttribute("l", fac);
        }
        else {
        req.setAttribute("l", fac);
        }
        
        
        
        
        
        if(form.getErreurs().isEmpty()){
            HttpSession session = req.getSession();
            
            Map<String, Faculte> facultes = (HashMap<String, Faculte>) session.getAttribute(SESSION_FACULTES);
        if(facultes == null){
            facultes = new HashMap<String, Faculte>();}
            facultes.put(departement.getFaculte().getNom_faculte(), departement.getFaculte());
            session.setAttribute(SESSION_FACULTES, facultes);
            req.setAttribute(ATT_MAP_FACULTES, facultes);
            
            // Pour Departement
            Map<String, Departement> departements = (HashMap<String, Departement>)session.getAttribute(SESSION_DEPARTEMENTS);
            if(departements == null){
                departements = new HashMap<String, Departement>();
            }
            departements.put(departement.getNom_departement(), departement);
            session.setAttribute(SESSION_DEPARTEMENTS, departements);
            req.setAttribute(ATT_MAP_DEPARTEMENTS, departements);
        
        }   
       
        
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);    }
    
}
