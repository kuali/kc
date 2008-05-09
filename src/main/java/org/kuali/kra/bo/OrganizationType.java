package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class OrganizationType extends KraPersistableBusinessObjectBase {

	private String organizationId;
	private Integer organizationTypeCode;
	private OrganizationTypeList organizationTypeList;

	public OrganizationType(){
		super();
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getOrganizationTypeCode() {
		return organizationTypeCode;
	}

	public void setOrganizationTypeCode(Integer organizationTypeCode) {
		this.organizationTypeCode = organizationTypeCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("organizationId", getOrganizationId());
		hashMap.put("organizationTypeCode", getOrganizationTypeCode());
		return hashMap;
	}

    public OrganizationTypeList getOrganizationTypeList() {
        return organizationTypeList;
    }

    public void setOrganizationTypeList(OrganizationTypeList organizationTypeList) {
        this.organizationTypeList = organizationTypeList;
    }
}
