/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Frais;
import java.util.List;

/**
 *
 * @author Gauss
 */
public interface FraisDao {
    
    void creer (Frais frais) throws DaoException;
    Frais trouver (long id) throws DaoException;
    List<Frais> lister() throws DaoException;
    void supprimer( Frais frais ) throws DaoException;
}
