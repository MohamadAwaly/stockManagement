package be.atc.entities;

import be.atc.controler.enumm.ModeOfPayement;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table( name = "orders", schema = "stock-management", catalog = "" )
public class OrdersEnt {
    private int                            idOrder;
//    private int                            idUser;
    private int                            reduction;
    private Date                           dateORder;
    private Boolean                        payed;
    private Date                           payementDate;
    private Boolean                        deliver;
    private Date                           deliverDate;

    private ModeOfPayement                 modeOfPayement;
    private UsersEnt                       usersByIdUser;
    private Collection<OrdersDocumentsEnt> ordersDocumentsByIdOrder;
    private Collection<OrdersProductsEnt>  ordersProductsByIdOrder;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Order", nullable = false )
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder( int idOrder ) {
        this.idOrder = idOrder;
    }

//    @Basic
//    @Column( name = "Id_User", nullable = false )
//    public int getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser( int idUser ) {
//        this.idUser = idUser;
//    }

    @Basic
    @Column( name = "reduction", nullable = true )
    public int getReduction() {
        return reduction;
    }

    public void setReduction( int reduction ) {
        this.reduction = reduction;
    }

    @Basic
    @Column( name = "dateORder", nullable = false )
    public Date getDateORder() {
        return dateORder;
    }

    public void setDateORder( Date dateORder ) {
        this.dateORder = dateORder;
    }

    @Basic
    @Column( name = "payed", nullable = true )
    public Boolean getPayed() {
        return payed;
    }

    public void setPayed( Boolean payed ) {
        this.payed = payed;
    }

    @Basic
    @Column( name = "payementDate", nullable = true )

    public Date getPayementDate() {
        return payementDate;
    }

    public void setPayementDate( Date payementDate ) {
        this.payementDate = payementDate;
    }

    @Basic
    @Column( name = "deliver", nullable = true )
    public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver( Boolean deliver ) {
        this.deliver = deliver;
    }

    @Basic
    @Column( name = "deliverDate", nullable = true )
    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate( Date deliverDate ) {
        this.deliverDate = deliverDate;
    }

    @Basic
    @Column( name = "modeOfPayement", nullable = true )
    @Enumerated( EnumType.STRING )
    public ModeOfPayement getModeOfPayement() {
        return modeOfPayement;
    }

    public void setModeOfPayement( ModeOfPayement modeOfPayement ) {
        this.modeOfPayement = modeOfPayement;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrdersEnt ordersEnt = (OrdersEnt) o;
        return idOrder == ordersEnt.idOrder && reduction == ordersEnt.reduction && Objects
                .equals( dateORder, ordersEnt.dateORder ) && Objects.equals( payed, ordersEnt.payed )
                && Objects.equals( payementDate, ordersEnt.payementDate ) && Objects
                .equals( deliver, ordersEnt.deliver ) && Objects.equals( deliverDate, ordersEnt.deliverDate )
                && modeOfPayement == ordersEnt.modeOfPayement && Objects
                .equals( usersByIdUser, ordersEnt.usersByIdUser ) && Objects
                .equals( ordersDocumentsByIdOrder, ordersEnt.ordersDocumentsByIdOrder ) && Objects
                .equals( ordersProductsByIdOrder, ordersEnt.ordersProductsByIdOrder );
    }

    @Override public int hashCode() {
        return Objects.hash( idOrder, reduction, dateORder, payed, payementDate, deliver, deliverDate, modeOfPayement,
                usersByIdUser, ordersDocumentsByIdOrder, ordersProductsByIdOrder );
    }

    @ManyToOne
    @JoinColumn( name = "Id_User", referencedColumnName = "ID_User", nullable = false )
    public UsersEnt getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser( UsersEnt usersByIdUser ) {
        this.usersByIdUser = usersByIdUser;
    }

    @OneToMany( mappedBy = "ordersByIdOrder" )
    public Collection<OrdersDocumentsEnt> getOrdersDocumentsByIdOrder() {
        return ordersDocumentsByIdOrder;
    }

    public void setOrdersDocumentsByIdOrder( Collection<OrdersDocumentsEnt> ordersDocumentsByIdOrder ) {
        this.ordersDocumentsByIdOrder = ordersDocumentsByIdOrder;
    }

    @OneToMany( mappedBy = "ordersByIdOrder" )
    public Collection<OrdersProductsEnt> getOrdersProductsByIdOrder() {
        return ordersProductsByIdOrder;
    }

    public void setOrdersProductsByIdOrder( Collection<OrdersProductsEnt> ordersProductsByIdOrder ) {
        this.ordersProductsByIdOrder = ordersProductsByIdOrder;
    }
}
