package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class is the bo class of mailby code.
 */
@Entity
@Table(name="MAIL_BY")
public class MailBy extends KraPersistableBusinessObjectBase {
	
	@Id
	@Column(name="MAIL_BY_CODE")
	private String mailByCode;
	@Column(name="DESCRIPTION")
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMailByCode() {
		return mailByCode;
	}
	public void setMailByCode(String mailByCode) {
		this.mailByCode = mailByCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("mailByCode", this.getMailByCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}

