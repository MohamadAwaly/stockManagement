package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table( name = "batchs", schema = "stockmanagement" )
public class BatchsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID_Batch", nullable = false )
    private int idBatch;
    @Basic
    @Column( name = "Quantity", nullable = false )
    private int quantity;
    @Basic
    @Column( name = "uniPrice", nullable = false )
    private int uniPrice;
    @Basic
    @Column( name = "numberBatch", nullable = false )
    private int numberBatch;

    @ManyToOne
    @JoinColumn( name = "id_products", referencedColumnName = "id_product", nullable = false )
    private ProductsEntity                           products;
    private Collection<CommandsuppliersBatchsEntity> commandsuppliersBatch;

    public int getIdBatch() {
        return idBatch;
    }

    public void setIdBatch( int idBatch ) {
        this.idBatch = idBatch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    public int getUniPrice() {
        return uniPrice;
    }

    public void setUniPrice( int uniPrice ) {
        this.uniPrice = uniPrice;
    }

    public int getNumberBatch() {
        return numberBatch;
    }

    public void setNumberBatch( int numberBatch ) {
        this.numberBatch = numberBatch;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        BatchsEntity that = (BatchsEntity) o;
        return idBatch == that.idBatch && quantity == that.quantity && uniPrice == that.uniPrice
                && numberBatch == that.numberBatch && Objects.equals( products, that.products ) && Objects
                .equals( commandsuppliersBatch, that.commandsuppliersBatch );
    }

    @Override
    public int hashCode() {
        return Objects.hash( idBatch, quantity, uniPrice, numberBatch, products, commandsuppliersBatch );
    }

    //Relation avec la table products

    public ProductsEntity getProducts() {
        return products;
    }

    public void setProducts( ProductsEntity products ) {
        this.products = products;
    }

    //Relation avec la table CommandSuppliersBatchs
    @OneToMany( mappedBy = "batchs" )
    public Collection<CommandsuppliersBatchsEntity> getCommandsuppliersBatch() {
        return commandsuppliersBatch;
    }

    public void setCommandsuppliersBatch( Collection<CommandsuppliersBatchsEntity> commandsuppliersBatch ) {
        this.commandsuppliersBatch = commandsuppliersBatch;
    }
}
