/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.protocol.onlinereview;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.common.committee.bo.CommitteeMembershipBase;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.common.committee.service.CommitteeServiceBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.service.KcAuthorizationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ProtocolOnlineReviewServiceImplBase implements ProtocolOnlineReviewService {

    private static final Log LOG = LogFactory.getLog(ProtocolOnlineReviewServiceImplBase.class);
    
    protected BusinessObjectService businessObjectService;
    protected DocumentService documentService;
    protected KcAuthorizationService kraAuthorizationService;
    protected ProtocolAssignReviewersService protocolAssignReviewersService;
    protected IdentityService identityManagementService;
    protected CommitteeServiceBase committeeService;
    protected KcDocumentRejectionService kraDocumentRejectionService;
    protected ProtocolFinderDao protocolFinderDao;
    protected ReviewCommentsService reviewCommentsService;
    protected WorkflowDocumentService workflowDocumentService;
    
    protected String reviewerApproveNodeName;
    
    @SuppressWarnings("unchecked")
    protected PersonService personService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService#createAndRouteProtocolOnlineReviewDocument(
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase, org.kuali.kra.irb.actions.submit.ProtocolReviewerBeanBase, java.lang.String, java.lang.String, 
     *      java.lang.String, java.lang.String, boolean, java.sql.Date, java.sql.Date, java.lang.String)
     */
    public ProtocolOnlineReviewDocumentBase createAndRouteProtocolOnlineReviewDocument(ProtocolSubmissionBase protocolSubmission, 
                                                                                   ProtocolReviewer protocolReviewer,
                                                                                   String documentDescription,
                                                                                   String documentExplanation,
                                                                                   String documentOrganizationDocumentNumber,
                                                                                   String documentRouteAnnotation,
                                                                                   boolean initialApproval,
                                                                                   Date dateRequested,
                                                                                   Date dateDue, 
                                                                                   String principalId) {
        
        ProtocolOnlineReviewDocumentBase document = null;
        
        try {
            if (LOG.isDebugEnabled()) {
                String protocolNumber = protocolSubmission.getProtocolNumber();
                Integer submissionNumber = protocolSubmission.getSubmissionNumber();
                LOG.debug(String.format("Assigning online reviewer [%s] to protocol [%s].", protocolReviewer, protocolNumber));
                LOG.debug(String.format("Current submission for protocol %s is %s.", protocolNumber, submissionNumber));
            }

            document = createProtocolOnlineReviewDocument(protocolSubmission, protocolReviewer, documentDescription, documentExplanation, 
                    documentOrganizationDocumentNumber, dateRequested, dateDue, principalId);
            
            documentService.routeDocument(document, "Review Requested by PI during protocol submission.", new ArrayList<AdHocRouteRecipient>());
            
            if (initialApproval) {
                documentService.approveDocument(document, "", new ArrayList<AdHocRouteRecipient>());
            }
        } catch (WorkflowException e) {
            String errorString = String.format("WorkflowException creating new ProtocolOnlineReviewDocumentBase for reviewer %s, protocol %s", 
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
    protected ProtocolOnlineReviewDocumentBase createProtocolOnlineReviewDocument(ProtocolSubmissionBase protocolSubmission, 
                                                                            ProtocolReviewer protocolReviewer, 
                                                                            String documentDescription,
                                                                            String documentExplanation,
                                                                            String documentOrganizationDocumentNumber,
                                                                            Date dateRequested,
                                                                            Date dateDue,
                                                                            String principalId) throws WorkflowException {
        
        ProtocolOnlineReviewDocumentBase protocolReviewDocument;
        
        Person person = personService.getPerson(principalId);
        WorkflowDocument workflowDocument = workflowDocumentService.createWorkflowDocument(getProtocolOLRDocumentTypeHook(), person);
        
        DocumentHeader docHeader = new DocumentHeader();
        docHeader.setWorkflowDocument(workflowDocument);
        docHeader.setDocumentNumber(workflowDocument.getDocumentId().toString());
        protocolReviewDocument = getNewProtocolOnlineReviewDocumentInstanceHook();
        protocolReviewDocument.setDocumentNumber(docHeader.getDocumentNumber());
        protocolReviewDocument.setDocumentHeader(docHeader);
        
      
        protocolReviewDocument.getProtocolOnlineReview().setProtocol(protocolSubmission.getProtocol());
        
        
        protocolReviewDocument.getProtocolOnlineReview().setProtocolId(protocolSubmission.getProtocolId());
        
        protocolReviewDocument.getProtocolOnlineReview().setProtocolSubmission(protocolSubmission);
        protocolReviewDocument.getProtocolOnlineReview().setSubmissionIdFk(protocolSubmission.getSubmissionId());
        protocolReviewDocument.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(getProtocolOLRSavedStatusCodeHook());
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
    
    protected abstract ProtocolOnlineReviewDocumentBase getNewProtocolOnlineReviewDocumentInstanceHook();
    
    protected abstract String getProtocolOLRSavedStatusCodeHook();

    protected abstract String getProtocolOLRDocumentTypeHook();

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService#createProtocolReviewer(java.lang.String, boolean, java.lang.String, 
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase)
     */
    public ProtocolReviewer createProtocolReviewer(String principalId, 
                                                   boolean nonEmployeeFlag, 
                                                   String reviewerTypeCode, 
                                                   ProtocolSubmissionBase protocolSubmission) {
        ProtocolReviewer reviewer = createNewProtocolReviewerInstanceHook();
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

    
    protected abstract ProtocolReviewer createNewProtocolReviewerInstanceHook();
    

    /**
     * @see org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService#getProtocolReviewDocumentsForCurrentSubmission(org.kuali.kra.protocol.ProtocolBase)
     */
    public List<ProtocolOnlineReviewDocumentBase> getProtocolReviewDocumentsForCurrentSubmission(ProtocolBase protocol) {
        List<ProtocolOnlineReviewDocumentBase> onlineReviewDocuments = new ArrayList<ProtocolOnlineReviewDocumentBase>();
        ProtocolSubmissionBase submission = protocol.getProtocolSubmission();
        List<ProtocolOnlineReviewBase> reviews = findProtocolOnlineReviews(protocol.getProtocolId(), submission.getSubmissionId());
        for (ProtocolOnlineReviewBase review : reviews) {
            if (review.isActive()) {
                review.refresh();
                try {
                    onlineReviewDocuments.add((ProtocolOnlineReviewDocumentBase)(documentService.getByDocumentHeaderId( review.getProtocolOnlineReviewDocument().getDocumentNumber() )));
                }
                catch (WorkflowException e) {
                    throw new RuntimeException( String.format( "Could not load ProtocolOnlineReviewBase docuemnt %s due to WorkflowException: %s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e.getMessage() ),e);
                }
            }
        }
        return onlineReviewDocuments;
    }

    
    /**
     * @see org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService#getAvailableCommitteeMembersForCurrentSubmission(org.kuali.kra.protocol.ProtocolBase)
     */
    public List<CommitteeMembershipBase> getAvailableCommitteeMembersForCurrentSubmission(ProtocolBase protocol) {
        List<CommitteeMembershipBase> results = new ArrayList<CommitteeMembershipBase>();
        
        ProtocolSubmissionBase submission = protocol.getProtocolSubmission();
        submission.refreshReferenceObject("protocolOnlineReviews");
        if (LOG.isDebugEnabled()) { 
            LOG.debug(String.format("Fetching available committee members for protocol %s, submission %s", protocol.getProtocolNumber(), 
                    submission.getSubmissionNumber()));
        }
           
        List<ProtocolOnlineReviewBase> currentReviews = submission.getProtocolOnlineReviews();
        List<CommitteeMembershipBase> committeeMembers = getCommitteeService().getAvailableMembers(submission.getCommitteeId(), submission.getScheduleId());
        //TODO: Make this better.
        for (CommitteeMembershipBase member : committeeMembers) {
            boolean found = false;
            for (ProtocolOnlineReviewBase review : currentReviews) {
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
     * @see org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService#getProtocolReviewsForCurrentSubmission(java.lang.String)
     */
    public List<ProtocolOnlineReviewBase> getProtocolReviews(String protocolNumber) {
        ProtocolBase protocol = protocolFinderDao.findCurrentProtocolByNumber(protocolNumber);
        List<ProtocolOnlineReviewBase> reviews = null;
        
     
        if (protocol != null && protocol.getProtocolSubmission() != null) {
            reviews = protocol.getProtocolSubmission().getProtocolOnlineReviews();
        } else {
            reviews = new ArrayList<ProtocolOnlineReviewBase>();
        }
        
        return reviews;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService#getProtocolReviews(java.lang.Long)
     */
    public List<ProtocolOnlineReviewBase> getProtocolReviews(Long submissionId) {
        List<ProtocolOnlineReviewBase> reviews = new ArrayList<ProtocolOnlineReviewBase>();
        
        ProtocolSubmissionBase submission = getBusinessObjectService().findBySinglePrimaryKey(getProtocolSubmissionBOClassHook(), submissionId);
        if (submission != null) {
            for(ProtocolOnlineReviewBase review : submission.getProtocolOnlineReviews()) {
                if(review.isActive()) {
                    reviews.add(review);
                }
            }
        }
        
        return reviews;
    }
    
    protected abstract Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook();

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService#getProtocolReviewer(java.lang.String, 
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase)
     */
    public ProtocolReviewer getProtocolReviewer(String personId, boolean nonEmployeeFlag, ProtocolSubmissionBase protocolSubmission) {
        ProtocolReviewer protocolReviewer = null;

        if (protocolSubmission != null) {
            for (ProtocolOnlineReviewBase protocolOnlineReview : protocolSubmission.getProtocolOnlineReviews()) {
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
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getProtocolOnlineReviewDocument(java.lang.String, boolean, org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase)
     */
    public ProtocolOnlineReviewDocumentBase getProtocolOnlineReviewDocument(String personId, boolean nonEmployeeFlag, ProtocolSubmissionBase protocolSubmission) {
        ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument = null;
        
        if (protocolSubmission != null) {
            for (ProtocolOnlineReviewBase protocolOnlineReview : protocolSubmission.getProtocolOnlineReviews()) {
                if (protocolOnlineReview.getProtocolReviewer().isPersonIdProtocolReviewer(personId,nonEmployeeFlag) 
                        && protocolOnlineReview.isActive()) {
                    try {
                        protocolOnlineReviewDocument =  (ProtocolOnlineReviewDocumentBase)getDocumentService().getByDocumentHeaderId(protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber());
                    }
                    catch (WorkflowException e) {
                       if (LOG.isDebugEnabled()) {
                           String errorMessage = String.format("WorkflowException encountered while looking up document number %s for ProtocolOnlineReviewDocumentBase associated with (submissionId=%s,personId=%s,nonEmployeeFlag=%s",
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
     * @see org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService#isUserAnOnlineReviewerOfProtocol(java.lang.String, org.kuali.kra.protocol.ProtocolBase)
     */
    public boolean isProtocolReviewer(String personId, boolean nonEmployeeFlag, ProtocolSubmissionBase protocolSubmission) {
        boolean isReviewer = false;
        
        if (protocolSubmission != null) {
            for (ProtocolOnlineReviewBase review : protocolSubmission.getProtocolOnlineReviews()) {
                if (review.getProtocolReviewer().isPersonIdProtocolReviewer(personId,nonEmployeeFlag) && review.isActive()) {
                    isReviewer = true;
                    break;
                }
            }
        }
        
        
        return isReviewer;
    }
        
    public void returnProtocolOnlineReviewDocumentToReviewer(ProtocolOnlineReviewDocumentBase reviewDocument, String reason, String principalId) {
        kraDocumentRejectionService.reject(reviewDocument, reason, principalId, (String)null, reviewerApproveNodeName);     
    }
    
    /**
     * Finds and returns all protocol online reviews for the protocolId and submissionId.
     * @param protocolId
     * @param submissionIdFk
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<ProtocolOnlineReviewBase> findProtocolOnlineReviews(Long protocolId,
                                                                 Long submissionIdFk) {
        List<ProtocolOnlineReviewBase> reviews = new ArrayList<ProtocolOnlineReviewBase>();
        if (protocolId != null && submissionIdFk != null) {
            Map<String,Object> hashMap = new HashMap<String,Object>();
            hashMap.put("protocolId", protocolId);
            hashMap.put("submissionIdFk", submissionIdFk);
            reviews.addAll(getBusinessObjectService().findMatchingOrderBy(getProtocolOnlineReviewBOClassHook(), hashMap, "dateRequested", false));
        }
        return reviews;
    }

    protected abstract Class<? extends ProtocolOnlineReviewBase> getProtocolOnlineReviewBOClassHook();
    

    protected void cancelOnlineReviewDocument(ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument, ProtocolSubmissionBase submission, String annotation) {
        try {
           
            final String principalId = identityManagementService.getPrincipalByPrincipalName(KRADConstants.SYSTEM_USER).getPrincipalId();
            WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(principalId, protocolOnlineReviewDocument.getDocumentNumber());
            
            if (workflowDocument.isEnroute() 
                ||
                workflowDocument.isInitiated()
                ||
                workflowDocument.isSaved()
                ) {
                workflowDocument.superUserCancel(String.format("Review Cancelled from assign reviewers action by %s", GlobalVariables.getUserSession().getPrincipalId()));
            }
        } catch(Exception e) {
            String errorMessage = String.format("Exception generated while executing superUserCancel on document %s in removeOnlineReviewDocument. Message: %s",protocolOnlineReviewDocument.getDocumentNumber(), e.getMessage());
            LOG.error(errorMessage);
            throw new RuntimeException(errorMessage,e);
        }
    }
    
    protected void finalizeOnlineReviewDocument(ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument, ProtocolSubmissionBase submission, String annotation) {
        
        try {

            final String principalId = identityManagementService.getPrincipalByPrincipalName(KRADConstants.SYSTEM_USER).getPrincipalId();
            WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(principalId, protocolOnlineReviewDocument.getDocumentNumber());
            ProtocolOnlineReviewBase review = protocolOnlineReviewDocument.getProtocolOnlineReview();
            review.addActionPerformed("Finalize:"+workflowDocument.getStatus().getCode()+":"+review.getProtocolOnlineReviewStatusCode());

            
            if (workflowDocument.isEnroute()
            ||
            workflowDocument.isInitiated()
            ||
            workflowDocument.isSaved()
            ) {
                workflowDocument.superUserBlanketApprove(annotation);
            }
        } catch(Exception e) {
            String errorMessage = String.format("Workflow exception generated while executing superUserApprove on document %s in finalizeOnlineReviewDocument. Message:%s",protocolOnlineReviewDocument.getDocumentNumber(), e.getMessage());
            LOG.error(errorMessage);
            throw new RuntimeException(errorMessage,e);
        }
        
    }
        
    /**
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#removeOnlineReviewDocument(java.lang.String, boolean, org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase, java.lang.String)
     */
    public void removeOnlineReviewDocument(String personId, boolean nonEmployeeFlag, ProtocolSubmissionBase submission, String annotation) {
        ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument = this.getProtocolOnlineReviewDocument(personId, nonEmployeeFlag, submission);
        
        ProtocolOnlineReviewBase submissionsProtocolOnlineReview = null;
        for (ProtocolOnlineReviewBase rev : submission.getProtocolOnlineReviews()) {
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
            submissionsProtocolOnlineReview.setProtocolOnlineReviewStatusCode(getProtocolOLRRemovedCancelledStatusCodeHook());
            
            List<CommitteeScheduleMinuteBase> reviewComments = protocolOnlineReviewDocument.getProtocolOnlineReview().getCommitteeScheduleMinutes();
            List<CommitteeScheduleMinuteBase> deletedReviewComments = new ArrayList<CommitteeScheduleMinuteBase>();
            getReviewerCommentsService().deleteAllReviewComments(reviewComments, deletedReviewComments);
            getReviewerCommentsService().saveReviewComments(reviewComments, deletedReviewComments);
            
            getBusinessObjectService().save(submissionsProtocolOnlineReview);
        
        } else {
            LOG.warn(String.format("ProtocolBase Online Review document could not be found for (personId=%s,nonEmployeeFlag=%s) from (protocol=%s,submission=%s)",personId,nonEmployeeFlag,submission.getProtocol().getProtocolNumber(),submission.getSubmissionNumber()));
        }
    }

    protected abstract String getProtocolOLRRemovedCancelledStatusCodeHook();


    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#cancelOnlineReviews(org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase, 
     *      java.lang.String)
     */
    public void cancelOnlineReviews(ProtocolSubmissionBase submission, String annotation) {
        //get the online reviews, loop through them and finalize them if necessary.
        for (ProtocolOnlineReviewBase review : submission.getProtocolOnlineReviews()) {
            cancelOnlineReviewDocument(review.getProtocolOnlineReviewDocument(), submission, annotation);
        }
    }
    
    /**
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#finalizeOnlineReviews(org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase)
     */
    public void finalizeOnlineReviews(ProtocolSubmissionBase submission, String annotation) {
        //get the online reviews, loop through them and finalize them if necessary.
        for(ProtocolOnlineReviewBase review : submission.getProtocolOnlineReviews()) {
//            review.addActionPerformed("Finalize:"+review.getProtocolOnlineReviewDocument().getDocumentHeader().getWorkflowDocument().getStatus().getCode()+":"+review.getProtocolOnlineReviewStatusCode());
            finalizeOnlineReviewDocument(review.getProtocolOnlineReviewDocument(), submission, annotation);
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#moveOnlineReviews(org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase, org.kuali.kra.irb.actions.submit.ProtocolSubmissionBase)
     */
    public void moveOnlineReviews(ProtocolSubmissionBase submission, ProtocolSubmissionBase newSubmission) {
        newSubmission.setProtocolOnlineReviews(new ArrayList<ProtocolOnlineReviewBase>());
        for (ProtocolOnlineReviewBase review : submission.getProtocolOnlineReviews()) {
            review.setProtocol(newSubmission.getProtocol());
            review.setProtocolId(newSubmission.getProtocol().getProtocolId());
            review.setSubmissionIdFk(newSubmission.getSubmissionId());
            if (CollectionUtils.isNotEmpty(review.getCommitteeScheduleMinutes())) {
                for (CommitteeScheduleMinuteBase comment : review.getCommitteeScheduleMinutes()) {
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
    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    public KcAuthorizationService getKraAuthorizationService() {
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
        return KcServiceLocator.getService(WorkflowDocumentService.class);
    }
    /**
     * Gets the workflowDocumentService attribute. 
     * @return Returns the workflowDocumentService.
     */
    public KcWorkflowService getKraWorkflowService() {
        return KcServiceLocator.getService(KcWorkflowService.class);
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
     * Sets the identityManagementService attribute value.
     * @param identityManagementService The identityManagementService to set.
     */
    public void setIdentityManagementService(IdentityService identityManagementService) {
        this.identityManagementService = identityManagementService;
    }
    
    /**
     * Gets the committeeService attribute. 
     * @return Returns the committeeService.
     */
    public CommitteeServiceBase getCommitteeService() {
        return committeeService;
    }
    /**
     * Sets the committeeService attribute value.
     * @param committeeService The committeeService to set.
     */
    public void setCommitteeService(CommitteeServiceBase committeeService) {
        this.committeeService = committeeService;
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
    public KcDocumentRejectionService getKraDocumentRejectionService() {
        return kraDocumentRejectionService;
    }

    /**
     * Sets the kraDocumentRejectionService attribute value.
     * @param kraDocumentRejectionService The kraDocumentRejectionService to set.
     */
    public void setKraDocumentRejectionService(KcDocumentRejectionService kraDocumentRejectionService) {
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
    
    private List<String> getPersonnelIds(ProtocolBase protocol) {
        List<String> PersonnelIds = new ArrayList<String>();
        for (ProtocolPersonBase person : protocol.getProtocolPersons()) {
            if (StringUtils.isNotBlank(person.getPersonId())) {
                PersonnelIds.add(person.getPersonId());
            }
            else {
                PersonnelIds.add(person.getRolodexId().toString());
            }
        }
        
        return PersonnelIds;
    }
    
    private boolean isProtocolPersonnel(ProtocolBase protocol, CommitteeMembershipBase member) {
        return getPersonnelIds(protocol).contains(member.getPersonId());
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

    
}
