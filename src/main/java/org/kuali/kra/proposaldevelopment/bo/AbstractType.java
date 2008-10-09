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
@Table(name="ABSTRACT_TYPE")
public class AbstractType extends KraPersistableBusinessObjectBase {
	
	@Id
	@Column(name="ABSTRACT_TYPE_CODE")
	private String abstractTypeCode;
	@Column(name="DESCRIPTION")
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

