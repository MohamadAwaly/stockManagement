package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table( name = "documenttypes", schema = "stock-management", catalog = "" ) public class DocumenttypesEnt {
    private int idTypeDocument;
    private String documentType;
    private Collection<DocumentsEnt> documentsByIdTypeDocument;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column( name = "ID_TypeDocument", nullable = false ) public int getIdTypeDocument() {
        return idTypeDocument;
    }

    public void setIdTypeDocument( int idTypeDocument ) {
        this.idTypeDocument = idTypeDocument;
    }

    @Basic @Column( name = "documentType", nullable = false, length = 60 ) public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType( String documentType ) {
        this.documentType = documentType;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        DocumenttypesEnt that = (DocumenttypesEnt) o;
        return idTypeDocument == that.idTypeDocument && Objects.equals( documentType, that.documentType );
    }

    @Override public int hashCode() {
        return Objects.hash( idTypeDocument, documentType );
    }

    @OneToMany( mappedBy = "documenttypesByIdDocumentType" ) public Collection<DocumentsEnt> getDocumentsByIdTypeDocument() {
        return documentsByIdTypeDocument;
    }

    public void setDocumentsByIdTypeDocument( Collection<DocumentsEnt> documentsByIdTypeDocument ) {
        this.documentsByIdTypeDocument = documentsByIdTypeDocument;
    }
}
