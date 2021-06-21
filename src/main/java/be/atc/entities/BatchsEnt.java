package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "batchs", schema = "stock-management", catalog = "" ) public class BatchsEnt {
    private int                                   idBatch;
//    private int                                   idProducts;
    private int                                   quantity;
    private int                                   uniPrice;
    private int                                   numberBatch;
    private ProductsEnt                           productsByIdProducts;
    private Collection<CommandsuppliersBatchsEnt> commandsuppliersBatchsByIdBatch;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Batch", nullable = false ) public int getIdBatch() {
        return idBatch;
    }

    public void setIdBatch( int idBatch ) {
        this.idBatch = idBatch;
    }

//    @Basic @Column( name = "ID_Products", nullable = false ) public int getIdProducts() {
//        return idProducts;
//    }
//
//    public void setIdProducts( int idProducts ) {
//        this.idProducts = idProducts;
//    }

    @Basic @Column( name = "Quantity", nullable = false ) public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    @Basic @Column( name = "uniPrice", nullable = false ) public int getUniPrice() {
        return uniPrice;
    }

    public void setUniPrice( int uniPrice ) {
        this.uniPrice = uniPrice;
    }

    @Basic @Column( name = "numberBatch", nullable = false ) public int getNumberBatch() {
        return numberBatch;
    }

    public void setNumberBatch( int numberBatch ) {
        this.numberBatch = numberBatch;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        BatchsEnt batchsEnt = (BatchsEnt) o;
        return idBatch == batchsEnt.idBatch && quantity == batchsEnt.quantity && uniPrice == batchsEnt.uniPrice
                && numberBatch == batchsEnt.numberBatch && Objects
                .equals( productsByIdProducts, batchsEnt.productsByIdProducts ) && Objects
                .equals( commandsuppliersBatchsByIdBatch, batchsEnt.commandsuppliersBatchsByIdBatch );
    }

    @Override public int hashCode() {
        return Objects
                .hash( idBatch, quantity, uniPrice, numberBatch, productsByIdProducts,
                        commandsuppliersBatchsByIdBatch );
    }

    @ManyToOne @JoinColumn( name = "ID_Products", referencedColumnName = "ID_Product", nullable = false ) public ProductsEnt getProductsByIdProducts() {
        return productsByIdProducts;
    }

    public void setProductsByIdProducts( ProductsEnt productsByIdProducts ) {
        this.productsByIdProducts = productsByIdProducts;
    }

    @OneToMany( mappedBy = "batchsByIdBatch" ) public Collection<CommandsuppliersBatchsEnt> getCommandsuppliersBatchsByIdBatch() {
        return commandsuppliersBatchsByIdBatch;
    }

    public void setCommandsuppliersBatchsByIdBatch(
            Collection<CommandsuppliersBatchsEnt> commandsuppliersBatchsByIdBatch ) {
        this.commandsuppliersBatchsByIdBatch = commandsuppliersBatchsByIdBatch;
    }
}
