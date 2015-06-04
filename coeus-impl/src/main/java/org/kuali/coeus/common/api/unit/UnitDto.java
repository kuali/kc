package org.kuali.coeus.common.api.unit;


public class UnitDto {

    private String unitNumber;
    private String parentUnitNumber;
    private String organizationId;
    private String unitName;
    
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getParentUnitNumber() {
		return parentUnitNumber;
	}
	public void setParentUnitNumber(String parentUnitNumber) {
		this.parentUnitNumber = parentUnitNumber;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
