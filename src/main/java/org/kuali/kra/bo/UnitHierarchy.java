package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class UnitHierarchy extends KraPersistableBusinessObjectBase {
	private String unitNumber;
	private Boolean hasChildrenFlag;
	private String parentUnitNumber;
    
    private Unit parentUnit;

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Boolean getHasChildrenFlag() {
		return hasChildrenFlag;
	}

	public void setHasChildrenFlag(Boolean hasChildrenFlag) {
		this.hasChildrenFlag = hasChildrenFlag;
	}

	public String getParentUnitNumber() {
		return parentUnitNumber;
	}

	public void setParentUnitNumber(String parentUnitNumber) {
		this.parentUnitNumber = parentUnitNumber;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("unitNumber", getUnitNumber());
		hashMap.put("hasChildrenFlag", getHasChildrenFlag());
		hashMap.put("parentUnitNumber", getParentUnitNumber());
		return hashMap;
	}

    /**
     * Gets the parentUnit attribute. 
     * @return Returns the parentUnit.
     */
    public Unit getParentUnit() {
        return parentUnit;
    }

    /**
     * Sets the parentUnit attribute value.
     * @param parentUnit The parentUnit to set.
     */
    public void setParentUnit(Unit parentUnit) {
        this.parentUnit = parentUnit;
    }
}
