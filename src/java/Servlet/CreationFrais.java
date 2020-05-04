/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Frais;
import Dao.DaoFactory;
import Dao.FraisDao;
import Form.FraisForm;
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
public class CreationFrais extends HttpServlet{
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_FRAIS = "frais";
    public static final String ATT_FORM = "form";
    public static final String SESSION_FRAIS = "fraiss";
    public static final String VUE = "/creationFrais.jsp";
    
    private FraisDao fraisDao;
    

    @Override
    public void init() throws ServletException {
        this.fraisDao = ( (DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFraisDao();
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FraisForm form = new FraisForm(fraisDao);
        Frais frais = form.creerFrais(req);
        
        req.setAttribute(ATT_FRAIS, frais);
        req.setAttribute(ATT_FORM, form);
        if(form.getErreurs().isEmpty()){
        
            HttpSession session = req.getSession();
            Map<Long, Frais> fraiss = (HashMap<Long, Frais>) session.getAttribute(SESSION_FRAIS);
            if(fraiss == null){
            fraiss = new HashMap<Long, Frais>();
            }
            fraiss.put(frais.getId_frais(), frais);
            session.setAttribute(SESSION_FRAIS, fraiss);
        }
       
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }
    
    
    
}
