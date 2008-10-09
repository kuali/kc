package org.kuali.kra.bo;

import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

@IdClass(org.kuali.kra.bo.id.UnitAdministratorId.class)
@Entity
@Table(name="UNIT_ADMINISTRATOR")
public class UnitAdministrator extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="PERSON_ID")
	private String personId;
	@Id
	@Column(name="UNIT_ADMINISTRATOR_TYPE_CODE")
	private String unitAdministratorTypeCode;
	@Id
	@Column(name="UNIT_NUMBER")
	private String unitNumber;
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="PERSON_ID", insertable=false, updatable=false)
	private Person person;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="UNIT_ADMINISTRATOR_TYPE_CODE", insertable=false, updatable=false)
	private UnitAdministratorType unitAdministratorType;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="UNIT_NUMBER", insertable=false, updatable=false)
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

