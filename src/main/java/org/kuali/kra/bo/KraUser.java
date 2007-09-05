package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class KraUser extends KraPersistableBusinessObjectBase {
	private String userId;
	private Boolean nonMitPersonFlag;
	private String personId;
	private Boolean status;
	private String unitNumber;
	private String userName;
	private Boolean userType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getNonMitPersonFlag() {
		return nonMitPersonFlag;
	}

	public void setNonMitPersonFlag(Boolean nonMitPersonFlag) {
		this.nonMitPersonFlag = nonMitPersonFlag;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getUserType() {
		return userType;
	}

	public void setUserType(Boolean userType) {
		this.userType = userType;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("userId", getUserId());
		hashMap.put("nonMitPersonFlag", getNonMitPersonFlag());
		hashMap.put("personId", getPersonId());
		hashMap.put("status", getStatus());
		hashMap.put("unitNumber", getUnitNumber());
		hashMap.put("userName", getUserName());
		hashMap.put("userType", getUserType());
		return hashMap;
	}
}
