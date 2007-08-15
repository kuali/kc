package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class S2sRevisionType extends KraPersistableBusinessObjectBase {
	private String s2sRevisionTypeCode;
	private String description;

	public String getS2sRevisionTypeCode() {
		return s2sRevisionTypeCode;
	}

	public void setS2sRevisionTypeCode(String s2sRevisionTypeCode) {
		this.s2sRevisionTypeCode = s2sRevisionTypeCode;
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
		hashMap.put("s2sRevisionTypeCode", getS2sRevisionTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}
