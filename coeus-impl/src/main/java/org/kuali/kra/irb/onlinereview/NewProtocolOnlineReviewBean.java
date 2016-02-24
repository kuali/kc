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
package org.kuali.kra.irb.onlinereview;

import org.kuali.rice.krad.bo.BusinessObjectBase;

import java.sql.Date;

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
