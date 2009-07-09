/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The Protocol Submission Builder is a helper class used to construct
 * a submission.  A client uses the builder to add items to the submission BO.
 * Once the submission is complete, the create() method is invoked by the
 * client to add the submission to the protocol and save it to the database.
 */
public class ProtocolSubmissionBuilder {

    private static final String NEXT_SUBMISSION_NUMBER_KEY = "submissionNumber";
    private static final String NEXT_SUBMISSION_DOCUMENT_ID_KEY = "submissionDocId";
    
    private ProtocolSubmission protocolSubmission;
    private List<FormFile> attachments = new ArrayList<FormFile>();
    
    /**
     * Constructs a ProtocolSubmissionBuilder.
     * @param protocol
     * @param submissionTypeCode
     */
    public ProtocolSubmissionBuilder(Protocol protocol, String submissionTypeCode) {
        protocolSubmission = new ProtocolSubmission();
        protocolSubmission.setProtocol(protocol);
        protocolSubmission.setProtocolId(protocol.getProtocolId());
        protocolSubmission.setProtocolNumber(protocol.getProtocolNumber());
        protocolSubmission.setSequenceNumber(protocol.getSequenceNumber());
        protocolSubmission.setSubmissionNumber(protocol.getNextValue(NEXT_SUBMISSION_NUMBER_KEY));
        protocolSubmission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
        protocolSubmission.setSubmissionTypeCode(submissionTypeCode);
        protocolSubmission.setSubmissionStatusCode("100");  // this will need to be changed in future development
    }
    
    /**
     * Saves the submission to the database and adds it to the protocol.
     * @return the submission
     */
    public ProtocolSubmission create() {
        getBusinessObjectService().save(protocolSubmission);
        protocolSubmission.getProtocol().getProtocolSubmissions().add(protocolSubmission);
        saveAttachments();
        return protocolSubmission;
    }
    
    /**
     * Set the submission type qualifier code.
     * @param submissionTypeQualifierCode
     */
    public void setSubmissionTypeQualifierCode(String submissionTypeQualifierCode) {
        protocolSubmission.setSubmissionTypeQualifierCode(submissionTypeQualifierCode);
    }
    
