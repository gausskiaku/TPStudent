/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.DaoConfigurationException;
import Dao.FaculteDao;
import Dao.FaculteDao;
import Dao.FaculteDaoImpl;
import Dao.FaculteDaoImpl;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Gauss
 */
public class DaoFactory {
    private static final String FICHIER_PROPERTIES = "/Dao/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";
    BoneCP connectionPool = null;
    
//    private String url;
//    private String username;
//    private String password;

    public DaoFactory(BoneCP connectionPool) {
        this.connectionPool = connectionPool;
    }
    
    public static DaoFactory getInstance() throws DaoConfigurationException{
    
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;
        BoneCP connectionPool = null ;
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
        
        if(fichierProperties == null){
        
            throw new DaoConfigurationException("Le fichier properties "+ FICHIER_PROPERTIES + " est introuvable.");
        }
        try {
            properties.load( fichierProperties );
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
            motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
            
        } catch(FileNotFoundException e){
        throw new DaoConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.", e);
        }catch (IOException e) {
            throw new DaoConfigurationException("Impossible de charger le fichier " + FICHIER_PROPERTIES, e);
        }
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DaoConfigurationException("Le driver est introuvable dans le classpath.", e);
        }
        try {
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(url);
            config.setUsername(nomUtilisateur);
            config.setPassword(motDePasse);
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(2);
            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoConfigurationException("Erreur de configuration du pool de connexion.", e);
        }
    
        DaoFactory instance = new DaoFactory(connectionPool);
        return instance;
    } 
    
    public Connection getConnection() throws SQLException{
    return connectionPool.getConnection();
    
    }
    
    public FaculteDao getFaculteDao(){
    return new FaculteDaoImpl(this);
    }
    public DepartementDao getDepartementDao(){
    return new DepartementDaoImpl(this);
    }
    public FraisDao getFraisDao(){
    return new FraisDaoImpl(this);
    }
    public PromotionDao getPromotionDao(){
    return new PromotionDaoImpl(this);
    }
    public EtudiantDao getEtudiantDao(){
    return new EtudiantDaoImpl(this);
    }
    public NotesDao getNotesDao(){
    return new NotesDaoImpl(this);
    }
    
}   
