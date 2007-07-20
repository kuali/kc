package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class NarrativeType extends KraPersistableBusinessObjectBase {
	
	private Integer narrativeTypeCode;
	private String description;
	private String narrativeTypeGroup;
	private String allowMultiple;
	private String systemGenerated;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNarrativeTypeGroup() {
		return narrativeTypeGroup;
	}
	public void setNarrativeTypeGroup(String narrativeTypeGroup) {
		this.narrativeTypeGroup = narrativeTypeGroup;
	}
	public Integer getNarrativeTypeCode() {
		return narrativeTypeCode;
	}
	public void setNarrativeTypeCode(Integer narrativeTypeCode) {
		this.narrativeTypeCode = narrativeTypeCode;
	}

	public String getAllowMultiple() {
		return allowMultiple;
	}
	public void setAllowMultiple(String allowMultiple) {
		this.allowMultiple = allowMultiple;
	}
	public String getSystemGenerated() {
		return systemGenerated;
	}
	public void setSystemGenerated(String systemGenerated) {
		this.systemGenerated = systemGenerated;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("narrativeTypeCode", this.narrativeTypeCode);
		propMap.put("description", this.description);
		return propMap;
	}
}
