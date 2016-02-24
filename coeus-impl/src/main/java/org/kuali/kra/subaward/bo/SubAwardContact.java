/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.subaward.bo;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.kra.award.home.ContactType;

public class SubAwardContact extends SubAwardAssociate { 

    private static final long serialVersionUID = 1L;

    private Integer subAwardContactId;
    private Long subAwardId; 
    private String subAwardCode;
    private String contactTypeCode;
    private Integer rolodexId;


    private ContactType contactType;

    private Rolodex rolodex;


    private String rolodexName;

    private String sponsorCode;

    private String organization;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String city;

    private String county;

    private String state;

    private String postalCode;

    private String countryCode;

    private String phoneNumber;

    private String emailAddress;

    private String faxNumber;

    private String comments;


    public SubAwardContact() {
    }

    /**
     * 
     * Constructs a SubAwardContact.java.
     * @param rolodex
     */
    public SubAwardContact(Rolodex rolodex) {
        this();
        this.rolodex = rolodex;
    }



    /**.
	 * This is the Getter Method for subAwardContactId
	 * @return Returns the subAwardContactId.
	 */
	public Integer getSubAwardContactId() {
		return subAwardContactId;
	}

	/**.
	 * This is the Setter Method for subAwardContactId
	 * @param subAwardContactId The subAwardContactId to set.
	 */
	public void setSubAwardContactId(Integer subAwardContactId) {
		this.subAwardContactId = subAwardContactId;
	}

	/**.
	 * This is the Getter Method for subAwardId
	 * @return Returns the subAwardId.
	 */
	public Long getSubAwardId() {
		return subAwardId;
	}

	/**.
	 * This is the Setter Method for subAwardId
	 * @param subAwardId The subAwardId to set.
	 */
	public void setSubAwardId(Long subAwardId) {
		this.subAwardId = subAwardId;
	}

	/**.
	 * This is the Getter Method for subAwardCode
	 * @return Returns the subAwardCode.
	 */
	public String getSubAwardCode() {
		return subAwardCode;
	}

	/**.
	 * This is the Setter Method for subAwardCode
	 * @param subAwardCode The subAwardCode to set.
	 */
	public void setSubAwardCode(String subAwardCode) {
		this.subAwardCode = subAwardCode;
	}

	/**.
	 * This is the Getter Method for contactTypeCode
	 * @return Returns the contactTypeCode.
	 */
	public String getContactTypeCode() {
		return contactTypeCode;
	}

	/**.
	 * This is the Setter Method for contactTypeCode
	 * @param contactTypeCode The contactTypeCode to set.
	 */
	public void setContactTypeCode(String contactTypeCode) {
		this.contactTypeCode = contactTypeCode;
	}

	/**.
	 * This is the Getter Method for rolodexId
	 * @return Returns the rolodexId.
	 */
	public Integer getRolodexId() {
		return rolodexId;
	}

	/**.
	 * This is the Setter Method for rolodexId
	 * @param rolodexId The rolodexId to set.
	 */
	public void setRolodexId(Integer rolodexId) {
		this.rolodexId = rolodexId;
	}

	/**.
	 * This is the Getter Method for contactType
	 * @return Returns the contactType.
	 */
	public ContactType getContactType() {
		return contactType;
	}

	/**.
	 * This is the Setter Method for contactType
	 * @param contactType The contactType to set.
	 */
	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	/**.
	 * This is the Getter Method for rolodex
	 * @return Returns the rolodex.
	 */
    public Rolodex getRolodex() {
        if (rolodex == null || !ObjectUtils.equals(rolodex.getRolodexId(), rolodexId)
                && rolodexId != null) {
            refreshRolodex();
        }
        return rolodex;
    }

	/**.
	 * This is the Setter Method for rolodex
	 * @param rolodex The rolodex to set.
	 */
	public void setRolodex(Rolodex rolodex) {
		this.rolodex = rolodex;
	}

	/**.
	 * This is the Getter Method for rolodexName
	 * @return Returns the rolodexName.
	 */
	public String getRolodexName() {
		return rolodexName;
	}

	/**.
	 * This is the Setter Method for rolodexName
	 * @param rolodexName The rolodexName to set.
	 */
	public void setRolodexName(String rolodexName) {
		this.rolodexName = rolodexName;
	}

	/**.
	 * This is the Getter Method for sponsorCode
	 * @return Returns the sponsorCode.
	 */
	public String getSponsorCode() {
		return sponsorCode;
	}

	/**.
	 * This is the Setter Method for sponsorCode
	 * @param sponsorCode The sponsorCode to set.
	 */
	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	/**.
	 * This is the Getter Method for organization
	 * @return Returns the organization.
	 */
	public String getOrganization() {
		return organization;
	}

	/**.
	 * This is the Setter Method for organization
	 * @param organization The organization to set.
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**.
	 * This is the Getter Method for addressLine1
	 * @return Returns the addressLine1.
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**.
	 * This is the Setter Method for addressLine1
	 * @param addressLine1 The addressLine1 to set.
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**.
	 * This is the Getter Method for addressLine2
	 * @return Returns the addressLine2.
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**.
	 * This is the Setter Method for addressLine2
	 * @param addressLine2 The addressLine2 to set.
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**.
	 * This is the Getter Method for addressLine3
	 * @return Returns the addressLine3.
	 */
	public String getAddressLine3() {
		return addressLine3;
	}

	/**.
	 * This is the Setter Method for addressLine3
	 * @param addressLine3 The addressLine3 to set.
	 */
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	/**.
	 * This is the Getter Method for city
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**.
	 * This is the Setter Method for city
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**.
	 * This is the Getter Method for county
	 * @return Returns the county.
	 */
	public String getCounty() {
		return county;
	}

	/**.
	 * This is the Setter Method for county
	 * @param county The county to set.
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**.
	 * This is the Getter Method for state
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**.
	 * This is the Setter Method for state
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**.
	 * This is the Getter Method for postalCode
	 * @return Returns the postalCode.
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**.
	 * This is the Setter Method for postalCode
	 * @param postalCode The postalCode to set.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**.
	 * This is the Getter Method for countryCode
	 * @return Returns the countryCode.
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**.
	 * This is the Setter Method for countryCode
	 * @param countryCode The countryCode to set.
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**.
	 * This is the Getter Method for phoneNumber
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**.
	 * This is the Setter Method for phoneNumber
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**.
	 * This is the Getter Method for emailAddress
	 * @return Returns the emailAddress.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**.
	 * This is the Setter Method for emailAddress
	 * @param emailAddress The emailAddress to set.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**.
	 * This is the Getter Method for faxNumber
	 * @return Returns the faxNumber.
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**.
	 * This is the Setter Method for faxNumber
	 * @param faxNumber The faxNumber to set.
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**.
	 * This is the Getter Method for comments
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**.
	 * This is the Setter Method for comments
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
    public void resetPersistenceState() {

            this.subAwardContactId = null;
    }
    
    /*
     * Refreshes the rolodex by referencing its name in case id is null
     */
    protected void refreshRolodex() {
        this.refreshReferenceObject("rolodex");
    
    }
    
}
