package be.atc.controler.dao;

import javax.persistence.*;
public class SchemaCreator {
    public static void main(String[] args)
    {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("bitcoin");
        EntityManager em = factory.createEntityManager();

//        String ddl = /// <--- How do I get it ?

//                em.createNativeQuery(ddl);
    }
}
