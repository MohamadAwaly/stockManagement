package be.atc.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@NamedQueries( value = {
        @NamedQuery( name = "User.findall",
                query = "SELECT u FROM UsersEntity u" )
} )
@Entity @Table( name = "users", schema = "stock-management" )
public class UsersEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_user", nullable = false )
    private int                           idUser;
    @Basic @Column( name = "lastName", nullable = false, length = 60 )
    private String                        lastName;
    @Basic @Column( name = "firstName", nullable = false, length = 60 )
    private String                        firstName;
    @Basic @Column( name = "dayOfBirth", nullable = true )
    private Date                          dayOfBirth;
    @Basic @Column( name = "inscriptionDate", nullable = false )
    private Date                          inscriptionDate;
    @Basic @Column( name = "vat", nullable = true, length = 20 )
    private String                        vat;
    @Basic @Column( name = "mail", nullable = true, length = 255 )
    private String                        mail;
    @Basic @Column( name = "password", nullable = false, length = 255 )
    private String                        password;
    @Basic @Column( name = "login", nullable = false, length = 60 )
    private String                        login;
    private Collection<AdressUsersEntity> adress;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser( int idUser ) {
        this.idUser = idUser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth( Date dayOfBirth ) {
        this.dayOfBirth = dayOfBirth;
    }

    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate( Date inscriptionDate ) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getVat() {
        return vat;
    }

    public void setVat( String vat ) {
        this.vat = vat;
    }

    public String getMail() {
        return mail;
    }

    public void setMail( String mail ) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        UsersEntity that = (UsersEntity) o;
        return idUser == that.idUser && Objects.equals( lastName, that.lastName ) && Objects
                .equals( firstName, that.firstName ) && Objects.equals( dayOfBirth, that.dayOfBirth )
                && Objects.equals( inscriptionDate, that.inscriptionDate ) && Objects
                .equals( vat, that.vat ) && Objects.equals( mail, that.mail ) && Objects
                .equals( password, that.password ) && Objects.equals( login, that.login ) && Objects
                .equals( adress, that.adress );
    }

    @Override public int hashCode() {
        return Objects
                .hash( idUser, lastName, firstName, dayOfBirth, inscriptionDate, vat, mail, password, login, adress );
    }
    @OneToMany( mappedBy = "users" )
    public Collection<AdressUsersEntity> getAdress() {
        return adress;
    }

    public void setAdress( Collection<AdressUsersEntity> adress ) {
        this.adress = adress;
    }
}
