package org.kuali.coeus.common.framework.auth.docperm;


import org.kuali.coeus.sys.api.model.DocumentNumbered;
import org.kuali.coeus.sys.api.model.Identifiable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "DOCUMENT_ACCESS")
public class DocumentAccess extends KcPersistableBusinessObjectBase implements Identifiable, DocumentNumbered {

    @PortableSequenceGenerator(name = "SEQ_DOCUMENT_ACCESS_ID")
    @GeneratedValue(generator = "SEQ_DOCUMENT_ACCESS_ID")
    @Id
    @Column(name = "DOC_ACCESS_ID")
    private String id;

    @Column(name = "DOC_HDR_ID", length=14)
    private String documentNumber;

    @Column(name = "PRNCPL_ID")
    private String principalId;

    @Column(name = "ROLE_NM")
    private String roleName;

    @Column(name = "NMSPC_CD")
    private String namespaceCode;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalName) {
        this.principalId = principalId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNamespaceCode() {
        return namespaceCode;
    }

    public void setNamespaceCode(String namespaceCode) {
        this.namespaceCode = namespaceCode;
    }
}
