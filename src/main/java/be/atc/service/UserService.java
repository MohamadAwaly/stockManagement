package be.atc.service;

import be.atc.controler.connexion.EMF;
import be.atc.entities.AdressEntity;
import be.atc.entities.AdressUsersEntity;
import be.atc.entities.UsersEntity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final Logger logger = Logger.getLogger( UserService.class );
    EntityManager     em    = EMF.getEM();
    EntityTransaction trans = em.getTransaction();

    /**
     * show all user
     *
     * @return
     */
    //    public List showAllUsers() {
    //        List<Object> user = new ArrayList<>();
    //        Query query = em.createNamedQuery("User.finddall");
    //        user = query.getResultList();
    //        return user;
    //    }

    /**
     * add user
     *
     * @param user
     */
    public boolean addUser( UsersEntity user, AdressEntity adressEntity, AdressUsersEntity adressUser ) {
        try {
            UserService usercheck = new UserService();
            logger.log( Level.INFO,"dans le service adduser utilisateur: " + usercheck.checkUserExist( user.getLogin() ) );
            if ( usercheck.checkUserExist( user.getLogin() ) || usercheck.checkVatExist( user.getVat() ) ) {
                logger.log( Level.INFO, "Utilisateur existe Impossible de l'ajouter" );
                return false;
            } else {
                trans.begin();
                em.merge( user );
                em.merge( adressEntity );
                em.merge( adressUser );
                trans.commit();
                logger.log( Level.INFO, "Utilisateur ajouter" );
                return true;
            }
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Erreur dans le servis d'ajout user" );
            trans.rollback();
            return false;
        } finally {
            // em.close();
        }
    }

    /**
     * List of users with adress and roles
     *
     * @return
     */
    public List<Object[]> showAllUsers() {
        List<Object[]> user = new ArrayList<>();
        Query query = em.createNamedQuery( "User.finddall" );
        user = query.getResultList();
        return query.getResultList();
    }

    /**
     * check if login & vat exist
     *
     * @param login
     * @return
     */
    public boolean checkUserExist( String login ) {
        Query query = em.createNamedQuery( "User.checkUserExist" );
        query.setParameter( "login", login );
        String user = "";
        try {
            user = (String) query.getSingleResult();
            if ( user.equals( login ) ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            return false;
        }

    }

    /**
     * check if vat exist
     *
     * @param vat
     * @return
     */
    public boolean checkVatExist( String vat ) {
        Query query = em.createNamedQuery( "User.checkVatExist" );
        query.setParameter( "vat", vat );
        String userVat = "";
        try {
            userVat = (String) query.getSingleResult();
            if ( userVat.equals( vat ) ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Numéro de tva existe: " + e.getMessage() );
            return false;
        }
    }
}