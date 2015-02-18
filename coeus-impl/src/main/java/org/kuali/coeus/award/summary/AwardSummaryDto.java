package org.kuali.coeus.award.summary;

import java.util.List;

import org.kuali.coeus.award.contacts.InvestigatorDto;
import org.kuali.coeus.common.framework.sponsor.SponsorDto;
import org.kuali.coeus.common.framework.type.ActivityTypeDto;

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
	@Property(translate = true)
	private AwardStatusDto awardStatus;
	@Property(translate = true)
	private ActivityTypeDto activityType;
	@Property(translate = true)
	private SponsorDto sponsor;
	@CollectionProperty(source = "allFundingProposals", itemSource="proposal", itemClass=org.kuali.coeus.award.summary.FundingProposalSummaryDto.class)
	private List<FundingProposalSummaryDto> fundingProposals;
	@Property(translate = true)
	private InvestigatorDto principalInvestigator;
	
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


}
