package org.kuali.kra.bo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Entity
@Table(name="ORGANIZATION_TYPE_LIST")
public class OrganizationTypeList extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="ORGANIZATION_TYPE_CODE")
	private Integer organizationTypeCode;
	@Column(name="DESCRIPTION")
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

