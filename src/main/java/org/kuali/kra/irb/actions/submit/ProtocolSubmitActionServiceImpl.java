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
package org.kuali.kra.irb.actions.submit;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Handles the processing of submitting a protocol to the IRB office for review.
 */
public class ProtocolSubmitActionServiceImpl implements ProtocolSubmitActionService {

    private static final String NEXT_ACTION_ID_KEY = "actionId";
    private static final String NEXT_SUBMISSION_NUMBER_KEY = "submissionNumber";
    
    private BusinessObjectService businessObjectService;
    private CommitteeService committeeService;
    
    /**
     * Set the committee service.
     * @param committeeService the committee service
     */
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    /**
     * Set the business object service.
     * @param businessObjectService the business office service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * When a protocol is submitted for review, an action entry must be added to the protocol. 
     * This action entry is a history of the major events that have occurred during the life
     * of the protocol.
     * 
     * Also, for a submission, a Submission BO must be created.  It contains all of the relevant
     * data for a submission: type, checklists, reviewers, etc.
     * 
     * @see org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService#submitToIrbForReview(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.submit.ProtocolSubmitAction)
     */
    public void submitToIrbForReview(Protocol protocol, ProtocolSubmitAction submitAction) {
        
        /*
         * The submission is saved first so that its new primary key can be added
         * to the protocol action entry.
         */
        ProtocolSubmission submission = createProtocolSubmission(protocol, submitAction);
        businessObjectService.save(submission);
        protocol.getProtocolSubmissions().add(submission);
        
        ProtocolAction protocolAction = createProtocolAction(protocol, submission, ProtocolActionType.SUBMIT_TO_IRB);
        protocol.getProtocolActions().add(protocolAction);
        
        businessObjectService.save(protocol.getProtocolDocument());
    }

    /**
     * Create a Protocol Action entry.
     * @param protocol the protocol
     * @param submission the submission
     * @param protocolActionTypeCode the event that has occurred
     * @return a new protocol action
     */
    private ProtocolAction createProtocolAction(Protocol protocol, ProtocolSubmission submission, String protocolActionTypeCode) {
        ProtocolAction protocolAction = new ProtocolAction();
        protocolAction.setProtocolId(protocol.getProtocolId());
        protocolAction.setProtocolNumber(protocol.getProtocolNumber());
        protocolAction.setSequenceNumber(0);
        protocolAction.setActionId(getNextValue(protocol, NEXT_ACTION_ID_KEY));
        protocolAction.setActionDate(new Timestamp(System.currentTimeMillis()));
        protocolAction.setProtocolActionTypeCode(protocolActionTypeCode);
        if (submission != null) {
            protocolAction.setSubmissionIdFk(submission.getSubmissionId());
            protocolAction.setSubmissionNumber(submission.getSubmissionNumber());
        }
        return protocolAction;
    }
    
