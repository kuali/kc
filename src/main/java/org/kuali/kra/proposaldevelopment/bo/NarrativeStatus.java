package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class NarrativeStatus extends KraPersistableBusinessObjectBase {
	private String narrativeStatusCode;
	private String description;

	public String getNarrativeStatusCode() {
		return narrativeStatusCode;
	}

	public void setNarrativeStatusCode(String narrativeStatusCode) {
		this.narrativeStatusCode = narrativeStatusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("narrativeStatusCode", getNarrativeStatusCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
	/**
	 * Determine if two NarrativeStatuses have the same values.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
	    if (obj == this) return true;
	    if (obj instanceof NarrativeStatus) {
	        NarrativeStatus other = (NarrativeStatus) obj;
	        return StringUtils.equals(this.narrativeStatusCode, other.narrativeStatusCode) &&
	               StringUtils.equals(this.description, other.description);
	    }
	    return false;
	}
}
