package be.atc.service;

import be.atc.controler.connexion.EMF;
import be.atc.entities.CitiesEntity;
import be.atc.entities.UsersEntity;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CitieService {
    private static Logger log = Logger.getLogger(CitieService.class);
    EntityManager em = EMF.getEM();

    public List showAllCities() {
        Query query = em.createNamedQuery("city.listAll");
        List<CitiesEntity> citiesList = query.getResultList();
        return citiesList;
    }
}
