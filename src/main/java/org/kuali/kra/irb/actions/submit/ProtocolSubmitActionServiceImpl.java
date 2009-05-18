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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Handles the processing of submitting a protocol to the IRB office for review.
 */
public class ProtocolSubmitActionServiceImpl implements ProtocolSubmitActionService {

    private BusinessObjectService businessObjectService;

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
        
        ProtocolAction protocolAction = new ProtocolAction(protocol, submission, ProtocolActionType.SUBMIT_TO_IRB);
        protocol.getProtocolActions().add(protocolAction);
        
        businessObjectService.save(protocol.getProtocolDocument());
    }
    
    /**
     * Create a Protocol Submission.
     * @param protocol the protocol
     * @param submitAction the submission data
     * @return a protocol submission
     */
    private ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolSubmitAction submitAction) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, submitAction.getSubmissionTypeCode());
        submissionBuilder.setSubmissionTypeQualifierCode(submitAction.getSubmissionQualifierTypeCode());
        submissionBuilder.setProtocolReviewTypeCode(submitAction.getProtocolReviewTypeCode());
        addCommitteeData(submissionBuilder, submitAction);
        addCheckLists(submissionBuilder, submitAction);
        return submissionBuilder.create();
    }
    
    /**
     * Add committee data, including schedule and reviewers, to the submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addCommitteeData(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
        if (submissionBuilder.setSchedule(submitAction.getNewCommitteeId(), submitAction.getNewScheduleId())) {
            addProtocolReviewers(submissionBuilder, submitAction);
        }
    }
    
    /**
     * Add the Protocol Reviewers to the Submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addProtocolReviewers(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
        for (ProtocolReviewerBean reviewer : submitAction.getReviewers()) {
            if (reviewer.getChecked()) {
                submissionBuilder.addReviewer(reviewer.getPersonId(),
                                              reviewer.getReviewerTypeCode(),
                                              reviewer.getNonEmployeeFlag());
            }
        }
    }
    
    /**
     * Add an optional Check List to the submission.  Exempt Studies and Expedited Reviews each
     * require a check list to be added to the submission.  Other protocol review types do not
     * have a check list.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addCheckLists(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
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
    private boolean isProtocolReviewType(ProtocolSubmitAction submitAction, String protocolReviewTypeCode) {
        return (StringUtils.equals(submitAction.getProtocolReviewTypeCode(), protocolReviewTypeCode));
    }

    /**
     * Add the Exempt Studies Check List items to the submission.
     * @param submission the submission
     * @param submitAction the submission data
     */
    private void addExemptStudiesCheckList(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction submitAction) {
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
    private void addExpeditedReviewCheckList(ProtocolSubmissionBuilder submissionBuilder, ProtocolSubmitAction action) {
        for (ExpeditedReviewCheckListItem item : action.getExpeditedReviewCheckList()) {
            if (item.getChecked()) {
                submissionBuilder.addExpeditedReviewCheckListItem(item.getExpeditedReviewCheckListCode());
            }
        }
    }
}
