package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products_categories", schema = "stockmanagement")
public class ProductsCategoriesEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_products_categories", nullable = false)
    private int idProductsCategories;

    private ProductsEntity products;
    private CategoriesEntity categories;

    public int getIdProductsCategories() {
        return idProductsCategories;
    }

    public void setIdProductsCategories(int idProductsCategories) {
        this.idProductsCategories = idProductsCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsCategoriesEntity that = (ProductsCategoriesEntity) o;
        return idProductsCategories == that.idProductsCategories && Objects.equals(products, that.products) && Objects.equals(categories, that.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductsCategories, products, categories);
    }

    //Realtion avec la table products
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", nullable = false)
    public ProductsEntity getProducts() {
        return products;
    }

    public void setProducts(ProductsEntity products) {
        this.products = products;
    }

    //Relation avec la table categorie
    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_categorie", nullable = false)
    public CategoriesEntity getCategories() {
        return categories;
    }

    public void setCategories(CategoriesEntity categories) {
        this.categories = categories;
    }
}
