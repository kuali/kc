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
import java.util.List;

import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;



/**
 * Protocol Online Review service provides all necessary functionality to manage the online reviews.
 */
public interface ProtocolOnlineReviewService {
    
    static final String ONLINE_REVIEW_DOCUMENT_DESCRIPTION_FORMAT = "%s/Protocol# %s";
    
    /**
     * Document type code for online review.
     */
    String PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE_CODE = "PTRV";
    
    /**
     * Name of the online review document.
     */
    String PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE = "ProtocolOnlineReviewDocument";
    
    
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
    ProtocolOnlineReviewDocument createAndRouteProtocolOnlineReviewDocument(ProtocolSubmission protocolSubmission, ProtocolReviewer protocolReviewer, 
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
    ProtocolReviewer createProtocolReviewer(String principalId, boolean nonEmployeeFlag, String reviewerTypeCode, ProtocolSubmission protocolSubmission);
    
    /**
     * Get a list of current ProtocolReview documents associated with the protocol and current submission.
     * @param protocol 
     * @return
     */
    List<ProtocolOnlineReviewDocument> getProtocolReviewDocumentsForCurrentSubmission(Protocol protocol);
   
    /**
     * This method...
     * @param protocol
     * @return
     */
    List<CommitteeMembership> getAvailableCommitteeMembersForCurrentSubmission(Protocol protocol);

    /**
     * Get a list of current ProtocolReview documents associated with the protocol and current submission.
     * @param protocolNumber 
     * @return
     */
    List<ProtocolOnlineReview> getProtocolReviews(String protocolNumber);
    
    /**
     * This method returns a list of ProtocolOnlineReview BOs that are associated with submission.
     * @param submissionId the submissionId for which you want the ProtocolOnlineReviews.
     * @return
     */
    List<ProtocolOnlineReview> getProtocolReviews(Long submissionId);
    
    /**
     * Returns the online reviewer for the protocol submission corresponding to the principal id, if one exists.
     * @param personId The id of the person
     * @param nonEmployeeFlag Is the person an employee or not?  Determines if the personId is treated as a KIM principal or a rolodex id.
     * @param protocolSubmission The protocol submission
     * @return
     */
    ProtocolReviewer getProtocolReviewer(String personId, boolean nonEmployeeFlag, ProtocolSubmission protocolSubmission);
    
    /**
     * Returns true if the principal has an online review for the protocol submission.
     * @param personId The personId (Rolodex or principal) we are checking
     * @param nonEmployeeFlag Is the person an employee or not?  Determines if the personId is treated as a KIM principal or a rolodex id..
     * @param protocolSubmission The protocolSubmission
     * @return
     */
    boolean isProtocolReviewer(String principalId, boolean nonEmployeeFlag, ProtocolSubmission protocolSubmission);
    
    /**
     * Returns the ProtocolOnlineReviewDocument associated with the 
     * @param personId The personId (Rolodex or principal) we are checking
     * @param nonEmployeeFlag Is the person an employee or not?  Determines if the personId is treated as a KIM principal or a rolodex id.
     * @param protocolSubmission The protocolSubmission
     * @return
     */
    ProtocolOnlineReviewDocument getProtocolOnlineReviewDocument(String principalId, boolean nonEmployeeFlag, ProtocolSubmission protocolSubmission);
    
    /**
     * Determine if the protocol is in a state that can be reviewed.  Right now checks to see if there is an active submission.
     * @param protocol
     * @return
     */
    boolean isProtocolInStateToBeReviewed(Protocol protocol);
    
    
    /**
     * Return the ProtocolOnlineReview document to the reviewer.
     * 
     * @param review
     */
    void returnProtocolOnlineReviewDocumentToReviewer(ProtocolOnlineReviewDocument reviewDocument,String reason,String principalId);
    
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
    void removeOnlineReviewDocument(String personId, boolean nonEmployeeFlag, ProtocolSubmission submission, String annotation);
    
    /**
     * Cancels all online review documents associated with the submission.
     * 
     * @param submission
     * @param annotation
     */
    void cancelOnlineReviews(ProtocolSubmission submission, String annotation);
    
    /*
     * Remove all online reviews associated with the submission.
     * If an online review document is enroute it will be cancelled.  
     * If an online review is final no action will be taken on the document.
     * In both cases, the status will be set to X and the comments ( if any ) will be removed.
     * 
     * @param ProtocolSubmission the submission you want to remove all of the online reviews on.
     * @param annotation  The annotation to be applied to the workflow document when we cancel.
     *  
     */
    void removeOnlineReviews(ProtocolSubmission submission, String annotation);
    
    /**
     * Finalizes all online review documents associated with the submission.
     * 
     * 
     * @param submission
     * 
     */
    void finalizeOnlineReviews(ProtocolSubmission submission, String annotation);
    
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
    void moveOnlineReviews(ProtocolSubmission submission, ProtocolSubmission newSubmission);

}
