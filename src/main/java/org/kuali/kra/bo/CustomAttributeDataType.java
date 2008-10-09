package org.kuali.kra.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

/**
 * 
 * This class is bo of CustomAttributeDataType.
 */
@Entity
@Table(name="CUSTOM_ATTRIBUTE_DATA_TYPE")
public class CustomAttributeDataType extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="DATA_TYPE_CODE")
	private String dataTypeCode;
	@Column(name="DESCRIPTION")
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

