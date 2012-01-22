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
package org.kuali.kra.irb.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.notifyirb.ProtocolActionAttachment;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * The Protocol Submission Builder is a helper class used to construct
 * a submission.  A client uses the builder to add items to the submission BO.
 * Once the submission is complete, the create() method is invoked by the
 * client to add the submission to the protocol and save it to the database.
 */
public class ProtocolSubmissionBuilder {

    private static final Log LOG = LogFactory.getLog(ProtocolSubmissionBuilder.class);
    private static final String NEXT_SUBMISSION_NUMBER_KEY = "submissionNumber";
    private static final String NEXT_SUBMISSION_DOCUMENT_ID_KEY = "submissionDocId";
    
    private ProtocolSubmission protocolSubmission;
    private List<FormFile> attachments = new ArrayList<FormFile>();
    private List<ProtocolActionAttachment> actionAttachments = new ArrayList<ProtocolActionAttachment>();
    
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
        protocolSubmission.setSubmissionNumber(getNextSubmissionNumber(protocol));
        
        protocolSubmission.setSubmissionDate(new Date(System.currentTimeMillis()));
        protocolSubmission.setSubmissionTypeCode(submissionTypeCode);
        
        ProtocolSubmission oldSubmission = protocol.getProtocolSubmission();
        setValuesFromOldSubmission(protocolSubmission, oldSubmission);
        
        
        /**        
         * this will need to be changed in future development
         */
        protocolSubmission.setSubmissionStatusCode("100");
    }
    
    private Integer getNextSubmissionNumber(Protocol protocol) {
        Integer nextSubmissionNumber;
        if (protocol.isAmendment() || protocol.isRenewal()) {
            String origProtocolNumber = protocol.getProtocolNumber();
            String protocolNumber = origProtocolNumber.substring(0, 10);
            Protocol origProtocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            nextSubmissionNumber = origProtocol.getNextValue(NEXT_SUBMISSION_NUMBER_KEY);            
            getBusinessObjectService().save(origProtocol.getProtocolDocument().getDocumentNextvalues());
            
        } else {
            nextSubmissionNumber = protocol.getNextValue(NEXT_SUBMISSION_NUMBER_KEY);
        }
        LOG.info("nextsubmissionnumber " + protocol.getProtocolId() + " " 
                + (CollectionUtils.isEmpty(protocol.getProtocolSubmissions()) ? 0 : protocol.getProtocolSubmissions().size()) + "-" + nextSubmissionNumber);
        return nextSubmissionNumber;
    }
    
    private ProtocolFinderDao getProtocolFinderDao() {
        return KraServiceLocator.getService(ProtocolFinderDao.class);    
    }
    
    private void setValuesFromOldSubmission(ProtocolSubmission newSubmission, ProtocolSubmission oldSubmission) {
        if (oldSubmission != null) {
            //old submission may not be found if in a unit test
            //TODO : some of these, such as scheduleid/scheduleidfk, should not be copied over.
            // need to investigate if this is good for app.
            // comment scheduleid&scheduleidfk.  this will cause confusing if selected a different committee (or no committee)
            // then this schedule will not match the selected committee
            //protocolSubmission.setScheduleId(oldSubmission.getScheduleId());
            //protocolSubmission.setScheduleIdFk(oldSubmission.getScheduleIdFk());
            protocolSubmission.setSubmissionTypeQualifierCode(oldSubmission.getSubmissionTypeQualifierCode());
            protocolSubmission.setComments(oldSubmission.getComments());
            protocolSubmission.setYesVoteCount(oldSubmission.getYesVoteCount());
            protocolSubmission.setNoVoteCount(oldSubmission.getNoVoteCount());
            protocolSubmission.setAbstainerCount(oldSubmission.getAbstainerCount());
            protocolSubmission.setAbstainers(oldSubmission.getAbstainers());
            protocolSubmission.setRecusedCount(oldSubmission.getRecusedCount());
            protocolSubmission.setRecusers(oldSubmission.getRecusers());
            protocolSubmission.setVotingComments(oldSubmission.getVotingComments());
            protocolSubmission.setBillable(oldSubmission.isBillable());
        } else {
            protocolSubmission.setAbstainerCount(0);
            protocolSubmission.setRecusedCount(0);
            protocolSubmission.setBillable(false);
        }
    }
    
    /**
     * Saves the submission to the database and adds it to the protocol.
     * @return the submission
     */
    public ProtocolSubmission create() {
        protocolSubmission.setSubmissionDate(new Date(System.currentTimeMillis()));
        getBusinessObjectService().save(protocolSubmission);
        protocolSubmission.getProtocol().getProtocolSubmissions().add(protocolSubmission);
//        if (ProtocolSubmissionType.NOTIFY_IRB.equals(protocolSubmission.getSubmissionTypeCode())) {
            saveAttachments();
//        } else {
//            saveAttachments();
//        }
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
     * Set the submission status.
     * @param submissionStatusCode
     */
    public void setSubmissionStatus(String submissionStatusCode) {
        protocolSubmission.setSubmissionStatusCode(submissionStatusCode);
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
     */
    public void setCommittee(String committeeId) {
        Committee committee = getCommitteeService().getCommitteeById(committeeId);
        if (committee != null) {
            protocolSubmission.setCommitteeId(committee.getCommitteeId());
            protocolSubmission.setCommitteeIdFk(committee.getId());
            protocolSubmission.setCommittee(committee);
        }    
        
    }
    
    /**
     * Set the schedule that the committee will use.
     * @param scheduleId
     */
    public void setSchedule(String scheduleId) {
        if (protocolSubmission.getCommittee() != null) {
            CommitteeSchedule schedule = getCommitteeService().getCommitteeSchedule(protocolSubmission.getCommittee(), scheduleId);
            if (schedule != null) {
                protocolSubmission.setScheduleId(schedule.getScheduleId());
                protocolSubmission.setScheduleIdFk(schedule.getId());
                protocolSubmission.setCommitteeSchedule(schedule);
            } else {
                // this builder also copied some data from previous submission.  if it is not cleared here, then it will cause problem
                clearCommScheduleDataFromPreviousSubmission();
            }
        } else {
            // this builder also copied some data from previous submission.  if it is not cleared here, then it will cause problem
            clearCommScheduleDataFromPreviousSubmission();
        }
    }
    
    private void clearCommScheduleDataFromPreviousSubmission() {
        protocolSubmission.setScheduleId(null);
        protocolSubmission.setScheduleIdFk(null);
        protocolSubmission.setCommitteeSchedule(null); 
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
//    public void addAttachment(FormFile file) {
//        if (file != null) {
//            attachments.add(file);
//        }
//    }
    
    /**
     * Save the attachments to the database.
     */
//    private void saveAttachments() {
//        for (FormFile file : attachments) {
//            saveAttachment(file, "");
//        }
//    }
    
    /*
     * save notify irb attachments.
     */
    private void saveAttachments() {
        for (ProtocolActionAttachment attachment : actionAttachments) {
            saveAttachment(attachment.getFile(), attachment.getDescription());
        }
        
    }
    /**
     * Save an attachment file to the database.
     * @param file
     */
    private void saveAttachment(FormFile file, String description) {
        try {
            byte[] data = file.getFileData();
            if (data.length > 0) {
                ProtocolSubmissionDoc submissionDoc = createProtocolSubmissionDoc(protocolSubmission, file.getFileName(), file.getContentType(), data, description);
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
    private ProtocolSubmissionDoc createProtocolSubmissionDoc(ProtocolSubmission submission, String fileName, String contentType, byte[] document, String description) {
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
        submissionDoc.setDescription(description);
        submissionDoc.setContentType(contentType);
        return submissionDoc;
    }
    
    private CommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommitteeService.class);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    public List<ProtocolActionAttachment> getActionAttachments() {
        return actionAttachments;
    }

    public void setActionAttachments(List<ProtocolActionAttachment> actionAttachments) {
        this.actionAttachments = actionAttachments;
    }
}