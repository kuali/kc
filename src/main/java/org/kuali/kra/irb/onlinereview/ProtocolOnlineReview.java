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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.UnitAclLoadable;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class encapsulates the notion of a protocol review. Essentially 
 * a join between protocol, submission, and a reviewer.  The ProtocolReview
 * is created by the IRB Admin as request.
 */
public class ProtocolOnlineReview extends KraPersistableBusinessObjectBase implements Permissionable, UnitAclLoadable {

    private static final long serialVersionUID = 531397319695764847L;

    private Long protocolOnlineReviewId;

    private Long protocolId;

    private Long submissionIdFk;

    private Long protocolReviewerId;

    private String protocolOnlineReviewStatusCode;

    private Long protocolOnlineReviewDeterminationRecommendationCode;

    private Date dateDue;

    private Date dateRequested;

    private String actionsPerformed;

    private boolean reviewerApproved = false;

    private boolean adminAccepted = false;

    private Protocol protocol;

    private ProtocolSubmission protocolSubmission;

    private ProtocolReviewer protocolReviewer;

    private ProtocolOnlineReviewStatus protocolOnlineReviewStatus;

    private ProtocolOnlineReviewDeterminationRecommendation protocolOnlineReviewDeterminationRecommendation;

    private List<CommitteeScheduleMinute> committeeScheduleMinutes;

    private List<ProtocolReviewAttachment> reviewAttachments;

    private ProtocolOnlineReviewDocument protocolOnlineReviewDocument;

    //lookup fields  
    //private transient String lookupProtocolNumber;  
    private transient Protocol lookupProtocol;

    private transient Integer lookupReviewerRolodexId;

    private transient Rolodex lookupReviewerRolodex;

    private transient String lookupReviewerPersonId;

    private transient KcPerson lookupReviewerPerson;

    private transient String lookupProtocolOnlineReviewStatusCode;

    // to limit reviewertype drop down to primary/secondary on OLR  
    private transient String reviewerTypeCode;

    public ProtocolOnlineReview() {
        this.committeeScheduleMinutes = new ArrayList<CommitteeScheduleMinute>();
    }

    /**
     * Gets the protocolReviewId attribute. 
     * @return Returns the protocolReviewId.
     */
    public Long getProtocolOnlineReviewId() {
        return protocolOnlineReviewId;
    }

    /**
     * Sets the protocolReviewId attribute value.
     * @param protocolOnlineReviewId The protocolReviewId to set.
     */
    public void setProtocolOnlineReviewId(Long protocolOnlineReviewId) {
        this.protocolOnlineReviewId = protocolOnlineReviewId;
    }

    /**
     * Gets the protocolId attribute. 
     * @return Returns the protocolId.
     */
    public Long getProtocolId() {
        return protocolId;
    }

    /**
     * Sets the protocolId attribute value.
     * @param protocolId The protocolId to set.
     */
    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    /**
     * Gets the submissionIdFk attribute. 
     * @return Returns the submissionIdFk.
     */
    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    /**
     * Sets the submissionIdFk attribute value.
     * @param submissionIdFk The submissionIdFk to set.
     */
    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    /** Gets the protocolReviewStatusCode attribute. 
     * @return Returns the protocolReviewStatusCode.
     */
    public String getProtocolOnlineReviewStatusCode() {
        return protocolOnlineReviewStatusCode;
    }

    /**
     * Sets the protocolReviewStatusCode attribute value.
     * @param protocolOnlineReviewStatusCode The protocolReviewStatusCode to set.
     */
    public void setProtocolOnlineReviewStatusCode(String protocolOnlineReviewStatusCode) {
        this.protocolOnlineReviewStatusCode = protocolOnlineReviewStatusCode;
    }

    /**
     * Gets the dueDate attribute. 
     * @return Returns the dueDate.
     */
    public Date getDateDue() {
        return dateDue;
    }

    /**
     * Sets the dueDate attribute value.
     * @param dateDue The dueDate to set.
     */
    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    /**
     * Gets the dateRequested attribute. 
     * @return Returns the dateRequested.
     */
    public Date getDateRequested() {
        return dateRequested;
    }

    /**
     * Sets the dateRequested attribute value.
     * @param dateRequested The dateRequested to set.
     */
    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    /**
     * Gets the protocol attribute. 
     * @return Returns the protocol.
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Sets the protocol attribute value.
     * @param protocol The protocol to set.
     */
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the protocolSubmission attribute. 
     * @return Returns the protocolSubmission.
     */
    public ProtocolSubmission getProtocolSubmission() {
        return protocolSubmission;
    }

