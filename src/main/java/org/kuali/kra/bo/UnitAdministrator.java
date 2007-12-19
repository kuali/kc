package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class UnitAdministrator extends KraPersistableBusinessObjectBase {

	private String personId;
	private Integer roleId;
	private String unitNumber;
    private Person person;
    private Role role;
    private Unit unit;

	public UnitAdministrator(){
		super();
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("personId", getPersonId());
		hashMap.put("roleId", getRoleId());
		hashMap.put("unitNumber", getUnitNumber());
		return hashMap;
	}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
