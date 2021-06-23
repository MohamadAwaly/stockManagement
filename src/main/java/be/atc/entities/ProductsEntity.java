package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table( name = "products", schema = "stockmanagement" )
public class ProductsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_product", nullable = false )
    private int    idProduct;
    @Basic
    @Column( name = "code", nullable = false, length = 255 )
    private String code;
    @Basic
    @Column( name = "designation", nullable = false, length = 255 )
    private String designation;
    @Basic
    @Column( name = "quantityTotal", nullable = false )
    private int    quantityTotal;
    @Basic
    @Column( name = "unitCostPrice", nullable = false )
    private int    unitCostPrice;
    @Basic
    @Column( name = "margin", nullable = false )
    private int    margin;
    @Basic
    @Column( name = "length", nullable = false )
    private int    length;
    @Basic
    @Column( name = "width", nullable = false )
    private int    width;
    @Basic
    @Column( name = "height", nullable = false )
    private int    height;

    @Basic
    @Column( name = "active", nullable = false )
    private boolean active;

    @Basic
    @Column( name = "minimumQte", nullable = false )
    private int minimumQte;

    private BrandsEntity brands;

    private Collection<BatchsEntity>             batchs;
    private Collection<OrdersProductsEntity>     ordersProducts;
    private Collection<ProductsCategoriesEntity> productsCategories;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct( int idProduct ) {
        this.idProduct = idProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation( String designation ) {
        this.designation = designation;
    }

    public int getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal( int quantityTotal ) {
        this.quantityTotal = quantityTotal;
    }

    public int getUnitCostPrice() {
        return unitCostPrice;
    }

    public void setUnitCostPrice( int unitCostPrice ) {
        this.unitCostPrice = unitCostPrice;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin( int margin ) {
        this.margin = margin;
    }

    public int getLength() {
        return length;
    }

    public void setLength( int length ) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth( int width ) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

    public int getMinimumQte() {
        return minimumQte;
    }

    public void setMinimumQte( int minimumQte ) {
        this.minimumQte = minimumQte;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        ProductsEntity that = (ProductsEntity) o;
        return idProduct == that.idProduct && quantityTotal == that.quantityTotal && unitCostPrice == that.unitCostPrice
                && margin == that.margin && length == that.length && width == that.width && height == that.height
                && active == that.active && minimumQte == that.minimumQte && Objects.equals( code, that.code )
                && Objects.equals( designation, that.designation ) && Objects.equals( brands, that.brands ) && Objects
                .equals( batchs, that.batchs ) && Objects.equals( ordersProducts, that.ordersProducts ) && Objects
                .equals( productsCategories, that.productsCategories );
    }

    @Override
    public int hashCode() {
        return Objects.hash( idProduct, code, designation, quantityTotal, unitCostPrice, margin, length, width, height,
                active, minimumQte, brands, batchs, ordersProducts, productsCategories );
    }

    //Relation avec la table brand
    @ManyToOne
    @JoinColumn( name = "id_brand", referencedColumnName = "id_brand", nullable = false )
    public BrandsEntity getBrands() {
        return brands;
    }

    public void setBrands( BrandsEntity brands ) {
        this.brands = brands;
    }

    //Relation avec la table batch
    @OneToMany( mappedBy = "products" )
    public Collection<BatchsEntity> getBatchs() {
        return batchs;
    }

    public void setBatchs( Collection<BatchsEntity> batchs ) {
        this.batchs = batchs;
    }

    @OneToMany( mappedBy = "products" )
    public Collection<OrdersProductsEntity> getOrdersProducts() {
        return ordersProducts;
    }

    public void setOrdersProducts( Collection<OrdersProductsEntity> ordersProducts ) {
        this.ordersProducts = ordersProducts;
    }

    @OneToMany( mappedBy = "products" )
    public Collection<ProductsCategoriesEntity> getProductsCategories() {
        return productsCategories;
    }

    public void setProductsCategories( Collection<ProductsCategoriesEntity> productsCategories ) {
        this.productsCategories = productsCategories;
    }
}
