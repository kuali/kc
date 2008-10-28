package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class is the bo class of mailby code.
 */
public class MailBy extends KraPersistableBusinessObjectBase {
	
	private String mailByCode;
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
