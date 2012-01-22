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
package org.kuali.kra.irb.actions.submit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Handles the processing of submitting a protocol to the IRB office for review.
 */
public class ProtocolSubmitActionServiceImpl implements ProtocolSubmitActionService {
    
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";

    private static final String SUBMIT_TO_IRB = "Submitted to IRB";
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolFinderDao protocolFinderDao;
    private BusinessObjectService businessObjectService;
    private ProtocolAssignReviewersService protocolAssignReviewersService;
    
    /**
     * Set the Document Service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the Protocol Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    /**
     * Set the Protocol Finder DAO.
     * @param protocolFinderDao
     */
    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
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
     * Set the Protocol Assign Reviewers Service.
     * @param protocolAssignReviewersService
     */
    public void setProtocolAssignReviewersService(ProtocolAssignReviewersService protocolAssignReviewersService) {
        this.protocolAssignReviewersService = protocolAssignReviewersService;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService#getTotalSubmissions(java.lang.String)
     */
    public int getTotalSubmissions(Protocol protocol) {
        int totalSubmissions = 0;
        
        for (ProtocolSubmission protocolSubmission : getProtocolSubmissions(protocol.getProtocolNumber())) {
            int submissionNumber = protocolSubmission.getSubmissionNumber();
            if (submissionNumber > totalSubmissions && protocolSubmission.getSequenceNumber() <= protocol.getSequenceNumber()) {
                totalSubmissions = submissionNumber;
            }
        }
        
        return totalSubmissions;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService#getProtocolSubmissions(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<ProtocolSubmission> getProtocolSubmissions(String protocolNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_NUMBER, protocolNumber);
        Collection<ProtocolSubmission> submissions = businessObjectService.findMatching(ProtocolSubmission.class, fieldValues);
        
        return new ArrayList<ProtocolSubmission>(submissions);
    }
   
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService#getProtocolSubmissions(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<ProtocolSubmission> getProtocolSubmissions(String protocolNumber, int submissionNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_NUMBER, protocolNumber);
        fieldValues.put(SUBMISSION_NUMBER, submissionNumber);
        Collection<ProtocolSubmission> submissions = businessObjectService.findMatching(ProtocolSubmission.class, fieldValues);
        
        return new ArrayList<ProtocolSubmission>(submissions);
    }
        
    /**
     * 
     * @see org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService#getProtocolSubmissionsLookupSequence(java.lang.String)
     */

