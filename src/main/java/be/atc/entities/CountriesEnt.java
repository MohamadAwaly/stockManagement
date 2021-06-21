package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "countries", schema = "stock-management", catalog = "" ) public class CountriesEnt {
    private int                   idCountry;
    private String                country;
    private Collection<CitiesEnt> citiesByIdCountry;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Country", nullable = false ) public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry( int idCountry ) {
        this.idCountry = idCountry;
    }

    @Basic @Column( name = "Country", nullable = false, length = 60 ) public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        CountriesEnt that = (CountriesEnt) o;
        return idCountry == that.idCountry && Objects.equals( country, that.country );
    }

    @Override public int hashCode() {
        return Objects.hash( idCountry, country );
    }

    @OneToMany( mappedBy = "countriesByIdCountry" ) public Collection<CitiesEnt> getCitiesByIdCountry() {
        return citiesByIdCountry;
    }

    public void setCitiesByIdCountry( Collection<CitiesEnt> citiesByIdCountry ) {
        this.citiesByIdCountry = citiesByIdCountry;
    }
}
