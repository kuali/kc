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
package org.kuali.kra.institutionalproposal.ipreview;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

public class InstitutionalProposalIpReviewActivity extends KraPersistableBusinessObjectBase implements SequenceAssociate { 
    
    private static final long serialVersionUID = 1L;

    private Long proposalIpReviewActivityId; 
    private Long proposalId;
    private String proposalNumber; 
    private Integer sequenceNumber; 
    private Integer activityNumber; 
    private String ipReviewActivityTypeCode; 
    private Date activityDate; 
    private String comments; 
    private IntellectualPropertyReviewActivityType ipReviewActivityType;
    private IntellectualPropertyReview intellectualPropertyReview;
    
    public InstitutionalProposalIpReviewActivity() { 

    } 
    
    public Long getProposalIpReviewActivityId() {
        return proposalIpReviewActivityId;
    }

    public void setProposalIpReviewActivityId(Long proposalIpReviewActivityId) {
        this.proposalIpReviewActivityId = proposalIpReviewActivityId;
    }
    
    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
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

    public String getIpReviewActivityTypeCode() {
        return ipReviewActivityTypeCode;
    }

    public void setIpReviewActivityTypeCode(String ipReviewActivityTypeCode) {
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
    
    public IntellectualPropertyReviewActivityType getIpReviewActivityType() {
        return ipReviewActivityType;
    }

    public void setIpReviewActivityType(IntellectualPropertyReviewActivityType ipReviewActivityType) {
        this.ipReviewActivityType = ipReviewActivityType;
    }
    
    public IntellectualPropertyReview getIntellectualPropertyReview() {
        return intellectualPropertyReview;
    }

    public void setIntellectualPropertyReview(IntellectualPropertyReview intellectualPropertyReview) {
        this.intellectualPropertyReview = intellectualPropertyReview;
        if (intellectualPropertyReview != null) {
            setSequenceNumber(intellectualPropertyReview.getSequenceNumber());
            setProposalNumber(intellectualPropertyReview.getProposalNumber());
        } else {
            setSequenceNumber(0);
            setProposalNumber("");
        }
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwner getSequenceOwner() {
        return getIntellectualPropertyReview();
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setIntellectualPropertyReview((IntellectualPropertyReview) newlyVersionedOwner);
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.proposalIpReviewActivityId = null;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activityDate == null) ? 0 : activityDate.hashCode());
        result = prime * result + ((activityNumber == null) ? 0 : activityNumber.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((ipReviewActivityTypeCode == null) ? 0 : ipReviewActivityTypeCode.hashCode());
        result = prime * result + ((proposalId == null) ? 0 : proposalId.hashCode());
        result = prime * result + ((proposalIpReviewActivityId == null) ? 0 : proposalIpReviewActivityId.hashCode());
        result = prime * result + ((proposalNumber == null) ? 0 : proposalNumber.hashCode());
        result = prime * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        InstitutionalProposalIpReviewActivity other = (InstitutionalProposalIpReviewActivity) obj;
        if (activityDate == null) {
            if (other.activityDate != null) {
                return false;
            }
        } else if (!activityDate.equals(other.activityDate)) {
            return false;
        }
        if (activityNumber == null) {
            if (other.activityNumber != null) {
                return false;
            }
        } else if (!activityNumber.equals(other.activityNumber)) {
            return false;
        }
        if (comments == null) {
            if (other.comments != null) {
                return false;
            }
        } else if (!comments.equals(other.comments)) {
            return false;
        }
        if (ipReviewActivityTypeCode == null) {
            if (other.ipReviewActivityTypeCode != null) {
                return false;
            }
        } else if (!ipReviewActivityTypeCode.equals(other.ipReviewActivityTypeCode)) {
            return false;
        }
        if (proposalId == null) {
            if (other.proposalId != null) {
                return false;
            }
        } else if (!proposalId.equals(other.proposalId)) {
            return false;
        }
        if (proposalIpReviewActivityId == null) {
            if (other.proposalIpReviewActivityId != null) {
                return false;
            }
        } else if (!proposalIpReviewActivityId.equals(other.proposalIpReviewActivityId)) {
            return false;
        }
        if (proposalNumber == null) {
            if (other.proposalNumber != null) {
                return false;
            }
        } else if (!proposalNumber.equals(other.proposalNumber)) {
            return false;
        }
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null) {
                return false;
            }
        } else if (!sequenceNumber.equals(other.sequenceNumber)) {
            return false;
        }
        return true;
    }
    
}