    private List<ProtocolSubmission> getProtocolSubmissionsLookupList(String protocolNumber,List<ProtocolSubmission> protocolSubmissionList) throws Exception{
           Collection<ProtocolSubmission> submissions = protocolSubmissionList;
           List<ProtocolSubmission> protocolSubmissionLookupDataList = new ArrayList<ProtocolSubmission>();
           for (ProtocolSubmission protocolSubmissionData : submissions) {
               if(protocolNumber.equals(protocolSubmissionData.getProtocolNumber())){
                  protocolSubmissionLookupDataList.add(protocolSubmissionData);}
           }               
           Set<Integer> submissionNumberList = new HashSet<Integer>();         
           List<ProtocolSubmission> protocolSubmissionLookupResult = new ArrayList<ProtocolSubmission>();
           for (ProtocolSubmission protocolSubmissionResult : protocolSubmissionLookupDataList) {
               submissionNumberList.add(protocolSubmissionResult.getSubmissionNumber());
           }        
           for(Integer submissionNumber : submissionNumberList){
               List<ProtocolSubmission> submissionList=null;
               int submissionSequenceNumber=0;
               for (ProtocolSubmission protocolsubmissionData : protocolSubmissionLookupDataList) {
                   if(protocolsubmissionData.getSubmissionNumber().equals(submissionNumber)){
                       if (protocolsubmissionData.getSequenceNumber() >= submissionSequenceNumber) {
                           submissionSequenceNumber=protocolsubmissionData.getSequenceNumber(); 
                           submissionList=new ArrayList<ProtocolSubmission>(); 
                           submissionList.add(protocolsubmissionData);
                       }                       
                   }
               } 
               if(submissionList!=null){
                   protocolSubmissionLookupResult.add(submissionList.get(0));
               }
           }
           return new ArrayList<ProtocolSubmission>(protocolSubmissionLookupResult);
       }
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService#getProtocolSubmissionsLookupData(java.util.List)
     */
    public List<ProtocolSubmission> getProtocolSubmissionsLookupData(List<ProtocolSubmission> protocolSubmissionList) throws Exception{        
        Collection<ProtocolSubmission> submissions = protocolSubmissionList;
        List<ProtocolSubmission> protocolSubmissionsLookupResult = new ArrayList<ProtocolSubmission>();
        Set<String> submissionProtocolNumberList = new HashSet<String>();       
        
        for (ProtocolSubmission protocolSubmissionData : submissions) {
            submissionProtocolNumberList.add(protocolSubmissionData.getProtocolNumber());
        }        
        for(String submissionProtocolNumber : submissionProtocolNumberList){
            List<ProtocolSubmission> protocolSubmissionLookupList=null;
            protocolSubmissionLookupList = getProtocolSubmissionsLookupList(submissionProtocolNumber,protocolSubmissionList);
           
            if((protocolSubmissionLookupList!=null)&&(protocolSubmissionLookupList.size()>0)){
                protocolSubmissionsLookupResult.addAll(protocolSubmissionLookupList);
            }
        }
        return new ArrayList<ProtocolSubmission>(protocolSubmissionsLookupResult);
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
     * @see org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService#submitToIrbForReview(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.submit.ProtocolSubmitAction)
     */
    public void submitToIrbForReview(Protocol protocol, ProtocolSubmitAction submitAction) throws Exception {
        
        /*
         * The submission is saved first so that its new primary key can be added
         * to the protocol action entry.
         */
        String prevSubmissionStatus = protocol.getProtocolSubmission().getSubmissionStatusCode();
        String submissionTypeCode = protocol.getProtocolSubmission().getSubmissionTypeCode();
        ProtocolSubmission submission = createProtocolSubmission(protocol, submitAction);
        
        /*
         * If this is an initial submission, then set the initial submission date.
         */
        if (protocol.getInitialSubmissionDate() == null) {
            protocol.setInitialSubmissionDate(new Date(submission.getSubmissionDate().getTime()));
        }
        
        protocolAssignReviewersService.assignReviewers(submission, submitAction.getReviewers());
        
        ProtocolAction protocolAction = new ProtocolAction(protocol, submission, ProtocolActionType.SUBMIT_TO_IRB);
        protocolAction.setComments(SUBMIT_TO_IRB);
        //For the purpose of audit trail
        protocolAction.setPrevProtocolStatusCode(protocol.getProtocolStatusCode());
        protocolAction.setPrevSubmissionStatusCode(prevSubmissionStatus);
        protocolAction.setSubmissionTypeCode(submissionTypeCode);
        protocol.getProtocolActions().add(protocolAction);

        //TODO this is for workflow testing, but we do need to plumb the status change in here somewhere.
        ProtocolStatus protocolStatus = new ProtocolStatus();
        protocolStatus.setProtocolStatusCode(ProtocolActionType.SUBMIT_TO_IRB);
        protocol.setProtocolStatus(protocolStatus);
        protocol.setProtocolStatusCode(ProtocolActionType.SUBMIT_TO_IRB);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        
// now usine mergeprotocolaction when A/R is approved
//        if (protocol.isAmendment()) {
//            addActionToOriginalProtocol(AMENDMENT, protocol.getProtocolNumber(), protocolAction.getSubmissionNumber());
//        }
//        else if (protocol.isRenewal()) {
//            addActionToOriginalProtocol(RENEWAL, protocol.getProtocolNumber(), protocolAction.getSubmissionNumber());
//        }
        
        if (submission.getScheduleIdFk() != null) {
            updateDefaultSchedule(submission);
        }
        businessObjectService.delete(protocol.getProtocolDocument().getPessimisticLocks());
        protocol.getProtocolDocument().getPessimisticLocks().clear();
        documentService.saveDocument(protocol.getProtocolDocument());
        
        protocol.refresh();
    }
    
    @SuppressWarnings("unchecked")
    protected void updateDefaultSchedule(ProtocolSubmission submission) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolIdFk", submission.getProtocolId().toString());
        fieldValues.put("scheduleIdFk", CommitteeSchedule.DEFAULT_SCHEDULE_ID.toString());
        List<CommitteeScheduleMinute> minutes = (List<CommitteeScheduleMinute>) businessObjectService.findMatching(CommitteeScheduleMinute.class, fieldValues);
        if (!minutes.isEmpty()) {
            for (CommitteeScheduleMinute minute : minutes) {
                minute.setScheduleIdFk(submission.getScheduleIdFk());
            }
            businessObjectService.save(minutes);
        }
    }
    
