/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Frais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import static Dao.DaoUtilitaire.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.joda.time.DateTime;

/**
 *
 * @author Gauss
 */
public class FraisDaoImpl implements FraisDao{

    private static final String SQL_SELECT = "SELECT id_frais, montant, type_frais FROM T_Frais";
    private static final String SQL_SELECT_PAR_ID = "SELECT id_frais, montant, type_frais FROM T_Frais WHERE id_frais = ?";
    private static final String SQL_INSERT = "INSERT INTO T_Frais (montant, type_frais) VALUES (?, ?) ";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM T_Frais WHERE id_frais = ? ";
    private DaoFactory daoFactory;

    public FraisDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    
    @Override
    public void creer(Frais frais) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_INSERT, true, frais.getMontant(), frais.getType_frais());
            int statut = preparedStatement.executeUpdate();
            if(statut == 0){
            throw new DaoException("Echec de paiement de frais, aucun ligne ajoutée dans la table");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if(valeursAutoGenerees.next()){
            frais.setId_frais(valeursAutoGenerees.getLong(1));
            } else {
            throw new DaoException("Echec de la creation de frais en base, aucun ID auto-généré retourné");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
        
    }

    @Override
    public Frais trouver(long id) throws DaoException {
        return trouver(SQL_SELECT_PAR_ID, id);
    }

    @Override
    public List<Frais> lister() throws DaoException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet= null;
       List<Frais> frais = new ArrayList<Frais>();
       
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while( resultSet.next()){
            frais.add( map (resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connection);
        }
        return frais;
    }

    @Override
    public void supprimer(Frais frais) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_DELETE_PAR_ID, true, frais.getId_frais());
            int statut = preparedStatement.executeUpdate();
            if( statut == 0 ){
            throw new DaoException("Echec de la suppression du frais, aucune ligne supprimée de la table");
            } else {
            frais.setId_frais(null);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
    }
    
    private Frais trouver (String sql, Object... objets) throws DaoException{
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Frais frais = null;
    
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, sql, true, objets);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            frais = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
    return frais;
    }
    
    private static Frais map (ResultSet resultSet) throws SQLException{
    Frais frais = new Frais();
    frais.setId_frais(resultSet.getLong("id_frais"));
    frais.setMontant(resultSet.getInt("montant"));
    frais.setType_frais(resultSet.getString("type_frais"));
    
    return frais;
    
    }
    
}
