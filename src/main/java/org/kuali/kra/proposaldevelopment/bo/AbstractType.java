package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AbstractType extends KraPersistableBusinessObjectBase {
	
	private String abstractTypeCode;
	private String description;
	
	public String getAbstractTypeCode() {
		return abstractTypeCode;
	}
	public void setAbstractTypeCode(String abstractTypeCode) {
		this.abstractTypeCode = abstractTypeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("abstractTypeCode", this.getAbstractTypeCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}