    /**
     * Sets the protocolSubmission attribute value.
     * @param protocolSubmission The protocolSubmission to set.
     */
    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    /**
     * Gets the protocolReviewStatus attribute. 
     * @return Returns the protocolReviewStatus.
     */
    public ProtocolOnlineReviewStatus getProtocolOnlineReviewStatus() {
        return protocolOnlineReviewStatus;
    }

    /**
     * Sets the protocolReviewStatus attribute value.
     * @param protocolOnlineReviewStatus The protocolReviewStatus to set.
     */
    public void setProtocolOnlineReviewStatus(ProtocolOnlineReviewStatus protocolOnlineReviewStatus) {
        this.protocolOnlineReviewStatus = protocolOnlineReviewStatus;
    }

    /**
     * Gets the protocolReviewDeterminationRecommendationCode attribute. 
     * @return Returns the protocolReviewDeterminationRecommendationCode.
     */
    public Long getProtocolOnlineReviewDeterminationRecommendationCode() {
        return protocolOnlineReviewDeterminationRecommendationCode;
    }

    /**
     * Sets the protocolReviewDeterminationRecommendationCode attribute value.
     * @param protocolOnlineReviewDeterminationRecommendationCode The protocolReviewDeterminationRecommendationCode to set.
     */
    public void setProtocolOnlineReviewDeterminationRecommendationCode(Long protocolOnlineReviewDeterminationRecommendationCode) {
        this.protocolOnlineReviewDeterminationRecommendationCode = protocolOnlineReviewDeterminationRecommendationCode;
    }

    /**
     * Gets the protocolReviewDeterminationRecommendation attribute. 
     * @return Returns the protocolReviewDeterminationRecommendation.
     */
    public ProtocolOnlineReviewDeterminationRecommendation getProtocolOnlineReviewDeterminationRecommendation() {
        return protocolOnlineReviewDeterminationRecommendation;
    }

    /**
     * Sets the protocolReviewDeterminationRecommendation attribute value.
     * @param protocolOnlineReviewDeterminationRecommendation The protocolReviewDeterminationRecommendation to set.
     */
    public void setProtocolOnlineReviewDeterminationRecommendation(ProtocolOnlineReviewDeterminationRecommendation protocolOnlineReviewDeterminationRecommendation) {
        this.protocolOnlineReviewDeterminationRecommendation = protocolOnlineReviewDeterminationRecommendation;
    }

    /**
     * Gets the protocolReviewerId attribute. 
     * @return Returns the protocolReviewerId.
     */
    public Long getProtocolReviewerId() {
        return protocolReviewerId;
    }

    /**
     * Sets the protocolReviewerId attribute value.
     * @param protocolReviewerId The protocolReviewerId to set.
     */
    public void setProtocolReviewerId(Long protocolReviewerId) {
        this.protocolReviewerId = protocolReviewerId;
    }

    /**
     * Gets the protocolReviewer attribute. 
     * @return Returns the protocolReviewer.
     */
    public ProtocolReviewer getProtocolReviewer() {
        if (protocolReviewer == null) {
            protocolReviewer = new ProtocolReviewer();
        }
        return protocolReviewer;
    }

    /**
     * Sets the protocolReviewer attribute value.
     * @param protocolReviewer The protocolReviewer to set.
     */
    public void setProtocolReviewer(ProtocolReviewer protocolReviewer) {
        this.protocolReviewer = protocolReviewer;
    }

    public String getDocumentKey() {
        return Permissionable.PROTOCOL_ONLINE_REVIEW_KEY;
    }

    public String getDocumentNumberForPermission() {
        return getProtocolOnlineReviewId().toString();
    }

    public String getDocumentRoleTypeCode() {
        // TODO Auto-generated method stub  
        return null;
    }

    public String getLeadUnitNumber() {
        return getProtocol().getLeadUnitNumber();
    }

