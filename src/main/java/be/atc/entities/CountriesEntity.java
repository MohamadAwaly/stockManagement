package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table( name = "countries", schema = "stock-management")
public class CountriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_country", nullable = false )
    private int                   idCountry;
    @Basic @Column( name = "country", nullable = false, length = 60 )
    private String                country;

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry( int idCountry ) {
        this.idCountry = idCountry;
    }

    public String getCountry() {
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
        CountriesEntity that = (CountriesEntity) o;
        return idCountry == that.idCountry && Objects.equals( country, that.country );
    }

    @Override public int hashCode() {
        return Objects.hash( idCountry, country );
    }

}
