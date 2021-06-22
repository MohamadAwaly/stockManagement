package be.atc.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
@Entity
@Table( name = "documents", schema = "stockmanagement")
public class DocumentsEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID_Document", nullable = false )
    private int                            idDocument;
    @Basic @Column( name = "Numero", nullable = false )
    private int                            numero;
    @Basic @Column( name = "DateDocument", nullable = false )
    private Date dateDocument;

    private DocumenttypesEntity               documenttypes;
    private Collection<OrdersDocumentsEntity> ordersDocuments;
}
