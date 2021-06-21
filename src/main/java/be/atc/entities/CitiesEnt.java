package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "cities", schema = "stock-management", catalog = "" ) public class CitiesEnt {
    private int                    idCity;
//    private int                    idCountry;
    private String                 citie;
    private int                    postalCode;
    private Collection<AddressEnt> addressesByIdCity;
    private CountriesEnt           countriesByIdCountry;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_City", nullable = false ) public int getIdCity() {
        return idCity;
    }

    public void setIdCity( int idCity ) {
        this.idCity = idCity;
    }

//    @Basic @Column( name = "ID_Country", nullable = false ) public int getIdCountry() {
//        return idCountry;
//    }
//
//    public void setIdCountry( int idCountry ) {
//        this.idCountry = idCountry;
//    }

    @Basic @Column( name = "citie", nullable = false, length = 60 ) public String getCitie() {
        return citie;
    }

    public void setCitie( String citie ) {
        this.citie = citie;
    }

    @Basic @Column( name = "postalCode", nullable = false ) public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode( int postalCode ) {
        this.postalCode = postalCode;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        CitiesEnt citiesEnt = (CitiesEnt) o;
        return idCity == citiesEnt.idCity && postalCode == citiesEnt.postalCode && Objects
                .equals( citie, citiesEnt.citie ) && Objects
                .equals( addressesByIdCity, citiesEnt.addressesByIdCity ) && Objects
                .equals( countriesByIdCountry, citiesEnt.countriesByIdCountry );
    }

    @Override public int hashCode() {
        return Objects.hash( idCity, citie, postalCode, addressesByIdCity, countriesByIdCountry );
    }

    @OneToMany( mappedBy = "citiesByIdCity" ) public Collection<AddressEnt> getAddressesByIdCity() {
        return addressesByIdCity;
    }

    public void setAddressesByIdCity( Collection<AddressEnt> addressesByIdCity ) {
        this.addressesByIdCity = addressesByIdCity;
    }

    @ManyToOne @JoinColumn( name = "ID_Country", referencedColumnName = "ID_Country", nullable = false ) public CountriesEnt getCountriesByIdCountry() {
        return countriesByIdCountry;
    }

    public void setCountriesByIdCountry( CountriesEnt countriesByIdCountry ) {
        this.countriesByIdCountry = countriesByIdCountry;
    }
}
