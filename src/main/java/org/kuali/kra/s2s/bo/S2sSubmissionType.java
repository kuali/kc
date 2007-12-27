package org.kuali.kra.s2s.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class S2sSubmissionType extends KraPersistableBusinessObjectBase {
	private String s2sSubmissionTypeCode;
	private String description;

	public String getS2sSubmissionTypeCode() {
		return s2sSubmissionTypeCode;
	}

	public void setS2sSubmissionTypeCode(String s2sSubmissionTypeCode) {
		this.s2sSubmissionTypeCode = s2sSubmissionTypeCode;
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
		hashMap.put("s2sSubmissionTypeCode", getS2sSubmissionTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}
