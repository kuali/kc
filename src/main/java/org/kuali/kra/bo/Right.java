package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class Right extends KraPersistableBusinessObjectBase {
	private String rightId;
	private Boolean descendFlag;
	private String description;
	private Boolean rightType;

	public String getRightId() {
		return rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

	public Boolean getDescendFlag() {
		return descendFlag;
	}

	public void setDescendFlag(Boolean descendFlag) {
		this.descendFlag = descendFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRightType() {
		return rightType;
	}

	public void setRightType(Boolean rightType) {
		this.rightType = rightType;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("rightId", getRightId());
		hashMap.put("descendFlag", getDescendFlag());
		hashMap.put("description", getDescription());
		hashMap.put("rightType", getRightType());
		return hashMap;
	}
}
