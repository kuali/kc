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
package org.kuali.kra.irb.onlinereview;

import java.sql.Date;

import org.kuali.rice.krad.bo.BusinessObjectBase;

public class NewProtocolOnlineReviewBean extends BusinessObjectBase {

    private Long newProtocolReviewCommitteeMembershipId;

    private String newReviewerTypeCode;

    private Date newReviewDateRequested;

    private Date newReviewDateDue;

    private String newReviewDocumentDescription;

    private String newReviewExplanation;

    private String newReviewOrganizationDocumentNumber;

    public Long getNewProtocolReviewCommitteeMembershipId() {
        return newProtocolReviewCommitteeMembershipId;
    }

    public void setNewProtocolReviewCommitteeMembershipId(Long newProtocolReviewCommitteeMembershipId) {
        this.newProtocolReviewCommitteeMembershipId = newProtocolReviewCommitteeMembershipId;
    }

    public String getNewReviewerTypeCode() {
        return newReviewerTypeCode;
    }

    public void setNewReviewerTypeCode(String newReviewerTypeCode) {
        this.newReviewerTypeCode = newReviewerTypeCode;
    }

    public Date getNewReviewDateRequested() {
        return newReviewDateRequested;
    }

    public void setNewReviewDateRequested(Date newReviewDateRequested) {
        this.newReviewDateRequested = newReviewDateRequested;
    }

    public Date getNewReviewDateDue() {
        return newReviewDateDue;
    }

    public void setNewReviewDateDue(Date newReviewDateDue) {
        this.newReviewDateDue = newReviewDateDue;
    }

    public String getNewReviewDocumentDescription() {
        return newReviewDocumentDescription;
    }

    public void setNewReviewDocumentDescription(String newReviewDocumentDescription) {
        this.newReviewDocumentDescription = newReviewDocumentDescription;
    }

    public String getNewReviewExplanation() {
        return newReviewExplanation;
    }

    public void setNewReviewExplanation(String newReviewExplanation) {
        this.newReviewExplanation = newReviewExplanation;
    }

    public String getNewReviewOrganizationDocumentNumber() {
        return newReviewOrganizationDocumentNumber;
    }

    public void setNewReviewOrganizationDocumentNumber(String newReviewOrganizationDocumentNumber) {
        this.newReviewOrganizationDocumentNumber = newReviewOrganizationDocumentNumber;
    }

    public void refresh() {
    }
}
