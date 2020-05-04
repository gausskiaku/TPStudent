/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Departement;
import Bean.Promotion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static Dao.DaoUtilitaire.*;
import java.util.ArrayList;

/**
 *
 * @author Gauss
 */
public class PromotionDaoImpl implements PromotionDao{
    
    private static final String SQL_SELECT = "SELECT nom_promotion, nom_departement FROM T_Promotion ";
    private static final String SQL_SELECT_PAR_NOM = "SELECT nom_promotion, nom_departement FROM T_Promotion WHERE nom_promotion = ? ";
    private static final String SQL_INSERT = "INSERT INTO T_Promotion (nom_promotion, nom_departement) VALUES (?, ?)";
    private static final String SQL_DELETE_PAR_NOM = "DELETE FROM T_Promotion WHERE nom_promotion = ? ";
    private DaoFactory daoFactory;

    public PromotionDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public void creer(Promotion promotion) throws DaoException {
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_INSERT, true, promotion.getNom_promotion(), promotion.getDepartement().getNom_departement());
            int statut = preparedStatement.executeUpdate();
            if(statut == 0){
            throw new DaoException("Echec de la creation de la promotion, aucune ligne ajoutée dans la table");
            }
//            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
//            if(valeursAutoGenerees.next()){
//            promotion.setNom_promotion(valeursAutoGenerees.getString(1));
//            } else {
//            throw new DaoException("Echec de la creation de la promotion, aucun ID auto-généré retourné.");
//            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public Promotion trouver(String nom) throws DaoException {
        return trouver(SQL_SELECT_PAR_NOM, nom);
    }

    @Override
    public List<Promotion> lister() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Promotion> promotions = new ArrayList<Promotion>();
        
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ){
            promotions.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connection);
        }
        return promotions;
    }

    @Override
    public void supprimer(Promotion promotion) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_DELETE_PAR_NOM, true, promotion.getNom_promotion());
            int statut =  preparedStatement.executeUpdate();
            if(statut == 0){
            throw new DaoException("Echec de la suppression de la promotion, aucune ligne supprimée de la table.");
            
            } else {
            promotion.setNom_promotion(null);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
    }
    
    private Promotion map (ResultSet resultSet) throws SQLException{
    Promotion promotion = new Promotion();
    promotion.setNom_promotion(resultSet.getString("nom_promotion"));
    
    DepartementDao departementDao = daoFactory.getDepartementDao();
    promotion.setDepartement(departementDao.trouver(resultSet.getString("nom_departement")));
    promotion.setNom_promotion(resultSet.getString("nom_promotion"));
    
    return promotion;
    
    }
    
    private Promotion trouver (String sql, Object... objets) throws DaoException{
    
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Promotion promotion = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, sql, false, objets);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
            promotion = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return promotion;
    }
    
}
