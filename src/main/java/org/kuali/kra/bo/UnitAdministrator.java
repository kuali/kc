package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class UnitAdministrator extends KraPersistableBusinessObjectBase {

	private String personId;
	private String unitAdministratorTypeCode;
	private String unitNumber;
    private Person person;
    private UnitAdministratorType unitAdministratorType;
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

	public String getUnitAdministratorTypeCode() {
		return unitAdministratorTypeCode;
	}

	public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
		this.unitAdministratorTypeCode = unitAdministratorTypeCode;
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
		hashMap.put("unitAdministratorTypeCode", getUnitAdministratorTypeCode());
		hashMap.put("unitNumber", getUnitNumber());
		return hashMap;
	}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public UnitAdministratorType getUnitAdministratorType() {
        return unitAdministratorType;
    }

    public void setUnitAdministratorType(UnitAdministratorType unitAdministratorType) {
        this.unitAdministratorType = unitAdministratorType;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
