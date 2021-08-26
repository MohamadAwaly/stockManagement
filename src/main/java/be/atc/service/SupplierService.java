package be.atc.service;

import be.atc.controler.connexion.EMF;
import be.atc.entities.SuppliersEntity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SupplierService {
    private static final Logger logger = Logger.getLogger(SupplierService.class);
    private EntityManager entityManager = EMF.getEM();
    private EntityTransaction transaction = entityManager.getTransaction();

    public List<Object[]> suppliersShowAll(){
        List<Object[]> lst_suppliers = new ArrayList<>();
        Query query = entityManager.createNamedQuery("suppliers.findAll");
        lst_suppliers = query.getResultList();
        return  lst_suppliers;
    }
    public void supplierCreate(SuppliersEntity supplier){
        try{
            transaction.begin();
            entityManager.merge(supplier);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
            logger.log(Level.INFO,"supplierCreate ");
        }
    }
    public Object supplierExist(String pWhereName){
        Query query = entityManager.createNamedQuery("suppliers.supplierExist")
                .setParameter("pWhereName",pWhereName);
        Object supplier = query.getSingleResult();
        logger.log(Level.INFO, "supplier exist : "+supplier);
        return supplier;
    }
    public List<Object[]> CommandSupplierList(String pWhere){
        Query query = entityManager.createNamedQuery("commandSuppliersBatchs.Find").setParameter("pWhere","%"+pWhere+"%");
        List<Object[]> commandSupplierBatch = query.getResultList();
        return commandSupplierBatch;
    }
}
