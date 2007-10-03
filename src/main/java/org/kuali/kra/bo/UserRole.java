package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class UserRole extends KraPersistableBusinessObjectBase {
	private Integer roleId;
	private String unitNumber;
	private String userId;
	private boolean descendFlag;
    
    private Role role;
    private Unit unit;
    private Person user;

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

	public boolean getDescendFlag() {
		return descendFlag;
	}

	public void setDescendFlag(boolean descendFlag) {
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

    /**
     * Gets the role attribute. 
     * @return Returns the role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role attribute value.
     * @param role The role to set.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the unit attribute. 
     * @return Returns the unit.
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the unit attribute value.
     * @param unit The unit to set.
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Gets the user attribute. 
     * @return Returns the user.
     */
    public Person getUser() {
        return user;
    }

    /**
     * Sets the user attribute value.
     * @param user The user to set.
     */
    public void setUser(Person user) {
        this.user = user;
    }
}
