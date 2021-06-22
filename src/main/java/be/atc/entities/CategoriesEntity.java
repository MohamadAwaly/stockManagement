package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "stockmanagement")
public class CategoriesEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Categorie", nullable = false)
    private int idCategorie;
    @Basic
    @Column(name = "category", nullable = false, length = 60)
    private String category;

    private Collection<ProductsCategoriesEntity> productsCategories;

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesEntity that = (CategoriesEntity) o;
        return idCategorie == that.idCategorie && Objects.equals(category, that.category) && Objects.equals(productsCategories, that.productsCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategorie, category, productsCategories);
    }

    //Relation avec la table productsCategories
    @OneToMany(mappedBy = "categories")
    public Collection<ProductsCategoriesEntity> getProductsCategories() {
        return productsCategories;
    }

    public void setProductsCategories(Collection<ProductsCategoriesEntity> productsCategories) {
        this.productsCategories = productsCategories;
    }
}
