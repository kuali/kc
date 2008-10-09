package org.kuali.kra.bo;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.budget.bo.BudgetCostShare;

@Entity
@Table(name="UNIT_ADMINISTRATOR_TYPE")
public class UnitAdministratorType extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="UNIT_ADMINISTRATOR_TYPE_CODE")
	private String unitAdministratorTypeCode;
	@Column(name="DESCRIPTION")
	private String description;
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.bo.UnitAdministrator.class, mappedBy="unitAdministratorType")
	private List<UnitAdministrator> unitAdministrators;

	public UnitAdministratorType(){
		super();
		unitAdministrators = new ArrayList<UnitAdministrator>();

	}

	public String getUnitAdministratorTypeCode() {
		return unitAdministratorTypeCode;
	}

	public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
		this.unitAdministratorTypeCode = unitAdministratorTypeCode;
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
		hashMap.put("unitAdministratorTypeCode", getUnitAdministratorTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}

    public List<UnitAdministrator> getUnitAdministrators() {
        return unitAdministrators;
    }

    public void setUnitAdministrators(List<UnitAdministrator> unitAdministrators) {
        this.unitAdministrators = unitAdministrators;
    }
}

