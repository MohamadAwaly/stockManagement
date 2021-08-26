package be.atc.service;

import be.atc.controler.connexion.EMF;
import be.atc.controler.enumm.TypeAdress;
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

public class AdressService {
    private static Logger logger = Logger.getLogger(AdressService.class);
    EntityManager em = EMF.getEM();
    EntityTransaction trans = em.getTransaction();

    /**
     * add adress
     *
     * @param adress
     */
    public void addUserAdress(AdressEntity adress) {
        try {
            em.persist(adress);
        } catch (Exception e) {
            logger.log(Level.INFO, "error add UserAdress: " + e.getMessage());
        } finally {
//            em.close();
        }

    }

    /**
     * List adress by id user
     *
     * @param idUser
     * @return
     */
    public List<Object[]> listAdressByIdUser(int idUser) {
        Query query = em.createNamedQuery("Adress.SelectByIdUser");
        query.setParameter("id", idUser);
        return query.getResultList();
    }

    /**
     * add different adress by type for user
     *
     * @param idUser
     * @param adress
     * @param adressUsers
     */
    public void addMultipleAdress(int idUser, AdressEntity adress, AdressUsersEntity adressUsers) {
        try {
            trans.begin();
            UsersEntity user = em.find(UsersEntity.class, idUser);
//                AdressUsersEntity adressUsers = new AdressUsersEntity();

            adressUsers.setUsers(user);
            adressUsers.setAddress(adress);

            em.merge(adress);
            em.merge(adressUsers);
            trans.commit();

        } catch (Exception e) {
            logger.log(Level.ERROR, "Error in method addMultipleAdress " + e.getMessage());
            trans.rollback();
        } finally {
//            em.close();
        }


    }

    /**
     * Very if user has already the type of adress
     *
     * @param typeAdress
     * @param idUser
     * @return
     */
    public boolean verfyIfTypeAdressExist(TypeAdress typeAdress, int idUser) {
        Query query = em.createNamedQuery("Adress.TypeAdressExist");
        query.setParameter("typeAdress", typeAdress);
        query.setParameter("idUser", idUser);
        List<Object> result = new ArrayList<Object>();
        try {
            result = (List<Object>) query.getResultList();
            if (result.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "no result so adress can be added " + e.getMessage());
            return true;
        } finally {
//            em.close();
        }
    }

    public List<Object[]> adressbyIdAdress(int idAdress) {
        Query query = em.createNamedQuery("Adress.SelectAdressById");
        query.setParameter("id", idAdress);
        em.clear();
        return query.getResultList();
    }

    public void updateadress(AdressEntity adress, AdressUsersEntity adressUsers) {
        try {
            trans.begin();
            Query query = em.createNamedQuery("Adress.GetIdAdress");
            query.setParameter("idAdress", adress.getIdAdress());
            Object idAdressUsers = query.getSingleResult();

            AdressEntity adressUpdate = em.find(AdressEntity.class, adress.getIdAdress());
            AdressUsersEntity adressUsersUpdate = em.find(AdressUsersEntity.class, idAdressUsers);

            adressUpdate.setStreet(adress.getStreet());
            adressUpdate.setNumber(adress.getNumber());
            adressUpdate.setBox(adress.getBox());
            adressUpdate.setCity(adress.getCity());
            adressUsersUpdate.setTypeAdress(adressUsers.getTypeAdress());
            trans.commit();

        } catch (Exception e) {
            logger.log(Level.ERROR, "Error - update adress user");
            trans.rollback();
        } finally {
//            em.close();
        }


    }

}
