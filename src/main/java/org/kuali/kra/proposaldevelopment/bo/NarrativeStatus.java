package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="NARRATIVE_STATUS")
public class NarrativeStatus extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="NARRATIVE_STATUS_CODE")
	private String narrativeStatusCode;
	@Column(name="DESCRIPTION")
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

