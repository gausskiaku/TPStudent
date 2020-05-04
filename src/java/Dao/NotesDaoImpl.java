/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Notes;
import java.util.List;
import static Dao.DaoUtilitaire.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gauss
 */
public class NotesDaoImpl implements NotesDao{
    public static final String SQL_SELECT = "SELECT * FROM T_Notes";
    public static final String SQL_SELECT_PAR_ID = "SELECT * FROM T_Notes WHERE id_notes = ?";
    public static final String SQL_INSERT = "INSERT INTO T_Notes(decision, pourcentage, nbre_reussi, nbre_echec, session, matricule_etu) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SQL_DELETE_PAR_ID = "DELETE FROM T_Notes WHERE id_notes = ?";
    private DaoFactory daoFactory;

    public NotesDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public void creer(Notes notes) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_INSERT, true, notes.getDecision(),
                                                                                          notes.getPourcentage(),
                                                                                          notes.getNbre_reussi(),
                                                                                          notes.getNbre_echec(),
                                                                                          notes.getSession(),
                                                                                          notes.getEtudiant().getMatricule_etu());
            int statut = preparedStatement.executeUpdate();
            if(statut == 0 ){
            throw new DaoException("Echec de la creation de notes, aucune ligne ajoutée dans la table");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if(valeursAutoGenerees.next()){
            notes.setId_notes(valeursAutoGenerees.getLong(1));
            } else {
            throw new DaoException("Echec de la creation de notes en base, aucun ID auto-généré ");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        } 
    }

    @Override
    public Notes trouver(long id) throws DaoException {
        return trouver(SQL_SELECT_PAR_ID, id);
    }

    @Override
    public List<Notes> lister() throws DaoException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       List<Notes> notes = new ArrayList<Notes>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            notes.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connection);
        }
        return notes;
    }

    @Override
    public void supprimer(Notes notes) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePrepare(connexion, SQL_DELETE_PAR_ID, true, notes.getId_notes());
            int statut = preparedStatement.executeUpdate();
            if(statut == 0){
            throw new DaoException("Echec de la suppression de notes, aucune ligne supprimée de la table.");
            } else {
            notes.setId_notes(null);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
    }
    
   private Notes trouver(String sql, Object... objets) throws DaoException{
       Connection connexion = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       Notes notes = null;
       try {
           connexion = daoFactory.getConnection();
           preparedStatement = initialisationRequetePrepare(connexion, sql, true, objets);
           resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
           notes = map(resultSet);
           }
       } catch (SQLException e) {
           throw new DaoException(e);
       } finally {
           fermeturesSilencieuses(resultSet, preparedStatement, connexion);
       }
       return notes;
   }
   
   private Notes map(ResultSet resultSet) throws SQLException{
   
       Notes notes = new Notes();
       EtudiantDao etudiantDao = daoFactory.getEtudiantDao();
       notes.setId_notes(resultSet.getLong("id_notes"));
       notes.setDecision(resultSet.getString("decision"));
       notes.setPourcentage(resultSet.getInt("pourcentage"));
       notes.setNbre_reussi(resultSet.getInt("nbre_reussi"));
       notes.setNbre_echec(resultSet.getInt("nbre_echec"));
       notes.setSession(resultSet.getString("session"));
       notes.setEtudiant(etudiantDao.trouver(resultSet.getLong("matricule_etu")));
       
       return notes;
   }
}
