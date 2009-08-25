/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.hierarchy.bo;

import java.io.Serializable;
import java.sql.Date;


/**
 * This class...
 */
public class HierarchyProposalSummary implements Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4513320772280178341L;

    private String proposalNumber;
    private Date requestedStartDateInitial;
    private Date requestedEndDateInitial;
    private String ownedByUnitName;
    private String activityTypeName;

    private String proposalStateName;
    private String proposalTypeName;
    private String narrative;
    private String budget;
    private String title;

    
    private Date deadlineDate;
    private String deadlineType;
    private String sponsorName;
    private String primeSponsorCode;
    private String nsfCode;
    private String agencyDivisionCode;
    private String programAnnouncementTitle;

    private String noticeOfOpportunityCode;
    private String cfdaNumber;
    private String programAnnouncementNumber;
    private String sponsorProposalNumber;
    private Boolean subcontracts;
    private String agencyProgramCode;

    
    private String principalInvestigatorName;
    private String investigatorsNameList;
    private String units;
    /**
     * Gets the proposalNumber attribute. 
     * @return Returns the proposalNumber.
     */
    public String getProposalNumber() {
        return proposalNumber;
    }
    /**
     * Sets the proposalNumber attribute value.
     * @param proposalNumber The proposalNumber to set.
     */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
    /**
     * Gets the requestedStartDateInitial attribute. 
     * @return Returns the requestedStartDateInitial.
     */
    public Date getRequestedStartDateInitial() {
        return requestedStartDateInitial;
    }
    /**
     * Sets the requestedStartDateInitial attribute value.
     * @param requestedStartDateInitial The requestedStartDateInitial to set.
     */
    public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
        this.requestedStartDateInitial = requestedStartDateInitial;
    }
    /**
     * Gets the requestedEndDateInitial attribute. 
     * @return Returns the requestedEndDateInitial.
     */
    public Date getRequestedEndDateInitial() {
        return requestedEndDateInitial;
    }
    /**
     * Sets the requestedEndDateInitial attribute value.
     * @param requestedEndDateInitial The requestedEndDateInitial to set.
     */
    public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
        this.requestedEndDateInitial = requestedEndDateInitial;
    }
    /**
     * Gets the ownedByUnitName attribute. 
     * @return Returns the ownedByUnitName.
     */
    public String getOwnedByUnitName() {
        return ownedByUnitName;
    }
    /**
     * Sets the ownedByUnitName attribute value.
     * @param ownedByUnitName The ownedByUnitName to set.
     */
    public void setOwnedByUnitName(String ownedByUnitName) {
        this.ownedByUnitName = ownedByUnitName;
    }
    /**
     * Gets the activityTypeName attribute. 
     * @return Returns the activityTypeName.
     */
    public String getActivityTypeName() {
        return activityTypeName;
    }
    /**
     * Sets the activityTypeName attribute value.
     * @param activityTypeName The activityTypeName to set.
     */
    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }
    /**
     * Gets the proposalStateName attribute. 
     * @return Returns the proposalStateName.
     */
    public String getProposalStateName() {
        return proposalStateName;
    }
    /**
     * Sets the proposalStateName attribute value.
     * @param status The proposalStateName to set.
     */
    public void setProposalStateName(String proposalStateName) {
        this.proposalStateName = proposalStateName;
    }
    /**
     * Sets the proposalTypeName attribute value.
     * @param proposalTypeName The proposalTypeName to set.
     */
    public void setProposalTypeName(String proposalTypeName) {
        this.proposalTypeName = proposalTypeName;
    }
    /**
     * Gets the proposalTypeName attribute. 
     * @return Returns the proposalTypeName.
     */
    public String getProposalTypeName() {
        return proposalTypeName;
    }
    /**
     * Gets the narrative attribute. 
     * @return Returns the narrative.
     */
    public String getNarrative() {
        return narrative;
    }
    /**
     * Sets the narrative attribute value.
     * @param narrative The narrative to set.
     */
    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }
    /**
     * Gets the budget attribute. 
     * @return Returns the budget.
     */
    public String getBudget() {
        return budget;
    }
    /**
     * Sets the budget attribute value.
     * @param budget The budget to set.
     */
    public void setBudget(String budget) {
        this.budget = budget;
    }
    /**
     * Gets the title attribute. 
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title attribute value.
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Gets the deadlineDate attribute. 
     * @return Returns the deadlineDate.
     */
    public Date getDeadlineDate() {
        return deadlineDate;
    }
    /**
     * Sets the deadlineDate attribute value.
     * @param deadlineDate The deadlineDate to set.
     */
    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
    /**
     * Gets the deadlineType attribute. 
     * @return Returns the deadlineType.
     */
    public String getDeadlineType() {
        return deadlineType;
    }
    /**
     * Sets the deadlineType attribute value.
     * @param deadlineType The deadlineType to set.
     */
    public void setDeadlineType(String deadlineType) {
        this.deadlineType = deadlineType;
    }
    /**
     * Gets the sponsorName attribute. 
     * @return Returns the sponsorName.
     */
    public String getSponsorName() {
        return sponsorName;
    }
    /**
     * Sets the sponsorName attribute value.
     * @param sponsorName The sponsorName to set.
     */
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
    /**
     * Gets the primeSponsorCode attribute. 
     * @return Returns the primeSponsorCode.
     */
    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }
    /**
     * Sets the primeSponsorCode attribute value.
     * @param primeSponsorCode The primeSponsorCode to set.
     */
    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }
    /**
     * Gets the nsfCode attribute. 
     * @return Returns the nsfCode.
     */
    public String getNsfCode() {
        return nsfCode;
    }
    /**
     * Sets the nsfCode attribute value.
     * @param nsfCode The nsfCode to set.
     */
    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }
    /**
     * Gets the agencyDivisionCode attribute. 
     * @return Returns the agencyDivisionCode.
     */
    public String getAgencyDivisionCode() {
        return agencyDivisionCode;
    }
    /**
     * Sets the agencyDivisionCode attribute value.
     * @param agencyDivisionCode The agencyDivisionCode to set.
     */
    public void setAgencyDivisionCode(String agencyDivisionCode) {
        this.agencyDivisionCode = agencyDivisionCode;
    }
    /**
     * Gets the programAnnouncementTitle attribute. 
     * @return Returns the programAnnouncementTitle.
     */
    public String getProgramAnnouncementTitle() {
        return programAnnouncementTitle;
    }
    /**
     * Sets the programAnnouncementTitle attribute value.
     * @param programAnnouncementTitle The programAnnouncementTitle to set.
     */
    public void setProgramAnnouncementTitle(String programAnnouncementTitle) {
        this.programAnnouncementTitle = programAnnouncementTitle;
    }
    /**
     * Gets the noticeOfOpportunityCode attribute. 
     * @return Returns the noticeOfOpportunityCode.
     */
    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }
    /**
     * Sets the noticeOfOpportunityCode attribute value.
     * @param noticeOfOpportunityCode The noticeOfOpportunityCode to set.
     */
    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }
    /**
     * Gets the cfdaNumber attribute. 
     * @return Returns the cfdaNumber.
     */
    public String getCfdaNumber() {
        return cfdaNumber;
    }
    /**
     * Sets the cfdaNumber attribute value.
     * @param cfdaNumber The cfdaNumber to set.
     */
    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }
    /**
     * Gets the programAnnouncementNumber attribute. 
     * @return Returns the programAnnouncementNumber.
     */
    public String getProgramAnnouncementNumber() {
        return programAnnouncementNumber;
    }
    /**
     * Sets the programAnnouncementNumber attribute value.
     * @param programAnnouncementNumber The programAnnouncementNumber to set.
     */
    public void setProgramAnnouncementNumber(String programAnnouncementNumber) {
        this.programAnnouncementNumber = programAnnouncementNumber;
    }
    /**
     * Gets the sponsorProposalNumber attribute. 
     * @return Returns the sponsorProposalNumber.
     */
    public String getSponsorProposalNumber() {
        return sponsorProposalNumber;
    }
    /**
     * Sets the sponsorProposalNumber attribute value.
     * @param sponsorProposalNumber The sponsorProposalNumber to set.
     */
    public void setSponsorProposalNumber(String sponsorProposalNumber) {
        this.sponsorProposalNumber = sponsorProposalNumber;
    }
    /**
     * Gets the subcontracts attribute. 
     * @return Returns the subcontracts.
     */
    public Boolean getSubcontracts() {
        return subcontracts;
    }
    /**
     * Sets the subcontracts attribute value.
     * @param subcontracts The subcontracts to set.
     */
    public void setSubcontracts(Boolean subcontracts) {
        this.subcontracts = subcontracts;
    }
    /**
     * Gets the agencyProgramCode attribute. 
     * @return Returns the agencyProgramCode.
     */
    public String getAgencyProgramCode() {
        return agencyProgramCode;
    }
    /**
     * Sets the agencyProgramCode attribute value.
     * @param agencyProgramCode The agencyProgramCode to set.
     */
    public void setAgencyProgramCode(String agencyProgramCode) {
        this.agencyProgramCode = agencyProgramCode;
    }
    /**
     * Gets the principalInvestigatorName attribute. 
     * @return Returns the principalInvestigatorName.
     */
    public String getPrincipalInvestigatorName() {
        return principalInvestigatorName;
    }
    /**
     * Sets the principalInvestigatorName attribute value.
     * @param principalInvestigatorName The principalInvestigatorName to set.
     */
    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }
    /**
     * Gets the investigatorsNameList attribute. 
     * @return Returns the investigatorsNameList.
     */
    public String getInvestigatorsNameList() {
        return investigatorsNameList;
    }
    /**
     * Sets the investigatorsNameList attribute value.
     * @param investigatorsNameList The investigatorsNameList to set.
     */
    public void setInvestigatorsNameList(String investigatorsNameList) {
        this.investigatorsNameList = investigatorsNameList;
    }
    /**
     * Gets the units attribute. 
     * @return Returns the units.
     */
    public String getUnits() {
        return units;
    }
    /**
     * Sets the units attribute value.
     * @param units The units to set.
     */
    public void setUnits(String units) {
        this.units = units;
    }
    
    
}
