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
    private static Logger logger = Logger.getLogger( UserService.class );
    EntityManager em = EMF.getEM();
    EntityTransaction trans = em.getTransaction();
    /**
     * show all user
     *
     * @return
     */
    public List showAllUsers() {
        List<UsersEntity> user = new ArrayList<>();
        Query query = em.createNamedQuery( "User.finddall" );
        user = query.getResultList();
        return user;
    }

    /**
     * add user
     *
     * @param user
     */
    public void addUser( UsersEntity user, AdressEntity adressEntity, AdressUsersEntity adressUser ) {
        try {
            trans.begin();
            em.merge(user);
            em.merge(adressEntity);
            em.merge( adressUser );
            trans.commit();
        } catch ( Exception e ) {
            trans.rollback();
        } finally {
            //            em.close();
        }
    }

}
