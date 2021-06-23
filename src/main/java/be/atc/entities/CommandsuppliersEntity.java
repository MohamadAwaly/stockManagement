package be.atc.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table( name = "commandsuppliers", schema = "stockmanagement" )
public class CommandsuppliersEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_commandSuppliers", nullable = false )
    private int  idCommandSuppliers;
    @Basic
    @Column( name = "orderDate", nullable = false )
    private Date orderDate;

    private SuppliersEntity                          suppliers;
    private UsersEntity                              users;
    private Collection<CommandsuppliersBatchsEntity> commandsuppliers;

    public int getIdCommandSuppliers() {
        return idCommandSuppliers;
    }

    public void setIdCommandSuppliers( int idCommandSuppliers ) {
        this.idCommandSuppliers = idCommandSuppliers;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate( Date orderDate ) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        CommandsuppliersEntity that = (CommandsuppliersEntity) o;
        return idCommandSuppliers == that.idCommandSuppliers && Objects.equals( orderDate, that.orderDate ) && Objects
                .equals( suppliers, that.suppliers ) && Objects.equals( users, that.users ) && Objects
                .equals( commandsuppliers, that.commandsuppliers );
    }

    @Override
    public int hashCode() {
        return Objects.hash( idCommandSuppliers, orderDate, suppliers, users, commandsuppliers );
    }

    //Relation avec la table supplier
    @ManyToOne
    @JoinColumn( name = "id_supplier", referencedColumnName = "id_supplier", nullable = false )
    public SuppliersEntity getSuppliers() {
        return suppliers;
    }

    public void setSuppliers( SuppliersEntity suppliers ) {
        this.suppliers = suppliers;
    }

    //Relation avec la tables Users
    @ManyToOne
    @JoinColumn( name = "id_user", referencedColumnName = "id_user", nullable = false )
    public UsersEntity getUsers() {
        return users;
    }

    public void setUsers( UsersEntity users ) {
        this.users = users;
    }

    //Relation avec la table CommandeSupplierBatchs
    @OneToMany( mappedBy = "commandsuppliers" )
    public Collection<CommandsuppliersBatchsEntity> getCommandsuppliers() {
        return commandsuppliers;
    }

    public void setCommandsuppliers( Collection<CommandsuppliersBatchsEntity> commandsuppliers ) {
        this.commandsuppliers = commandsuppliers;
    }
}
