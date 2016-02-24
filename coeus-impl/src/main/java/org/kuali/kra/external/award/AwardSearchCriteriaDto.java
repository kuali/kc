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
package org.kuali.kra.external.award;

public class AwardSearchCriteriaDto {

	private String awardId;
	private String awardNumber;
	private String accountNumber;
	private String chartOfAccounts;
	private String principalInvestigatorId;
	private String sponsorCode;
	private String startDate;
	private String startDateLowerBound;
	private String endDate;
	private String endDateLowerBound;
	private String billingFrequency;
	private String awardTotal;

	public String getAwardId() {
		return awardId;
	}
	public void setAwardId(String awardId) {
		this.awardId = awardId;
	}
	public String getAwardNumber() {
		return awardNumber;
	}
	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getChartOfAccounts() {
		return chartOfAccounts;
	}
	public void setChartOfAccounts(String chartOfAccounts) {
		this.chartOfAccounts = chartOfAccounts;
	}
    public String getPrincipalInvestigatorId() {
        return principalInvestigatorId;
    }
    public void setPrincipalInvestigatorId(String principalInvestigatorId) {
        this.principalInvestigatorId = principalInvestigatorId;
    }
	public String getSponsorCode() {
		return sponsorCode;
	}
	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBillingFrequency() {
		return billingFrequency;
	}
	public void setBillingFrequency(String billingFrequency) {
		this.billingFrequency = billingFrequency;
	}
	public String getAwardTotal() {
		return awardTotal;
	}
	public void setAwardTotal(String awardTotal) {
		this.awardTotal = awardTotal;
	}
	public String getStartDateLowerBound() {
		return startDateLowerBound;
	}
	public void setStartDateLowerBound(String startDateLowerBound) {
		this.startDateLowerBound = startDateLowerBound;
	}
	public String getEndDateLowerBound() {
		return endDateLowerBound;
	}
	public void setEndDateLowerBound(String endDateLowerBound) {
		this.endDateLowerBound = endDateLowerBound;
	}
}
