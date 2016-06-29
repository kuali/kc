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
package org.kuali.kra.irb.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.coi.framework.ProjectPublisher;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;
import java.util.*;

/**
 * Handles the processing of submitting a protocol to the IRB office for review.
 */
public class ProtocolSubmitActionServiceImpl implements ProtocolSubmitActionService {
    
    private static final String PROTOCOL_NUMBER = "protocolNumber";

    private static final String SUBMIT_TO_IRB = "Submitted to IRB";
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private BusinessObjectService businessObjectService;
    private ProtocolAssignReviewersService protocolAssignReviewersService;
	private DataObjectService dataObjectService;
    private ProjectRetrievalService projectRetrievalService;
    private ProjectPublisher projectPublisher;

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

    @Override
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

    @SuppressWarnings("unchecked")
    protected List<ProtocolSubmission> getProtocolSubmissions(String protocolNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_NUMBER, protocolNumber);
        Collection<ProtocolSubmission> submissions = businessObjectService.findMatching(ProtocolSubmission.class, fieldValues);
        
        return new ArrayList<ProtocolSubmission>(submissions);
    }

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

    @Override
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
    @Override
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
        protocolStatus.setDescription(SUBMIT_TO_IRB);
        protocol.setProtocolStatus(protocolStatus);
        protocol.setProtocolStatusCode(ProtocolActionType.SUBMIT_TO_IRB);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        
        if (submission.getScheduleIdFk() != null) {
            updateDefaultSchedule(submission);
        }
        
        List<PessimisticLock> locks = protocol.getProtocolDocument().getPessimisticLocks();
        if (locks != null) {
        	for(PessimisticLock lock : locks) {
        		dataObjectService.delete(lock);
        	}
        }

        protocol.getProtocolDocument().getPessimisticLocks().clear();
        final ProtocolDocument protocolDocument = (ProtocolDocument) documentService.saveDocument(protocol.getProtocolDocument());
        protocol.refresh();
        getProjectPublisher().publishProject(getProjectRetrievalService().retrieveProject(protocolDocument.getProtocol().getProtocolNumber()));
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
     * @param submissionBuilder the submission
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
     * @param submissionBuilder the submission
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
     */
    protected void addExpeditedReviewCheckList(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction action) {
        for (ExpeditedReviewCheckListItem item : action.getExpeditedReviewCheckList()) {
            if (item.getChecked()) {
                submissionBuilder.addExpeditedReviewCheckListItem(item.getExpeditedReviewCheckListCode());
            }
        }
    }
    
    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }
    
    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public ProjectPublisher getProjectPublisher() {
        //since COI is loaded last and @Lazy does not work, we have to use the ServiceLocator
        if (projectPublisher == null) {
            projectPublisher = KcServiceLocator.getService(ProjectPublisher.class);
        }

        return projectPublisher;
    }

    public void setProjectPublisher(ProjectPublisher projectPublisher) {
        this.projectPublisher = projectPublisher;
    }

    public ProjectRetrievalService getProjectRetrievalService() {
        return projectRetrievalService;
    }

    public void setProjectRetrievalService(ProjectRetrievalService projectRetrievalService) {
        this.projectRetrievalService = projectRetrievalService;
    }
}
