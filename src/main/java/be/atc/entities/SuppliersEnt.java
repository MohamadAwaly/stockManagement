package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "suppliers", schema = "stock-management", catalog = "" ) public class SuppliersEnt {
    private int                             idSupplier;
    private String                          name;
    private Collection<CommandsuppliersEnt> commandsuppliersByIdSupplier;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Supplier", nullable = false ) public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier( int idSupplier ) {
        this.idSupplier = idSupplier;
    }

    @Basic @Column( name = "name", nullable = false, length = 60 ) public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        SuppliersEnt that = (SuppliersEnt) o;
        return idSupplier == that.idSupplier && Objects.equals( name, that.name );
    }

    @Override public int hashCode() {
        return Objects.hash( idSupplier, name );
    }

    @OneToMany( mappedBy = "suppliersByIdSupplier" ) public Collection<CommandsuppliersEnt> getCommandsuppliersByIdSupplier() {
        return commandsuppliersByIdSupplier;
    }

    public void setCommandsuppliersByIdSupplier(
            Collection<CommandsuppliersEnt> commandsuppliersByIdSupplier ) {
        this.commandsuppliersByIdSupplier = commandsuppliersByIdSupplier;
    }
}
