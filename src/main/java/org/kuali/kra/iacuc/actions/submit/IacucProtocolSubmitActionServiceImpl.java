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
package org.kuali.kra.iacuc.actions.submit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolFinderDao;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Handles the processing of submitting a protocol to the IACUC office for review.
 */
public class IacucProtocolSubmitActionServiceImpl implements IacucProtocolSubmitActionService {
    
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";

    private static final String SUBMITTED_TO_IACUC = "SubmittedToIACUC";
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private IacucProtocolFinderDao protocolFinderDao;
    private BusinessObjectService businessObjectService;
    
    /**
     * Set the Document Service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the ProtocolBase Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    /**
     * Set the ProtocolBase Finder DAO.
     * @param protocolFinderDao
     */
    public void setProtocolFinderDao(IacucProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionService#getTotalSubmissions(java.lang.String)
     */
    @Override
    public int getTotalSubmissions(IacucProtocol protocol) {
        int totalSubmissions = 0;
        
        for (IacucProtocolSubmission protocolSubmission : getProtocolSubmissions(protocol.getProtocolNumber())) {
            int submissionNumber = protocolSubmission.getSubmissionNumber();
            if (submissionNumber > totalSubmissions && protocolSubmission.getSequenceNumber() <= protocol.getSequenceNumber()) {
                totalSubmissions = submissionNumber;
            }
        }
        
        return totalSubmissions;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionService#getProtocolSubmissions(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IacucProtocolSubmission> getProtocolSubmissions(String protocolNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_NUMBER, protocolNumber);
        Collection<IacucProtocolSubmission> submissions = businessObjectService.findMatching(IacucProtocolSubmission.class, fieldValues);
        
        return new ArrayList<IacucProtocolSubmission>(submissions);
    }
   
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionService#getProtocolSubmissions(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<IacucProtocolSubmission> getProtocolSubmissions(String protocolNumber, int submissionNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_NUMBER, protocolNumber);
        fieldValues.put(SUBMISSION_NUMBER, submissionNumber);
        Collection<IacucProtocolSubmission> submissions = businessObjectService.findMatching(IacucProtocolSubmission.class, fieldValues);
        
        return new ArrayList<IacucProtocolSubmission>(submissions);
    }
        
    /**
     * 
     * @see org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionService#getProtocolSubmissionsLookupSequence(java.lang.String)
     */

    private List<IacucProtocolSubmission> getProtocolSubmissionsLookupList(String protocolNumber,List<IacucProtocolSubmission> protocolSubmissionList) throws Exception{
           Collection<IacucProtocolSubmission> submissions = protocolSubmissionList;
           List<IacucProtocolSubmission> protocolSubmissionLookupDataList = new ArrayList<IacucProtocolSubmission>();      
           List<IacucProtocolSubmission> protocolSubmissionLookupResult = new ArrayList<IacucProtocolSubmission>();
           return new ArrayList<IacucProtocolSubmission>(protocolSubmissionLookupResult);
       }
    
    /**
     * When a protocol is submitted for review, an action entry must be added to the protocol. 
     * This action entry is a history of the major events that have occurred during the life
     * of the protocol.
     * 
     * Also, for a submission, a Submission BO must be created.  It contains all of the relevant
     * data for a submission: type, checklists, reviewers, etc.
     * @throws Exception 
     * 
     * @see org.kuali.kra.iacuc.actions.submit.ProtocolSubmitActionService#submitToIacucForReview(org.kuali.kra.protocol.ProtocolBase, org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction)
     */
    public void submitToIacucForReview(IacucProtocol protocol, IacucProtocolSubmitAction submitAction) throws Exception {
        
        /*
         * The submission is saved first so that its new primary key can be added
         * to the protocol action entry.
         */
        String prevSubmissionStatus = protocol.getProtocolSubmission().getSubmissionStatusCode();
        String submissionTypeCode = protocol.getProtocolSubmission().getSubmissionTypeCode();
        IacucProtocolSubmission submission = createProtocolSubmission(protocol, submitAction);
        
        /*
         * If this is an initial submission, then set the initial submission date.
         */
        if (protocol.getInitialSubmissionDate() == null) {
            protocol.setInitialSubmissionDate(new Date(submission.getSubmissionDate().getTime()));
        }
        
        IacucProtocolAction protocolAction = new IacucProtocolAction(protocol, submission, IacucProtocolActionType.SUBMITTED_TO_IACUC);
        protocolAction.setComments(SUBMITTED_TO_IACUC);
        //For the purpose of audit trail
        protocolAction.setPrevProtocolStatusCode(protocol.getProtocolStatusCode());
        protocolAction.setPrevSubmissionStatusCode(prevSubmissionStatus);
        protocolAction.setSubmissionTypeCode(submissionTypeCode);
        protocolAction.setCreatedSubmission(true);
        protocol.getProtocolActions().add(protocolAction);
        
        IacucProtocolStatus protocolStatus = new IacucProtocolStatus();
        protocolStatus.setProtocolStatusCode(IacucProtocolActionType.SUBMITTED_TO_IACUC);
        protocolStatus.setDescription(SUBMITTED_TO_IACUC);
        protocol.setProtocolStatus(protocolStatus);
        protocol.setProtocolStatusCode(IacucProtocolActionType.SUBMITTED_TO_IACUC);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        
        if (submission.getScheduleIdFk() != null) {
            updateDefaultSchedule(submission);
        }
        businessObjectService.delete(protocol.getProtocolDocument().getPessimisticLocks());
        protocol.getProtocolDocument().getPessimisticLocks().clear();
        documentService.saveDocument(protocol.getProtocolDocument());

        protocol.refresh();
    }
    
