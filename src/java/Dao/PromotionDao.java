/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Promotion;
import java.util.List;

/**
 *
 * @author Gauss
 */
public interface PromotionDao {
    
    void creer ( Promotion promotion ) throws DaoException;
    Promotion trouver ( String nom ) throws DaoException;
    List<Promotion> lister() throws DaoException;
    void supprimer (Promotion promotion) throws DaoException;
}
