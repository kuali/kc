package org.kuali.kra.bo;

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

@IdClass(org.kuali.kra.bo.id.OrganizationTypeId.class)
@Entity
@Table(name="ORGANIZATION_TYPE")
public class OrganizationType extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="ORGANIZATION_ID")
	private String organizationId;
	
	@Id
	@Column(name="ORGANIZATION_TYPE_CODE")
	private Integer organizationTypeCode;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ORGANIZATION_ID", insertable=false, updatable=false)
	private Organization organization;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ORGANIZATION_TYPE_CODE", insertable=false, updatable=false)
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

	public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

