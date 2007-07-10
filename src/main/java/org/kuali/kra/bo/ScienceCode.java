package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class ScienceCode extends KraPersistableBusinessObjectBase {
	
	private String scienceCode;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getScienceCode() {
		return scienceCode;
	}
	public void setScienceCode(String scienceCode) {
		this.scienceCode = scienceCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("scienceCode", this.scienceCode);
		propMap.put("description", this.description);
		return propMap;
	}
}
