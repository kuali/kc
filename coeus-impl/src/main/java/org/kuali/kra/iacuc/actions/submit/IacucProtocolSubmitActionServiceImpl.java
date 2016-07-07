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
package org.kuali.kra.iacuc.actions.submit;

import java.sql.Date;
import java.util.*;

import org.kuali.coeus.coi.framework.Project;
import org.kuali.coeus.coi.framework.ProjectPublisher;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.assignreviewers.IacucProtocolAssignReviewersService;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class IacucProtocolSubmitActionServiceImpl implements IacucProtocolSubmitActionService {
    
    private static final String PROTOCOL_NUMBER = "protocolNumber";

    private static final String SUBMITTED_TO_IACUC = "SubmittedToIACUC";
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private BusinessObjectService businessObjectService;
    private IacucProtocolAssignReviewersService iacucProtocolAssignReviewersService;
	private DataObjectService dataObjectService;
    private ProjectRetrievalService projectRetrievalService;
    private ProjectPublisher projectPublisher;

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

    protected List<IacucProtocolSubmission> getProtocolSubmissions(String protocolNumber) {
        Collection<IacucProtocolSubmission> submissions = businessObjectService.findMatching(IacucProtocolSubmission.class,
                Collections.singletonMap(PROTOCOL_NUMBER, protocolNumber));
        return new ArrayList<>(submissions);
    }
    
    /**
     * When a protocol is submitted for review, an action entry must be added to the protocol. 
     * This action entry is a history of the major events that have occurred during the life
     * of the protocol.
     * 
     * Also, for a submission, a Submission BO must be created.  It contains all of the relevant
     * data for a submission: type, checklists, reviewers, etc.
     * 
     */
    @Override
    public void submitToIacucForReview(IacucProtocol protocol, IacucProtocolSubmitAction submitAction) {
        
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
        
        getIacucProtocolAssignReviewersService().assignReviewers(submission, submitAction.getReviewers());
        
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

        List<PessimisticLock> locks = protocol.getProtocolDocument().getPessimisticLocks();
        if (locks != null) {
        	for(PessimisticLock lock : locks) {
        		dataObjectService.delete(lock);
        	}
        }
        
        protocol.getProtocolDocument().getPessimisticLocks().clear();

        final IacucProtocolDocument protocolDocument;
        try {
            protocolDocument = (IacucProtocolDocument) documentService.saveDocument(protocol.getProtocolDocument());
        } catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
        protocol.refresh();
        final Project project = getProjectRetrievalService().retrieveProject(protocolDocument.getIacucProtocol().getProtocolNumber());
        if (project != null) {
            getProjectPublisher().publishProject(project);
        }
    }

    protected void updateDefaultSchedule(IacucProtocolSubmission submission) {
        Map<String, String> fieldValues = new HashMap<>();
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
        setSchedule(submissionBuilder, submitAction);
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
     * Set schedule for the submission.
     * @param submissionBuilder the submission builder
     * @param submitAction the submission data
     */
    protected void setSchedule(IacucProtocolSubmissionBuilder submissionBuilder, IacucProtocolSubmitAction submitAction) {
        submissionBuilder.setSchedule(submitAction.getNewScheduleId());
    }

    public IacucProtocolAssignReviewersService getIacucProtocolAssignReviewersService() {
        return iacucProtocolAssignReviewersService;
    }

    public void setIacucProtocolAssignReviewersService(IacucProtocolAssignReviewersService iacucProtocolAssignReviewersService) {
        this.iacucProtocolAssignReviewersService = iacucProtocolAssignReviewersService;
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

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
}
