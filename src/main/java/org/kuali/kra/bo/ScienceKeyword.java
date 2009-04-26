package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class ScienceKeyword extends KraPersistableBusinessObjectBase {

	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7064465474079964486L;
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

	@SuppressWarnings("unchecked")
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
