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
package org.kuali.kra.iacuc.actions.submit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolFinderDao;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.Protocol;
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
//    private ProtocolAssignReviewersService protocolAssignReviewersService;
    
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
     * Set the Protocol Assign Reviewers Service.
     * @param protocolAssignReviewersService
     */
//    public void setProtocolAssignReviewersService(ProtocolAssignReviewersService protocolAssignReviewersService) {
//        this.protocolAssignReviewersService = protocolAssignReviewersService;
//    }
//    
    
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
//           for (IacucProtocolSubmission protocolSubmissionData : submissions) {
//               if(protocolNumber.equals(protocolSubmissionData.getProtocolNumber())){
//                  protocolSubmissionLookupDataList.add(protocolSubmissionData);}
//           }               
//           Set<Integer> submissionNumberList = new HashSet<Integer>();         
           List<IacucProtocolSubmission> protocolSubmissionLookupResult = new ArrayList<IacucProtocolSubmission>();
//           for (IacucProtocolSubmission protocolSubmissionResult : protocolSubmissionLookupDataList) {
//               submissionNumberList.add(protocolSubmissionResult.getSubmissionNumber());
//           }        
//           for(Integer submissionNumber : submissionNumberList){
//               List<IacucProtocolSubmission> submissionList=null;
//               int submissionSequenceNumber=0;
//               for (IacucProtocolSubmission protocolsubmissionData : protocolSubmissionLookupDataList) {
//                   if(protocolsubmissionData.getSubmissionNumber().equals(submissionNumber)){
//                       if (protocolsubmissionData.getSequenceNumber() >= submissionSequenceNumber) {
//                           submissionSequenceNumber=protocolsubmissionData.getSequenceNumber(); 
//                           submissionList=new ArrayList<IacucProtocolSubmission>(); 
//                           submissionList.add(protocolsubmissionData);
//                       }                       
//                   }
//               } 
//               if(submissionList!=null){
//                   protocolSubmissionLookupResult.add(submissionList.get(0));
//               }
//           }
           return new ArrayList<IacucProtocolSubmission>(protocolSubmissionLookupResult);
       }
    
