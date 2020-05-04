/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Etudiant;
import java.util.List;

/**
 *
 * @author Gauss
 */
public interface EtudiantDao {
    void creer (Etudiant etudiant) throws DaoException;
    Etudiant trouver (long id) throws DaoException;
    List<Etudiant> lister() throws DaoException;
    void supprimer (Etudiant etudiant) throws DaoException;
    
}
