package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "brands", schema = "stock-management", catalog = "" ) public class BrandsEnt {
    private int                     idBrand;
//    private String                  brand;
    private Collection<ProductsEnt> productsByIdBrand;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Brand", nullable = false ) public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand( int idBrand ) {
        this.idBrand = idBrand;
    }

//    @Basic @Column( name = "brand", nullable = false, length = 60 ) public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand( String brand ) {
//        this.brand = brand;
//    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        BrandsEnt brandsEnt = (BrandsEnt) o;
        return idBrand == brandsEnt.idBrand && Objects.equals( productsByIdBrand, brandsEnt.productsByIdBrand );
    }

    @Override public int hashCode() {
        return Objects.hash( idBrand, productsByIdBrand );
    }

    @OneToMany( mappedBy = "brandsByIdBrand" ) public Collection<ProductsEnt> getProductsByIdBrand() {
        return productsByIdBrand;
    }

    public void setProductsByIdBrand( Collection<ProductsEnt> productsByIdBrand ) {
        this.productsByIdBrand = productsByIdBrand;
    }
}