//    /**
//     * 
//     * @see org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionService#getProtocolSubmissionsLookupData(java.util.List)
//     */
//    public List<IacucProtocolSubmission> getProtocolSubmissionsLookupData(List<IacucProtocolSubmission> protocolSubmissionList) throws Exception{        
//        Collection<IacucProtocolSubmission> submissions = protocolSubmissionList;
//        List<IacucProtocolSubmission> protocolSubmissionsLookupResult = new ArrayList<IacucProtocolSubmission>();
//        Set<String> submissionProtocolNumberList = new HashSet<String>();       
//        
//        for (IacucProtocolSubmission protocolSubmissionData : submissions) {
//            submissionProtocolNumberList.add(protocolSubmissionData.getProtocolNumber());
//        }        
//        for(String submissionProtocolNumber : submissionProtocolNumberList){
//            List<IacucProtocolSubmission> protocolSubmissionLookupList=null;
//            protocolSubmissionLookupList = getProtocolSubmissionsLookupList(submissionProtocolNumber,protocolSubmissionList);
//           
//            if((protocolSubmissionLookupList!=null)&&(protocolSubmissionLookupList.size()>0)){
//                protocolSubmissionsLookupResult.addAll(protocolSubmissionLookupList);
//            }
//        }
//        return new ArrayList<IacucProtocolSubmission>(protocolSubmissionsLookupResult);
//    }
    
    /**
     * When a protocol is submitted for review, an action entry must be added to the protocol. 
     * This action entry is a history of the major events that have occurred during the life
     * of the protocol.
     * 
     * Also, for a submission, a Submission BO must be created.  It contains all of the relevant
     * data for a submission: type, checklists, reviewers, etc.
     * @throws Exception 
     * 
     * @see org.kuali.kra.iacuc.actions.submit.ProtocolSubmitActionService#submitToIacucForReview(org.kuali.kra.protocol.Protocol, org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction)
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
        
//TODO        protocolAssignReviewersService.assignReviewers(submission, submitAction.getReviewers());
        
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
        fieldValues.put("scheduleIdFk", CommonCommitteeSchedule.DEFAULT_SCHEDULE_ID.toString());
        List<CommitteeScheduleMinute> minutes = (List<CommitteeScheduleMinute>) businessObjectService.findMatching(CommitteeScheduleMinute.class, fieldValues);
        if (!minutes.isEmpty()) {
            for (CommitteeScheduleMinute minute : minutes) {
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
        Protocol protocol = protocolFinderDao.findCurrentProtocolByNumber(protocolNumber);
        IacucProtocolAction protocolAction = new IacucProtocolAction((IacucProtocol) protocol, null, IacucProtocolActionType.SUBMITTED_TO_IACUC);
        protocolAction.setComments(type + "-" + index + ": " + SUBMITTED_TO_IACUC);
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
    protected IacucProtocolSubmission createProtocolSubmission(IacucProtocol protocol, IacucProtocolSubmitAction submitAction) {
        IacucProtocolSubmissionBuilder submissionBuilder = new IacucProtocolSubmissionBuilder(protocol, submitAction.getSubmissionTypeCode());
        submissionBuilder.setSubmissionTypeQualifierCode(submitAction.getSubmissionQualifierTypeCode());
        submissionBuilder.setProtocolReviewTypeCode(submitAction.getProtocolReviewTypeCode());
        setSubmissionStatus(submissionBuilder, submitAction);
//TODO: Must implement the following for IACUC work
        setCommittee(submissionBuilder, submitAction);
//        setSchedule(submissionBuilder, submitAction);
//        addCheckLists(submissionBuilder, submitAction);
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
    
//    /**
//     * Set schedule for the submission.
//     * @param submissionBuilder the submission builder
//     * @param submitAction the submission data
//     */
//    protected void setSchedule(IacucProtocolSubmissionBuilder submissionBuilder, IacucProtocolSubmitAction submitAction) {
//        submissionBuilder.setSchedule(submitAction.getNewScheduleId());
//    }
//    
//    /**
//     * Add an optional Check List to the submission.  Exempt Studies and Expedited Reviews each
//     * require a check list to be added to the submission.  Other protocol review types do not
//     * have a check list.
//     * @param submission the submission
//     * @param submitAction the submission data
//     */
////TODO: Rework for IACUC refactor
////    protected void addCheckLists(IacucProtocolSubmissionBuilder submissionBuilder, IacucProtocolSubmitAction submitAction) {
////        if (isProtocolReviewType(submitAction, IacucProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE)) {
////            addExemptStudiesCheckList(submissionBuilder, submitAction);
////        }
////        else if (isProtocolReviewType(submitAction, IacucProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE)) {
////            addExpeditedReviewCheckList(submissionBuilder, submitAction);
////        }
////    }
//    
//    /**
//     * Is the submission of a certain protocol review type?
//     * @param submitAction the submit action
//     * @param protocolReviewTypeCode the protocol review type to compare against
//     * @return true if the submission uses the given protocol review type; otherwise false
//     */
//    protected boolean isProtocolReviewType(IacucProtocolSubmitAction submitAction, String protocolReviewTypeCode) {
//        return (StringUtils.equals(submitAction.getProtocolReviewTypeCode(), protocolReviewTypeCode));
//    }
//
//    /**
//     * Add the Exempt Studies Check List items to the submission.
//     * @param submission the submission
//     * @param submitAction the submission data
//     */
//    protected void addExemptStudiesCheckList(IacucProtocolSubmissionBuilder submissionBuilder, IacucProtocolSubmitAction submitAction) {
//        for (ExemptStudiesCheckListItem item : submitAction.getExemptStudiesCheckList()) {
//            if (item.getChecked()) {
//                submissionBuilder.addExemptStudiesCheckListItem(item.getExemptStudiesCheckListCode());
//            }
//        }
//    }
//    
//    /**
//     * Add the Expedited Review Check List items to the submission.
//     * @param submission the submission
//     * @param submitAction the submission data
//     */
//    protected void addExpeditedReviewCheckList(IacucProtocolSubmissionBuilder submissionBuilder, IacucProtocolSubmitAction action) {
//        for (ExpeditedReviewCheckListItem item : action.getExpeditedReviewCheckList()) {
//            if (item.getChecked()) {
//                submissionBuilder.addExpeditedReviewCheckListItem(item.getExpeditedReviewCheckListCode());
//            }
//        }
//    }
    
    /**
     * @TODO when this gets implemented, see IacucProtocolSubmissionLookupableHelperServiceImpl.getSearchResults ..... that function orginally called this function, but now that it returns null, it had broken the search, so that call is commented out.
     * @see org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionService#getProtocolSubmissionsLookupData(java.util.List)
     */
    public List<IacucProtocolSubmission> getProtocolSubmissionsLookupData(List<IacucProtocolSubmission> protocolSubmissionList) throws Exception {
        // TODO
        return null;
    }

}
