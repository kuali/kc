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
package org.kuali.kra.institutionalproposal.ipreview;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.sql.Date;

/**
 * This class represents an Intellectual Property Review Activity.
 */
public class IntellectualPropertyReviewActivity extends KcPersistableBusinessObjectBase implements SequenceAssociate<IntellectualPropertyReview> {

    private static final long serialVersionUID = 1L;

    private Long proposalIpReviewActivityId;

    private Long ipReviewId;

    private String proposalNumber;

    private Integer sequenceNumber;

    private Integer activityNumber;

    private String ipReviewActivityTypeCode;

    private Date activityDate;

    private String comments;

    private IntellectualPropertyReviewActivityType ipReviewActivityType;

    private IntellectualPropertyReview intellectualPropertyReview;

    /**
     * Constructs an IntellectualPropertyReviewActivity.java.
     */
    public IntellectualPropertyReviewActivity() {
    }

    public Long getProposalIpReviewActivityId() {
        return proposalIpReviewActivityId;
    }

    public void setProposalIpReviewActivityId(Long proposalIpReviewActivityId) {
        this.proposalIpReviewActivityId = proposalIpReviewActivityId;
    }

    public Long getIpReviewId() {
        return ipReviewId;
    }

    public void setIpReviewId(Long ipReviewId) {
        this.ipReviewId = ipReviewId;
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

    @Override
    public IntellectualPropertyReview getSequenceOwner() {
        return getIntellectualPropertyReview();
    }

    @Override
    public void setSequenceOwner(IntellectualPropertyReview newlyVersionedOwner) {
        setIntellectualPropertyReview((IntellectualPropertyReview) newlyVersionedOwner);
    }

    @Override
    public void resetPersistenceState() {
        this.proposalIpReviewActivityId = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activityDate == null) ? 0 : activityDate.hashCode());
        result = prime * result + ((activityNumber == null) ? 0 : activityNumber.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((ipReviewActivityTypeCode == null) ? 0 : ipReviewActivityTypeCode.hashCode());
        result = prime * result + ((ipReviewId == null) ? 0 : ipReviewId.hashCode());
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
        IntellectualPropertyReviewActivity other = (IntellectualPropertyReviewActivity) obj;
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
        if (ipReviewId == null) {
            if (other.ipReviewId != null) {
                return false;
            }
        } else if (!ipReviewId.equals(other.ipReviewId)) {
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
