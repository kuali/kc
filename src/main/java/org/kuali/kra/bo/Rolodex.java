package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class Rolodex extends KraPersistableBusinessObjectBase {
	private Integer rolodexId;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String comments;
	private String countryCode;
	private String county;
	private Boolean deleteFlag;
	private String emailAddress;
	private String faxNumber;
	private String firstName;
	private String lastName;
	private String middleName;
	private String organization;
	private String ownedByUnit;
	private String phoneNumber;
	private String postalCode;
	private String prefix;
	private Boolean sponsorAddressFlag;
	private String sponsorCode;
	private String state;
	private String suffix;
	private String title;

	public Integer getRolodexId() {
		return rolodexId;
	}

	public void setRolodexId(Integer rolodexId) {
		this.rolodexId = rolodexId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOwnedByUnit() {
		return ownedByUnit;
	}

	public void setOwnedByUnit(String ownedByUnit) {
		this.ownedByUnit = ownedByUnit;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Boolean getSponsorAddressFlag() {
		return sponsorAddressFlag;
	}

	public void setSponsorAddressFlag(Boolean sponsorAddressFlag) {
		this.sponsorAddressFlag = sponsorAddressFlag;
	}

	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("rolodexId", getRolodexId());
		hashMap.put("addressLine1", getAddressLine1());
		hashMap.put("addressLine2", getAddressLine2());
		hashMap.put("addressLine3", getAddressLine3());
		hashMap.put("city", getCity());
		hashMap.put("comments", getComments());
		hashMap.put("countryCode", getCountryCode());
		hashMap.put("county", getCounty());
		hashMap.put("deleteFlag", getDeleteFlag());
		hashMap.put("emailAddress", getEmailAddress());
		hashMap.put("faxNumber", getFaxNumber());
		hashMap.put("firstName", getFirstName());
		hashMap.put("lastName", getLastName());
		hashMap.put("middleName", getMiddleName());
		hashMap.put("organization", getOrganization());
		hashMap.put("ownedByUnit", getOwnedByUnit());
		hashMap.put("phoneNumber", getPhoneNumber());
		hashMap.put("postalCode", getPostalCode());
		hashMap.put("prefix", getPrefix());
		hashMap.put("sponsorAddressFlag", getSponsorAddressFlag());
		hashMap.put("sponsorCode", getSponsorCode());
		hashMap.put("state", getState());
		hashMap.put("suffix", getSuffix());
		hashMap.put("title", getTitle());
		return hashMap;
	}
}
