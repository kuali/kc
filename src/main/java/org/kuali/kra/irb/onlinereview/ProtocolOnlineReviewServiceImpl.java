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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.kew.KraDocumentRejectionService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kim.service.PersonService;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
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
    private ProtocolOnlineReviewDocument createProtocolOnlineReviewDocument(ProtocolSubmission protocolSubmission, 
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
            review.refresh();
            try {
                onlineReviewDocuments.add((ProtocolOnlineReviewDocument)(documentService.getByDocumentHeaderId( review.getProtocolOnlineReviewDocument().getDocumentNumber() )));
            }
            catch (WorkflowException e) {
                throw new RuntimeException( String.format( "Could not load ProtocolOnlineReview docuemnt %s due to WorkflowException: %s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e.getMessage() ),e);
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
        if (LOG.isDebugEnabled()) { 
            LOG.debug(String.format("Fetching available committee members for protocol %s, submission %s", protocol.getProtocolNumber(), 
                    submission.getSubmissionNumber()));
        }
        
        List<ProtocolOnlineReview> currentReviews = submission.getProtocolOnlineReviews();
        
        List<CommitteeMembership> committeeMembers = getCommitteeService().getAvailableMembers(submission.getCommitteeId(), submission.getScheduleId());
        //TODO: Make this better.
        for (CommitteeMembership member : committeeMembers) {
            boolean found = false;
            for( ProtocolOnlineReview review : currentReviews ) {
                if ( review.getProtocolReviewer().isProtocolReviewerFromCommitteeMembership(member)) {
                    found=true;
                    break;
                }
            }
            if (!found) {
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
        return protocol.getProtocolSubmission().getProtocolOnlineReviews();
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getProtocolReviews(java.lang.Long)
     */
    public List<ProtocolOnlineReview> getProtocolReviews(Long submissionId) {
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        
        ProtocolSubmission submission = getBusinessObjectService().findBySinglePrimaryKey(ProtocolSubmission.class, submissionId);
        if (submission != null) {
            reviews.addAll(submission.getProtocolOnlineReviews());
        }
        
        return reviews;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#getProtocolReviewer(java.lang.String, 
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public ProtocolReviewer getProtocolReviewer(String principalId, ProtocolSubmission protocolSubmission) {
        ProtocolReviewer protocolReviewer = null;

        if (protocolSubmission != null) {
            for (ProtocolOnlineReview protocolOnlineReview : protocolSubmission.getProtocolOnlineReviews()) {
                if (protocolOnlineReview.getProtocolReviewer().isPersonIdProtocolReviewer(principalId)) {
                    protocolReviewer = protocolOnlineReview.getProtocolReviewer();
                    break;
                }
            }
        }
        
        return protocolReviewer;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#isUserAnOnlineReviewerOfProtocol(java.lang.String, org.kuali.kra.irb.Protocol)
     */
    public boolean isProtocolReviewer(String personId, ProtocolSubmission protocolSubmission) {
        boolean isReviewer = false;

        if (protocolSubmission != null) {
            for (ProtocolOnlineReview review : protocolSubmission.getProtocolOnlineReviews()) {
                if (review.getProtocolReviewer().isPersonIdProtocolReviewer(personId)) {
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
            isReviewable = StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) 
                || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) 
                || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA);
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
    private List<ProtocolOnlineReview> findProtocolOnlineReviews(Long protocolId,
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

}