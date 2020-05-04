/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Departement;
import Bean.Faculte;
import java.util.List;

/**
 *
 * @author Gauss
 */
public interface DepartementDao {
    
    void creer ( Departement departement ) throws DaoException;
    Departement trouver ( String nom ) throws DaoException;
    List<Departement> lister() throws DaoException;
    void supprimer (Departement departement) throws DaoException;
}
