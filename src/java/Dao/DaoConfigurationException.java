/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

/**
 *
 * @author Gauss
 */
public class DaoConfigurationException extends RuntimeException {

    public DaoConfigurationException(String message) {
        super(message);
    }

    public DaoConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoConfigurationException(Throwable cause) {
        super(cause);
    }
    
}
