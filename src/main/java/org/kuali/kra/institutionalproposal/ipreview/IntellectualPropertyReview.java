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
package org.kuali.kra.institutionalproposal.ipreview;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.kra.institutionalproposal.ProposalComment;

public class IntellectualPropertyReview extends InstitutionalProposalAssociate {
    
    private static final long serialVersionUID = 1L;
    
    private Long proposalId;
    private Integer ipReviewRequirementTypeCode; 
    private Date reviewSubmissionDate; 
    private Date reviewReceiveDate; 
    private Integer reviewResultCode; 
    private String ipReviewer; 
    private List<ProposalComment> comments;
    private List<InstitutionalProposalIpReviewActivity> ipReviewActivities;
    
    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Integer getIpReviewRequirementTypeCode() {
        return ipReviewRequirementTypeCode;
    }

    public void setIpReviewRequirementTypeCode(Integer ipReviewRequirementTypeCode) {
        this.ipReviewRequirementTypeCode = ipReviewRequirementTypeCode;
    }

    public Date getReviewSubmissionDate() {
        return reviewSubmissionDate;
    }

    public void setReviewSubmissionDate(Date reviewSubmissionDate) {
        this.reviewSubmissionDate = reviewSubmissionDate;
    }

    public Date getReviewReceiveDate() {
        return reviewReceiveDate;
    }

    public void setReviewReceiveDate(Date reviewReceiveDate) {
        this.reviewReceiveDate = reviewReceiveDate;
    }

    public Integer getReviewResultCode() {
        return reviewResultCode;
    }

    public void setReviewResultCode(Integer reviewResultCode) {
        this.reviewResultCode = reviewResultCode;
    }

    public String getIpReviewer() {
        return ipReviewer;
    }

    public void setIpReviewer(String ipReviewer) {
        this.ipReviewer = ipReviewer;
    }
    
    public List<ProposalComment> getComments() {
        return comments;
    }

    public void setComments(List<ProposalComment> comments) {
        this.comments = comments;
    }
    
    // TODO gmcgrego get the actual comment type
    public String getGeneralComment() {
        if (comments.size() > 0) {
            return comments.get(0).getComments();
        }
        return "";
    }
    
    public String getReviewerComment() {
        if (comments.size() > 2) {
            return comments.get(1).getComments();
        }
        return "";
    }
    
    public List<InstitutionalProposalIpReviewActivity> getIpReviewActivities() {
        return ipReviewActivities;
    }

    public void setIpReviewActivities(List<InstitutionalProposalIpReviewActivity> ipReviewActivities) {
        this.ipReviewActivities = ipReviewActivities;
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    public void beforeInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.beforeInsert(persistenceBroker);
        setReferenceFields();
    }
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    public void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.beforeUpdate(persistenceBroker);
        setReferenceFields();
    }
    
    protected void setReferenceFields() {
        for (InstitutionalProposalIpReviewActivity ipReviewActivity: ipReviewActivities) {
            ipReviewActivity.setProposalId(this.getProposalId());
            ipReviewActivity.setProposalNumber(this.getProposalNumber());
            ipReviewActivity.setSequenceNumber(this.getSequenceNumber());
        }
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalId", this.getProposalId());
        hashMap.put("ipReviewRequirementTypeCode", this.getIpReviewRequirementTypeCode());
        hashMap.put("reviewSubmissionDate", this.getReviewSubmissionDate());
        hashMap.put("reviewReceiveDate", this.getReviewReceiveDate());
        hashMap.put("reviewResultCode", this.getReviewResultCode());
        hashMap.put("ipReviewer", this.getIpReviewer());
        return hashMap;
    }

}
