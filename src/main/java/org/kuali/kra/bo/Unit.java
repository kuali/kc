package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class Unit extends KraPersistableBusinessObjectBase {
	private String unitNumber;
	private String administrativeOfficer;
	private String deanVp;
	private String organizationId;
	private String ospAdministrator;
	private String otherIndividualToNotify;
	private String unitHead;
	private String unitName;
    
    private UnitHierarchy unitHierarchy;
    private Organization organization;

    public Unit() {
        super();
    }

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getAdministrativeOfficer() {
		return administrativeOfficer;
	}

	public void setAdministrativeOfficer(String administrativeOfficer) {
		this.administrativeOfficer = administrativeOfficer;
	}

	public String getDeanVp() {
		return deanVp;
	}

	public void setDeanVp(String deanVp) {
		this.deanVp = deanVp;
	}

	public String getOrganizationId() {
        if (organizationId == null && this.getUnitHierarchy() != null && this.getUnitHierarchy().getParentUnit() != null) {
            //will recurse up hierarchy until an Organization Id is found
            return this.getUnitHierarchy().getParentUnit().getOrganizationId();
        }
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOspAdministrator() {
		return ospAdministrator;
	}

	public void setOspAdministrator(String ospAdministrator) {
		this.ospAdministrator = ospAdministrator;
	}

	public String getOtherIndividualToNotify() {
		return otherIndividualToNotify;
	}

	public void setOtherIndividualToNotify(String otherIndividualToNotify) {
		this.otherIndividualToNotify = otherIndividualToNotify;
	}

	public String getUnitHead() {
		return unitHead;
	}

	public void setUnitHead(String unitHead) {
		this.unitHead = unitHead;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("unitNumber", getUnitNumber());
		hashMap.put("administrativeOfficer", getAdministrativeOfficer());
		hashMap.put("deanVp", getDeanVp());
		hashMap.put("organizationId", getOrganizationId());
		hashMap.put("ospAdministrator", getOspAdministrator());
		hashMap.put("otherIndividualToNotify", getOtherIndividualToNotify());
		hashMap.put("unitHead", getUnitHead());
		hashMap.put("unitName", getUnitName());
		return hashMap;
	}

    /**
     * Gets the unitHierarchy attribute. 
     * @return Returns the unitHierarchy.
     */
    public UnitHierarchy getUnitHierarchy() {
        return unitHierarchy;
    }

    /**
     * Sets the unitHierarchy attribute value.
     * @param unitHierarchy The unitHierarchy to set.
     */
    public void setUnitHierarchy(UnitHierarchy unitHierarchy) {
        this.unitHierarchy = unitHierarchy;
    }

    /**
     * Gets the organization attribute. 
     * @return Returns the organization.
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Sets the organization attribute value.
     * @param organization The organization to set.
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
