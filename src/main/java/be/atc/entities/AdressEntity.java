package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NamedQueries(value = {
        @NamedQuery(name = "Adress.SelectByIdUser",
                query = "SELECT a, au, c FROM AdressEntity a " +
                        "JOIN AdressUsersEntity au on au.address = a " +
                        "JOIN UsersEntity u on au.users = u  " +
                        "JOIN CitiesEntity c on a.city = c" +
                        " where au.users.idUser = :id"),
        @NamedQuery(name = "Adress.TypeAdressExist",
                query = "Select au.typeAdress from AdressUsersEntity au" +
                        " join UsersEntity u on au.users = u" +
                        " where au.users.idUser = :idUser and au.typeAdress = :typeAdress"),
        @NamedQuery(name = "Adress.SelectAdressById",
                query = "SELECT a, au from AdressEntity a " +
                        "left join AdressUsersEntity au on au.address = a " +
                        "where a.idAdress = :id")
})

@Entity
@Table(name = "address", schema = "stockmanagement")
public class AdressEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adress", nullable = false)
    private int idAdress;

    @Basic
    @Column(name = "street", nullable = false, length = 255)
    private String street;

    @Basic
    @Column(name = "number", nullable = false)
    private int number;

    @Basic
    @Column(name = "box", nullable = false)
    private int box;

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id_city", nullable = false)
    private CitiesEntity city;
    private Collection<AdressUsersEntity> adressUsers;

    public int getIdAdress() {
        return idAdress;
    }

    public void setIdAdress(int idAdress) {
        this.idAdress = idAdress;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AdressEntity that = (AdressEntity) o;
        return idAdress == that.idAdress && number == that.number && box == that.box && Objects
                .equals(street, that.street) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdress, street, number, box, city);
    }

    public CitiesEntity getCity() {
        return city;
    }

    public void setCity(CitiesEntity city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "address", cascade = CascadeType.PERSIST)
    public Collection<AdressUsersEntity> getAdressUsers() {
        return adressUsers;
    }

    public void setAdressUsers(Collection<AdressUsersEntity> adressUsers) {
        this.adressUsers = adressUsers;
    }
}

