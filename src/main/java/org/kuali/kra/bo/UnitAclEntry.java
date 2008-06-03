package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.kim.bo.KimRole;

public class UnitAclEntry extends KraPersistableBusinessObjectBase {
    
    private Long id;
    private String personId;
    private Long roleId;
	private String unitNumber;
	private Boolean subunits;
	private Boolean active;
	
	private Person person;
	private Unit unit;
	private KimRole role;

    public UnitAclEntry() {
        subunits = false;
        active = true;
    }

	public Long getId() {
        return id;
    }
	
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public String getPersonName() {
        return person.getFullName();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return role.getName();
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    
    public String getUnitName() {
        return unit.getUnitName();
    }

    public Boolean getSubunits() {
        return subunits;
    }

    public void setSubunits(Boolean subunits) {
        this.subunits = subunits;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public KimRole getRole() {
        return role;
    }

    public void setRole(KimRole role) {
        this.role = role;
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("unitNumber", getUnitNumber());
		return hashMap;
	}
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!(obj instanceof UnitAclEntry))
            return false;
        UnitAclEntry other = (UnitAclEntry) obj;
        return id.equals(other.id);
    }

}
