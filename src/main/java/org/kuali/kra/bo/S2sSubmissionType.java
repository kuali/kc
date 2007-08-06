package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class S2sSubmissionType extends KraPersistableBusinessObjectBase {
	
	private String s2sSubmissionTypeCode;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getS2sSubmissionTypeCode() {
		return s2sSubmissionTypeCode;
	}
	public void setS2sSubmissionTypeCode(String s2sSubmissionTypeCode) {
		this.s2sSubmissionTypeCode = s2sSubmissionTypeCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("s2sSubmissionTypeCode", getS2sSubmissionTypeCode());
		propMap.put("description", getDescription());
		return propMap;
	}
}
