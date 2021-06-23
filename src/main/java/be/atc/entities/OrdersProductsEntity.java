package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "orders_products", schema = "stockmanagement" )
public class OrdersProductsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_orders_products", nullable = false )
    private int     idOrdersProducts;
    @Basic @Column( name = "qteUnits", nullable = false )
    private int     qteUnits;
    @Basic @Column( name = "unitPrice", nullable = false )
    private int     unitPrice;
    @Basic @Column( name = "deliver", nullable = true )
    private Boolean deliver;

    private OrdersEntity   orders;
    private ProductsEntity products;

    public int getIdOrdersProducts() {
        return idOrdersProducts;
    }

    public void setIdOrdersProducts( int idOrdersProducts ) {
        this.idOrdersProducts = idOrdersProducts;
    }

    public int getQteUnits() {
        return qteUnits;
    }

    public void setQteUnits( int qteUnits ) {
        this.qteUnits = qteUnits;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice( int unitPrice ) {
        this.unitPrice = unitPrice;
    }

    public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver( Boolean deliver ) {
        this.deliver = deliver;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrdersProductsEntity that = (OrdersProductsEntity) o;
        return idOrdersProducts == that.idOrdersProducts && qteUnits == that.qteUnits && unitPrice == that.unitPrice
                && Objects.equals( deliver, that.deliver ) && Objects.equals( orders, that.orders ) && Objects
                .equals( products, that.products );
    }

    @Override
    public int hashCode() {
        return Objects.hash( idOrdersProducts, qteUnits, unitPrice, deliver, orders, products );
    }

    // Relation avec la table order
    @ManyToOne @JoinColumn( name = "id_order", referencedColumnName = "id_order", nullable = false )
    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders( OrdersEntity orders ) {
        this.orders = orders;
    }

    //Relation avec la table product
    @ManyToOne @JoinColumn( name = "id_product", referencedColumnName = "id_product", nullable = false )
    public ProductsEntity getProducts() {
        return products;
    }

    public void setProducts( ProductsEntity products ) {
        this.products = products;
    }
}
