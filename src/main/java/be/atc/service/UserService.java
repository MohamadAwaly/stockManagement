package be.atc.service;

import be.atc.controler.connexion.EMF;
import be.atc.entities.UsersEntity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserService {
    private static Logger logger = Logger.getLogger(UserService.class);
    EntityManager em = EMF.getEM();

    public List showAllUsers() {
        List<UsersEntity> user = new ArrayList<>();
        Query query = em.createNamedQuery("User.finddall");
        user = query.getResultList();
        //user = em.createQuery( "select  u from UsersEntity u" ).getResultList();
        return user;
    }
    //String lastName, String firstName, Date dayOfBirth,
    //                        Date inscriptionDate, String vat, String mail, String password, String login
    public void addUser(UsersEntity user) {
        logger.log(Level.INFO,"lastName: " + user.getFirstName());
    }

}
