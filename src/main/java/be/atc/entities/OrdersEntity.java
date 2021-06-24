package be.atc.entities;

import be.atc.controler.enumm.ModeOfPayement;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table( name = "orders", schema = "stockmanagement" )
public class OrdersEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID_Order", nullable = false )
    private int            idOrder;
    @Basic
    @Column( name = "reduction", nullable = true )
    private int            reduction;
    @Basic
    @Column( name = "dateORder", nullable = false )
    private Date           dateORder;
    @Basic
    @Column( name = "payed", nullable = true )
    private Boolean        payed;
    @Basic
    @Column( name = "payementDate", nullable = true )
    private Date           payementDate;
    @Basic
    @Column( name = "deliver", nullable = true )
    private Boolean        deliver;
    @Basic
    @Column( name = "deliverDate", nullable = true )
    private Date           deliverDate;
    @Basic
    @Column( name = "modeOfPayement", nullable = true )
    @Enumerated( EnumType.STRING )
    private ModeOfPayement modeOfPayement;

    @ManyToOne
    @JoinColumn( name = "id_user", referencedColumnName = "id_user", nullable = false )
    private UsersEntity                       users;
    private Collection<OrdersDocumentsEntity> ordersDocuments;
    private Collection<OrdersProductsEntity>  ordersProducts;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder( int idOrder ) {
        this.idOrder = idOrder;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction( int reduction ) {
        this.reduction = reduction;
    }

    public Date getDateORder() {
        return dateORder;
    }

    public void setDateORder( Date dateORder ) {
        this.dateORder = dateORder;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed( Boolean payed ) {
        this.payed = payed;
    }

    public Date getPayementDate() {
        return payementDate;
    }

    public void setPayementDate( Date payementDate ) {
        this.payementDate = payementDate;
    }

    public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver( Boolean deliver ) {
        this.deliver = deliver;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate( Date deliverDate ) {
        this.deliverDate = deliverDate;
    }

    public ModeOfPayement getModeOfPayement() {
        return modeOfPayement;
    }

    public void setModeOfPayement( ModeOfPayement modeOfPayement ) {
        this.modeOfPayement = modeOfPayement;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrdersEntity that = (OrdersEntity) o;
        return idOrder == that.idOrder && reduction == that.reduction && Objects.equals( dateORder, that.dateORder )
                && Objects.equals( payed, that.payed ) && Objects.equals( payementDate, that.payementDate ) && Objects
                .equals( deliver, that.deliver ) && Objects.equals( deliverDate, that.deliverDate )
                && modeOfPayement == that.modeOfPayement && Objects.equals( users, that.users ) && Objects
                .equals( ordersDocuments, that.ordersDocuments ) && Objects
                .equals( ordersProducts, that.ordersProducts );
    }

    @Override
    public int hashCode() {
        return Objects
                .hash( idOrder, reduction, dateORder, payed, payementDate, deliver, deliverDate, modeOfPayement, users,
                        ordersDocuments, ordersProducts );
    }

    //Relation avec la table Uers

    public UsersEntity getUsers() {
        return users;
    }

    public void setUsers( UsersEntity users ) {
        this.users = users;
    }

    //Relation avec la table Orderdocument
    @OneToMany( mappedBy = "orders" )
    public Collection<OrdersDocumentsEntity> getOrdersDocuments() {
        return ordersDocuments;
    }

    public void setOrdersDocuments( Collection<OrdersDocumentsEntity> ordersDocuments ) {
        this.ordersDocuments = ordersDocuments;
    }

    //Relation avec la table OrderProducts
    @OneToMany( mappedBy = "orders" )
    public Collection<OrdersProductsEntity> getOrdersProducts() {
        return ordersProducts;
    }

    public void setOrdersProducts( Collection<OrdersProductsEntity> ordersProducts ) {
        this.ordersProducts = ordersProducts;
    }
}
