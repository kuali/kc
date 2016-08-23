package org.kuali.kra.subaward.fdp;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

public class SubAwardModificationType extends KcPersistableBusinessObjectBase implements MutableInactivatable {

	private String code;
	private String description;
	private boolean active;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean isActive() {
		return active;
	}
	@Override
	public void setActive(boolean active) {
		this.active = active;
	}
}