    /**
     * When an amendment/renewal is submitted to the IRB office, a corresponding
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
        Protocol protocol = protocolFinderDao.findCurrentProtocolByNumber(protocolNumber);
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.SUBMIT_TO_IRB);
        protocolAction.setComments(type + "-" + index + ": " + SUBMIT_TO_IRB);
        protocolAction.setSubmissionNumber(submissionNumber);
        protocol.getProtocolActions().add(protocolAction);
        businessObjectService.save(protocol);
        // do following will get null workflow exception
        //documentService.saveDocument(protocol.getProtocolDocument());

    }

    /**
     * Create a Protocol Submission.
     * @param protocol the protocol
     * @param submitAction the submission data
     * @return a protocol submission
     */
    protected ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolSubmitAction submitAction) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, submitAction.getSubmissionTypeCode());
        submissionBuilder.setSubmissionTypeQualifierCode(submitAction.getSubmissionQualifierTypeCode());
        submissionBuilder.setProtocolReviewTypeCode(submitAction.getProtocolReviewTypeCode());
        setSubmissionStatus(submissionBuilder, submitAction);
        setCommittee(submissionBuilder, submitAction);
        setSchedule(submissionBuilder, submitAction);
        addCheckLists(submissionBuilder, submitAction);
        return submissionBuilder.create();
    }
    
    /**
     * Set the submission status.
     * @param submissionBuilder the submission builder
     * @param submitAction the submission data
     */
    protected void setSubmissionStatus(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
        if (StringUtils.isBlank(submitAction.getNewCommitteeId())) {
            submissionBuilder.setSubmissionStatus(ProtocolSubmissionStatus.PENDING);
        }
        else {
            submissionBuilder.setSubmissionStatus(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        }
    }
    
    /**
     * Set committee for the submission.
     * @param submissionBuilder the submission builder
     * @param submitAction the submission data
     */
    protected void setCommittee(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
        submissionBuilder.setCommittee(submitAction.getNewCommitteeId());
    }
    
    /**
     * Set schedule for the submission.
     * @param submissionBuilder the submission builder
     * @param submitAction the submission data
     */
    protected void setSchedule(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
        submissionBuilder.setSchedule(submitAction.getNewScheduleId());
    }
    
    /**
     * Add an optional Check List to the submission.  Exempt Studies and Expedited Reviews each
     * require a check list to be added to the submission.  Other protocol review types do not
     * have a check list.
     * @param submission the submission
     * @param submitAction the submission data
     */
    protected void addCheckLists(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
        if (isProtocolReviewType(submitAction, ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE)) {
            addExemptStudiesCheckList(submissionBuilder, submitAction);
        }
        else if (isProtocolReviewType(submitAction, ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE)) {
            addExpeditedReviewCheckList(submissionBuilder, submitAction);
        }
    }
    
    /**
     * Is the submission of a certain protocol review type?
     * @param submitAction the submit action
     * @param protocolReviewTypeCode the protocol review type to compare against
     * @return true if the submission uses the given protocol review type; otherwise false
     */
    protected boolean isProtocolReviewType(ProtocolSubmitAction submitAction, String protocolReviewTypeCode) {
        return (StringUtils.equals(submitAction.getProtocolReviewTypeCode(), protocolReviewTypeCode));
    }

    /**
     * Add the Exempt Studies Check List items to the submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    protected void addExemptStudiesCheckList(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
        for (ExemptStudiesCheckListItem item : submitAction.getExemptStudiesCheckList()) {
            if (item.getChecked()) {
                submissionBuilder.addExemptStudiesCheckListItem(item.getExemptStudiesCheckListCode());
            }
        }
    }
    
    /**
     * Add the Expedited Review Check List items to the submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    protected void addExpeditedReviewCheckList(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction action) {
        for (ExpeditedReviewCheckListItem item : action.getExpeditedReviewCheckList()) {
            if (item.getChecked()) {
                submissionBuilder.addExpeditedReviewCheckListItem(item.getExpeditedReviewCheckListCode());
            }
        }
    }
}