    /**
     * Set the protocol review type code.
     * @param protocolReviewTypeCode
     */
    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        protocolSubmission.setProtocolReviewTypeCode(protocolReviewTypeCode);
    }
    
    /**
     * Set the comments for the submission.
     * @param comments
     */
    public void setComments(String comments) {
        protocolSubmission.setComments(comments);
    }
    
    /**
     * Set the committee that the submission will use.
     * @param committeeId
     * @return
     */
    public boolean setCommittee(String committeeId) {
        Committee committee = getCommitteeService().getCommitteeById(committeeId);
        if (committee != null) {
            protocolSubmission.setCommitteeId(committee.getCommitteeId());
            protocolSubmission.setCommitteeIdFk(committee.getId());
            protocolSubmission.setCommittee(committee);
            return true;
        }
        return false;
    }
    
    /**
     * Set the schedule that the committee will use.
     * @param committeeId
     * @param scheduleId
     * @return
     */
    public boolean setSchedule(String committeeId, String scheduleId) {
        if (setCommittee(committeeId)) {
            CommitteeSchedule schedule = getCommitteeService().getCommitteeSchedule(protocolSubmission.getCommittee(), scheduleId);
            if (schedule != null) {
                protocolSubmission.setScheduleId(schedule.getScheduleId());
                protocolSubmission.setScheduleIdFk(schedule.getId());
                protocolSubmission.setCommitteeSchedule(schedule);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Add a reviewer to the submission.
     * @param personId
     * @param reviewerTypeCode
     * @param nonEmployeeFlag
     */
    public void addReviewer(String personId, String reviewerTypeCode, boolean nonEmployeeFlag) {
        protocolSubmission.getProtocolReviewers().add(createProtocolReviewer(personId, reviewerTypeCode, nonEmployeeFlag));
    }
    
    /**
     * Create a protocol reviewer.
     * @param personId
     * @param reviewerTypeCode
     * @param nonEmployeeFlag
     * @return
     */
    private ProtocolReviewer createProtocolReviewer(String personId, String reviewerTypeCode, boolean nonEmployeeFlag) {
        ProtocolReviewer protocolReviewer = new ProtocolReviewer();
        protocolReviewer.setProtocolId(protocolSubmission.getProtocolId());
        protocolReviewer.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        protocolReviewer.setProtocolNumber(protocolSubmission.getProtocolNumber());
        protocolReviewer.setSequenceNumber(protocolSubmission.getSequenceNumber());
        protocolReviewer.setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        protocolReviewer.setPersonId(personId);
        protocolReviewer.setReviewerTypeCode(reviewerTypeCode);
        protocolReviewer.setNonEmployeeFlag(nonEmployeeFlag);
        return protocolReviewer;
    }
    
    /**
     * Add an exempt studies check list item to the submission.
     * @param exemptStudiesCheckListCode
     */
    public void addExemptStudiesCheckListItem(String exemptStudiesCheckListCode) {
        protocolSubmission.getExemptStudiesCheckList().add(createProtocolExemptStudiesCheckListItem(exemptStudiesCheckListCode));
    }
    
    /**
     * Create an exempt studies check list item.
     * @param exemptStudiesCheckListCode
     * @return
     */
    private ProtocolExemptStudiesCheckListItem createProtocolExemptStudiesCheckListItem(String exemptStudiesCheckListCode) {
        ProtocolExemptStudiesCheckListItem chkLstItem = new ProtocolExemptStudiesCheckListItem();
        chkLstItem.setProtocolId(protocolSubmission.getProtocolId());
        chkLstItem.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        chkLstItem.setProtocolNumber(protocolSubmission.getProtocolNumber());
        chkLstItem.setSequenceNumber(protocolSubmission.getSequenceNumber());
        chkLstItem.setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        chkLstItem.setExemptStudiesCheckListCode(exemptStudiesCheckListCode);
        return chkLstItem;
    }
    
    /**
     * Add an expedited review check list item to the submission.
     * @param expeditedReviewCheckListCode
     */
    public void addExpeditedReviewCheckListItem(String expeditedReviewCheckListCode) {
        protocolSubmission.getExpeditedReviewCheckList().add(createProtocolExpeditedReviewCheckListItem(expeditedReviewCheckListCode));
    }
    
    /**
     * Create an expedited review check list item.
     * @param expeditedReviewCheckListCode
     * @return
     */
    private ProtocolExpeditedReviewCheckListItem createProtocolExpeditedReviewCheckListItem(String expeditedReviewCheckListCode) {
        ProtocolExpeditedReviewCheckListItem chkLstItem = new ProtocolExpeditedReviewCheckListItem();
        chkLstItem.setProtocolId(protocolSubmission.getProtocolId());
        chkLstItem.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        chkLstItem.setProtocolNumber(protocolSubmission.getProtocolNumber());
        chkLstItem.setSequenceNumber(protocolSubmission.getSequenceNumber());
        chkLstItem.setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        chkLstItem.setExpeditedReviewCheckListCode(expeditedReviewCheckListCode);
        return chkLstItem;
    }
    
    /**
     * Add an attachment to the submission.
     * @param file
     */
    public void addAttachment(FormFile file) {
        if (file != null) {
            attachments.add(file);
        }
    }
    
    /**
     * Save the attachments to the database.
     */
    private void saveAttachments() {
        for (FormFile file : attachments) {
            saveAttachment(file);
        }
    }
    
    /**
     * Save an attachment file to the database.
     * @param file
     */
    private void saveAttachment(FormFile file) {
        try {
            byte[] data = file.getFileData();
            if (data.length > 0) {
                ProtocolSubmissionDoc submissionDoc = createProtocolSubmissionDoc(protocolSubmission, file.getFileName(), data);
                getBusinessObjectService().save(submissionDoc);
            }
        }
        catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Create a protocol submission document (attachment).
     * @param submission
     * @param fileName
     * @param document
     * @return
     */
    private ProtocolSubmissionDoc createProtocolSubmissionDoc(ProtocolSubmission submission, String fileName, byte[] document) {
        ProtocolSubmissionDoc submissionDoc = new ProtocolSubmissionDoc();
        submissionDoc.setProtocolNumber(submission.getProtocolNumber());
        submissionDoc.setSequenceNumber(submission.getSequenceNumber());
        submissionDoc.setSubmissionNumber(submission.getSubmissionNumber());
        submissionDoc.setProtocolId(submission.getProtocolId());
        submissionDoc.setSubmissionIdFk(submission.getSubmissionId());
        submissionDoc.setProtocol(submission.getProtocol());
        submissionDoc.setProtocolSubmission(submission);
        submissionDoc.setDocumentId(submission.getProtocol().getNextValue(NEXT_SUBMISSION_DOCUMENT_ID_KEY));
        submissionDoc.setFileName(fileName);
        submissionDoc.setDocument(document);
        return submissionDoc;
    }
    
    private CommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommitteeService.class);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
}
