package be.atc.entities;

import be.atc.controler.enumm.TypeAdress;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "adress_users", schema = "stockmanagement" )
public class AdressUsersEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_adress_users", nullable = false )
    private int idAdressUsers;

    @Basic
    @Column( name = "typeadress", nullable = false )
    @Enumerated( EnumType.STRING )
    private TypeAdress typeAdress;

    @ManyToOne
    @JoinColumn( name = "id_adress", referencedColumnName = "id_adress", nullable = false )
    private AdressEntity address;
    @ManyToOne
    @JoinColumn( name = "id_user", referencedColumnName = "id_user", nullable = false )
    private UsersEntity  users;

    public int getIdAdressUsers() {
        return idAdressUsers;
    }

    public void setIdAdressUsers( int idAdressUsers ) {
        this.idAdressUsers = idAdressUsers;
    }

    public TypeAdress getTypeAdress() {
        return typeAdress;
    }

    public void setTypeAdress( TypeAdress typeAdress ) {
        this.typeAdress = typeAdress;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        AdressUsersEntity that = (AdressUsersEntity) o;
        return idAdressUsers == that.idAdressUsers && typeAdress == that.typeAdress && Objects
                .equals( address, that.address ) && Objects.equals( users, that.users );
    }

    @Override public int hashCode() {
        return Objects.hash( idAdressUsers, typeAdress, address, users );
    }

    public AdressEntity getAddress() {
        return address;
    }

    public void setAddress( AdressEntity address ) {
        this.address = address;
    }

    public UsersEntity getUsers() {
        return users;
    }

    public void setUsers( UsersEntity users ) {
        this.users = users;
    }
}
