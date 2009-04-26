package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class OrganizationTypeList extends KraPersistableBusinessObjectBase {

	private Integer organizationTypeCode;
	private String description;

	public OrganizationTypeList(){
		super();  
	}

	public Integer getOrganizationTypeCode() {
		return organizationTypeCode;
	}

	public void setOrganizationTypeCode(Integer organizationTypeCode) {
		this.organizationTypeCode = organizationTypeCode;
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
		hashMap.put("organizationTypeCode", getOrganizationTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}
