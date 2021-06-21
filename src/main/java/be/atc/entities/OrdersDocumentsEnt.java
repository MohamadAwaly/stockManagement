package be.atc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table( name = "orders_documents", schema = "stock-management", catalog = "" ) public class OrdersDocumentsEnt {
    private int idOrdersDocuements;
//    private int idOrder;
//    private int idDocument;
    private OrdersEnt ordersByIdOrder;
    private DocumentsEnt documentsByIdDocument;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Orders_docuements", nullable = false ) public int getIdOrdersDocuements() {
        return idOrdersDocuements;
    }

    public void setIdOrdersDocuements( int idOrdersDocuements ) {
        this.idOrdersDocuements = idOrdersDocuements;
    }

//    @Basic @Column( name = "ID_Order", nullable = false ) public int getIdOrder() {
//        return idOrder;
//    }
//
//    public void setIdOrder( int idOrder ) {
//        this.idOrder = idOrder;
//    }
//
//    @Basic @Column( name = "ID_Document", nullable = false ) public int getIdDocument() {
//        return idDocument;
//    }
//
//    public void setIdDocument( int idDocument ) {
//        this.idDocument = idDocument;
//    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrdersDocumentsEnt that = (OrdersDocumentsEnt) o;
        return idOrdersDocuements == that.idOrdersDocuements && Objects
                .equals( ordersByIdOrder, that.ordersByIdOrder ) && Objects
                .equals( documentsByIdDocument, that.documentsByIdDocument );
    }

    @Override public int hashCode() {
        return Objects.hash( idOrdersDocuements, ordersByIdOrder, documentsByIdDocument );
    }

    @ManyToOne @JoinColumn( name = "ID_Order", referencedColumnName = "ID_Order", nullable = false ) public OrdersEnt getOrdersByIdOrder() {
        return ordersByIdOrder;
    }

    public void setOrdersByIdOrder( OrdersEnt ordersByIdOrder ) {
        this.ordersByIdOrder = ordersByIdOrder;
    }

    @ManyToOne @JoinColumn( name = "ID_Document", referencedColumnName = "ID_Document", nullable = false ) public DocumentsEnt getDocumentsByIdDocument() {
        return documentsByIdDocument;
    }

    public void setDocumentsByIdDocument( DocumentsEnt documentsByIdDocument ) {
        this.documentsByIdDocument = documentsByIdDocument;
    }
}
