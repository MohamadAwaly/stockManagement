package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "commandSuppliersBatchs.FindAll",
                 query = "SELECT s, cs, csb, b, p FROM CommandsuppliersBatchsEntity csb " +
                         "JOIN CommandsuppliersEntity cs ON cs = csb.commandsuppliers " +
                         "JOIN BatchsEntity b ON b = csb.batchs " +
                         "JOIN SuppliersEntity s ON s = cs.suppliers " +
                         "JOIN ProductsEntity p ON p = b.products " +
                         "ORDER BY cs.orderDate desc ")
})

@Entity
@Table( name = "commandsuppliers_batchs", schema = "stockmanagement" )
public class CommandsuppliersBatchsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_commandsuppliers_batchs", nullable = false )
    private int idCommandsuppliersBatchs;
    @Basic
    @Column( name = "costPrice", nullable = false )
    private int costPrice;
    @Basic
    @Column( name = "lotQuantity", nullable = false )
    private int lotQuantity;

    @ManyToOne
    @JoinColumn( name = "id_commandSupplier", referencedColumnName = "id_commandSuppliers", nullable = false )
    private CommandsuppliersEntity commandsuppliers;
    @ManyToOne
    @JoinColumn( name = "id_batch", referencedColumnName = "id_batch", nullable = false )
    private BatchsEntity           batchs;

    public int getIdCommandsuppliersBatchs() {
        return idCommandsuppliersBatchs;
    }

    public void setIdCommandsuppliersBatchs( int idCommandsuppliersBatchs ) {
        this.idCommandsuppliersBatchs = idCommandsuppliersBatchs;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice( int costPrice ) {
        this.costPrice = costPrice;
    }

    public int getLotQuantity() {
        return lotQuantity;
    }

    public void setLotQuantity( int lotQuantity ) {
        this.lotQuantity = lotQuantity;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        CommandsuppliersBatchsEntity that = (CommandsuppliersBatchsEntity) o;
        return idCommandsuppliersBatchs == that.idCommandsuppliersBatchs && costPrice == that.costPrice
                && lotQuantity == that.lotQuantity && Objects.equals( commandsuppliers, that.commandsuppliers )
                && Objects.equals( batchs, that.batchs );
    }

    @Override
    public int hashCode() {
        return Objects.hash( idCommandsuppliersBatchs, costPrice, lotQuantity, commandsuppliers, batchs );
    }

    //Relation avec la table commandsuppliers

    public CommandsuppliersEntity getCommandsuppliers() {
        return commandsuppliers;
    }

    public void setCommandsuppliers( CommandsuppliersEntity commandsuppliers ) {
        this.commandsuppliers = commandsuppliers;
    }

    //Relation avec la table batchs

    public BatchsEntity getBatchs() {
        return batchs;
    }

    public void setBatchs( BatchsEntity batchs ) {
        this.batchs = batchs;
    }

}
