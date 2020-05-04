/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Notes;
import java.util.List;

/**
 *
 * @author Gauss
 */
public interface NotesDao {
    void creer (Notes notes) throws DaoException;
    Notes trouver (long id) throws DaoException;
    List<Notes> lister() throws  DaoException;
    void supprimer(Notes notes) throws DaoException;
    
}
