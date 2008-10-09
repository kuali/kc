package org.kuali.kra.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

@Entity
@Table(name="EPS_PROP_PER_DOC_TYPE")
public class PropPerDocType extends KraPersistableBusinessObjectBase {
    @Id
	@Column(name="DOCUMENT_TYPE_CODE")
	private String documentTypeCode;
    @Column(name="DESCRIPTION")
	private String description;

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("documentTypeCode", getDocumentTypeCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }
}

