package org.kuali.kra.external.frequency;

import java.io.Serializable;

public class FrequencyDto implements Serializable {

    private static final long serialVersionUID = -115792438972874644L;

    private String frequencyCode;
	private String description;
	
	public String getFrequencyCode() {
		return frequencyCode;
	}
	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
