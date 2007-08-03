package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class PropPerDocType extends KraPersistableBusinessObjectBase {
    private String documentTypeCode;
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
