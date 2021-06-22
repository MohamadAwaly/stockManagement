package be.atc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table( name = "documenttypes", schema = "stockmanagement")
public class DocumenttypesEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_typeDocument", nullable = false )
    private int idTypeDocument;
    @Basic @Column( name = "documentType", nullable = false, length = 60 )
    private String documentType;

    private Collection<DocumentsEntity> documents;

    public int getIdTypeDocument() {
        return idTypeDocument;
    }

    public void setIdTypeDocument(int idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumenttypesEntity that = (DocumenttypesEntity) o;
        return idTypeDocument == that.idTypeDocument && Objects.equals(documentType, that.documentType) && Objects.equals(documents, that.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeDocument, documentType, documents);
    }

    //Relation avec la table docuements
    @OneToMany( mappedBy = "documenttypes" )
    public Collection<DocumentsEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(Collection<DocumentsEntity> documents) {
        this.documents = documents;
    }
}
