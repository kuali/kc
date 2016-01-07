/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.award.api;

import java.util.Date;
import java.util.List;

import org.kuali.coeus.sys.framework.summary.FundingProposalSummaryDto;
import org.kuali.coeus.sys.framework.summary.InvestigatorDto;
import org.kuali.coeus.common.api.type.ActivityTypeDto;
import org.kuali.coeus.common.api.unit.UnitDto;
import org.kuali.coeus.common.framework.sponsor.SponsorDto;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Property;

public class AwardSummaryDto {

	private Long awardId;
	private String awardNumber;
	private Integer sequenceNumber;
	private String accountNumber;
	private String modificationNumber;
	private String sponsorAwardNumber;
	private String cfdaNumber;
	private String title;
	private Date updateTimestamp;
	@Property(translate = true)
	private AwardStatusDto awardStatus;
	@Property(translate = true)
	private ActivityTypeDto activityType;
	@Property(translate = true)
	private SponsorDto sponsor;
	@CollectionProperty(source = "allFundingProposals", itemClass=FundingProposalSummaryDto.class)
	private List<FundingProposalSummaryDto> fundingProposals;
	@Property(translate = true)
	private InvestigatorDto principalInvestigator;
	@Property(translate = true)
	private UnitDto leadUnit;
	
	public String getAwardNumber() {
		return awardNumber;
	}
	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getModificationNumber() {
		return modificationNumber;
	}
	public void setModificationNumber(String modificationNumber) {
		this.modificationNumber = modificationNumber;
	}
	public String getSponsorAwardNumber() {
		return sponsorAwardNumber;
	}
	public void setSponsorAwardNumber(String sponsorAwardNumber) {
		this.sponsorAwardNumber = sponsorAwardNumber;
	}
	public String getCfdaNumber() {
		return cfdaNumber;
	}
	public void setCfdaNumber(String cfdaNumber) {
		this.cfdaNumber = cfdaNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public AwardStatusDto getAwardStatus() {
		return awardStatus;
	}
	public void setAwardStatus(AwardStatusDto awardStatus) {
		this.awardStatus = awardStatus;
	}
	public ActivityTypeDto getActivityType() {
		return activityType;
	}
	public void setActivityType(ActivityTypeDto activityType) {
		this.activityType = activityType;
	}
	public InvestigatorDto getPrincipalInvestigator() {
		return principalInvestigator;
	}
	public void setPrincipalInvestigator(InvestigatorDto principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}
	public Long getAwardId() {
		return awardId;
	}
	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}
	public SponsorDto getSponsor() {
		return sponsor;
	}
	public void setSponsor(SponsorDto sponsor) {
		this.sponsor = sponsor;
	}
	public List<FundingProposalSummaryDto> getFundingProposals() {
		return fundingProposals;
	}
	public void setFundingProposals(List<FundingProposalSummaryDto> fundingProposals) {
		this.fundingProposals = fundingProposals;
	}
	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}
	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	public UnitDto getLeadUnit() {
		return leadUnit;
	}
	public void setLeadUnit(UnitDto leadUnit) {
		this.leadUnit = leadUnit;
	}


}
