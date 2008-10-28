package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ScienceKeyword extends KraPersistableBusinessObjectBase {

	private String scienceKeywordCode;
	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getScienceKeywordCode() {
		return scienceKeywordCode;
	}
	public void setScienceKeywordCode(String scienceCode) {
		this.scienceKeywordCode = scienceCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("scienceKeywordCode", this.getScienceKeywordCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}
