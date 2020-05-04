/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import Dao.DaoFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 
 *
 * @author Gauss
 */

public class InitialisationDaoFactory implements ServletContextListener {
//private static final String ATT_DAO_FACTORY = "daofactory";
//private DaoFactory daoFactory;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ServletContext servletContext = sce.getServletContext();
//        this.daoFactory = DaoFactory.getInstance();
//        servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
       throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
