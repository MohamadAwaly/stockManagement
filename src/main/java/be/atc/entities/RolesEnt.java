package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "roles", schema = "stock-management", catalog = "" ) public class RolesEnt {
    private int                  idRole;
    private String               role;
    private Collection<UsersEnt> usersByIdRole;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Role", nullable = false ) public int getIdRole() {
        return idRole;
    }

    public void setIdRole( int idRole ) {
        this.idRole = idRole;
    }

    @Basic @Column( name = "role", nullable = false, length = 60 ) public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        RolesEnt rolesEnt = (RolesEnt) o;
        return idRole == rolesEnt.idRole && Objects.equals( role, rolesEnt.role );
    }

    @Override public int hashCode() {
        return Objects.hash( idRole, role );
    }

    @OneToMany( mappedBy = "rolesByIdRole" ) public Collection<UsersEnt> getUsersByIdRole() {
        return usersByIdRole;
    }

    public void setUsersByIdRole( Collection<UsersEnt> usersByIdRole ) {
        this.usersByIdRole = usersByIdRole;
    }
}