    @SuppressWarnings("unchecked")
    protected void updateDefaultSchedule(IacucProtocolSubmission submission) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolIdFk", submission.getProtocolId().toString());
        fieldValues.put("scheduleIdFk", CommitteeScheduleBase.DEFAULT_SCHEDULE_ID.toString());
        List<IacucCommitteeScheduleMinute> minutes = (List<IacucCommitteeScheduleMinute>) businessObjectService.findMatching(IacucCommitteeScheduleMinute.class, fieldValues);
        if (!minutes.isEmpty()) {
            for (CommitteeScheduleMinuteBase minute : minutes) {
                minute.setScheduleIdFk(submission.getScheduleIdFk());
            }
            businessObjectService.save(minutes);
        }
    }
    
    /**
     * When an amendment/renewal is submitted to the IACUC office, a corresponding
     * action entry must be added to the original protocol so that the user will
     * know when the amendment/renewal was submitted.
     * @param type
     * @param origProtocolNumber
     * @throws WorkflowException 
     * @throws Exception
     */
    protected void addActionToOriginalProtocol(String type, String origProtocolNumber, Integer submissionNumber) throws WorkflowException {
        String protocolNumber = origProtocolNumber.substring(0, 10);
        String index = origProtocolNumber.substring(11);
        ProtocolBase protocol = protocolFinderDao.findCurrentProtocolByNumber(protocolNumber);
        IacucProtocolAction protocolAction = new IacucProtocolAction((IacucProtocol) protocol, null, IacucProtocolActionType.SUBMITTED_TO_IACUC);
        protocolAction.setComments(type + "-" + index + ": " + SUBMITTED_TO_IACUC);
        protocolAction.setSubmissionNumber(submissionNumber);
        protocol.getProtocolActions().add(protocolAction);
        businessObjectService.save(protocol);
    }

    /**
     * Create a ProtocolBase Submission.
     * @param protocol the protocol
     * @param submitAction the submission data
     * @return a protocol submission
     */
    protected IacucProtocolSubmission createProtocolSubmission(IacucProtocol protocol, IacucProtocolSubmitAction submitAction) {
        IacucProtocolSubmissionBuilder submissionBuilder = new IacucProtocolSubmissionBuilder(protocol, submitAction.getSubmissionTypeCode());
        submissionBuilder.setSubmissionTypeQualifierCode(submitAction.getSubmissionQualifierTypeCode());
        submissionBuilder.setProtocolReviewTypeCode(submitAction.getProtocolReviewTypeCode());
        setSubmissionStatus(submissionBuilder, submitAction);
        setCommittee(submissionBuilder, submitAction);
        return submissionBuilder.create();
    }
    
    /**
     * Set the submission status.
     * @param submissionBuilder the submission builder
     * @param submitAction the submission data
     */
    protected void setSubmissionStatus(IacucProtocolSubmissionBuilder submissionBuilder, IacucProtocolSubmitAction submitAction) {
        // not setting committee during submit in iacuc so need not check that like in irb.
        submissionBuilder.setSubmissionStatus(IacucProtocolSubmissionStatus.PENDING);
    }
    
    /**
     * Set committee for the submission.
     * @param submissionBuilder the submission builder
     * @param submitAction the submission data
     */
    protected void setCommittee(IacucProtocolSubmissionBuilder submissionBuilder, IacucProtocolSubmitAction submitAction) {
        submissionBuilder.setCommittee(submitAction.getNewCommitteeId());
    }
    
    /**
     * @TODO when this gets implemented, see IacucProtocolSubmissionLookupableHelperServiceImpl.getSearchResults ..... that function orginally called this function, but now that it returns null, it had broken the search, so that call is commented out.
     * @see org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionService#getProtocolSubmissionsLookupData(java.util.List)
     */
    public List<IacucProtocolSubmission> getProtocolSubmissionsLookupData(List<IacucProtocolSubmission> protocolSubmissionList) throws Exception {
        // TODO
        return null;
    }

}
