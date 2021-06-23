package be.atc.service;

import be.atc.controler.connexion.EMF;
import be.atc.entities.UsersEntity;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class UserService {
    private static Logger log = Logger.getLogger(UserService.class);
    EntityManager em = EMF.getEM();

    public List showAllUser() {
        Query query = em.createNamedQuery("User.finddall");
        List<UsersEntity> userList = query.getResultList();
        return userList;
    }
}
