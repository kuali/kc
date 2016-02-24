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
package org.kuali.kra.protocol.onlinereview;

import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

import java.sql.Date;
import java.util.List;



/**
 * ProtocolBase Online Review service provides all necessary functionality to manage the online reviews.
 */
public interface ProtocolOnlineReviewService {
    
    static final String ONLINE_REVIEW_DOCUMENT_DESCRIPTION_FORMAT = "%s/Protocol# %s";
    
    /**
     * Assign an online review to a reviewer.  Reviewers must be a member of the committee.
     * This method will create a new ProtocolReviewDocument, and the associated BO and return it.
     * The method routes the document so it will show immediately in the reviewer's kew action list.
     * 
     * 
     * @param protocolSubmission The protocol submission for which the review is being requested.
     * @param protocolReviewer The user who will review the document.
     * @param documentDescription The description to be used on the associated workflow document
     * @param documentExplanation The explanation to be used on the associated workflow document
     * @param documentOrganizationDocumentNumber the organization document number to be used on the associated workflow document
     * @param documentRouteAnnotation - The annotation to apply to the document when routing it.
     * @param approveDocument Should the service approve the document with the given principalID.  In the case of
     * IRB Administrators this will approve the document through the initial node.
     * @param principalId The principalId to use when creating the document, and routing it into workflow.
     * @return The ProtocolReviewDocument that was created.
     */
    ProtocolOnlineReviewDocumentBase createAndRouteProtocolOnlineReviewDocument(ProtocolSubmissionBase protocolSubmission, ProtocolReviewer protocolReviewer, 
            String documentDescription, String documentExplanation, String documentOrganizationDocumentNumber, String documentRouteAnnotation, 
            boolean approveDocument, Date dateRequested, Date dateDue, String principalId);
    
    /**
     * Creates a reviewer for the protocol included in the protocolSubmission.
     * @param principalId The ID of the user who will review the document
     * @param nonEmployeeFlag Whether or not the user is an employee
     * @param reviewerTypeCode The type of reviewer (usually primary or secondary) that this user will be
     * @param protocolSubmission The submission which the user will review
     * @return the ProtocolReviewer that was created
     */
    ProtocolReviewer createProtocolReviewer(String principalId, boolean nonEmployeeFlag, String reviewerTypeCode, ProtocolSubmissionBase protocolSubmission);
    
    /**
     * Get a list of current ProtocolReview documents associated with the protocol and current submission.
     * @param protocol 
     * @return
     */
    List<ProtocolOnlineReviewDocumentBase> getProtocolReviewDocumentsForCurrentSubmission(ProtocolBase protocol);
   

    List<CommitteeMembershipBase> getAvailableCommitteeMembersForCurrentSubmission(ProtocolBase protocol);

    /**
     * Get a list of current ProtocolReview documents associated with the protocol and current submission.
     * @param protocolNumber 
     * @return
     */
    List<ProtocolOnlineReviewBase> getProtocolReviews(String protocolNumber);
    
    /**
     * This method returns a list of ProtocolOnlineReviewBase BOs that are associated with submission.
     * @param submissionId the submissionId for which you want the ProtocolOnlineReviews.
     * @return
     */
    List<ProtocolOnlineReviewBase> getProtocolReviews(Long submissionId);
    
    /**
     * Returns the online reviewer for the protocol submission corresponding to the principal id, if one exists.
     * @param personId The id of the person
     * @param nonEmployeeFlag Is the person an employee or not?  Determines if the personId is treated as a KIM principal or a rolodex id.
     * @param protocolSubmission The protocol submission
     * @return
     */
    ProtocolReviewer getProtocolReviewer(String personId, boolean nonEmployeeFlag, ProtocolSubmissionBase protocolSubmission);
    
    /**
     * Returns true if the principal has an online review for the protocol submission.
     * @param personId The personId (Rolodex or principal) we are checking
     * @param nonEmployeeFlag Is the person an employee or not?  Determines if the personId is treated as a KIM principal or a rolodex id..
     * @param protocolSubmission The protocolSubmission
     * @return
     */
    boolean isProtocolReviewer(String principalId, boolean nonEmployeeFlag, ProtocolSubmissionBase protocolSubmission);
    
    /**
     * Returns the ProtocolOnlineReviewDocumentBase associated with the 
     * @param personId The personId (Rolodex or principal) we are checking
     * @param nonEmployeeFlag Is the person an employee or not?  Determines if the personId is treated as a KIM principal or a rolodex id.
     * @param protocolSubmission The protocolSubmission
     * @return
     */
    ProtocolOnlineReviewDocumentBase getProtocolOnlineReviewDocument(String principalId, boolean nonEmployeeFlag, ProtocolSubmissionBase protocolSubmission);
    
    /**
     * Determine if the protocol is in a state that can be reviewed.  Right now checks to see if there is an active submission.
     * @param protocol
     * @return
     */
    boolean isProtocolInStateToBeReviewed(ProtocolBase protocol);
    
    
    /**
     * Return the ProtocolOnlineReviewBase document to the reviewer.
     * 
     * @param review
     */
    void returnProtocolOnlineReviewDocumentToReviewer(ProtocolOnlineReviewDocumentBase reviewDocument,String reason,String principalId);
    
    /**
     * Sets the status to Cancelled/Removed.  If the review document is enroute, then we do a superuser disapprove on it.  If it is in saved or initiated
     * state, then a super user cancel is performed on it. Those two actions will cause all review comments to be deleted.  If the document is in final state
     * we simply delete all of the review comments.
     * 
     * @param personId
     * @param nonEmployeeFlag
     * @param submission
     * @param annotation
     */
    void removeOnlineReviewDocument(String personId, boolean nonEmployeeFlag, ProtocolSubmissionBase submission, String annotation);
    
    /**
     * Cancels all online review documents associated with the submission.
     * 
     * @param submission
     * @param annotation
     */
    void cancelOnlineReviews(ProtocolSubmissionBase submission, String annotation);

    /**
     * Finalizes all online review documents associated with the submission.
     * 
     * 
     * @param submission
     * 
     */
    void finalizeOnlineReviews(ProtocolSubmissionBase submission, String annotation);
    
    /**
     * Generate the standard document description for OLR documents.
     * Gurantees it will be less than or equal to the 40 char limit
     * by truncating the PI name.
     * 
     * @param protocolNumber the protocol number to add to the description
     * @param piName The name of the pi to add to the description.
     * @return String to be used in the description.
     */
    String getProtocolOnlineReviewDocumentDescription( String protocolNumber, String piName );
    
    /**
     * 
     * This method is to get the online reviews, loop through them and reassign them to new protocol.
     * When do SMR/SRR, a new protocol will be versioned.  Attach the existing OLR to the new protocol submission.
     * @param submission
     * @param newSubmission
     */
    void moveOnlineReviews(ProtocolSubmissionBase submission, ProtocolSubmissionBase newSubmission);

}
