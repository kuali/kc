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
package org.kuali.kra.protocol.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.protocol.ProtocolReviewerBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ProtocolReviewer extends ProtocolReviewerBase {

    private Long protocolReviewerId;

    private String protocolNumber;

    private Integer sequenceNumber;

    private Integer submissionNumber;

    private String reviewerTypeCode;

    private ProtocolReviewerType protocolReviewerType;

    // transient property for submission detail display 
    @SkipVersioning
    private transient List<ProtocolOnlineReviewBase> protocolOnlineReviews = new ArrayList<ProtocolOnlineReviewBase>();

    public Long getProtocolReviewerId() {
        return protocolReviewerId;
    }

    public void setProtocolReviewerId(Long protocolReviewerId) {
        this.protocolReviewerId = protocolReviewerId;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public void setReviewerTypeCode(String reviewerTypeCode) {
        this.reviewerTypeCode = reviewerTypeCode;
    }

    public String getReviewerTypeCode() {
        return reviewerTypeCode;
    }

    public ProtocolReviewerType getProtocolReviewerType() {
        if (protocolReviewerType == null && StringUtils.isNotBlank(reviewerTypeCode)) {
            refreshReferenceObject("protocolReviewerType");
        }
        return protocolReviewerType;
    }

    public void setProtocolReviewerType(ProtocolReviewerType protocolReviewerType) {
        this.protocolReviewerType = protocolReviewerType;
    }

    /**
     * Gets the protocolOnlineReviews attribute. 
     * @return Returns the protocolOnlineReviews.
     */
    public List<ProtocolOnlineReviewBase> getProtocolOnlineReviews() {
        return protocolOnlineReviews;
    }

    /**
     * Sets the protocolOnlineReviews attribute value.
     * @param protocolOnlineReviews The protocolOnlineReviews to set.
     */
    public void setProtocolOnlineReviews(List<ProtocolOnlineReviewBase> protocolOnlineReviews) {
        this.protocolOnlineReviews = protocolOnlineReviews;
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        if (protocolOnlineReviews != null) {
            managedLists.add(protocolOnlineReviews);
        }
        return managedLists;
    }
    
}
