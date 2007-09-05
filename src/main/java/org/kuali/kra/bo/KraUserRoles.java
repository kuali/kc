package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class KraUserRoles extends KraPersistableBusinessObjectBase {
	private Integer roleId;
	private String unitNumber;
	private String userId;
	private Boolean descendFlag;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getDescendFlag() {
		return descendFlag;
	}

	public void setDescendFlag(Boolean descendFlag) {
		this.descendFlag = descendFlag;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("roleId", getRoleId());
		hashMap.put("unitNumber", getUnitNumber());
		hashMap.put("userId", getUserId());
		hashMap.put("descendFlag", getDescendFlag());
		return hashMap;
	}
}
