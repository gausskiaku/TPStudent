/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Etudiant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import static Dao.DaoUtilitaire.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.joda.time.DateTime;
/**
 *
 * @author Gauss
 */
public class EtudiantDaoImpl implements EtudiantDao{

    private static final String SQL_SELECT = "SELECT * FROM T_Etudiant";
    private static final String SQL_SELECT_PAR_MATRICULE = "SELECT * FROM T_Etudiant WHERE matricule_etu = ? ";
    private static final String SQL_INSERT = "INSERT INTO T_Etudiant (nom_etu, postnom_etu, prenom_etu, sexe_etu, situation, nom_promotion, annee_academique) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_MATRICULE = "DELETE FROM T_Etudiant WHERE matricule_etu = ?";
    private DaoFactory daoFactory;
    

    public EtudiantDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public void creer(Etudiant etudiant) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_INSERT, true, etudiant.getNom_etu(),
                                                                                          etudiant.getPostnom_etu(),
                                                                                          etudiant.getPrenom_etu(),
                                                                                          etudiant.getSexe_etu(),
                                                                                          etudiant.getSituation(),
                                                                                          etudiant.getPromotion().getNom_promotion(),
                                                                                          etudiant.getAnnee_academique()
                                                                                          ); 
            int statut = preparedStatement.executeUpdate();
            if(statut == 0){
            throw new DaoException("Echec de la creation du client, aucune ligne ajoutée dans la table");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if(valeursAutoGenerees.next()){
            etudiant.setMatricule_etu(valeursAutoGenerees.getLong(1));
            } else {
            throw new DaoException("Echec de la creation de l'etudiant, aucune ligne ajoutée dans la table.");
            }
            
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public Etudiant trouver(long id) throws DaoException {
        return trouver(SQL_SELECT_PAR_MATRICULE, id);
    }

    @Override
    public List<Etudiant> lister() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Etudiant> etudiants = new ArrayList<Etudiant>();
        
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            etudiants.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connection);
        }
        return etudiants;
    }

    @Override
    public void supprimer(Etudiant etudiant) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_DELETE_PAR_MATRICULE, true, etudiant.getMatricule_etu());
            int statut = preparedStatement.executeUpdate();
            if(statut == 0){
            throw new DaoException("Echec de la suppression de l'etudiant, aucune ligne supprimée de la table");
            } else {
            etudiant.setMatricule_etu(null);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
    }
    
    private Etudiant trouver (String sql, Object... objets) throws DaoException{
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Etudiant etudiant = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, sql, true, objets);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
            etudiant = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
    return etudiant;
    
    }
    
    private Etudiant map (ResultSet resultSet) throws SQLException{
    
        Etudiant etudiant = new Etudiant();
        etudiant.setMatricule_etu(resultSet.getLong("matricule_etu"));
        etudiant.setNom_etu(resultSet.getString("nom_etu"));
        etudiant.setPostnom_etu(resultSet.getString("postnom_etu"));
        etudiant.setPrenom_etu(resultSet.getString("prenom_etu"));
        etudiant.setSexe_etu(resultSet.getString("sexe_etu"));
        etudiant.setSituation(resultSet.getString("situation"));
        
        PromotionDao promotionDao = daoFactory.getPromotionDao();
        etudiant.setPromotion(promotionDao.trouver(resultSet.getString("nom_promotion")));
        
        etudiant.setAnnee_academique(resultSet.getString("annee_academique"));
        
       return etudiant;
    }
}
