package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class DocumentNextvalue extends KraPersistableBusinessObjectBase {
	private String propertyName;
	private Integer proposalNumber;
	private Integer nextValue;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Integer getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Integer proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getNextValue() {
		return nextValue;
	}

	public void setNextValue(Integer nextValue) {
		this.nextValue = nextValue;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("propertyName", getPropertyName());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("nextValue", getNextValue());
		return hashMap;
	}
}
