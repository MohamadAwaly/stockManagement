package be.atc.service;

import be.atc.controler.connexion.EMF;
import be.atc.entities.AdressEntity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

public class AdressService {
    private static Logger logger = Logger.getLogger(AdressService.class);
    EntityManager em = EMF.getEM();
    public void addUserAdress (AdressEntity adress ){
        try{
            em.persist(adress);
        } catch (Exception e){
            logger.log(Level.INFO,"error add UserAdress: " + e.getMessage());
        } finally {
            em.close();
        }

    }
}
