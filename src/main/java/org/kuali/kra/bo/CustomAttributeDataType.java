package org.kuali.kra.bo;

import java.util.LinkedHashMap;

/**
 * 
 * This class is bo of CustomAttributeDataType.
 */
public class CustomAttributeDataType extends KraPersistableBusinessObjectBase {

	private String dataTypeCode;
	private String description;

	public CustomAttributeDataType(){
		super();
	}

	public String getDataTypeCode() {
		return dataTypeCode;
	}

	public void setDataTypeCode(String dataTypeCode) {
		this.dataTypeCode = dataTypeCode;
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
		hashMap.put("dataTypeCode", getDataTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}
