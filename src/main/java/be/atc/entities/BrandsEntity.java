package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

public class BrandsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_brand", nullable = false)
    private int idBrand;
    @Basic
    @Column(name = "brand", nullable = false, length = 60)
    private String brand;

    private Collection<ProductsEntity> products;

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandsEntity that = (BrandsEntity) o;
        return idBrand == that.idBrand && Objects.equals(brand, that.brand) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBrand, brand, products);
    }

    //Relation avec la table products
    @OneToMany( mappedBy = "brands" )
    public Collection<ProductsEntity> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductsEntity> products) {
        this.products = products;
    }
}
