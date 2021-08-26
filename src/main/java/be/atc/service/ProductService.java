package be.atc.service;


import be.atc.controler.connexion.EMF;
import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ProductService {
    private static final Logger logger = Logger.getLogger(ProductService.class);
    private EntityManager entityManager = EMF.getEM();
    private EntityTransaction transaction = entityManager.getTransaction();

    public List<Object[]> ShowIdAndNameProduct(){
       Query query = entityManager.createNamedQuery("product.AllIdAndName");
       return query.getResultList();
    }
}
