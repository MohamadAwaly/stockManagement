package be.atc.controler.dao;

import be.atc.entities.CountriesEntity;
import be.atc.entities.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

//[sourcecode language="java" highlight="4"]
//        persistence unit name ("stock-lanagement") :
//        [sourcecode language="java" highlight="4"]
class Main {

    public static void main(String[] args) {
//        CountriesEntity country = new CountriesEntity();
//        country.setCountry( "test" );
//        System.out.println("test1");
//        //        Persistence.generateSchema("stock-management", null);
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "stock-management" );
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist( country );
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
//        System.out.println("test");
        Map<String, String> configurationOverrides = new HashMap<String, String>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stock-management", configurationOverrides);
        EntityManager entityManager = emf.createEntityManager();
    }
}
//[/sourcecode]