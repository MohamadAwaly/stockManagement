package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NamedQueries(value = {
        @NamedQuery(name = "suppliers.findAll",query = "SELECT s FROM SuppliersEntity s"),
        @NamedQuery(name="Suppliers.findName",query = "SELECT s.name from SuppliersEntity s")
})


@Entity
@Table( name = "suppliers", schema = "stockmanagement" )
public class SuppliersEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_supplier", nullable = false )
    private int    idSupplier;
    @Basic
    @Column( name = "name", nullable = false, length = 60 )
    private String name;

    private Collection<CommandsuppliersEntity> commandsuppliers;

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier( int idSupplier ) {
        this.idSupplier = idSupplier;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        SuppliersEntity that = (SuppliersEntity) o;
        return idSupplier == that.idSupplier && Objects.equals( name, that.name ) && Objects
                .equals( commandsuppliers, that.commandsuppliers );
    }

    @Override
    public int hashCode() {
        return Objects.hash( idSupplier, name, commandsuppliers );
    }

    //Relation avec la table commandeSupplier via le champs suppliers
    @OneToMany( mappedBy = "suppliers" )
    public Collection<CommandsuppliersEntity> getCommandsuppliers() {
        return commandsuppliers;
    }

    public void setCommandsuppliers( Collection<CommandsuppliersEntity> commandsuppliers ) {
        this.commandsuppliers = commandsuppliers;
    }
}
