package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "stockmanagement")
public class RolesEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false)
    private int idRole;
    @Basic
    @Column(name = "role", nullable = false, length = 60)
    private String role;

    private Collection<UsersEntity> users;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesEntity that = (RolesEntity) o;
        return idRole == that.idRole && Objects.equals(role, that.role) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, role, users);
    }

    //Relation avec la table users via le champs roles.
    @OneToMany(mappedBy = "roles")
    public Collection<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UsersEntity> users) {
        this.users = users;
    }
}
