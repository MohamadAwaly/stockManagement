package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "categories", schema = "sto-ckmanagement", catalog = "" ) public class CategoriesEnt {
    private int                               idCategorie;
    private String                            category;
    private Collection<ProductsCategoriesEnt> productsCategoriesByIdCategorie;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Categorie", nullable = false ) public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie( int idCategorie ) {
        this.idCategorie = idCategorie;
    }

    @Basic @Column( name = "category", nullable = false, length = 60 ) public String getCategory() {
        return category;
    }

    public void setCategory( String category ) {
        this.category = category;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        CategoriesEnt that = (CategoriesEnt) o;
        return idCategorie == that.idCategorie && Objects.equals( category, that.category );
    }

    @Override public int hashCode() {
        return Objects.hash( idCategorie, category );
    }

    @OneToMany( mappedBy = "categoriesByIdCategory" ) public Collection<ProductsCategoriesEnt> getProductsCategoriesByIdCategorie() {
        return productsCategoriesByIdCategorie;
    }

    public void setProductsCategoriesByIdCategorie(
            Collection<ProductsCategoriesEnt> productsCategoriesByIdCategorie ) {
        this.productsCategoriesByIdCategorie = productsCategoriesByIdCategorie;
    }
}
