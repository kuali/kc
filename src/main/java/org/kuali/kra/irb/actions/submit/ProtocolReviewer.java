/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.irb.ProtocolReviewerBase;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;

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
    private transient List<ProtocolOnlineReview> protocolOnlineReviews = new ArrayList<ProtocolOnlineReview>();

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
    public List<ProtocolOnlineReview> getProtocolOnlineReviews() {
        return protocolOnlineReviews;
    }

    /**
     * Sets the protocolOnlineReviews attribute value.
     * @param protocolOnlineReviews The protocolOnlineReviews to set.
     */
    public void setProtocolOnlineReviews(List<ProtocolOnlineReview> protocolOnlineReviews) {
        this.protocolOnlineReviews = protocolOnlineReviews;
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(protocolOnlineReviews);
        return managedLists;
    }
}
