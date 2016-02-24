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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolFinderDao;
import org.kuali.kra.iacuc.actions.IacucProtocolSubmissionDoc;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The Protocol Submission Builder is a helper class used to construct
 * a submission.  A client uses the builder to add items to the submission BO.
 * Once the submission is complete, the create() method is invoked by the
 * client to add the submission to the protocol and save it to the database.
 */
public class IacucProtocolSubmissionBuilder {

    private static final Log LOG = LogFactory.getLog(IacucProtocolSubmissionBuilder.class);
    private static final String NEXT_SUBMISSION_NUMBER_KEY = "submissionNumber";
    private static final String NEXT_SUBMISSION_DOCUMENT_ID_KEY = "submissionDocId";
    
    private IacucProtocolSubmission protocolSubmission;
    private List<ProtocolActionAttachment> actionAttachments = new ArrayList<ProtocolActionAttachment>();

    public IacucProtocolSubmissionBuilder(IacucProtocol protocol, String submissionTypeCode) {
        protocolSubmission = new IacucProtocolSubmission();
        protocolSubmission.setProtocol(protocol);
        protocolSubmission.setProtocolId(protocol.getProtocolId());
        protocolSubmission.setProtocolNumber(protocol.getProtocolNumber());
        protocolSubmission.setSequenceNumber(protocol.getSequenceNumber());
        protocolSubmission.setSubmissionNumber(getNextSubmissionNumber(protocol));
        
        protocolSubmission.setSubmissionDate(new Date(System.currentTimeMillis()));
        protocolSubmission.setSubmissionTypeCode(submissionTypeCode);
        
        IacucProtocolSubmission oldSubmission = protocol.getIacucProtocolSubmission();
        setValuesFromOldSubmission(protocolSubmission, oldSubmission);
        
        
        /**        
         * this will need to be changed in future development
         */
        protocolSubmission.setSubmissionStatusCode("100");
    }
    
    private Integer getNextSubmissionNumber(IacucProtocol protocol) {
        Integer nextSubmissionNumber;
        if (protocol.isAmendment() || protocol.isRenewal()) {
            String origProtocolNumber = protocol.getProtocolNumber();
            String protocolNumber = origProtocolNumber.substring(0, 10);
            IacucProtocol origProtocol = (IacucProtocol)getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            nextSubmissionNumber = origProtocol.getNextValue(NEXT_SUBMISSION_NUMBER_KEY);
            
            getBusinessObjectService().save(origProtocol.getProtocolDocument().getDocumentNextvalues());
            
        } else {
            nextSubmissionNumber = protocol.getNextValue(NEXT_SUBMISSION_NUMBER_KEY);
        }
        LOG.info("nextsubmissionnumber " + protocol.getProtocolId() + " " 
                + (CollectionUtils.isEmpty(protocol.getProtocolSubmissions()) ? 0 : protocol.getProtocolSubmissions().size()) + "-" + nextSubmissionNumber);
        return nextSubmissionNumber;
    }
    
    private IacucProtocolFinderDao getProtocolFinderDao() {
        return KcServiceLocator.getService(IacucProtocolFinderDao.class);
    }
    
    private void setValuesFromOldSubmission(IacucProtocolSubmission newSubmission, IacucProtocolSubmission oldSubmission) {
        if (oldSubmission != null) {
            //old submission may not be found if in a unit test
            //TODO : some of these, such as scheduleid/scheduleidfk, should not be copied over.
            // need to investigate if this is good for app.
            // comment scheduleid&scheduleidfk.  this will cause confusing if selected a different committee (or no committee)
            // then this schedule will not match the selected committee
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
    public IacucProtocolSubmission create() {
        protocolSubmission.setSubmissionDate(new Date(System.currentTimeMillis()));
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
        CommitteeBase committee = getCommitteeService().getCommitteeById(committeeId);
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
            CommitteeScheduleBase schedule = getCommitteeService().getCommitteeSchedule(protocolSubmission.getCommittee(), scheduleId);
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
                IacucProtocolSubmissionDoc submissionDoc = createProtocolSubmissionDoc(protocolSubmission, file.getFileName(), file.getContentType(), data, description);
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
    private IacucProtocolSubmissionDoc createProtocolSubmissionDoc(IacucProtocolSubmission submission, String fileName, String contentType, byte[] document, String description) {
        IacucProtocolSubmissionDoc submissionDoc = new IacucProtocolSubmissionDoc();
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
    
    private CommitteeServiceBase getCommitteeService() {
        return KcServiceLocator.getService(IacucCommitteeService.class);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    public List<ProtocolActionAttachment> getActionAttachments() {
        return actionAttachments;
    }

    public void setActionAttachments(List<ProtocolActionAttachment> actionAttachments) {
        this.actionAttachments = actionAttachments;
    }
}
