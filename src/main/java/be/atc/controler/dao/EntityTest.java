package be.atc.controler.dao;

import be.atc.controler.connexion.EMF;
import be.atc.entities.UsersEnt;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EntityTest {
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger
            .getLogger(EntityTest.class);

//    public UsersEnt allUser() {
//        UserService user = new UserService();
//        UsersEnt usersEnt = user.test();
//        return usersEnt;
//    }

    public List<UsersEnt> listDesPersonnes() {
        List<UsersEnt> user = new ArrayList<>();
        try {
            EntityManager em = EMF.getEM();
            user = em
                    .createQuery("select u from UsersEnt u ")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Erreur dans l'entityTest: " + e);
        }
        return user;
    }

}
