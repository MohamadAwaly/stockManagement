package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "orders_documents", schema = "stockmanagement" )
public class OrdersDocumentsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID_Orders_docuements", nullable = false )
    private int idOrdersDocuements;

    @ManyToOne @JoinColumn( name = "id_order", referencedColumnName = "id_order", nullable = false )
    private OrdersEntity    orders;
    @ManyToOne @JoinColumn( name = "id_document", referencedColumnName = "id_document", nullable = false )
    private DocumentsEntity documents;

    public int getIdOrdersDocuements() {
        return idOrdersDocuements;
    }

    public void setIdOrdersDocuements( int idOrdersDocuements ) {
        this.idOrdersDocuements = idOrdersDocuements;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrdersDocumentsEntity that = (OrdersDocumentsEntity) o;
        return idOrdersDocuements == that.idOrdersDocuements && Objects.equals( orders, that.orders )
                && Objects.equals( documents, that.documents );
    }

    @Override public int hashCode() {
        return Objects.hash( idOrdersDocuements, orders, documents );
    }

    //Relation avec la table orders

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders( OrdersEntity orders ) {
        this.orders = orders;
    }

    //Relation avec la table document

    public DocumentsEntity getDocuments() {
        return documents;
    }

    public void setDocuments( DocumentsEntity documents ) {
        this.documents = documents;
    }
}
