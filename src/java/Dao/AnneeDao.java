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
public interface AnneeDao {
    void creer (Annee annee) throws DaoException;
    Annee trouver (long id) throws DaoException;
    List<Annee> lister() throws DaoException;
    void supprimer(Annee annee) throws DaoException;
}
