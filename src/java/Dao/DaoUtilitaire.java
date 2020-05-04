/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gauss
 */
public class DaoUtilitaire {
    public static void fermetureSilencieuse( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
            System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
                                        }
                                }
            }
    
    public static void fermetureSilencieuse( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
        System.out.println( "Échec de la fermeture du Statement: " + e.getMessage() );
        }
        }
}
    public static void fermetureSilencieuse( Connection connexion ) {
        if ( connexion != null ) {
            try {
            connexion.close();
        } catch (SQLException e ) {
    System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
                            }
            }
        }
    
    
    public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
    fermetureSilencieuse( statement );
    fermetureSilencieuse( connexion );
    }
    
    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
    fermetureSilencieuse( resultSet );
    fermetureSilencieuse( statement );
    fermetureSilencieuse( connexion );
    }
    
    public static PreparedStatement initialisationRequetePrepare (Connection connexion, String sql, boolean returnGeneratedKeys, Object... objects) throws SQLException{
    PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
    
    for(int i = 0; i < objects.length; i++){
    preparedStatement.setObject(i + 1, objects[i]);
    }
    return preparedStatement;
    }
}
