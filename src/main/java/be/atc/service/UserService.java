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
    private static final Logger logger = Logger.getLogger(UserService.class);
    EntityManager em = EMF.getEM();
    EntityTransaction trans = em.getTransaction();

    /**
     * show all user
     *
     * @return
     */
    public List showAllUsers() {
        List<Object> user = new ArrayList<>();
        Query query = em.createNamedQuery("User.finddall");
        user = query.getResultList();
        return user;
    }

    /**
     * add user
     *
     * @param user
     */
    public void addUser(UsersEntity user, AdressEntity adressEntity, AdressUsersEntity adressUser) {
//        UsersEntity usercheck = new UsersEntity();
//        UserService usercheck = new UserService();
//        if (usercheck.checkUserExist(user.getLogin())){
//            logger.log(Level.INFO,"Utilisateur existe Impossible de l'ajouter");
//        } else {
//            logger.log(Level.INFO,"L'utilisateur n'existe pas et va etre ajouter");
//        }
        try {
            trans.begin();
            em.merge(user);
            em.merge(adressEntity);
            em.merge(adressUser);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            // em.close();
        }
    }


    public List showAllUserstest() {
        List<Object[]> user = new ArrayList<>();
        Query query = em.createNamedQuery("User.finddall");
        user = query.getResultList();
        return user;
    }

    /**
     * check if login exist
     * @param login
     * @return
     */
    public boolean checkUserExist(String login) {
        Query query = em.createNamedQuery("User.checkUserExist");
        query.setParameter("login", login);
        String user = "";
        try {
            user = (String) query.getSingleResult();
//            logger.log(Level.INFO, "user check: " + user);
            if (user.equals(login)) {
//                logger.log(Level.INFO, "Dans le if de check user existe : " + user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
//            logger.log(Level.INFO, "Erreur: login n'existe pas " + login );
            return false;
        }

    }
}