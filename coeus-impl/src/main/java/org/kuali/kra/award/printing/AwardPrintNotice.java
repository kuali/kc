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
package org.kuali.kra.award.printing;

import org.kuali.rice.krad.bo.BusinessObjectBase;

public class AwardPrintNotice extends BusinessObjectBase {

	private Boolean terms;
	private Boolean specialReview;
	private Boolean equipment;
	private Boolean foreignTravel;
	private Boolean subAward;
	private Boolean costShare;
	private Boolean faRates;
	private Boolean benefitsRates;
	private Boolean flowThru;
	private Boolean comments;
	private Boolean fundingSummary;
	private Boolean hierarchy;
	private Boolean technicalReports;
	private Boolean reports;
	private Boolean payment;
	private Boolean closeout;
	private Boolean sponsorContacts;
	private Boolean otherData;
	private Boolean keywords;
	private Boolean requireSignature;

	public AwardPrintNotice() {
		setDefaults();
	}

	public void refresh() {
		// do nothing
	}

	public void setDefaults() {
		terms = true;
		specialReview = true;
		equipment = true;
		foreignTravel = true;
		subAward = true;
		costShare = true;
		faRates = true;
		benefitsRates = true;
		flowThru = true;
		comments = true;
		technicalReports = true;
		reports = true;
		payment = true;
		closeout = true;
		sponsorContacts = true;
		otherData = true;
		keywords = true;
		fundingSummary = false;
		hierarchy = false;
		requireSignature = false;
	}

	/**
	 * Selects all items except requireSignature
	 */
	public void selectAllItems() {
		setAllItems(true);
	}

	/**
	 * Deselects all items except requireSignature
	 */
	public void deselectAllItems() {
		setAllItems(false);
	}

	/**
	 * Sets all items, except requireSignature for the select all/none button
	 */
	private void setAllItems(Boolean value) {
		terms = true;
		specialReview = true;
		equipment = true;
		foreignTravel = true;
		subAward = true;
		costShare = true;
		faRates = true;
		benefitsRates = true;
		flowThru = true;
		comments = true;
		fundingSummary = true;
		hierarchy = true;
		technicalReports = true;
		reports = true;
		payment = true;
		closeout = true;
		sponsorContacts = true;
		otherData = true;
		keywords = true;
	}

	public Boolean getCloseout() {
		return closeout;
	}

	public void setCloseout(Boolean closeout) {
		this.closeout = closeout;
	}

	public Boolean getCostShare() {
		return costShare;
	}

	public void setCostShare(Boolean costShare) {
		this.costShare = costShare;
	}

	public Boolean getEquipment() {
		return equipment;
	}

	public void setEquipment(Boolean equipment) {
		this.equipment = equipment;
	}

	public Boolean getFlowThru() {
		return flowThru;
	}

	public void setFlowThru(Boolean flowThru) {
		this.flowThru = flowThru;
	}

	public Boolean getForeignTravel() {
		return foreignTravel;
	}

	public void setForeignTravel(Boolean foreignTravel) {
		this.foreignTravel = foreignTravel;
	}

	public Boolean getFundingSummary() {
		return fundingSummary;
	}

	public void setFundingSummary(Boolean fundingSummary) {
		this.fundingSummary = fundingSummary;
	}

	public Boolean getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Boolean hierarchy) {
		this.hierarchy = hierarchy;
	}

	public Boolean getKeywords() {
		return keywords;
	}

	public void setKeywords(Boolean keywords) {
		this.keywords = keywords;
	}

	public Boolean getOtherData() {
		return otherData;
	}

	public void setOtherData(Boolean otherData) {
		this.otherData = otherData;
	}

	public Boolean getPayment() {
		return payment;
	}

	public void setPayment(Boolean payment) {
		this.payment = payment;
	}

	public Boolean getSpecialReview() {
		return specialReview;
	}

	public void setSpecialReview(Boolean specialReview) {
		this.specialReview = specialReview;
	}

	public Boolean getSubAward() {
		return subAward;
	}

	public void setSubAward(Boolean subAward) {
		this.subAward = subAward;
	}

	public Boolean getTerms() {
		return terms;
	}

	public void setTerms(Boolean terms) {
		this.terms = terms;
	}

	public Boolean getRequireSignature() {
		return requireSignature;
	}

	public void setRequireSignature(Boolean requireSignature) {
		this.requireSignature = requireSignature;
	}

	public Boolean getFaRates() {
		return faRates;
	}

	public void setFaRates(Boolean faRates) {
		this.faRates = faRates;
	}

	public Boolean getBenefitsRates() {
		return benefitsRates;
	}

	public void setBenefitsRates(Boolean benefitsRates) {
		this.benefitsRates = benefitsRates;
	}

	public Boolean getTechnicalReports() {
		return technicalReports;
	}

	public void setTechnicalReports(Boolean technicalReports) {
		this.technicalReports = technicalReports;
	}

	public Boolean getReports() {
		return reports;
	}

	public void setReports(Boolean reports) {
		this.reports = reports;
	}

	public Boolean getSponsorContacts() {
		return sponsorContacts;
	}

	public void setSponsorContacts(Boolean sponsorContacts) {
		this.sponsorContacts = sponsorContacts;
	}

	public Boolean getComments() {
		return comments;
	}

	public void setComments(Boolean comments) {
		this.comments = comments;
	}

}
