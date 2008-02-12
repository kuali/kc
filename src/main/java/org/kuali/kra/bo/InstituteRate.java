package org.kuali.kra.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.budget.BudgetDecimal;

public class InstituteRate extends InstituteLaRate {
	private String activityTypeCode;

	public String getActivityTypeCode() {
		return activityTypeCode;
	}

	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("activityTypeCode", getActivityTypeCode());
		return hashMap;
	}
}
