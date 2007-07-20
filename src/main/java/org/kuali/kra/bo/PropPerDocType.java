package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class PropPerDocType extends KraPersistableBusinessObjectBase {
	
	private Integer documentTypeCode;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDocumentTypeCode() {
		return documentTypeCode;
	}
	public void setDocumentTypeCode(Integer documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("documentTypeCode", this.documentTypeCode);
		propMap.put("description", this.description);
		return propMap;
	}
}
