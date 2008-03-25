package org.kuali.kra.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.budget.bo.BudgetCostShare;

public class UnitAdministratorType extends KraPersistableBusinessObjectBase {

	private String unitAdministratorTypeCode;
	private String description;
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
