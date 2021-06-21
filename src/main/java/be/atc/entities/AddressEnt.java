package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "address", schema = "stock-management", catalog = "" )
public class AddressEnt {
    private int                        idAdress;
//    private int                        idCity;
    private String                     street;
    private int                        number;
    private int                        box;
    private CitiesEnt                  citiesByIdCity;
    private Collection<AdressUsersEnt> adressUsersByIdAdress;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID_Adress", nullable = false ) public int getIdAdress() {
        return idAdress;
    }

    public void setIdAdress( int idAdress ) {
        this.idAdress = idAdress;
    }

//    @Basic @Column( name = "ID_City", nullable = false ) public int getIdCity() {
//        return idCity;
//    }
//
//    public void setIdCity( int idCity ) {
//        this.idCity = idCity;
//    }

    @Basic @Column( name = "street", nullable = false, length = 255 ) public String getStreet() {
        return street;
    }

    public void setStreet( String street ) {
        this.street = street;
    }

    @Basic @Column( name = "number", nullable = false ) public int getNumber() {
        return number;
    }

    public void setNumber( int number ) {
        this.number = number;
    }

    @Basic @Column( name = "box", nullable = true ) public int getBox() {
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
        AddressEnt that = (AddressEnt) o;
        return idAdress == that.idAdress && number == that.number && box == that.box && Objects
                .equals( street, that.street ) && Objects.equals( citiesByIdCity, that.citiesByIdCity )
                && Objects.equals( adressUsersByIdAdress, that.adressUsersByIdAdress );
    }

    @Override public int hashCode() {
        return Objects.hash( idAdress, street, number, box, citiesByIdCity, adressUsersByIdAdress );
    }

    @ManyToOne @JoinColumn( name = "ID_City", referencedColumnName = "ID_City", nullable = false ) public CitiesEnt getCitiesByIdCity() {
        return citiesByIdCity;
    }

    public void setCitiesByIdCity( CitiesEnt citiesByIdCity ) {
        this.citiesByIdCity = citiesByIdCity;
    }

    @OneToMany( mappedBy = "addressByIdAdress" ) public Collection<AdressUsersEnt> getAdressUsersByIdAdress() {
        return adressUsersByIdAdress;
    }

    public void setAdressUsersByIdAdress( Collection<AdressUsersEnt> adressUsersByIdAdress ) {
        this.adressUsersByIdAdress = adressUsersByIdAdress;
    }
}
