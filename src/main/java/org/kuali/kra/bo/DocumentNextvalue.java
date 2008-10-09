package org.kuali.kra.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

@IdClass(org.kuali.kra.bo.id.DocumentNextvalueId.class)
@Entity
@Table(name="DOCUMENT_NEXTVALUE")
public class DocumentNextvalue extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="PROPERTY_NAME")
	private String propertyName; 
	
	@Id
	@Column(name="DOCUMENT_NUMBER")
	private String documentKey;
	
	@Column(name="NEXT_VALUE")
	private Integer nextValue;

	public String getPropertyName() {
		return propertyName;  
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;  
	}

	public Integer getNextValue() {
		return nextValue;
	}

	public void setNextValue(Integer nextValue) {
		this.nextValue = nextValue;
	}

    public String getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("propertyName", getPropertyName());
        hashMap.put("documentKey", getDocumentKey());
        hashMap.put("nextValue", getNextValue());
        return hashMap;
    }

}

