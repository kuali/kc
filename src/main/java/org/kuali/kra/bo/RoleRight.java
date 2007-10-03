package org.kuali.kra.bo;

import java.util.LinkedHashMap;


public class RoleRight extends KraPersistableBusinessObjectBase {
	private String rightId;
	private Integer roleId;
	private boolean descendFlag;
    
    private Right right;
    private Role role;

	public String getRightId() {
		return rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

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


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("rightId", getRightId());
		hashMap.put("roleId", getRoleId());
		hashMap.put("descendFlag", getDescendFlag());
		return hashMap;
	}

    /**
     * Gets the right attribute. 
     * @return Returns the right.
     */
    public Right getRight() {
        return right;
    }

    /**
     * Sets the right attribute value.
     * @param right The right to set.
     */
    public void setRight(Right right) {
        this.right = right;
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
}
