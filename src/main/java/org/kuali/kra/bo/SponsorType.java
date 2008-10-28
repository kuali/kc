package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class SponsorType extends KraPersistableBusinessObjectBase {
	
	private String sponsorTypeCode;
	private String description;
	
	/**
     * Gets the sponsorTypeCode attribute. 
     * @return Returns the sponsorTypeCode.
     */
    public String getSponsorTypeCode() {
        return sponsorTypeCode;
    }
    /**
     * Sets the sponsorTypeCode attribute value.
     * @param sponsorTypeCode The sponsorTypeCode to set.
     */
    public void setSponsorTypeCode(String sponsorTypeCode) {
        this.sponsorTypeCode = sponsorTypeCode;
    }
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap lkupMap = new LinkedHashMap();
		lkupMap.put("sponsorTypeCode", getSponsorTypeCode());
		lkupMap.put("description", getDescription());
		return lkupMap;
	}
}
