package org.kuali.kra.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Role extends KraPersistableBusinessObjectBase {
	private Integer roleId;
	private boolean descendFlag;
	private String description;
	private String ownedByUnit;
	private String roleName;
	private String roleType;
	private String statusFlag;
    
    private List<RoleRight> roleRights = new ArrayList();

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public boolean getDescendFlag() {
		return descendFlag;
	}

	public void setDescendFlag(boolean descendFlag) {
		this.descendFlag = descendFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwnedByUnit() {
		return ownedByUnit;
	}

	public void setOwnedByUnit(String ownedByUnit) {
		this.ownedByUnit = ownedByUnit;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("roleId", getRoleId());
		hashMap.put("descendFlag", getDescendFlag());
		hashMap.put("description", getDescription());
		hashMap.put("ownedByUnit", getOwnedByUnit());
		hashMap.put("roleName", getRoleName());
		hashMap.put("roleType", getRoleType());
		hashMap.put("statusFlag", getStatusFlag());
		return hashMap;
	}

    /**
     * Gets the roleRight attribute. 
     * @return Returns the roleRight.
     */
    public List<RoleRight> getRoleRights() {
        return roleRights;
    }

    /**
     * Sets the roleRight attribute value.
     * @param roleRight The roleRight to set.
     */
    public void setRoleRights(List<RoleRight> roleRights) {
        this.roleRights = roleRights;
    }
}
