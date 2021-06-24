package be.atc.controler.dao;

import be.atc.controler.connexion.EMF;
import be.atc.entities.UsersEntity;
import be.atc.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntityTest {
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger
            .getLogger(EntityTest.class);
//    EntityManager em = EMF.getEM();
//
//    public UsersEntity allUser() {
//        Query query = em.createNamedQuery("User.findall");
//        List<UsersEntity> userList = query.getResultList();
//        return userList;
//    }

//    public List<UsersEntity> listDesPersonnes() {
//        List<UsersEntity> user = new ArrayList<>();
//        try {
//            EntityManager em = EMF.getEM();
//            user = em
//                    .createQuery("select u from UsersEntity u ")
//                    .getResultList();
//        } catch (Exception e) {
//            System.out.println("Erreur dans l'entityTest: " + e);
//        }
//        return user;
//    }

}
