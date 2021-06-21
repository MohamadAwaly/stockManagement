package be.atc.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "commandsuppliers", schema = "stock-management", catalog = "" ) public class CommandsuppliersEnt {
    private int                                   idCommandSuppliers;
//    private int                                   idSupplier;
//    private int                                   idUser;
    private Date                                  orderDate;
    private SuppliersEnt                          suppliersByIdSupplier;
    private UsersEnt                              usersByIdUser;
    private Collection<CommandsuppliersBatchsEnt> commandsuppliersBatchsByIdCommandSuppliers;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_CommandSuppliers", nullable = false ) public int getIdCommandSuppliers() {
        return idCommandSuppliers;
    }

    public void setIdCommandSuppliers( int idCommandSuppliers ) {
        this.idCommandSuppliers = idCommandSuppliers;
    }

//    @Basic @Column( name = "ID_Supplier", nullable = false ) public int getIdSupplier() {
//        return idSupplier;
//    }
//
//    public void setIdSupplier( int idSupplier ) {
//        this.idSupplier = idSupplier;
//    }
//
//    @Basic @Column( name = "ID_User", nullable = false ) public int getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser( int idUser ) {
//        this.idUser = idUser;
//    }

    @Basic @Column( name = "orderDate", nullable = false ) public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate( Date orderDate ) {
        this.orderDate = orderDate;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        CommandsuppliersEnt that = (CommandsuppliersEnt) o;
        return idCommandSuppliers == that.idCommandSuppliers && Objects.equals( orderDate, that.orderDate )
                && Objects.equals( suppliersByIdSupplier, that.suppliersByIdSupplier ) && Objects
                .equals( usersByIdUser, that.usersByIdUser ) && Objects
                .equals( commandsuppliersBatchsByIdCommandSuppliers, that.commandsuppliersBatchsByIdCommandSuppliers );
    }

    @Override public int hashCode() {
        return Objects.hash( idCommandSuppliers, orderDate, suppliersByIdSupplier, usersByIdUser,
                commandsuppliersBatchsByIdCommandSuppliers );
    }

    @ManyToOne @JoinColumn( name = "ID_Supplier", referencedColumnName = "ID_Supplier", nullable = false ) public SuppliersEnt getSuppliersByIdSupplier() {
        return suppliersByIdSupplier;
    }

    public void setSuppliersByIdSupplier( SuppliersEnt suppliersByIdSupplier ) {
        this.suppliersByIdSupplier = suppliersByIdSupplier;
    }

    @ManyToOne @JoinColumn( name = "ID_User", referencedColumnName = "ID_User", nullable = false ) public UsersEnt getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser( UsersEnt usersByIdUser ) {
        this.usersByIdUser = usersByIdUser;
    }

    @OneToMany( mappedBy = "commandsuppliersByIdCommandSupplier" ) public Collection<CommandsuppliersBatchsEnt> getCommandsuppliersBatchsByIdCommandSuppliers() {
        return commandsuppliersBatchsByIdCommandSuppliers;
    }

    public void setCommandsuppliersBatchsByIdCommandSuppliers(
            Collection<CommandsuppliersBatchsEnt> commandsuppliersBatchsByIdCommandSuppliers ) {
        this.commandsuppliersBatchsByIdCommandSuppliers = commandsuppliersBatchsByIdCommandSuppliers;
    }
}
