/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Departement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import static Dao.DaoUtilitaire.*;
import java.sql.SQLException;
import java.util.ArrayList;
//import static Dao.DaoUtilitaire.

/**
 *
 * @author Gauss
 */
public class DepartementDaoImpl implements DepartementDao{

    private static final String SQL_SELECT_PAR_NOM = "SELECT nom_departement, nom_faculte FROM T_Departement WHERE nom_departement = ?";
    private static final String SQL_INSERT = "INSERT INTO T_Departement (nom_departement, nom_faculte) VALUES (?, ?)";
    private static final String SQL_SELECT = "SELECT nom_departement, nom_faculte FROM T_Departement ";
    private static final String SQL_DELETE_PAR_NOM = "DELETE FROM T_Departement WHERE nom_departement = ?";
    private DaoFactory daoFactory;

    public DepartementDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public void creer(Departement departement) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_INSERT, true, departement.getNom_departement(),departement.getFaculte().getNom_faculte());
            
            int statut = preparedStatement.executeUpdate();
            if( statut == 0 ){
            throw new DaoException("Echec de la création du departement, aucune ligne ajoutée dans la table");
            }
            
        } catch (Exception e) {
            throw new DaoException(e);
        } finally{
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public Departement trouver(String nom) throws DaoException {
       return trouver(SQL_SELECT_PAR_NOM, nom);
    }

    @Override
    public List<Departement> lister() throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Departement> departements = new ArrayList<Departement>();
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
            departements.add( map(resultSet) );
            }
        } catch (SQLException e) {
            throw new DaoException (e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return departements;
    }

    @Override
    public void supprimer(Departement departement) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_DELETE_PAR_NOM, true, departement.getNom_departement());
            int statut = preparedStatement.executeUpdate();
            
            if(statut == 0){
            throw new DaoException("Echec de la suppression du departement, aucune ligne supprimée de la table.");
            } else {
            departement.setNom_departement(null);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }
    
    private Departement trouver (String sql, Object... objets) throws DaoException{
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Departement departement = null;
    
        try {
            
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, sql, false, objets);
            resultSet = preparedStatement.executeQuery();
            
            if( resultSet.next() ){
            departement = map( resultSet );
            }
            
        } catch (Exception e) {
            throw new DaoException (e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
    return departement;
    }
    
    private Departement map (ResultSet resultSet) throws SQLException{
    
        Departement departement = new Departement();
        departement.setNom_departement(resultSet.getString("nom_departement"));
        
        FaculteDao faculteDao = daoFactory.getFaculteDao();
        departement.setFaculte(faculteDao.trouver(resultSet.getString("nom_faculte")));
        
        departement.setNom_departement(resultSet.getString("nom_departement"));
        return departement;
    }
    
}
