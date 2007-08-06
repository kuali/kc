package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class Sponsor extends KraPersistableBusinessObjectBase {
	private String sponsorCode;
	private String acronym;
	private String auditReportSentForFy;
	private String cageNumber;
	private String countryCode;
	private String dodacNumber;
	private String dunAndBradstreetNumber;
	private String dunsPlusFourNumber;
	private String ownedByUnit;
	private String postalCode;
	private Integer rolodexId;
	private String sponsorName;
	private String sponsorTypeCode;
	private String state;
	private String createUser;
    private SponsorType sponsorType;
    private Country country;

    public Sponsor(){
        super();
        setCreateUser(getUpdateUser());
    }
	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getAuditReportSentForFy() {
		return auditReportSentForFy;
	}

	public void setAuditReportSentForFy(String auditReportSentForFy) {
		this.auditReportSentForFy = auditReportSentForFy;
	}

	public String getCageNumber() {
		return cageNumber;
	}

	public void setCageNumber(String cageNumber) {
		this.cageNumber = cageNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDodacNumber() {
		return dodacNumber;
	}

	public void setDodacNumber(String dodacNumber) {
		this.dodacNumber = dodacNumber;
	}

	public String getDunAndBradstreetNumber() {
		return dunAndBradstreetNumber;
	}

	public void setDunAndBradstreetNumber(String dunAndBradstreetNumber) {
		this.dunAndBradstreetNumber = dunAndBradstreetNumber;
	}

	public String getDunsPlusFourNumber() {
		return dunsPlusFourNumber;
	}

	public void setDunsPlusFourNumber(String dunsPlusFourNumber) {
		this.dunsPlusFourNumber = dunsPlusFourNumber;
	}

	public String getOwnedByUnit() {
		return ownedByUnit;
	}

	public void setOwnedByUnit(String ownedByUnit) {
		this.ownedByUnit = ownedByUnit;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getRolodexId() {
		return rolodexId;
	}

	public void setRolodexId(Integer rolodexId) {
		this.rolodexId = rolodexId;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getSponsorTypeCode() {
		return sponsorTypeCode;
	}

	public void setSponsorTypeCode(String sponsorTypeCode) {
		this.sponsorTypeCode = sponsorTypeCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("sponsorCode", getSponsorCode());
		hashMap.put("acronym", getAcronym());
		hashMap.put("auditReportSentForFy", getAuditReportSentForFy());
		hashMap.put("cageNumber", getCageNumber());
		hashMap.put("countryCode", getCountryCode());
		hashMap.put("dodacNumber", getDodacNumber());
		hashMap.put("dunAndBradstreetNumber", getDunAndBradstreetNumber());
		hashMap.put("dunsPlusFourNumber", getDunsPlusFourNumber());
		hashMap.put("ownedByUnit", getOwnedByUnit());
		hashMap.put("postalCode", getPostalCode());
		hashMap.put("rolodexId", getRolodexId());
		hashMap.put("sponsorName", getSponsorName());
		hashMap.put("sponsorTypeCode", getSponsorTypeCode());
		hashMap.put("state", getState());
		return hashMap;
	}

    public SponsorType getSponsorType() {
        return sponsorType;
    }

    public void setSponsorType(SponsorType sponsorType) {
        this.sponsorType = sponsorType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    /**
     * Gets the country attribute. 
     * @return Returns the country.
     */
    public Country getCountry() {
        return country;
    }
    /**
     * Sets the country attribute value.
     * @param country The country to set.
     */
    public void setCountry(Country country) {
        this.country = country;
    }
}
