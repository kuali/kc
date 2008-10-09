package org.kuali.kra.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

@Entity
@Table(name="SPONSOR_TYPE")
public class SponsorType extends KraPersistableBusinessObjectBase {
	
	@Id
	@Column(name="SPONSOR_TYPE_CODE")
	private String sponsorTypeCode;
	@Column(name="DESCRIPTION")
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

