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
package org.kuali.kra.award.printing;

import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.BusinessObjectBase;

public class AwardPrintNotice extends BusinessObjectBase {
    
    private Boolean addressList;
    private Boolean closeout;
    private Boolean comments;
    private Boolean costShare;
    private Boolean equipment;
    private Boolean faCost;
    private Boolean flowThru;
    private Boolean foreignTravel;
    private Boolean fundingSummary;
    private Boolean hierarchy;
    private Boolean keywords;
    private Boolean otherData;
    private Boolean payment;
    private Boolean proposalsDue;
    private Boolean reporting;
    private Boolean specialReview;
    private Boolean subAward;
    private Boolean technicalReporting;
    private Boolean terms;
    private Boolean requireSignature;
    

    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }

    public void refresh() {
        //do nothing
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
        addressList = closeout = comments = costShare = equipment = faCost =
            flowThru = foreignTravel = fundingSummary = hierarchy = keywords =
            otherData = payment = proposalsDue = reporting = specialReview =
            subAward = technicalReporting = terms = value;
    }

    public Boolean getAddressList() {
        return addressList;
    }

    public void setAddressList(Boolean addressList) {
        this.addressList = addressList;
    }

    public Boolean getCloseout() {
        return closeout;
    }

    public void setCloseout(Boolean closeout) {
        this.closeout = closeout;
    }

    public Boolean getComments() {
        return comments;
    }

    public void setComments(Boolean comments) {
        this.comments = comments;
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

    public Boolean getFaCost() {
        return faCost;
    }

    public void setFaCost(Boolean faCost) {
        this.faCost = faCost;
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

    public Boolean getProposalsDue() {
        return proposalsDue;
    }

    public void setProposalsDue(Boolean proposalsDue) {
        this.proposalsDue = proposalsDue;
    }

    public Boolean getReporting() {
        return reporting;
    }

    public void setReporting(Boolean reporting) {
        this.reporting = reporting;
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

    public Boolean getTechnicalReporting() {
        return technicalReporting;
    }

    public void setTechnicalReporting(Boolean technicalReporting) {
        this.technicalReporting = technicalReporting;
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

}
