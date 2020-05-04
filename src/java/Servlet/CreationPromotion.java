/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Departement;
import Bean.Promotion;
import Dao.DaoFactory;
import Dao.DepartementDao;
import Dao.PromotionDao;
import Form.PromotionForm;
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
public class CreationPromotion extends HttpServlet {
    public static final String VUE = "/creationPromotion.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_PROMOTION = "promotion";
    public static final String ATT_FORM = "form";
    public static final String SESSION_DEPARTEMENTS = "departements";
    public static final String SESSION_PROMOTIONS = "promotions";
    public static final String APPLICATION_DEPARTEMENTS = "initDepartements";
    public static final String APPLICATIONS_PROMOTIONS = "initPromotions";
    
    private DepartementDao departementDao;
    private PromotionDao promotionDao;

    @Override
    public void init() throws ServletException {
        this.departementDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getDepartementDao();
        this.promotionDao = ((DaoFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getPromotionDao();
    } 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
//        String val = req.getParameter("nom_promotion");
//        req.setAttribute("gauss", val);
        PromotionForm form = new PromotionForm(departementDao, promotionDao);
        Promotion promotion = form.creerPromotion(req);
        req.setAttribute(ATT_PROMOTION, promotion);
        req.setAttribute(ATT_FORM, form);
        
        if(form.getErreurs().isEmpty()){
        
            HttpSession session = req.getSession();
            Map<String, Departement> departements = (HashMap<String, Departement>) session.getAttribute(SESSION_DEPARTEMENTS);
            if(departements == null){
            departements = new HashMap<String, Departement>();
            }
            departements.put(promotion.getDepartement().getNom_departement(), promotion.getDepartement());
            session.setAttribute(SESSION_DEPARTEMENTS, departements);
            
            Map<String, Promotion> promotions = (HashMap<String,Promotion>) session.getAttribute(SESSION_PROMOTIONS);
            if ( promotions == null){
            promotions = new HashMap<String, Promotion>();
            }
            promotions.put(promotion.getNom_promotion(), promotion);
            session.setAttribute(SESSION_PROMOTIONS, promotions);
        }
        
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }
    
    
    
}
