package be.atc.entities;

import javax.persistence.*;

@Entity @Table( name = "adresse")
public class AdressEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdress;
    @Basic @Column (name = "street", nullable = false, length = 255)
    private String street;
    @Basic @Column(name = "number", nullable = false)
    private int number;

}

