package org.kuali.kra.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class OrganizationTypeList extends KraPersistableBusinessObjectBase {

	private Integer organizationTypeCode;
	private String description;
    private List<OrganizationType> organizationTypes;

	public OrganizationTypeList(){
		super();
        organizationTypes = new ArrayList<OrganizationType>();        
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

    public List<OrganizationType> getOrganizationTypes() {
        return organizationTypes;
    }

    public void setOrganizationTypes(List<OrganizationType> organizationTypes) {
        this.organizationTypes = organizationTypes;
    }

    public OrganizationType getOrganizationType(int index) {
        while (getOrganizationTypes().size() <= index) {
            getOrganizationTypes().add(new OrganizationType());
        }
        
        return (OrganizationType)getOrganizationTypes().get(index);
    }

}
