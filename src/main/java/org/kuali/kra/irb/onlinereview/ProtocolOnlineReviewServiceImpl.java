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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.kew.KraDocumentRejectionService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kim.service.PersonService;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.kuali.rice.kns.workflow.service.WorkflowDocumentService;

public class ProtocolOnlineReviewServiceImpl implements ProtocolOnlineReviewService {

    private static final Log LOG = LogFactory.getLog(ProtocolOnlineReviewServiceImpl.class);
    
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private KraAuthorizationService kraAuthorizationService;
    private ProtocolAssignReviewersService protocolAssignReviewersService;
    private IdentityManagementService identityManagementService;
    private CommitteeService committeeService;
    private KraDocumentRejectionService kraDocumentRejectionService;
    private ProtocolFinderDao protocolFinderDao;
    private ReviewCommentsService reviewCommentsService;
    
    private String reviewerApproveNodeName;
    private String irbAdminApproveNodeName;
    
    @SuppressWarnings("unchecked")
    private PersonService personService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#createAndRouteProtocolOnlineReviewDocument(
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmission, org.kuali.kra.irb.actions.submit.ProtocolReviewerBean, java.lang.String, java.lang.String, 
     *      java.lang.String, java.lang.String, boolean, java.sql.Date, java.sql.Date, java.lang.String)
     */
    public ProtocolOnlineReviewDocument createAndRouteProtocolOnlineReviewDocument(ProtocolSubmission protocolSubmission, 
                                                                                   ProtocolReviewer protocolReviewer,
                                                                                   String documentDescription,
                                                                                   String documentExplanation,
                                                                                   String documentOrganizationDocumentNumber,
                                                                                   String documentRouteAnnotation,
                                                                                   boolean initialApproval,
                                                                                   Date dateRequested,
                                                                                   Date dateDue, 
                                                                                   String principalId) {
        
        ProtocolOnlineReviewDocument document = null;
        
        try {
            if (LOG.isDebugEnabled()) {
                String protocolNumber = protocolSubmission.getProtocolNumber();
                Integer submissionNumber = protocolSubmission.getSubmissionNumber();
                LOG.debug(String.format("Assigning online reviewer [%s] to protocol [%s].", protocolReviewer, protocolNumber));
                LOG.debug(String.format("Current submission for protocol %s is %s.", protocolNumber, submissionNumber));
            }

            document = createProtocolOnlineReviewDocument(protocolSubmission, protocolReviewer, documentDescription, documentExplanation, 
                    documentOrganizationDocumentNumber, dateRequested, dateDue, principalId);
            
            documentService.routeDocument(document, "Review Requested by PI during protocol submission.", new ArrayList<String>());
            
            if (initialApproval) {
                documentService.approveDocument(document, "", new ArrayList<String>());
            }
        } catch (WorkflowException e) {
            String errorString = String.format("WorkflowException creating new ProtocolOnlineReviewDocument for reviewer %s, protocol %s", 
                    protocolReviewer.getPersonId(), protocolSubmission.getProtocolNumber());
            LOG.error(errorString, e);
            throw new RuntimeException(errorString, e);
        }
        
        return document;
    }
    
    /**
     * Creates a new ProtocolReviewDocument.  
     * 
     * Handles creating the workflow document, and the underlying ProtocolReview BO linking the protocol, submission, and reviewer.
     * 
     * @param protocolSubmission The protocol submission
     * @param protocolReviewerBean The bean that holds
     * @param documentDescription the description for the created document
     * @param documentExplanation the explanation for the created document
     * @param documentOrganizationNumber the organizationNumber for the created document
     * @param principalId The principalId to use when creating the workflow document. Usually this should be the principal of the user creating the review.
     * @return
     * @throws WorkflowException
     */
    protected ProtocolOnlineReviewDocument createProtocolOnlineReviewDocument(ProtocolSubmission protocolSubmission, 
                                                                            ProtocolReviewer protocolReviewer, 
                                                                            String documentDescription,
                                                                            String documentExplanation,
                                                                            String documentOrganizationDocumentNumber,
                                                                            Date dateRequested,
                                                                            Date dateDue,
                                                                            String principalId) throws WorkflowException {
        
        ProtocolOnlineReviewDocument protocolReviewDocument;
        
        Person person = personService.getPerson(principalId);
        KualiWorkflowDocument workflowDocument = getWorkflowDocumentService().createWorkflowDocument(PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE, person);
        
        DocumentHeader docHeader = new DocumentHeader();
        docHeader.setWorkflowDocument(workflowDocument);
        docHeader.setDocumentNumber(workflowDocument.getRouteHeaderId().toString());
        protocolReviewDocument = new ProtocolOnlineReviewDocument();
        protocolReviewDocument.setDocumentNumber(docHeader.getDocumentNumber());
        protocolReviewDocument.setDocumentHeader(docHeader);
        
        protocolReviewDocument.getProtocolOnlineReview().setProtocol(protocolSubmission.getProtocol());
        protocolReviewDocument.getProtocolOnlineReview().setProtocolId(protocolSubmission.getProtocolId());
        
        protocolReviewDocument.getProtocolOnlineReview().setProtocolSubmission(protocolSubmission);
        protocolReviewDocument.getProtocolOnlineReview().setSubmissionIdFk(protocolSubmission.getSubmissionId());
        protocolReviewDocument.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.SAVED_STATUS_CD);
        protocolReviewDocument.getProtocolOnlineReview().setDateRequested(dateRequested == null ? new Date((new java.util.Date()).getTime()) : dateRequested);
        protocolReviewDocument.getProtocolOnlineReview().setDateDue(dateDue);
        
        protocolReviewDocument.getProtocolOnlineReview().setProtocolReviewerId(protocolReviewer.getProtocolReviewerId());
        protocolReviewDocument.getProtocolOnlineReview().setProtocolReviewer(protocolReviewer);
        
        docHeader.setDocumentDescription(documentDescription);
        docHeader.setOrganizationDocumentNumber(documentOrganizationDocumentNumber);
        docHeader.setExplanation(documentExplanation);
        
        documentService.saveDocument(protocolReviewDocument);
        return protocolReviewDocument;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#createProtocolReviewer(java.lang.String, boolean, java.lang.String, 
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public ProtocolReviewer createProtocolReviewer(String principalId, 
                                                   boolean nonEmployeeFlag, 
                                                   String reviewerTypeCode, 
                                                   ProtocolSubmission protocolSubmission) {
        ProtocolReviewer reviewer = new ProtocolReviewer();
        reviewer.setProtocolIdFk(protocolSubmission.getProtocolId());
        reviewer.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        reviewer.setProtocolNumber(protocolSubmission.getProtocolNumber());
        reviewer.setSequenceNumber(protocolSubmission.getSequenceNumber());
        reviewer.setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        if (!nonEmployeeFlag) {
            reviewer.setPersonId(principalId);
        } else {
            reviewer.setRolodexId(Integer.parseInt(principalId));
        }
        reviewer.setNonEmployeeFlag(nonEmployeeFlag);
        reviewer.setReviewerTypeCode(reviewerTypeCode);
        
        businessObjectService.save(reviewer);
        
        return reviewer;
    }

    
    /**
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getProtocolReviewDocumentsForCurrentSubmission(org.kuali.kra.irb.Protocol)
     */
    public List<ProtocolOnlineReviewDocument> getProtocolReviewDocumentsForCurrentSubmission(Protocol protocol) {
        List<ProtocolOnlineReviewDocument> onlineReviewDocuments = new ArrayList<ProtocolOnlineReviewDocument>();
        ProtocolSubmission submission = protocol.getProtocolSubmission();
        List<ProtocolOnlineReview> reviews = findProtocolOnlineReviews(protocol.getProtocolId(), submission.getSubmissionId());
        for (ProtocolOnlineReview review : reviews) {
            if (review.isActive()) {
                review.refresh();
                try {
                    onlineReviewDocuments.add((ProtocolOnlineReviewDocument)(documentService.getByDocumentHeaderId( review.getProtocolOnlineReviewDocument().getDocumentNumber() )));
                }
                catch (WorkflowException e) {
                    throw new RuntimeException( String.format( "Could not load ProtocolOnlineReview docuemnt %s due to WorkflowException: %s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e.getMessage() ),e);
                }
            }
        }
        return onlineReviewDocuments;
    }

    
    /**
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getAvailableCommitteeMembersForCurrentSubmission(org.kuali.kra.irb.Protocol)
     */
    public List<CommitteeMembership> getAvailableCommitteeMembersForCurrentSubmission(Protocol protocol) {
        List<CommitteeMembership> results = new ArrayList<CommitteeMembership>();
        
        ProtocolSubmission submission = protocol.getProtocolSubmission();
        submission.refreshReferenceObject("protocolOnlineReviews");
        if (LOG.isDebugEnabled()) { 
            LOG.debug(String.format("Fetching available committee members for protocol %s, submission %s", protocol.getProtocolNumber(), 
                    submission.getSubmissionNumber()));
        }
        
        List<ProtocolOnlineReview> currentReviews = submission.getProtocolOnlineReviews();
        List<CommitteeMembership> committeeMembers = getCommitteeService().getAvailableMembers(submission.getCommitteeId(), submission.getScheduleId());
        //TODO: Make this better.
        for (CommitteeMembership member : committeeMembers) {
            boolean found = false;
            for (ProtocolOnlineReview review : currentReviews) {
                if (review.getProtocolReviewer().isProtocolReviewerFromCommitteeMembership(member) && review.isActive()) {
                    found=true;
                    break;
                }
            }
            if (!found && !isProtocolPersonnel(protocol, member)) {
                results.add(member);
            }
        }
        return results;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getProtocolReviewsForCurrentSubmission(java.lang.String)
     */
    public List<ProtocolOnlineReview> getProtocolReviews(String protocolNumber) {
        Protocol protocol = protocolFinderDao.findCurrentProtocolByNumber(protocolNumber);
        List<ProtocolOnlineReview> reviews = null;
        if (protocol != null && protocol.getProtocolSubmission() != null) {
            reviews = protocol.getProtocolSubmission().getProtocolOnlineReviews();
        } else {
            reviews = new ArrayList<ProtocolOnlineReview>();
        }
        return reviews;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getProtocolReviews(java.lang.Long)
     */
    public List<ProtocolOnlineReview> getProtocolReviews(Long submissionId) {
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        ProtocolSubmission submission = getBusinessObjectService().findBySinglePrimaryKey(ProtocolSubmission.class, submissionId);
        if (submission != null) {
            for(ProtocolOnlineReview review : submission.getProtocolOnlineReviews()) {
                if(review.isActive()) {
                    reviews.add(review);
                }
            }
        }
        return reviews;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getProtocolReviewer(java.lang.String, 
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public ProtocolReviewer getProtocolReviewer(String personId, boolean nonEmployeeFlag, ProtocolSubmission protocolSubmission) {
        ProtocolReviewer protocolReviewer = null;
        if (protocolSubmission != null) {
            for (ProtocolOnlineReview protocolOnlineReview : protocolSubmission.getProtocolOnlineReviews()) {
                if (protocolOnlineReview.getProtocolReviewer().isPersonIdProtocolReviewer(personId,nonEmployeeFlag) 
                        && protocolOnlineReview.isActive()) {
                    protocolReviewer = protocolOnlineReview.getProtocolReviewer();
                    break;
                }
            }
        }
        return protocolReviewer;
    }

    
    /**
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getProtocolOnlineReviewDocument(java.lang.String, boolean, org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public ProtocolOnlineReviewDocument getProtocolOnlineReviewDocument(String personId, boolean nonEmployeeFlag, ProtocolSubmission protocolSubmission) {
        ProtocolOnlineReviewDocument protocolOnlineReviewDocument = null;
        if (protocolSubmission != null) {
            for (ProtocolOnlineReview protocolOnlineReview : protocolSubmission.getProtocolOnlineReviews()) {
                if (protocolOnlineReview.getProtocolReviewer().isPersonIdProtocolReviewer(personId,nonEmployeeFlag) 
                        && protocolOnlineReview.isActive()) {
                    try {
                        protocolOnlineReviewDocument =  (ProtocolOnlineReviewDocument)getDocumentService().getByDocumentHeaderId(protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber());
                    }
                    catch (WorkflowException e) {
                       if (LOG.isDebugEnabled()) {
                           String errorMessage = String.format("WorkflowException encountered while looking up document number %s for ProtocolOnlineReviewDocument associated with (submissionId=%s,personId=%s,nonEmployeeFlag=%s",
                                   protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber(),
                                   protocolSubmission.getSubmissionId(),
                                   personId,nonEmployeeFlag);
                           
                           LOG.error(errorMessage,e);
                           throw new RuntimeException(errorMessage,e);
                       }
                    }
                }
            }
        }
        return protocolOnlineReviewDocument;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#isUserAnOnlineReviewerOfProtocol(java.lang.String, org.kuali.kra.irb.Protocol)
     */
    public boolean isProtocolReviewer(String personId, boolean nonEmployeeFlag, ProtocolSubmission protocolSubmission) {
        boolean isReviewer = false;
        if (protocolSubmission != null) {
            for (ProtocolOnlineReview review : protocolSubmission.getProtocolOnlineReviews()) {
                if (review.getProtocolReviewer().isPersonIdProtocolReviewer(personId,nonEmployeeFlag) && review.isActive()) {
                    isReviewer = true;
                    break;
                }
            }
        }
        return isReviewer;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#isProtocolInStateToBeReviewed(org.kuali.kra.irb.Protocol)
     */
    public boolean isProtocolInStateToBeReviewed(Protocol protocol) {
        boolean isReviewable = false;
        ProtocolSubmission submission = protocol.getProtocolSubmission();
        if (submission != null) {
            try {
                isReviewable = StringUtils.isNotEmpty(submission.getScheduleId()); 
                isReviewable &= (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) 
                        || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA));
                ProtocolDocument protocolDocument = (ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                isReviewable &= getKraWorkflowService().isDocumentOnNode(protocolDocument, Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME);
            } catch (WorkflowException e) {
                String errorString = String.format("WorkflowException checking route node for creating new ProtocolOnlineReviewDocument " +
                		"for protocol %s", submission.getProtocolNumber());
                LOG.error(errorString, e);
                throw new RuntimeException(errorString, e);
            }
        }
        return isReviewable;
    }

    public void returnProtocolOnlineReviewDocumentToReviewer(ProtocolOnlineReviewDocument reviewDocument, String reason, String principalId) {
        kraDocumentRejectionService.reject(reviewDocument, reason, principalId, (String)null, reviewerApproveNodeName);     
    }
    
    /**
     * Finds and returns all protocol online reviews for the protocolId and submissionId.
     * @param protocolId
     * @param submissionIdFk
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<ProtocolOnlineReview> findProtocolOnlineReviews(Long protocolId,
                                                                 Long submissionIdFk) {
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        if (protocolId != null && submissionIdFk != null) {
            Map<String,Object> hashMap = new HashMap<String,Object>();
            hashMap.put("protocolId", protocolId);
            hashMap.put("submissionIdFk", submissionIdFk);
            reviews.addAll(getBusinessObjectService().findMatchingOrderBy(ProtocolOnlineReview.class, hashMap, "dateRequested", false));
        }
        return reviews;
    }

    protected void cancelOnlineReviewDocument(ProtocolOnlineReviewDocument protocolOnlineReviewDocument, ProtocolSubmission submission, String annotation) {
        try {
           
            final String principalId = identityManagementService.getPrincipalByPrincipalName(KNSConstants.SYSTEM_USER).getPrincipalId();
            WorkflowDocument workflowDocument = new WorkflowDocument(principalId, Long.parseLong(protocolOnlineReviewDocument.getDocumentNumber()));
            
            if (workflowDocument.stateIsEnroute() 
                ||
                workflowDocument.stateIsInitiated()
                ||
                workflowDocument.stateIsSaved()
                ) {
                workflowDocument.superUserCancel(String.format("Review Cancelled from assign reviewers action by %s", GlobalVariables.getUserSession().getPrincipalId()));
            }
        } catch(WorkflowException e) {
            String errorMessage = String.format("Workflow exception generated while executing superUserCancel on document %s in removeOnlineReviewDocument. Message: %s",protocolOnlineReviewDocument.getDocumentNumber(), e.getMessage());
            LOG.error(errorMessage);
            throw new RuntimeException(errorMessage,e);
        }
    }
    
    protected void finalizeOnlineReviewDocument(ProtocolOnlineReviewDocument protocolOnlineReviewDocument, ProtocolSubmission submission, String annotation) {
        
        try {

            final String principalId = identityManagementService.getPrincipalByPrincipalName(KNSConstants.SYSTEM_USER).getPrincipalId();
            WorkflowDocument workflowDocument = new WorkflowDocument(principalId, Long.parseLong(protocolOnlineReviewDocument.getDocumentNumber()));
            ProtocolOnlineReview review = protocolOnlineReviewDocument.getProtocolOnlineReview();
            review.addActionPerformed("Finalize:"+workflowDocument.getRouteHeader().getDocRouteStatus()+":"+review.getProtocolOnlineReviewStatusCode());

            
            if (workflowDocument.stateIsEnroute()
            ||
            workflowDocument.stateIsInitiated()
            ||
            workflowDocument.stateIsSaved()
            ) {
                workflowDocument.superUserApprove(annotation);
            }
        } catch(WorkflowException e) {
            String errorMessage = String.format("Workflow exception generated while executing superUserApprove on document %s in finalizeOnlineReviewDocument. Message:%s",protocolOnlineReviewDocument.getDocumentNumber(), e.getMessage());
            LOG.error(errorMessage);
            throw new RuntimeException(errorMessage,e);
        }
        
    }
    
    protected void removeOnlineReviewDocument(ProtocolOnlineReviewDocument protocolOnlineReviewDocument, ProtocolSubmission submission, String annotation) {

        if (protocolOnlineReviewDocument != null) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(String.format("Found protocolOnlineReviewDocument %s, removing it.",protocolOnlineReviewDocument.getDocumentNumber()));
            }
            cancelOnlineReviewDocument(protocolOnlineReviewDocument, submission, annotation);
            protocolOnlineReviewDocument.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
            
            List<CommitteeScheduleMinute> reviewComments = protocolOnlineReviewDocument.getProtocolOnlineReview().getCommitteeScheduleMinutes();
            List<CommitteeScheduleMinute> deletedReviewComments = new ArrayList<CommitteeScheduleMinute>();
            getReviewerCommentsService().deleteAllReviewComments(reviewComments, deletedReviewComments);
            getReviewerCommentsService().saveReviewComments(reviewComments, deletedReviewComments);

//            for (ProtocolReviewer reviewer : submission.getProtocolReviewers()) {
//                if (protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolReviewer().getProtocolReviewerId().equals(reviewer.getProtocolReviewerId())) {
//                    protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolReviewer().setSubmissionIdFk(null);
//                    boolean success = submission.getProtocolReviewers().remove(reviewer);
//                }
//            }
            submission.getProtocolReviewers().remove(protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolReviewer());
            getBusinessObjectService().save(protocolOnlineReviewDocument.getProtocolOnlineReview());
        } 
                
    }
        
    /**
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#removeOnlineReviewDocument(java.lang.String, boolean, org.kuali.kra.irb.actions.submit.ProtocolSubmission, java.lang.String)
     */
    public void removeOnlineReviewDocument(String personId, boolean nonEmployeeFlag, ProtocolSubmission submission, String annotation) {
        ProtocolOnlineReviewDocument protocolOnlineReviewDocument = this.getProtocolOnlineReviewDocument(personId, nonEmployeeFlag, submission);
        
        ProtocolOnlineReview submissionsProtocolOnlineReview = null;
        for (ProtocolOnlineReview rev : submission.getProtocolOnlineReviews()) {
            if (rev.getProtocolOnlineReviewId().equals(protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolOnlineReviewId())) {
                submissionsProtocolOnlineReview = rev;
                break;
            }
        }
        
        if (submissionsProtocolOnlineReview == null) {
            throw new IllegalStateException("Could not match OnlineReview document being removed to a protocolOnlineReview in the submission.");
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Processing request to remove online review for (personId=%s,nonEmployeeFlag=%s) from (protocol=%s,submission=%s)",personId,nonEmployeeFlag,submission.getProtocol().getProtocolNumber(),submission.getSubmissionNumber()));
        }
        
        if (protocolOnlineReviewDocument != null) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(String.format("Found protocolOnlineReviewDocument %s, removing it.",protocolOnlineReviewDocument.getDocumentNumber()));
            }
            cancelOnlineReviewDocument(protocolOnlineReviewDocument, submission, annotation);
            submissionsProtocolOnlineReview.setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
            
            List<CommitteeScheduleMinute> reviewComments = protocolOnlineReviewDocument.getProtocolOnlineReview().getCommitteeScheduleMinutes();
            List<CommitteeScheduleMinute> deletedReviewComments = new ArrayList<CommitteeScheduleMinute>();
            getReviewerCommentsService().deleteAllReviewComments(reviewComments, deletedReviewComments);
            getReviewerCommentsService().saveReviewComments(reviewComments, deletedReviewComments);
            
//            for (ProtocolReviewer reviewer : submission.getProtocolReviewers()) {
//                if (protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolReviewer().getProtocolReviewerId().equals(reviewer.getProtocolReviewerId())) {
//                    submissionsProtocolOnlineReview.getProtocolReviewer().setSubmissionIdFk(null);
//                    boolean success = submission.getProtocolReviewers().remove(reviewer);
//                    LOG.info(success);
//                }
//            }
//            
            getBusinessObjectService().save(submissionsProtocolOnlineReview);
        
        } else {
            LOG.warn(String.format("Protocol Online Review document could not be found for (personId=%s,nonEmployeeFlag=%s) from (protocol=%s,submission=%s)",personId,nonEmployeeFlag,submission.getProtocol().getProtocolNumber(),submission.getSubmissionNumber()));
        }
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#cancelOnlineReviews(org.kuali.kra.irb.actions.submit.ProtocolSubmission, 
     *      java.lang.String)
     */
    public void cancelOnlineReviews(ProtocolSubmission submission, String annotation) {
        //get the online reviews, loop through them and finalize them if necessary.
        for (ProtocolOnlineReview review : submission.getProtocolOnlineReviews()) {
            cancelOnlineReviewDocument(review.getProtocolOnlineReviewDocument(), submission, annotation);
        }
    }
    
    /**
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#finalizeOnlineReviews(org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public void finalizeOnlineReviews(ProtocolSubmission submission, String annotation) {
        //get the online reviews, loop through them and finalize them if necessary.
        for(ProtocolOnlineReview review : submission.getProtocolOnlineReviews()) {
//            review.addActionPerformed("Finalize:"+review.getProtocolOnlineReviewDocument().getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus()+":"+review.getProtocolOnlineReviewStatusCode());
            finalizeOnlineReviewDocument(review.getProtocolOnlineReviewDocument(), submission, annotation);
        }
    }
    
    /**
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#finalizeOnlineReviews(org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public void removeOnlineReviews(ProtocolSubmission submission, String annotation) {
        //get the online reviews, loop through them and finalize them if necessary.
        for(ProtocolOnlineReview review : submission.getProtocolOnlineReviews()) {
            removeOnlineReviewDocument(review.getProtocolOnlineReviewDocument(), submission, annotation);
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#moveOnlineReviews(org.kuali.kra.irb.actions.submit.ProtocolSubmission, org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public void moveOnlineReviews(ProtocolSubmission submission, ProtocolSubmission newSubmission) {
        newSubmission.setProtocolOnlineReviews(new ArrayList<ProtocolOnlineReview>());
        for (ProtocolOnlineReview review : submission.getProtocolOnlineReviews()) {
            review.setProtocol(newSubmission.getProtocol());
            review.setProtocolId(newSubmission.getProtocol().getProtocolId());
            review.setSubmissionIdFk(newSubmission.getSubmissionId());
            if (CollectionUtils.isNotEmpty(review.getCommitteeScheduleMinutes())) {
                for (CommitteeScheduleMinute comment : review.getCommitteeScheduleMinutes()) {
                    comment.setProtocolIdFk(review.getProtocolId());
                    comment.setScheduleIdFk(newSubmission.getScheduleIdFk());
                }
            }
            newSubmission.getProtocolOnlineReviews().add(review);
        }
    }

    
    /*
     * Getters and setters for needed services.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    public DocumentService getDocumentService() {
        return documentService;
    }
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    public KraAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }
    public void setProtocolAssignReviewersService(ProtocolAssignReviewersService protocolAssignReviewersService) {
        this.protocolAssignReviewersService = protocolAssignReviewersService;
    }
    public ProtocolAssignReviewersService getProtocolAssignReviewersService() {
        return protocolAssignReviewersService;
    }
    /**
     * Gets the workflowDocumentService attribute. 
     * @return Returns the workflowDocumentService.
     */
    public WorkflowDocumentService getWorkflowDocumentService() {
        return KraServiceLocator.getService(WorkflowDocumentService.class);
    }
    /**
     * Gets the workflowDocumentService attribute. 
     * @return Returns the workflowDocumentService.
     */
    public KraWorkflowService getKraWorkflowService() {
        return KraServiceLocator.getService(KraWorkflowService.class);
    }
    /**
     * Gets the personService attribute. 
     * @return Returns the personService.
     */
    @SuppressWarnings("unchecked")
    public PersonService getPersonService() {
        return personService;
    }
    /**
     * Sets the personService attribute value.
     * @param personService The personService to set.
     */
    @SuppressWarnings("unchecked")
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    /**
     * Gets the identityManagementService attribute. 
     * @return Returns the identityManagementService.
     */
    public IdentityManagementService getIdentityManagementService() {
        return identityManagementService;
    }
    /**
     * Sets the identityManagementService attribute value.
     * @param identityManagementService The identityManagementService to set.
     */
    public void setIdentityManagementService(IdentityManagementService identityManagementService) {
        this.identityManagementService = identityManagementService;
    }
    
    /**
     * Gets the committeeService attribute. 
     * @return Returns the committeeService.
     */
    public CommitteeService getCommitteeService() {
        return committeeService;
    }
    /**
     * Sets the committeeService attribute value.
     * @param committeeService The committeeService to set.
     */
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    /**
     * Gets the irbAdminApproveNodeName attribute. 
     * @return Returns the irbAdminApproveNodeName.
     */
    public String getIrbAdminApproveNodeName() {
        return irbAdminApproveNodeName;
    }

    /**
     * Sets the irbAdminApproveNodeName attribute value.
     * @param irbAdminApproveNodeName The irbAdminApproveNodeName to set.
     */
    public void setIrbAdminApproveNodeName(String irbAdminApproveNodeName) {
        this.irbAdminApproveNodeName = irbAdminApproveNodeName;
    }

    /**
     * Gets the reviewerApproveNodeName attribute. 
     * @return Returns the reviewerApproveNodeName.
     */
    public String getReviewerApproveNodeName() {
        return reviewerApproveNodeName;
    }

    /**
     * Sets the reviewerApproveNodeName attribute value.
     * @param reviewerApproveNodeName The reviewerApproveNodeName to set.
     */
    public void setReviewerApproveNodeName(String reviewerApproveNodeName) {
        this.reviewerApproveNodeName = reviewerApproveNodeName;
    }


    /**
     * Gets the kraDocumentRejectionService attribute. 
     * @return Returns the kraDocumentRejectionService.
     */
    public KraDocumentRejectionService getKraDocumentRejectionService() {
        return kraDocumentRejectionService;
    }

    /**
     * Sets the kraDocumentRejectionService attribute value.
     * @param kraDocumentRejectionService The kraDocumentRejectionService to set.
     */
    public void setKraDocumentRejectionService(KraDocumentRejectionService kraDocumentRejectionService) {
        this.kraDocumentRejectionService = kraDocumentRejectionService;
    }

    /**
     * Sets the protocolFinderDao attribute value.
     * @param protocolFinderDao The protocolFinderDao to set.
     */
    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }

    public ReviewCommentsService getReviewerCommentsService() {
        return reviewCommentsService;
    }

    public void setReviewCommentsService(ReviewCommentsService reviewCommentsService) {
        this.reviewCommentsService = reviewCommentsService;
    }

    public String getProtocolOnlineReviewDocumentDescription(String protocolNumber, String piName) {
        final int fieldLimit = 40;
        int pilen = piName!=null?piName.length():0;
        int pnlen = protocolNumber!=null?protocolNumber.length():0;
        int ttlLength =  pilen + pnlen
                + ONLINE_REVIEW_DOCUMENT_DESCRIPTION_FORMAT.length() -4;
        String piNameToUse = piName;
        
        if ( ttlLength > fieldLimit && piName!=null ) {
            int charsToTrim = ttlLength - fieldLimit;
            piNameToUse = piName.substring(0, Math.max(piName.length() - charsToTrim -1,0));
        }
        String init = String.format( ONLINE_REVIEW_DOCUMENT_DESCRIPTION_FORMAT, piNameToUse,protocolNumber);
        if (init.length() > fieldLimit ) {
            return init.substring(0, fieldLimit-1);
        } else {
            return init;
        }
        
    }
    
    private List<String> getPersonnelIds(Protocol protocol) {
        List<String> PersonnelIds = new ArrayList<String>();
        for (ProtocolPerson person : protocol.getProtocolPersons()) {
            if (StringUtils.isNotBlank(person.getPersonId())) {
                PersonnelIds.add(person.getPersonId());
            }
            else {
                PersonnelIds.add(person.getRolodexId().toString());
            }
        }
        
        return PersonnelIds;
    }
    
    private boolean isProtocolPersonnel(Protocol protocol, CommitteeMembership member) {
        return getPersonnelIds(protocol).contains(member.getPersonId());
    }

}