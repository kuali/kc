/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Finds the available set of Submission Types when a protocol
 * is submitted for review by the IRB Committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SubmissionTypeValuesFinder extends IrbActionsKeyValuesBase {
    
    /**
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
       
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        
        Collection<ProtocolSubmissionType> submissionTypes = this.getKeyValuesService().findAll(ProtocolSubmissionType.class);
        for (ProtocolSubmissionType submissionType : submissionTypes) {
            if (isSubmitForReviewType(submissionType)) {
                keyValues.add(new KeyLabelPair(submissionType.getSubmissionTypeCode(), submissionType.getDescription()));
            }
        }
        
        return keyValues;
    }

    /**
     * There are many submission types but only a few are available
     * for a submission for a protocol that will be reviewed.
     * @param submissionType the submission type
     * @return true if applicable for a review submission; otherwise false
     */
    private boolean isSubmitForReviewType(ProtocolSubmissionType submissionType) {
        /*
        String typeCodes[] = { ProtocolSubmissionType.INITIAL_SUBMISSION,
                               ProtocolSubmissionType.RESPONSE_TO_PREV_IRB_NOTIFICATION,
                               ProtocolSubmissionType.AMENDMENT,
                               ProtocolSubmissionType.CONTINUATION,
                               ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT };
                               */
        Collection<String> typeCodes = getValidSubmissionTypes();

        for (String typeCode : typeCodes) {
            if (StringUtils.equals(typeCode, submissionType.getSubmissionTypeCode())) {
                return true;
            }
        }
        return false;
    }
    
    private Collection<String> getValidSubmissionTypes() {
        Collection<String> types = new ArrayList<String>();
        ProtocolForm pf = (ProtocolForm)GlobalVariables.getKualiForm();
        if (pf != null){
            String currentStatus  = pf.getProtocolDocument().getProtocol().getProtocolStatusCode();
            if (displayInitialSubmission(currentStatus)){
                types.add(ProtocolSubmissionType.INITIAL_SUBMISSION);
            }
            if (displayAmendment(currentStatus)) {
                types.add(ProtocolSubmissionType.AMENDMENT);
            }
            if( displayContinuation(currentStatus)) {
                types.add(ProtocolSubmissionType.CONTINUATION);
            }
            if (displayContinuationWithAmendment(currentStatus)) {
                types.add(ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
            }
            if (displayResponseToPrevIRBNotication(currentStatus)) {
                types.add(ProtocolSubmissionType.RESPONSE_TO_PREV_IRB_NOTIFICATION);
            }
        }
        return types;
    }
    
    private boolean displayInitialSubmission(String currentStatus){
        String validStatuses[] = { ProtocolStatus.IN_PROGRESS,
                ProtocolStatus.SUBMITTED_TO_IRB,
                ProtocolStatus.DISAPPROVED,
                ProtocolStatus.WITHDRAWN,
                ProtocolStatus.DEFERRED,
                ProtocolStatus.IRB_REVIEW_NOT_REQUIRED };
        for (String status : validStatuses) {
            if (StringUtils.equals(currentStatus, status)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean displayResponseToPrevIRBNotication(String currentStatus){
        String validStatuses[] = { ProtocolStatus.SUBMITTED_TO_IRB,
                ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED,
                ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED,
                ProtocolStatus.AMENDMENT_MERGED,
                ProtocolStatus.AMENDMENT_IN_PROGRESS,
                ProtocolStatus.RENEWAL_IN_PROGRESS,
                ProtocolStatus.RENEWAL_MERGED,
                ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT,
                ProtocolStatus.SUSPENDED_BY_PI,
                ProtocolStatus.SUSPENDED_BY_DSMB,
                ProtocolStatus.SUSPENDED_BY_IRB,
                ProtocolStatus.CLOSED_ADMINISTRATIVELY,
                ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT,
                ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY,
                ProtocolStatus.EXEMPT,
                ProtocolStatus.DEFERRED,
                ProtocolStatus.CLOSED_BY_INVESTIGATOR };
        for (String status : validStatuses) {
            if (StringUtils.equals(currentStatus, status)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean displayAmendment(String currentStatus){
        String validStatuses[] = { ProtocolStatus.SUBMITTED_TO_IRB,
                ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED,
                ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED,
                ProtocolStatus.AMENDMENT_MERGED,
                ProtocolStatus.AMENDMENT_IN_PROGRESS,
                ProtocolStatus.RENEWAL_MERGED,
                ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT,
                ProtocolStatus.SUSPENDED_BY_PI,
                ProtocolStatus.SUSPENDED_BY_DSMB,
                ProtocolStatus.SUSPENDED_BY_IRB,
                ProtocolStatus.CLOSED_ADMINISTRATIVELY,
                ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT,
                ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY,
                ProtocolStatus.EXEMPT,
                ProtocolStatus.DEFERRED,
                ProtocolStatus.CLOSED_BY_INVESTIGATOR };
        for (String status : validStatuses) {
            if (StringUtils.equals(currentStatus, status)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean displayContinuation(String currentStatus){
        String validStatuses[] = { ProtocolStatus.SUBMITTED_TO_IRB,
                ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED,
                ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED,
                ProtocolStatus.AMENDMENT_MERGED,
                ProtocolStatus.RENEWAL_IN_PROGRESS,
                ProtocolStatus.RENEWAL_MERGED,
                ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT,
                ProtocolStatus.SUSPENDED_BY_PI,
                ProtocolStatus.SUSPENDED_BY_DSMB,
                ProtocolStatus.SUSPENDED_BY_IRB,
                ProtocolStatus.EXPIRED,
                ProtocolStatus.CLOSED_ADMINISTRATIVELY,
                ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT,
                ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY,
                ProtocolStatus.EXEMPT,
                ProtocolStatus.DEFERRED,
                ProtocolStatus.CLOSED_BY_INVESTIGATOR };
        for (String status : validStatuses) {
            if (StringUtils.equals(currentStatus, status)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean displayContinuationWithAmendment(String currentStatus){
        String validStatuses[] = { ProtocolStatus.SUBMITTED_TO_IRB,
                ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED,
                ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED,
                ProtocolStatus.AMENDMENT_MERGED,
                ProtocolStatus.RENEWAL_IN_PROGRESS,
                ProtocolStatus.RENEWAL_MERGED,
                ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT,
                ProtocolStatus.SUSPENDED_BY_PI,
                ProtocolStatus.SUSPENDED_BY_DSMB,
                ProtocolStatus.SUSPENDED_BY_IRB,
                ProtocolStatus.EXPIRED,
                ProtocolStatus.CLOSED_ADMINISTRATIVELY,
                ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT,
                ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY,
                ProtocolStatus.EXEMPT,
                ProtocolStatus.DEFERRED,
                ProtocolStatus.CLOSED_BY_INVESTIGATOR };
        for (String status : validStatuses) {
            if (StringUtils.equals(currentStatus, status)) {
                return true;
            }
        }
        return false;
    }
}
