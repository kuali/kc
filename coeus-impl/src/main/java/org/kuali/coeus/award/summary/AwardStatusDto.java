package org.kuali.coeus.award.summary;

import com.codiform.moo.annotation.Property;

public class AwardStatusDto {

	@Property(source="statusCode")
	private String code;
	private String description;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
