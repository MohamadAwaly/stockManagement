package be.atc.entities;

import javax.persistence.*;

@Entity
@Table( name = "orders_documents", schema = "stockmanagement")
public class OrdersDocumentsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Orders_docuements", nullable = false )
    private int idOrdersDocuements;

    private OrdersEntity orders;
    private DocumentsEntity documents;

}