    /**
     * Create a Protocol Submission.
     * @param protocol the protocol
     * @param submitAction the submission data
     * @return a protocol submission
     */
    private ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolSubmitAction submitAction) {
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocolId(protocol.getProtocolId());
        submission.setProtocolNumber(protocol.getProtocolNumber());
        submission.setSequenceNumber(0);
        submission.setSubmissionNumber(getNextValue(protocol, NEXT_SUBMISSION_NUMBER_KEY));
        submission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
        submission.setSubmissionTypeCode(submitAction.getSubmissionTypeCode());
        submission.setSubmissionTypeQualifierCode(submitAction.getSubmissionQualifierTypeCode());
        submission.setProtocolReviewTypeCode(submitAction.getProtocolReviewTypeCode());
        submission.setSubmissionStatusCode("100");  // this will need to be changed in future development
        addCommitteeData(submission, submitAction);
        addCheckLists(submission, submitAction);
        return submission;
    }
    
    /**
     * Add committee data, including schedule and reviewers, to the submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addCommitteeData(ProtocolSubmission submission, ProtocolSubmitAction submitAction) {
        Committee committee = committeeService.getCommitteeById(submitAction.getNewCommitteeId());
        if (committee != null) {
            submission.setCommitteeId(committee.getCommitteeId());
            submission.setCommitteeIdFk(committee.getId());
            CommitteeSchedule schedule = committeeService.getCommitteeSchedule(committee, submitAction.getNewScheduleId());
            if (schedule != null) {
                submission.setScheduleId(schedule.getScheduleId());
                submission.setScheduleIdFk(schedule.getId());
                addProtocolReviewers(submission, submitAction);
            }
        }
    }
    
    /**
     * Add the Protocol Reviewers to the Submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addProtocolReviewers(ProtocolSubmission submission, ProtocolSubmitAction submitAction) {
        for (ProtocolReviewerBean reviewer : submitAction.getReviewers()) {
            if (reviewer.getChecked()) {
                ProtocolReviewer protocolReviewer = createProtocolReviewer(submission, reviewer);
                submission.getProtocolReviewers().add(protocolReviewer);
            }
        }
    }

    /**
     * Create a Protocol Reviewer.
     * @param submission the submission
     * @param reviewer the reviewer data
     * @return the protocol reviewer
     */
    private ProtocolReviewer createProtocolReviewer(ProtocolSubmission submission, ProtocolReviewerBean reviewer) {
        ProtocolReviewer protocolReviewer = new ProtocolReviewer();
        protocolReviewer.setProtocolId(submission.getProtocolId());
        protocolReviewer.setSubmissionIdFk(submission.getSubmissionId());
        protocolReviewer.setProtocolNumber(submission.getProtocolNumber());
        protocolReviewer.setSequenceNumber(0);
        protocolReviewer.setSubmissionNumber(submission.getSubmissionNumber());
        protocolReviewer.setPersonId(reviewer.getPersonId());
        protocolReviewer.setNonEmployeeFlag(reviewer.getNonEmployeeFlag());
        protocolReviewer.setReviewerTypeCode(reviewer.getReviewerTypeCode());
        return protocolReviewer;
    }
    
    /**
     * Add an optional Check List to the submission.  Exempt Studies and Expedited Reviews each
     * require a check list to be added to the submission.  Other protocol review types do not
     * have a check list.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addCheckLists(ProtocolSubmission submission, ProtocolSubmitAction submitAction) {
        if (isProtocolReviewType(submitAction, ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE)) {
            addExemptStudiesCheckList(submission, submitAction);
        }
        else if (isProtocolReviewType(submitAction, ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE)) {
            addExpeditedReviewCheckList(submission, submitAction);
        }
    }
    
    /**
     * Is the submission of a certain protocol review type?
     * @param submitAction the submit action
     * @param protocolReviewTypeCode the protocol review type to compare against
     * @return true if the submission uses the given protocol review type; otherwise false
     */
    private boolean isProtocolReviewType(ProtocolSubmitAction submitAction, String protocolReviewTypeCode) {
        return (StringUtils.equals(submitAction.getProtocolReviewTypeCode(), protocolReviewTypeCode));
    }

    /**
     * Add the Exempt Studies Check List items to the submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addExemptStudiesCheckList(ProtocolSubmission submission, ProtocolSubmitAction submitAction) {
        for (ExemptStudiesCheckListItem item : submitAction.getExemptStudiesCheckList()) {
            if (item.getChecked()) {
                ProtocolExemptStudiesCheckListItem protocolChkLstItem = createProtocolExemptStudiesCheckListItem(submission, item);
                submission.getExemptStudiesCheckList().add(protocolChkLstItem);
            }
        }
    }

    /**
     * Create a Protocol Exempt Studies Check List Item.
     * @param submission the submission
     * @param item the check list item data
     * @return a protocol exempt studies check list item
     */
    private ProtocolExemptStudiesCheckListItem createProtocolExemptStudiesCheckListItem(ProtocolSubmission submission,
                                                                                        ExemptStudiesCheckListItem item) {
        ProtocolExemptStudiesCheckListItem chkLstItem = new ProtocolExemptStudiesCheckListItem();
        chkLstItem.setProtocolId(submission.getProtocolId());
        chkLstItem.setSubmissionIdFk(submission.getSubmissionId());
        chkLstItem.setProtocolNumber(submission.getProtocolNumber());
        chkLstItem.setSequenceNumber(0);
        chkLstItem.setSubmissionNumber(submission.getSubmissionNumber());
        chkLstItem.setExemptStudiesCheckListCode(item.getExemptStudiesCheckListCode());
        return chkLstItem;
    }
    
    /**
     * Add the Expedited Review Check List items to the submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addExpeditedReviewCheckList(ProtocolSubmission submission, ProtocolSubmitAction action) {
        for (ExpeditedReviewCheckListItem item : action.getExpeditedReviewCheckList()) {
            if (item.getChecked()) {
                ProtocolExpeditedReviewCheckListItem protocolChkLstItem = createProtocolExpeditedReviewCheckListItem(submission, item);
                submission.getExpeditedReviewCheckList().add(protocolChkLstItem);
            }
        }
    }

    /**
     * Create a Protocol Expedited Review Check List Item.
     * @param submission the submission
     * @param item the check list item data
     * @return a protocol expedited review check list item
     */
    private ProtocolExpeditedReviewCheckListItem createProtocolExpeditedReviewCheckListItem(ProtocolSubmission submission,
                                                                                            ExpeditedReviewCheckListItem item) {
        ProtocolExpeditedReviewCheckListItem chkLstItem = new ProtocolExpeditedReviewCheckListItem();
        chkLstItem.setProtocolId(submission.getProtocolId());
        chkLstItem.setSubmissionIdFk(submission.getSubmissionId());
        chkLstItem.setProtocolNumber(submission.getProtocolNumber());
        chkLstItem.setSequenceNumber(0);
        chkLstItem.setSubmissionNumber(submission.getSubmissionNumber());
        chkLstItem.setExpeditedReviewCheckListCode(item.getExpeditedReviewCheckListCode());
        return chkLstItem;
    }

    /**
     * Get the next value in a sequence.
     * @param protocol the protocol
     * @param key the unique key of the sequence
     * @return the next value
     */
    private Integer getNextValue(Protocol protocol, String key) {
        ProtocolDocument protocolDocument = protocol.getProtocolDocument();
        return protocolDocument.getDocumentNextValue(key);
    }
}
