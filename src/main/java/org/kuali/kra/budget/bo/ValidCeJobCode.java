package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidCeJobCode extends KraPersistableBusinessObjectBase {
	private String costElement;
	private String jobCode;

	public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("costElement", getCostElement());
		hashMap.put("jobCode", getJobCode());
		return hashMap;
	}
}
