/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Annee;
import java.util.List;

/**
 *
 * @author Gauss
 */
public class AnneeDaoImpl implements AnneeDao{

    private static final String SQL_SELECT = "SELECT annee_academique FROM T_Annee ";
    private static final String SQL_SELECT_PAR_ANNEE = "SELECT annee_academique FROM T_Annee WHERE annee_academique = ? ";
    public static final String SQL_INSERT = "INSERT INTO T_Annee(annee_academique) VALUES (?)";
    public static final String SQL_DELETE = "DELETE FROM T_Annee WHERE annee_academique = ? ";
    private DaoFactory daoFactory;

    public AnneeDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    
    @Override
    public void creer(Annee annee) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Annee trouver(long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Annee> lister() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Annee annee) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    private Annee trouver (String sql, Object... objets) throws DaoException{
//    
//    Connection connexion = null;
//    PreparedStatement preparedStatement = null;
//    Resu
//    }
    
}
