package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table( name = "commandsuppliers_batchs", schema = "stock-management", catalog = "" ) public class CommandsuppliersBatchsEnt {
    private int idCommandsuppliersBatchs;
//    private int idCommandSupplier;
//    private int idBatch;
    private int costPrice;
    private int lotQuantity;
    private CommandsuppliersEnt commandsuppliersByIdCommandSupplier;
    private BatchsEnt batchsByIdBatch;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Commandsuppliers_batchs", nullable = false ) public int getIdCommandsuppliersBatchs() {
        return idCommandsuppliersBatchs;
    }

    public void setIdCommandsuppliersBatchs( int idCommandsuppliersBatchs ) {
        this.idCommandsuppliersBatchs = idCommandsuppliersBatchs;
    }

//    @Basic @Column( name = "ID_CommandSupplier", nullable = false ) public int getIdCommandSupplier() {
//        return idCommandSupplier;
//    }
//
//    public void setIdCommandSupplier( int idCommandSupplier ) {
//        this.idCommandSupplier = idCommandSupplier;
//    }
//
//    @Basic @Column( name = "ID_Batch", nullable = false ) public int getIdBatch() {
//        return idBatch;
//    }
//
//    public void setIdBatch( int idBatch ) {
//        this.idBatch = idBatch;
//    }

    @Basic @Column( name = "costPrice", nullable = false ) public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice( int costPrice ) {
        this.costPrice = costPrice;
    }

    @Basic @Column( name = "lotQuantity", nullable = false ) public int getLotQuantity() {
        return lotQuantity;
    }

    public void setLotQuantity( int lotQuantity ) {
        this.lotQuantity = lotQuantity;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        CommandsuppliersBatchsEnt that = (CommandsuppliersBatchsEnt) o;
        return idCommandsuppliersBatchs == that.idCommandsuppliersBatchs && costPrice == that.costPrice
                && lotQuantity == that.lotQuantity && Objects
                .equals( commandsuppliersByIdCommandSupplier, that.commandsuppliersByIdCommandSupplier )
                && Objects.equals( batchsByIdBatch, that.batchsByIdBatch );
    }

    @Override public int hashCode() {
        return Objects.hash( idCommandsuppliersBatchs, costPrice, lotQuantity, commandsuppliersByIdCommandSupplier,
                batchsByIdBatch );
    }

    @ManyToOne @JoinColumn( name = "ID_CommandSupplier", referencedColumnName = "ID_CommandSuppliers", nullable = false ) public CommandsuppliersEnt getCommandsuppliersByIdCommandSupplier() {
        return commandsuppliersByIdCommandSupplier;
    }

    public void setCommandsuppliersByIdCommandSupplier( CommandsuppliersEnt commandsuppliersByIdCommandSupplier ) {
        this.commandsuppliersByIdCommandSupplier = commandsuppliersByIdCommandSupplier;
    }

    @ManyToOne @JoinColumn( name = "ID_Batch", referencedColumnName = "ID_Batch", nullable = false ) public BatchsEnt getBatchsByIdBatch() {
        return batchsByIdBatch;
    }

    public void setBatchsByIdBatch( BatchsEnt batchsByIdBatch ) {
        this.batchsByIdBatch = batchsByIdBatch;
    }
}
