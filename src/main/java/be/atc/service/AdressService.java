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
    private static Logger logger = Logger.getLogger( AdressService.class );
    EntityManager em = EMF.getEM();
    EntityTransaction trans = em.getTransaction();

    /**
     * add adress
     * @param adress
     */
    public void addUserAdress( AdressEntity adress ) {
        try {
            em.persist( adress );
        } catch ( Exception e ) {
            logger.log( Level.INFO, "error add UserAdress: " + e.getMessage() );
        } finally {
            em.close();
        }

    }

    /**
     * List adress by id user
     * @param idUser
     * @return
     */
    public List<Object[]> listAdressByIdUser( int idUser ) {
        Query query = em.createNamedQuery( "Adress.SelectByIdUser" );
        query.setParameter( "id", idUser );
        return query.getResultList();
    }

    public void addMultipleAdress(int idUser, AdressEntity adress, AdressUsersEntity adressUsers){
            try {
                trans.begin();
                UsersEntity user = em.find(UsersEntity.class, idUser);
//                AdressUsersEntity adressUsers = new AdressUsersEntity();

                adressUsers.setUsers(user);
                adressUsers.setAddress(adress);

                em.merge(adress);
                em.merge(adressUsers);
                trans.commit();

            } catch (Exception e){
                logger.log(Level.ERROR, "Error in method addMultipleAdress " + e.getMessage());
                trans.rollback();
            } finally {
//                em.close();
            }


    }

    public boolean verfyIfTypeAdressExist (TypeAdress typeAdress, int idUser){
        Query query = em.createNamedQuery("Adress.TypeAdressExist");
        query.setParameter("typeAdress",typeAdress);
        query.setParameter("idUser", idUser);
//        String result = (String) query.getSingleResult();
//        logger.log(Level.INFO,"adressService: " + result);
        logger.log(Level.INFO,"adressService: " + query.getResultList());
//        if(result != null ){
//            return false;
//        } else{
//            return false;
//        }
        return true;
    }
}
