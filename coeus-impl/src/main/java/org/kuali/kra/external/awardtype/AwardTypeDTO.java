package org.kuali.kra.external.awardtype;

import java.io.Serializable;

public class AwardTypeDTO implements Serializable {

	private static final long serialVersionUID = 1728984743819678819L;
	
	private Integer awardTypeCode;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAwardTypeCode() {
		return awardTypeCode;
	}
	public void setAwardTypeCode(Integer awardTypeCode) {
		this.awardTypeCode = awardTypeCode;
	}

}
