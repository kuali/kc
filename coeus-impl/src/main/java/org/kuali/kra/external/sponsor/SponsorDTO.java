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
package org.kuali.kra.external.sponsor;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sponsorDTO", propOrder = {
    "sponsorCode",
    "acronym",
    "sponsorName",
    "sponsorTypeCode",
    "customerTypeCode",
    "customerNumber",
    "sponsorTypeDescription",
    "cageNumber",
    "dodacNumber",
    "dunAndBradstreetNumber",
    "dunsPlusFourNumber",
    "state",
    "stateAgency",
    "active",
    "contactInformation",
    "dunningCampaignId"
})
public class SponsorDTO implements Serializable {

	private static final long serialVersionUID = -1928119075816128754L;

	private String sponsorCode;
	private String acronym;
	private String sponsorName;
	private String sponsorTypeCode;
	private String customerTypeCode;
	private String customerNumber;
	private String sponsorTypeDescription;
	private String cageNumber;
    private String dodacNumber;
    private String dunAndBradstreetNumber;
    private String dunsPlusFourNumber;
    private String state;
    private boolean stateAgency;
    private String dunningCampaignId;
    private boolean active;

    private RolodexDTO contactInformation;

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

	public String getSponsorTypeDescription() {
		return sponsorTypeDescription;
	}

	public void setSponsorTypeDescription(String sponsorTypeDescription) {
		this.sponsorTypeDescription = sponsorTypeDescription;
	}

	public String getCageNumber() {
		return cageNumber;
	}

	public void setCageNumber(String cageNumber) {
		this.cageNumber = cageNumber;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public RolodexDTO getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(RolodexDTO contactInformation) {
		this.contactInformation = contactInformation;
	}

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

	public boolean isStateAgency() {
		return stateAgency;
	}

	public void setStateAgency(boolean stateAgency) {
		this.stateAgency = stateAgency;
	}

	public String getCustomerTypeCode() {
		return customerTypeCode;
	}

	public void setCustomerTypeCode(String customerTypeCode) {
		this.customerTypeCode = customerTypeCode;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getDunningCampaignId() {
		return dunningCampaignId;
	}

	public void setDunningCampaignId(String dunningCampaignId) {
		this.dunningCampaignId = dunningCampaignId;
	}
}
