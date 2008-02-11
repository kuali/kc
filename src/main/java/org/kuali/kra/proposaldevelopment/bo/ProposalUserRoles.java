package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.RoleRight;

public class ProposalUserRoles extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private Integer roleId;
	private String userId;
	private List<RoleRight> roleRightsList;

	public ProposalUserRoles(){
	    roleRightsList = new ArrayList<RoleRight>();
	}
	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("roleId", getRoleId());
		hashMap.put("userId", getUserId());
		return hashMap;
	}

    public List<RoleRight> getRoleRightsList() {
        return roleRightsList;
    }

    public void setRoleRightsList(List<RoleRight> roleRightsList) {
        this.roleRightsList = roleRightsList;
    }
}
