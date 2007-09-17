/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;

/**
 * This class is a Proposal BO used for lookups, essentially so we can do a lookup
 * against transactional document data.
 */
public class Proposal extends KraPersistableBusinessObjectBase {

    private Integer proposalNumber;
    private Integer proposalTypeCode;
    private String sponsorCode;
    private Integer activityTypeCode;
    private String ownedByUnit;
    private Date requestedStartDateInitial;
    private Date requestedEndDateInitial;
    private String title;
    private ActivityType activityType;
    private ProposalType proposalType;
    private Sponsor sponsor;

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalNumber", this.getProposalNumber());
        propMap.put("proposalTypeCode", this.getProposalTypeCode());
        propMap.put("sponsorCode", this.getSponsorCode());
        propMap.put("activityTypeCode", this.getActivityTypeCode());
        propMap.put("ownedByUnit", this.getOwnedByUnit());
        propMap.put("requestedStartDateInitial", this.getRequestedStartDateInitial());
        propMap.put("requestedEndDateInitial", this.getRequestedEndDateInitial());
        propMap.put("title", this.getTitle());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
    }

    /**
     * Sets the proposalType attribute value.
     * @param proposalType The proposalType to set.
     */
    public void setProposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
    }

    /**
     * Gets the proposalType attribute.
     * @return Returns the proposalType.
     */
    public ProposalType getProposalType() {
        return proposalType;
    }

    /**
     * Gets the activityTypeCode attribute.
     * @return Returns the activityTypeCode.
     */
    public Integer getActivityTypeCode() {
        return activityTypeCode;
    }

    /**
     * Sets the activityTypeCode attribute value.
     * @param activityTypeCode The activityTypeCode to set.
     */
    public void setActivityTypeCode(Integer activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    /**
     * Gets the ownedByUnit attribute.
     * @return Returns the ownedByUnit.
     */
    public String getOwnedByUnit() {
        return ownedByUnit;
    }

    /**
     * Sets the ownedByUnit attribute value.
     * @param ownedByUnit The ownedByUnit to set.
     */
    public void setOwnedByUnit(String ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }

    /**
     * Gets the proposalNumber attribute.
     * @return Returns the proposalNumber.
     */
    public Integer getProposalNumber() {
        return proposalNumber;
    }

    /**
     * Sets the proposalNumber attribute value.
     * @param proposalNumber The proposalNumber to set.
     */
    public void setProposalNumber(Integer proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    /**
     * Gets the proposalTypeCode attribute.
     * @return Returns the proposalTypeCode.
     */
    public Integer getProposalTypeCode() {
        return proposalTypeCode;
    }

    /**
     * Sets the proposalTypeCode attribute value.
     * @param proposalTypeCode The proposalTypeCode to set.
     */
    public void setProposalTypeCode(Integer proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
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
     * Gets the sponsorCode attribute.
     * @return Returns the sponsorCode.
     */
    public String getSponsorCode() {
        return sponsorCode;
    }

    /**
     * Sets the sponsorCode attribute value.
     * @param sponsorCode The sponsorCode to set.
     */
    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
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
     * Sets the sponsor attribute value.
     * @param sponsor The sponsor to set.
     */
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * Gets the sponsor attribute.
     * @return Returns the sponsor.
     */
    public Sponsor getSponsor() {
        return sponsor;
    }

    /**
     * Gets the activityType attribute.
     * @return Returns the activityType.
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Sets the activityType attribute value.
     * @param activityType The activityType to set.
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

}
