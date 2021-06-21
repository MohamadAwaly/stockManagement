package be.atc.entities;

import be.atc.controler.enumm.TypeAdress;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "adress_users", schema = "stock-management", catalog = "")
public class AdressUsersEnt {
    private int idAdressUsers;
//    private int idAdress;
//    private int idUser;

    private TypeAdress typeAdress;
    private AddressEnt addressByIdAdress;
    private UsersEnt usersByIdUser;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Adress_users", nullable = false)
    public int getIdAdressUsers() {
        return idAdressUsers;
    }

    public void setIdAdressUsers(int idAdressUsers) {
        this.idAdressUsers = idAdressUsers;
    }

//    @Basic
//    @Column(name = "ID_Adress", nullable = false)
//    public int getIdAdress() {
//        return idAdress;
//    }
//
//    public void setIdAdress(int idAdress) {
//        this.idAdress = idAdress;
//    }
//
//    @Basic
//    @Column(name = "ID_User", nullable = false)
//    public int getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(int idUser) {
//        this.idUser = idUser;
//    }

    @Basic
    @Column(name = "typeAdress", nullable = false)
    @Enumerated(EnumType.STRING)
    public TypeAdress getTypeAdress() {
        return typeAdress;
    }

    public void setTypeAdress(TypeAdress typeAdress) {
        this.typeAdress = typeAdress;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        AdressUsersEnt that = (AdressUsersEnt) o;
        return idAdressUsers == that.idAdressUsers && typeAdress == that.typeAdress && Objects
                .equals( addressByIdAdress, that.addressByIdAdress ) && Objects
                .equals( usersByIdUser, that.usersByIdUser );
    }

    @Override public int hashCode() {
        return Objects.hash( idAdressUsers, typeAdress, addressByIdAdress, usersByIdUser );
    }

    @ManyToOne
    @JoinColumn(name = "ID_Adress", referencedColumnName = "ID_Adress", nullable = false)
    public AddressEnt getAddressByIdAdress() {
        return addressByIdAdress;
    }

    public void setAddressByIdAdress(AddressEnt addressByIdAdress) {
        this.addressByIdAdress = addressByIdAdress;
    }

    @ManyToOne
    @JoinColumn(name = "ID_User", referencedColumnName = "ID_User", nullable = false)
    public UsersEnt getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEnt usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }
}
