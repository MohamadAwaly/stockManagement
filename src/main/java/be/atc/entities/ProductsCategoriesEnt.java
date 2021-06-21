package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table( name = "products_categories", schema = "stock-management", catalog = "" ) public class ProductsCategoriesEnt {
    private int           idProductsCategories;
//    private int           idProduct;
//    private int           idCategory;
    private ProductsEnt   productsByIdProduct;
    private CategoriesEnt categoriesByIdCategory;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Products_categories", nullable = false ) public int getIdProductsCategories() {
        return idProductsCategories;
    }

    public void setIdProductsCategories( int idProductsCategories ) {
        this.idProductsCategories = idProductsCategories;
    }

//    @Basic @Column( name = "ID_Product", nullable = false ) public int getIdProduct() {
//        return idProduct;
//    }
//
//    public void setIdProduct( int idProduct ) {
//        this.idProduct = idProduct;
//    }
//
//    @Basic @Column( name = "ID_Category", nullable = false ) public int getIdCategory() {
//        return idCategory;
//    }
//
//    public void setIdCategory( int idCategory ) {
//        this.idCategory = idCategory;
//    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        ProductsCategoriesEnt that = (ProductsCategoriesEnt) o;
        return idProductsCategories == that.idProductsCategories && Objects
                .equals( productsByIdProduct, that.productsByIdProduct ) && Objects
                .equals( categoriesByIdCategory, that.categoriesByIdCategory );
    }

    @Override public int hashCode() {
        return Objects.hash( idProductsCategories, productsByIdProduct, categoriesByIdCategory );
    }

    @ManyToOne @JoinColumn( name = "ID_Product", referencedColumnName = "ID_Product", nullable = false ) public ProductsEnt getProductsByIdProduct() {
        return productsByIdProduct;
    }

    public void setProductsByIdProduct( ProductsEnt productsByIdProduct ) {
        this.productsByIdProduct = productsByIdProduct;
    }

    @ManyToOne @JoinColumn( name = "ID_Category", referencedColumnName = "ID_Categorie", nullable = false ) public CategoriesEnt getCategoriesByIdCategory() {
        return categoriesByIdCategory;
    }

    public void setCategoriesByIdCategory( CategoriesEnt categoriesByIdCategory ) {
        this.categoriesByIdCategory = categoriesByIdCategory;
    }
}
