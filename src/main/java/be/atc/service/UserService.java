package be.atc.service;

import be.atc.controler.connexion.EMF;
import be.atc.entities.AdressEntity;
import be.atc.entities.AdressUsersEntity;
import be.atc.entities.UsersEntity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final Logger logger = Logger.getLogger( UserService.class );
    EntityManager     em    = EMF.getEM();
    EntityTransaction trans = em.getTransaction();

    /**
     * add new user
     *
     * @param user
     * @param adressEntity
     * @param adressUser
     * @return
     */
    public boolean addUser( UsersEntity user, AdressEntity adressEntity, AdressUsersEntity adressUser ) {
        try {
            UserService usercheck = new UserService();
            boolean chekvatisempty = true;
            if ( user.getVat() == null ) {
                chekvatisempty = false;
            } else {
                chekvatisempty = usercheck.checkVatExist( user.getVat() );
            }
            if ( usercheck.checkUserExist( user.getLogin() ) || chekvatisempty ) {
                logger.log( Level.INFO, "user/vat already exists, Cannot add" );
                return false;
            } else {
                trans.begin();
                em.merge( user );
                em.merge( adressEntity );
                em.merge( adressUser );
                trans.commit();
                logger.log( Level.INFO, "User added" );
                return true;
            }
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Error in method add user" );
            trans.rollback();
            return false;
        } finally {
            //             em.close();
        }
    }

    /**
     * Update user
     *
     * @param user
     * @param adressEntity
     * @param adressUser
     */
    public void updateUser( UsersEntity user, AdressEntity adressEntity, AdressUsersEntity adressUser ) {
        try {
            trans.begin();
            UsersEntity userUpdate = em.find( UsersEntity.class, user.getIdUser() );
            userUpdate.setLastName( user.getLastName() );
            userUpdate.setFirstName( user.getFirstName() );
            userUpdate.setDayOfBirth( user.getDayOfBirth() );
            userUpdate.setMail( user.getMail() );
            userUpdate.setActive( user.isActive() );
            userUpdate.setRoles( user.getRoles() );
            userUpdate.setVat( user.getVat() );
            /**
             *             check if the password has been changed
             */
            if ( !userUpdate.getPassword().equals( user.getPassword() ) ) {
                String password = user.getPassword();
                String passwordHached = BCrypt.hashpw( password, BCrypt.gensalt() );
                userUpdate.setPassword( passwordHached );
                logger.log( Level.INFO, "Password modified " );
            } else {
                logger.log( Level.INFO, " Password not modified" );
            }
            em.merge( userUpdate );
            //            em.merge( adressEntity );
            //            em.merge( adressUser );
            trans.commit();
            logger.log( Level.INFO, "User updated" );
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Error in method update user" );
            trans.rollback();
        } finally {
            //             em.close();
        }
    }

    /**
     * List of users with adress and roles
     *
     * @return
     */
    public List<Object[]> showAllUsers() {
        List<Object[]> user = new ArrayList<>();
        Query query = em.createNamedQuery( "User.finddall" );
        user = query.getResultList();
        em.clear();
        //        em.close();
        return query.getResultList();
    }

    /**
     * List of id and username
     *
     * @return List<Objet>
     */
    public List<Object[]> showAllUsersIdAndName() {
        List<Object[]> user = new ArrayList<>();
        Query query = em.createNamedQuery( "User.findAllIdAndName" );
        user = query.getResultList();
        return query.getResultList();
    }

    /**
     * check if login & vat exist
     *
     * @param login
     * @return
     */
    public boolean checkUserExist( String login ) {
        Query query = em.createNamedQuery( "User.checkUserExist" );
        query.setParameter( "login", login );
        String user = "";
        try {
            user = (String) query.getSingleResult();
            if ( user.equals( login ) ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            return false;
        } finally {
            //            em.close();
        }

    }

    /**
     * check if vat exist
     *
     * @param vat
     * @return
     */
    public boolean checkVatExist( String vat ) {
        Query query = em.createNamedQuery( "User.checkVatExist" );
        query.setParameter( "vat", vat );
        String userVat = "";
        try {
            userVat = (String) query.getSingleResult();
            if ( !vat.isEmpty() ) {
                if ( userVat.equals( vat ) ) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Num??ro de tva existe: " + e.getMessage() );
            return false;
        } finally {
            //            em.close();
        }
    }

    /**
     * Select user by id
     *
     * @param id
     * @return
     */
    public List<Object[]> selectUserById( int id ) {
        Query query = em.createNamedQuery( "User.SelectById" );
        query.setParameter( "id", id );
        return query.getResultList();
    }

    /**
     * Check login and password
     *
     * @param login
     * @return
     */
    public UsersEntity checkLogin( String login ) {
        Query query = em.createNamedQuery( "User.CheckLogin", UsersEntity.class );
        query.setParameter( "login", login );
        int id = 0;
        try {
            id = (int) query.getSingleResult();
            return em.find( UsersEntity.class, id );
        } catch ( Exception e ) {
            return null;
        }
    }

    /**
     * Profile user
     *
     * @param login
     * @return
     */
    public List<UsersEntity> profile( String login ) {
        Query query = em.createNamedQuery( "User.profile" );
        query.setParameter( "login", login );
        return query.getResultList();
    }

    /**
     * Search user bar
     *
     * @param search
     * @return
     */
    public List<Object[]> searchUser( String search ) {
        Query query = em.createNamedQuery( "User.finddallBySerach" );
        query.setParameter( "search", "%" + search + "%" );
        em.clear();
        //        em.close();
        return query.getResultList();
    }

}