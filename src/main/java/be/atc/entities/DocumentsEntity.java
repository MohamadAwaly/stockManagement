package be.atc.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table( name = "documents", schema = "stockmanagement" )
public class DocumentsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID_Document", nullable = false )
    private int  idDocument;
    @Basic @Column( name = "Numero", nullable = false )
    private int  numero;
    @Basic @Column( name = "DateDocument", nullable = false )
    private Date dateDocument;

    private DocumenttypesEntity               documenttypes;
    private Collection<OrdersDocumentsEntity> ordersDocuments;

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument( int idDocument ) {
        this.idDocument = idDocument;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero( int numero ) {
        this.numero = numero;
    }

    public Date getDateDocument() {
        return dateDocument;
    }

    public void setDateDocument( Date dateDocument ) {
        this.dateDocument = dateDocument;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        DocumentsEntity that = (DocumentsEntity) o;
        return idDocument == that.idDocument && numero == that.numero && Objects
                .equals( dateDocument, that.dateDocument ) && Objects.equals( documenttypes, that.documenttypes )
                && Objects.equals( ordersDocuments, that.ordersDocuments );
    }

    @Override public int hashCode() {
        return Objects.hash( idDocument, numero, dateDocument, documenttypes, ordersDocuments );
    }

    //Relation avec la table documentType
    @ManyToOne @JoinColumn( name = "id_documentType", referencedColumnName = "id_typeDocument", nullable = false )
    public DocumenttypesEntity getDocumenttypes() {
        return documenttypes;
    }

    public void setDocumenttypes( DocumenttypesEntity documenttypes ) {
        this.documenttypes = documenttypes;
    }

    //Relation avec la table ordersDocuements
    @OneToMany( mappedBy = "documents" )
    public Collection<OrdersDocumentsEntity> getOrdersDocuments() {
        return ordersDocuments;
    }

    public void setOrdersDocuments( Collection<OrdersDocumentsEntity> ordersDocuments ) {
        this.ordersDocuments = ordersDocuments;
    }
}
