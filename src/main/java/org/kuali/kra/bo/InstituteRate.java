package org.kuali.kra.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.budget.BudgetDecimal;

public class InstituteRate extends InstituteLaRate {
	private String activityTypeCode;
    
    private Unit unit;

	public String getActivityTypeCode() {
		return activityTypeCode;
	}

	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}
    
	public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("activityTypeCode", getActivityTypeCode());
		return hashMap;
	}
}
