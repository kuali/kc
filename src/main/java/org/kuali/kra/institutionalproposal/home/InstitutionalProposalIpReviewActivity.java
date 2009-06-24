/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.home;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class InstitutionalProposalIpReviewActivity extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer proposalIpReviewActivityId; 
    private String proposalNumber; 
    private Integer sequenceNumber; 
    private Integer activityNumber; 
    private Integer ipReviewActivityTypeCode; 
    private Date activityDate; 
    private String comments; 
    
    
    public InstitutionalProposalIpReviewActivity() { 

    } 
    
    public Integer getProposalIpReviewActivityId() {
        return proposalIpReviewActivityId;
    }

    public void setProposalIpReviewActivityId(Integer proposalIpReviewActivityId) {
        this.proposalIpReviewActivityId = proposalIpReviewActivityId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(Integer activityNumber) {
        this.activityNumber = activityNumber;
    }

    public Integer getIpReviewActivityTypeCode() {
        return ipReviewActivityTypeCode;
    }

    public void setIpReviewActivityTypeCode(Integer ipReviewActivityTypeCode) {
        this.ipReviewActivityTypeCode = ipReviewActivityTypeCode;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalIpRevActivityId", this.getProposalIpReviewActivityId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("activityNumber", this.getActivityNumber());
        hashMap.put("ipReviewActivityTypeCode", this.getIpReviewActivityTypeCode());
        hashMap.put("activityDate", this.getActivityDate());
        hashMap.put("comments", this.getComments());
        return hashMap;
    }
    
}