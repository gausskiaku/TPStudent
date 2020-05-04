/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Departement;
import Bean.Faculte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static Dao.DaoUtilitaire.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Gauss
 */
public class FaculteDaoImpl implements FaculteDao{

    
    private static final String SQL_SELECT_PAR_NOM = "SELECT nom_faculte FROM T_Faculte WHERE nom_faculte = ?";
    private static final String SQL_INSERT = "INSERT INTO T_Faculte (nom_faculte, mot_de_passe) VALUES (?, ?)";
    private static final String SQL_SELECT = "SELECT nom_faculte FROM T_Faculte ";
    private static final String SQL_DELETE_PAR_NOM = "DELETE FROM T_Faculte WHERE nm_faculte = ?";
    private DaoFactory daoFactory;
    
    public FaculteDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public FaculteDaoImpl() {
    }

    
    private static Faculte map (ResultSet resultSet) throws SQLException{
    Faculte faculte = new Faculte();
    faculte.setNom_faculte(resultSet.getString("nom_faculte"));
    
    return faculte;
    }
    @Override
    public void creer(Faculte faculte) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_INSERT, false, faculte.getNom_faculte(), faculte.getMot_de_passe());
            int statut = preparedStatement.executeUpdate();
            
            if(statut == 0){
            throw new DaoException("Échec de la création de la faculte, aucune ligne ajoutée dans la table.");
            }
//            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
//            if(valeursAutoGenerees.next()){
//            faculte.setNom_faculte(valeursAutoGenerees.getString(1));
//            } else {
//            throw new DaoException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
//            }
            
        
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public Faculte trouver(String nom) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Faculte faculte = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_SELECT_PAR_NOM, false, nom);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
            faculte = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(resultSet , preparedStatement, connexion);
        }
        return faculte;
    }

    @Override
    public List<Faculte> lister() throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Faculte> facultes = new ArrayList<Faculte>();
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
            facultes.add( map(resultSet) );
            }
        } catch (SQLException e) {
            throw new DaoException (e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return facultes;
    }

    @Override
    public void supprimer(Faculte faculte) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
