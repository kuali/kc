/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="EPS_PROPOSAL")
public class ProposalOverview extends KraPersistableBusinessObjectBase { 
    @Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber; 
    @Column(name="DOCUMENT_NUMBER")
	private String documentNumber;
    @Column(name="PROPOSAL_TYPE_CODE")
	private String proposalTypeCode;
    @Column(name="CONTINUED_FROM")
	private String continuedFrom;
    @Column(name="SPONSOR_CODE")
	private String sponsorCode;
    @Column(name="ACTIVITY_TYPE_CODE")
	private String activityTypeCode;
    @Column(name="OWNED_BY_UNIT")
	private String ownedByUnitNumber;
    @Column(name="REQUESTED_START_DATE_INITIAL")
	private Date requestedStartDateInitial;
    @Column(name="REQUESTED_END_DATE_INITIAL")
	private Date requestedEndDateInitial;
    @Column(name="TITLE")
	private String title;
    @Column(name="CURRENT_AWARD_NUMBER")
	private String currentAwardNumber;
    @Column(name="DEADLINE_DATE")
	private Date deadlineDate;
    @Column(name="NOTICE_OF_OPPORTUNITY_CODE")
	private String noticeOfOpportunityCode;
    @Column(name="DEADLINE_TYPE")
	private String deadlineType;
    @Column(name="CFDA_NUMBER")
	private String cfdaNumber;
    @Column(name="PROGRAM_ANNOUNCEMENT_NUMBER")
	private String programAnnouncementNumber;
    @Column(name="PRIME_SPONSOR_CODE")
	private String primeSponsorCode;
    @Column(name="SPONSOR_PROPOSAL_NUMBER")
	private String sponsorProposalNumber;
    @Column(name="NSF_CODE")
	private String nsfCode;
    @Column(name="SUBCONTRACT_FLAG")
	private Boolean subcontracts;
    @Column(name="AGENCY_DIVISION_CODE")
	private String agencyDivisionCode;
    @Column(name="AGENCY_PROGRAM_CODE")
	private String agencyProgramCode;
    @Column(name="PROGRAM_ANNOUNCEMENT_TITLE")
	private String programAnnouncementTitle;
    @Column(name="MAIL_BY")
	private String mailBy;
    @Column(name="MAIL_TYPE")
	private String mailType;
    @Column(name="MAIL_ACCOUNT_NUMBER")
	private String mailAccountNumber;
    @Column(name="MAIL_DESCRIPTION")
	private String mailDescription;
    @Column(name="MAILING_ADDRESS_ID")
	private Integer mailingAddressId;
    @Column(name="NUMBER_OF_COPIES")
	private String numberOfCopies;
    @Column(name="ORGANIZATION_ID")
	private String organizationId;
    @Column(name="PERFORMING_ORGANIZATION_ID")
	private String performingOrganizationId; 
    private String budgetStatus;
    @Column(name="CREATION_STATUS_CODE")
	private String creationStatusCode;
    
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("proposalNumber", proposalNumber);
        return hashMap;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(String proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    public String getContinuedFrom() {
        return continuedFrom;
    }

    public void setContinuedFrom(String continuedFrom) {
        this.continuedFrom = continuedFrom;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public String getOwnedByUnitNumber() {
        return ownedByUnitNumber;
    }

    public void setOwnedByUnitNumber(String ownedByUnitNumber) {
        this.ownedByUnitNumber = ownedByUnitNumber;
    }

    public Date getRequestedStartDateInitial() {
        return requestedStartDateInitial;
    }

    public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
        this.requestedStartDateInitial = requestedStartDateInitial;
    }

    public Date getRequestedEndDateInitial() {
        return requestedEndDateInitial;
    }

    public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
        this.requestedEndDateInitial = requestedEndDateInitial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentAwardNumber() {
        return currentAwardNumber;
    }

    public void setCurrentAwardNumber(String currentAwardNumber) {
        this.currentAwardNumber = currentAwardNumber;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }

    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }

    public String getDeadlineType() {
        return deadlineType;
    }

    public void setDeadlineType(String deadlineType) {
        this.deadlineType = deadlineType;
    }

    public String getCfdaNumber() {
        return cfdaNumber;
    }

    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    public String getProgramAnnouncementNumber() {
        return programAnnouncementNumber;
    }

    public void setProgramAnnouncementNumber(String programAnnouncementNumber) {
        this.programAnnouncementNumber = programAnnouncementNumber;
    }

    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    public String getSponsorProposalNumber() {
        return sponsorProposalNumber;
    }

    public void setSponsorProposalNumber(String sponsorProposalNumber) {
        this.sponsorProposalNumber = sponsorProposalNumber;
    }

    public String getNsfCode() {
        return nsfCode;
    }

    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }

    public Boolean getSubcontracts() {
        return subcontracts;
    }

    public void setSubcontracts(Boolean subcontracts) {
        this.subcontracts = subcontracts;
    }

    public String getAgencyDivisionCode() {
        return agencyDivisionCode;
    }

    public void setAgencyDivisionCode(String agencyDivisionCode) {
        this.agencyDivisionCode = agencyDivisionCode;
    }

    public String getAgencyProgramCode() {
        return agencyProgramCode;
    }

    public void setAgencyProgramCode(String agencyProgramCode) {
        this.agencyProgramCode = agencyProgramCode;
    }

    public String getProgramAnnouncementTitle() {
        return programAnnouncementTitle;
    }

    public void setProgramAnnouncementTitle(String programAnnouncementTitle) {
        this.programAnnouncementTitle = programAnnouncementTitle;
    }

    public String getMailBy() {
        return mailBy;
    }

    public void setMailBy(String mailBy) {
        this.mailBy = mailBy;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getMailAccountNumber() {
        return mailAccountNumber;
    }

    public void setMailAccountNumber(String mailAccountNumber) {
        this.mailAccountNumber = mailAccountNumber;
    }

    public String getMailDescription() {
        return mailDescription;
    }

    public void setMailDescription(String mailDescription) {
        this.mailDescription = mailDescription;
    }

    public Integer getMailingAddressId() {
        return mailingAddressId;
    }

    public void setMailingAddressId(Integer mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }

    public String getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPerformingOrganizationId() {
        return performingOrganizationId;
    }

    public void setPerformingOrganizationId(String performingOrganizationId) {
        this.performingOrganizationId = performingOrganizationId;
    }

    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    public String getCreationStatusCode() {
        return creationStatusCode;
    }

    public void setCreationStatusCode(String creationStatusCode) {
        this.creationStatusCode = creationStatusCode;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

}

