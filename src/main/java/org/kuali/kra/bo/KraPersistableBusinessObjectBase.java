package org.kuali.kra.bo;

import java.sql.Timestamp;

import org.kuali.core.bo.PersistableBusinessObjectBase;

public abstract class KraPersistableBusinessObjectBase extends PersistableBusinessObjectBase {
	
	private String updateUser;
	private Timestamp updateTimestamp;
	
	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}
	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
