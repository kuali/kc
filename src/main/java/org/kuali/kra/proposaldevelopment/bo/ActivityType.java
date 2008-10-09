package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="ACTIVITY_TYPE")
public class ActivityType extends KraPersistableBusinessObjectBase {
	
	@Id
	@Column(name="ACTIVITY_TYPE_CODE")
	private String activityTypeCode;
	@Column(name="DESCRIPTION")
	private String description;
	
	public String getActivityTypeCode() {
		return activityTypeCode;
	}
	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
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
		propMap.put("activityTypeCode", this.getActivityTypeCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}

