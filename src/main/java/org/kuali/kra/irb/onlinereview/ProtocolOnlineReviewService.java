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

import java.util.List;

import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.kew.exception.WorkflowException;

/**
 * Protocol Online Review service provides all necessary functionality to manage the online reviews.
 */
public interface ProtocolOnlineReviewService {
    
    
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
     * @param protocol The protocol for which the review is being requested.
     * @param personId The id of the person being made a reviewer.
     * @param documentDescription The description to be used on the associated workflow document
     * @param documentExplanation The explanation to be used on the associated workflow document
     * @param documentOrganizationDocumentNumber the organization document number to be used on the associated workflow document
     * @param documentRouteAnnotation - The annotation to apply to the document when routing it.
     * @param approveDocument Should the service approve the document with the given principalID.  In the case of
     * IRB Administrators this will approve the document through the initial node.
     * @param principalId The principalId to use when creating the document, and routing it into workflow.
     * @return The ProtocolReviewDocument that was created.
     */
   
    ProtocolOnlineReviewDocument createAndRouteProtocolOnlineReviewDocument(Protocol protocol, 
            String personId, 
            String documentDescription,
            String documentExplanation,
            String documentOrganizationDocumentNumber,
            String documentRouteAnnotation,
            boolean approveDocument,
            String principalId) throws WorkflowException;
    
    /**
     * Assign an online review to a reviewer.  Reviewers must be a member of the committee.
     * This method will create a new ProtocolReviewDocument, and the associated BO and return it.
     * This method does not route the document before it is returned.  An IRB Admin will need to
     * approve it before it will go to the reviewers action list. 
     * 
     * @param protocol
     * @param personId
     * @param documentDescription
     * @param documentExplanation
     * @param documentOrganizationDocumentNumber
     * @param principalId
     * @return
     * @throws WorkflowException
     */
    public ProtocolOnlineReviewDocument createProtocolOnlineReviewDocument(Protocol protocol, String personId, String documentDescription, String documentExplanation, String documentOrganizationDocumentNumber, String principalId)
    throws WorkflowException;
    
    
    /**
     * Submits the ProtocolReviewDocument to workflow.  The document should then show in the
     * reviewer's action list.  
     * 
     * @param protocolReviewDocument The document you wish to submit. 
     */
    void submitOnlineReviwToWorkflow(ProtocolOnlineReviewDocument protocolReviewDocument);
    
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
     * This method returns a list of ProtocolOnlineReview BOs that are associated with submission.
     * @param submissionId the submissionId for which you want the ProtocolOnlineReviews.
     * @return
     */
    List<ProtocolOnlineReview> getOnlineReviewersForProtocolSubmission(Long submissionId);
    
    
    /**
     * Get a list of current ProtocolReview documents associated with the protocol and current submission.
     * @param protocolNumber 
     * @return
     */
    List<ProtocolOnlineReview> getProtocolReviewsForCurrentSubmission(String protocolNumber);
    
    /**
     * Get the other ProtocolOnlineReviews - the list will not include the provided review.
     * @param ProtocolOnlineReview - the review you are not interested in.
     * @return
     */
    List<ProtocolOnlineReview> getOtherProtocolOnlineReviews(ProtocolOnlineReview review);
    
    
    /**
     * Returns true if the principal has an online review for the protocol and current submission.
     * @param principalId The principalId we are checking
     * @param protocol The protocol.
     * @return
     */
    boolean isUserAnOnlineReviewerOfProtocol(String principalId, Protocol protocol);
    
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
    
}
