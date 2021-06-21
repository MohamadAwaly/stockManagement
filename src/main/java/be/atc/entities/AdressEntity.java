package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "adresse", schema = "stock-management" )
public class AdressEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_adress", nullable = false )
    private int          idAdress;
    @Basic @Column( name = "street", nullable = false, length = 255 )
    private String       street;
    @Basic @Column( name = "number", nullable = false )
    private int          number;
    @Basic @Column( name = "box", nullable = false )
    private int          box;
    private CitiesEntity city;
    private Collection<AdressUsersEntity> adressUsers;

    public int getIdAdress() {
        return idAdress;
    }

    public void setIdAdress( int idAdress ) {
        this.idAdress = idAdress;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet( String street ) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber( int number ) {
        this.number = number;
    }

    public int getBox() {
        return box;
    }

    public void setBox( int box ) {
        this.box = box;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        AdressEntity that = (AdressEntity) o;
        return idAdress == that.idAdress && number == that.number && box == that.box && Objects
                .equals( street, that.street ) && Objects.equals( city, that.city );
    }

    @Override public int hashCode() {
        return Objects.hash( idAdress, street, number, box, city );
    }

    @ManyToOne @JoinColumn( name = "id_city", referencedColumnName = "id_city", nullable = false )
    public CitiesEntity getCity() {
        return city;
    }

    public void setCity( CitiesEntity city ) {
        this.city = city;
    }
    @OneToMany( mappedBy = "adressUsers" )
    public Collection<AdressUsersEntity> getAdressUsers() {
        return adressUsers;
    }

    public void setAdressUsers( Collection<AdressUsersEntity> adressUsers ) {
        this.adressUsers = adressUsers;
    }
}

