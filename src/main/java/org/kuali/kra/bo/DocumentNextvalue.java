package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class DocumentNextvalue extends KraPersistableBusinessObjectBase {
	private String propertyName; 
	private Integer documentKey;
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

    public Integer getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(Integer documentKey) {
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
