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
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;

/**
 * Finds the available set of Submission Types when a protocol
 * is submitted for review by the IRB Committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SubmissionTypeValuesFinder extends IrbActionsKeyValuesBase {
    
    /**
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
       
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        Collection<ProtocolSubmissionType> submissionTypes = this.getKeyValuesService().findAll(ProtocolSubmissionType.class);
        for (ProtocolSubmissionType submissionType : submissionTypes) {
            if (isSubmitForReviewType(submissionType)) {
                keyValues.add(new ConcreteKeyValue(submissionType.getSubmissionTypeCode(), submissionType.getDescription()));
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
        ProtocolForm pf = (ProtocolForm) KNSGlobalVariables.getKualiForm();
        if (pf != null) {
            String currentStatus  = pf.getProtocolDocument().getProtocol().getProtocolStatusCode();
            if (displayInitialSubmission(currentStatus)) {
                types.add(ProtocolSubmissionType.INITIAL_SUBMISSION);
            }
            if (displayAmendment(currentStatus, pf.getProtocolDocument().getProtocol())) {
                types.add(ProtocolSubmissionType.AMENDMENT);
            }
            if (displayContinuation(currentStatus, pf.getProtocolDocument().getProtocol())) {
                types.add(ProtocolSubmissionType.CONTINUATION);
            }
            if (displayContinuationWithAmendment(currentStatus, pf.getProtocolDocument().getProtocol())) {
                types.add(ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
            }
            if (displayResponseToPrevIRBNotication(currentStatus)) {
                types.add(ProtocolSubmissionType.RESPONSE_TO_PREV_IRB_NOTIFICATION);
            }
            if (displayResubmission(currentStatus)) {
                types.add(ProtocolSubmissionType.RESUBMISSION);
            }
        }
        return types;
    }
    
    private boolean displayInitialSubmission(String currentStatus) {
        String validStatuses[] = { ProtocolStatus.IN_PROGRESS,
                ProtocolStatus.WITHDRAWN, ProtocolStatus.SUBMITTED_TO_IRB};
        return validateCurrentStatus(currentStatus, validStatuses);
    }
    
    private boolean displayResponseToPrevIRBNotication(String currentStatus) {
        String validStatuses[] = { ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED,
                ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED,
                ProtocolStatus.DEFERRED, ProtocolStatus.SUBMITTED_TO_IRB };
        return validateCurrentStatus(currentStatus, validStatuses);
    }
    
    private boolean displayAmendment(String currentStatus, Protocol protocol) {
        String validStatuses[] = { ProtocolStatus.WITHDRAWN, ProtocolStatus.AMENDMENT_IN_PROGRESS, ProtocolStatus.SUBMITTED_TO_IRB };
        return validateCurrentStatus(currentStatus, validStatuses)  && hasAmmendmentProtocolNumber(protocol.getProtocolNumber());
    }
    
    private boolean displayContinuation(String currentStatus, Protocol protocol) {
        String validStatuses[] = { ProtocolStatus.WITHDRAWN, ProtocolStatus.RENEWAL_IN_PROGRESS, ProtocolStatus.SUBMITTED_TO_IRB };
        return validateCurrentStatus(currentStatus, validStatuses)  && hasRenewalProtocolNumber(protocol.getProtocolNumber());
    }
    
    private boolean displayContinuationWithAmendment(String currentStatus, Protocol protocol) {
        String validStatuses[] = { ProtocolStatus.WITHDRAWN, ProtocolStatus.RENEWAL_IN_PROGRESS, ProtocolStatus.SUBMITTED_TO_IRB };
        return validateCurrentStatus(currentStatus, validStatuses)  && hasRenewalProtocolNumber(protocol.getProtocolNumber());
    }
    
    private boolean hasAmmendmentProtocolNumber(String protocolNumber) {
        return protocolNumber.contains("A");
    }
    
    private boolean hasRenewalProtocolNumber(String protocolNumber) {
        return protocolNumber.contains("R");
    }
    
    private boolean displayResubmission(String currentStatus) {
        String validStatuses[] = {ProtocolStatus.WITHDRAWN, ProtocolStatus.SUBMITTED_TO_IRB};
        return validateCurrentStatus(currentStatus, validStatuses);
    }
    
    private boolean validateCurrentStatus(String currentStatus, String[] validStatuses) {
        for (String status : validStatuses) {
            if (StringUtils.equals(currentStatus, status)) {
                return true;
            }
        }
        return false;
    }
}
