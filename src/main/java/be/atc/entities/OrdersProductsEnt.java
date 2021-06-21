package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table( name = "orders_products", schema = "stock-management", catalog = "" ) public class OrdersProductsEnt {
    private int         idOrdersProducts;
//    private int         idOrder;
//    private int         idProduct;
    private int         qteUnits;
    private int         unitPrice;
    private Boolean     deliver;
    private OrdersEnt   ordersByIdOrder;
    private ProductsEnt productsByIdProduct;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Orders_products", nullable = false ) public int getIdOrdersProducts() {
        return idOrdersProducts;
    }

    public void setIdOrdersProducts( int idOrdersProducts ) {
        this.idOrdersProducts = idOrdersProducts;
    }

//    @Basic @Column( name = "ID_Order", nullable = false ) public int getIdOrder() {
//        return idOrder;
//    }
//
//    public void setIdOrder( int idOrder ) {
//        this.idOrder = idOrder;
//    }
//
//    @Basic @Column( name = "ID_Product", nullable = false ) public int getIdProduct() {
//        return idProduct;
//    }
//
//    public void setIdProduct( int idProduct ) {
//        this.idProduct = idProduct;
//    }

    @Basic @Column( name = "qteUnits", nullable = false ) public int getQteUnits() {
        return qteUnits;
    }

    public void setQteUnits( int qteUnits ) {
        this.qteUnits = qteUnits;
    }

    @Basic @Column( name = "unitPrice", nullable = false ) public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice( int unitPrice ) {
        this.unitPrice = unitPrice;
    }

    @Basic @Column( name = "deliver", nullable = true ) public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver( Boolean deliver ) {
        this.deliver = deliver;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrdersProductsEnt that = (OrdersProductsEnt) o;
        return idOrdersProducts == that.idOrdersProducts && qteUnits == that.qteUnits && unitPrice == that.unitPrice
                && Objects.equals( deliver, that.deliver ) && Objects
                .equals( ordersByIdOrder, that.ordersByIdOrder ) && Objects
                .equals( productsByIdProduct, that.productsByIdProduct );
    }

    @Override public int hashCode() {
        return Objects.hash( idOrdersProducts, qteUnits, unitPrice, deliver, ordersByIdOrder, productsByIdProduct );
    }

    @ManyToOne @JoinColumn( name = "ID_Order", referencedColumnName = "ID_Order", nullable = false ) public OrdersEnt getOrdersByIdOrder() {
        return ordersByIdOrder;
    }

    public void setOrdersByIdOrder( OrdersEnt ordersByIdOrder ) {
        this.ordersByIdOrder = ordersByIdOrder;
    }

    @ManyToOne @JoinColumn( name = "ID_Product", referencedColumnName = "ID_Product", nullable = false ) public ProductsEnt getProductsByIdProduct() {
        return productsByIdProduct;
    }

    public void setProductsByIdProduct( ProductsEnt productsByIdProduct ) {
        this.productsByIdProduct = productsByIdProduct;
    }
}