    public String getNamespace() {
        return "KC-PROTOCOL";
    }

    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(RoleConstants.IRB_REVIEWER);
        return roleNames;
    }

    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        qualifiedRoleAttributes.put(protocol.getDocumentKey(), protocol.getDocumentNumberForPermission());
    }

    public String getDocumentUnitNumber() {
        // TODO Auto-generated method stub  
        return null;
    }

    /**
     * Gets the committeeScheduleMinutes attribute. 
     * @return Returns the committeeScheduleMinutes.
     */
    public List<CommitteeScheduleMinute> getCommitteeScheduleMinutes() {
        return committeeScheduleMinutes;
    }

    /**
     * Sets the committeeScheduleMinutes attribute value.
     * @param committeeScheduleMinutes The committeeScheduleMinutes to set.
     */
    public void setCommitteeScheduleMinutes(List<CommitteeScheduleMinute> committeeScheduleMinutes) {
        this.committeeScheduleMinutes = committeeScheduleMinutes;
    }

    /**
     * Gets the protocolReviewDocument attribute. 
     * @return Returns the protocolReviewDocument.
     */
    public ProtocolOnlineReviewDocument getProtocolOnlineReviewDocument() {
        return protocolOnlineReviewDocument;
    }

    /**
     * Sets the protocolReviewDocument attribute value.
     * @param protocolOnlineReviewDocument The protocolReviewDocument to set.
     */
    public void setProtocolOnlineReviewDocument(ProtocolOnlineReviewDocument protocolOnlineReviewDocument) {
        this.protocolOnlineReviewDocument = protocolOnlineReviewDocument;
    }

    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        return super.buildListOfDeletionAwareLists();
    }

    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.protocolOnlineReviewId = null;
    }

    //    /**  
    //     * Gets the lookupProtocolNumber attribute.   
    //     * @return Returns the lookupProtocolNumber.  
    //     */  
    //    public String getLookupProtocolNumber() {  
    //        return protocol.getProtocolNumber();  
    //    }  
    //  
    //    /**  
    //     * Sets the lookupProtocolNumber attribute value.  
    //     * @param lookupProtocolNumber The lookupProtocolNumber to set.  
    //     */  
    //    public void setLookupProtocolNumber(String lookupProtocolNumber) {  
    //        this.lookupProtocolNumber = lookupProtocolNumber;  
    //    }  
    /**
     * Gets the lookupReviewerRolodexId attribute. 
     * @return Returns the lookupReviewerRolodexId.
     */
    public Integer getLookupReviewerRolodexId() {
        return lookupReviewerRolodexId;
    }

    /**
     * Sets the lookupReviewerRolodexId attribute value.
     * @param lookupReviewerRolodexId The lookupReviewerRolodexId to set.
     */
    public void setLookupReviewerRolodexId(Integer lookupReviewerRolodexId) {
        this.lookupReviewerRolodexId = lookupReviewerRolodexId;
    }

    /**
     * Gets the lookupReviewerPersonId attribute. 
     * @return Returns the lookupReviewerPersonId.
     */
    public String getLookupReviewerPersonId() {
        return lookupReviewerPersonId;
    }

    /**
     * Sets the lookupReviewerPersonId attribute value.
     * @param lookupReviewerPersonId The lookupReviewerPersonId to set.
     */
    public void setLookupReviewerPersonId(String lookupReviewerPersonId) {
        this.lookupReviewerPersonId = lookupReviewerPersonId;
    }

    /**
     * Gets the lookupReviewerRolodex attribute. 
     * @return Returns the lookupReviewerRolodex.
     */
    public Rolodex getLookupReviewerRolodex() {
        return lookupReviewerRolodex;
    }

    /**
     * Sets the lookupReviewerRolodex attribute value.
     * @param lookupReviewerRolodex The lookupReviewerRolodex to set.
     */
    public void setLookupReviewerRolodex(Rolodex lookupReviewerRolodex) {
        this.lookupReviewerRolodex = lookupReviewerRolodex;
    }

    /**
     * Gets the lookupReviewerPerson attribute. 
     * @return Returns the lookupReviewerPerson.
     */
    public KcPerson getLookupReviewerPerson() {
        return lookupReviewerPerson;
    }

    /**
     * Sets the lookupReviewerPerson attribute value.
     * @param lookupReviewerPerson The lookupReviewerPerson to set.
     */
    public void setLookupReviewerPerson(KcPerson lookupReviewerPerson) {
        this.lookupReviewerPerson = lookupReviewerPerson;
    }

    /**
     * Gets the loopReviewerFullName attribute. 
     * @return Returns the loopReviewerFullName.
     */
    public String getLookupReviewerFullName() {
        return protocolReviewer.getFullName();
    }

    /**
     * Sets the loopReviewerFullName attribute value.
     * @param loopReviewerFullName The loopReviewerFullName to set.
     */
    public void setLookupReviewerFullName(String loopReviewerFullName) {
    }

    /*
     * Returns if the review is active or not.
     * If the review has a status code of 'X' we return false;
     */
    public boolean isActive() {
        return !StringUtils.equals(ProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD, getProtocolOnlineReviewStatusCode());
    }

    public Protocol getLookupProtocol() {
        return lookupProtocol;
    }

    public void setLookupProtocol(Protocol lookupProtocol) {
        this.lookupProtocol = lookupProtocol;
    }

    public String getLookupProtocolOnlineReviewStatusCode() {
        return lookupProtocolOnlineReviewStatusCode;
    }

    public void setLookupProtocolOnlineReviewStatusCode(String lookupProtocolOnlineReviewStatusCode) {
        this.lookupProtocolOnlineReviewStatusCode = lookupProtocolOnlineReviewStatusCode;
    }

    /**
     * Returns the dateDue if it is non-empty; otherwise, returns the scheduled meeting date.
     * @return dateDue if not-null, otherwise scheduled meeting date
     */
    public Date getResultDueDate() {
        Date resultDueDate = null;
        if (dateDue != null) {
            resultDueDate = dateDue;
        } else {
            if (protocolSubmission != null && protocolSubmission.getCommitteeSchedule() != null) {
                resultDueDate = protocolSubmission.getCommitteeSchedule().getScheduledDate();
            }
        }
        return resultDueDate;
    }

    public String getActionsPerformed() {
        return actionsPerformed;
    }

    public void setActionsPerformed(String actionsPerformed) {
        this.actionsPerformed = actionsPerformed;
    }

    /**
     * 
     * This method is to add action performed for this OLR.  This used as audit trail and be used when undo
     * action.
     * @param action
     */
    public void addActionPerformed(String action) {
        if (StringUtils.isBlank(this.actionsPerformed)) {
            this.actionsPerformed = action + Constants.COLON + GlobalVariables.getUserSession().getPrincipalName();
        } else {
            this.actionsPerformed = this.actionsPerformed + Constants.SEMI_COLON + action + Constants.COLON + GlobalVariables.getUserSession().getPrincipalName();
        }
    }

    //    public boolean isLastActionReviewerApproved() {  
    //        return isStatusMatched(KewApiConstants.ROUTE_HEADER_ENROUTE_CD);  
    //    }  
    //      
    //    public boolean isLastActionAdminApproved() {  
    //        return isStatusMatched(KewApiConstants.ROUTE_HEADER_FINAL_CD);  
    //    }  
    public boolean isReviewerApproved() {
        return reviewerApproved;
    }

    public void setReviewerApproved(boolean reviewerApproved) {
        this.reviewerApproved = reviewerApproved;
    }

    public boolean isAdminAccepted() {
        return adminAccepted;
    }

    public void setAdminAccepted(boolean adminAccepted) {
        this.adminAccepted = adminAccepted;
    }

    /**
     * check if the OLR is reviewer or admin approved.  Then decided what's to do with the versioned
     * OLR doc
     */
    public boolean isStatusMatched(String docStatus) {
        boolean isMatched = false;
        if (StringUtils.isNotBlank(actionsPerformed)) {
            String[] actions = actionsPerformed.split(Constants.SEMI_COLON);
            String[] finalizeAction = actions[actions.length - 1].split(Constants.COLON);
            if (finalizeAction.length == 4) {
                isMatched = StringUtils.equals(finalizeAction[1], docStatus) && StringUtils.equals(finalizeAction[2], ProtocolOnlineReviewStatus.FINAL_STATUS_CD);
            }
        }
        return isMatched;
    }

    /*
     * get the reviewer user name of the last action performed if last action is reviewer approve OLR doc.
     */
    /**
     * get the reviewer user name of the last action performed if last action is reviewer approve OLR doc.
     */
    public String getReviewerUserName() {
        String[] actions = actionsPerformed.split(Constants.SEMI_COLON);
        String[] approveAction = actions[actions.length - 2].split(Constants.COLON);
        return approveAction[1];
    }

    /**
     * after undo last action, OLR will be versioned.  remove the last actionperformed from "actionsPerformed"
     * In case, this OLR is finalized again and undo.
     */
    public void removeLastAction() {
        int idx = -1;
        if (StringUtils.isNotEmpty(actionsPerformed)) {
            idx = actionsPerformed.lastIndexOf(Constants.SEMI_COLON);
        }
        if (idx < 0) {
            actionsPerformed = Constants.EMPTY_STRING;
        } else {
            actionsPerformed = actionsPerformed.substring(0, idx);
        }
    }

    public String getReviewerTypeCode() {
        return reviewerTypeCode;
    }

    public void setReviewerTypeCode(String reviewerTypeCode) {
        this.reviewerTypeCode = reviewerTypeCode;
    }

    public List<ProtocolReviewAttachment> getReviewAttachments() {
        return reviewAttachments;
    }

    public void setReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments) {
        this.reviewAttachments = reviewAttachments;
    }
}
