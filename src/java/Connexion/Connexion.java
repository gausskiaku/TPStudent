/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Gauss
 */
public class Connexion {
  
    private static String url ="jdbc:odbc:TpGenie";
    private static String user ="sa";
    private static String passwd = "gausskiaku05";
    
    private static Connection connect;
    ResultSet rs ;
    Statement st;
    public static Connection getInstance(){
        if(connect== null){
            try {
                connect = DriverManager.getConnection(url, user, passwd);
                System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
                
            } catch (SQLException e) {
//               JOptionPane.showMessageDialog(null, "ERREUR : La connexion n'est pas etablie", "PROBLEME DE CONNEXION", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connect;
    }
}
