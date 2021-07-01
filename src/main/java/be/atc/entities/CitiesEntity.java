package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries(value = {
        @NamedQuery(name = "city.listAll",
                query = "SELECT c FROM CitiesEntity c order by c.postalCode ASC")
})

@Entity
@Table( name = "cities", schema = "stockmanagement" )
public class CitiesEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_city", nullable = false )
    private int idCity;

    @Basic
    @Column( name = "citie", nullable = false, length = 60 )
    private String citie;

    @Basic
    @Column( name = "postalCode", nullable = false )
    private int postalCode;

    @ManyToOne
    @JoinColumn( name = "id_country", referencedColumnName = "id_country", nullable = false )
    private CountriesEntity country;

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity( int idCity ) {
        this.idCity = idCity;
    }

    public String getCitie() {
        return citie;
    }

    public void setCitie( String citie ) {
        this.citie = citie;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode( int postalCode ) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        CitiesEntity that = (CitiesEntity) o;
        return idCity == that.idCity && postalCode == that.postalCode && Objects.equals( citie, that.citie )
                && Objects.equals( country, that.country );
    }

    @Override
    public int hashCode() {
        return Objects.hash( idCity, citie, postalCode, country );
    }


    public CountriesEntity getCountry() {
        return country;
    }

    public void setCountry( CountriesEntity countriesByIdCountry ) {
        this.country = countriesByIdCountry;
    }

}
