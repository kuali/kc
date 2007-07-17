package org.kuali.kra.bo;

import java.util.LinkedHashMap;


public class NsfCode extends KraPersistableBusinessObjectBase {
	
    private Integer nsfSequenceNumber;
	private String nsfCode;
	private String description;
	
    public Integer getNsfSequenceNumber() {
        return nsfSequenceNumber;
    }
    public void setNsfSequenceNumber(Integer nsfSequenceNumber) {
        this.nsfSequenceNumber = nsfSequenceNumber;
    }
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNsfCode() {
		return nsfCode;
	}
	public void setNsfCode(String nsfCode) {
		this.nsfCode = nsfCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("nsfSequenceNumber", this.getNsfSequenceNumber());
		propMap.put("nsfCode", this.getNsfCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}
