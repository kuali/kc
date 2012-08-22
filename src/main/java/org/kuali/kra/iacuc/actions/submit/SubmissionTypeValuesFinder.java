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
package org.kuali.kra.iacuc.actions.submit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.actions.IacucActionsKeyValuesBase;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;

/**
 * Finds the available set of Submission Types when a protocol
 * is submitted for review by the IRB Committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SubmissionTypeValuesFinder extends IacucActionsKeyValuesBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4743158845190302163L;

    /**
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
       
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        Collection<IacucProtocolSubmissionType> submissionTypes = this.getKeyValuesService().findAll(IacucProtocolSubmissionType.class);
        for (IacucProtocolSubmissionType submissionType : submissionTypes) {
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
    private boolean isSubmitForReviewType(IacucProtocolSubmissionType submissionType) {
        
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
                types.add(IacucProtocolSubmissionType.INITIAL_SUBMISSION);
            }
            if (displayAmendment(currentStatus, pf.getProtocolDocument().getProtocol())) {
                types.add(IacucProtocolSubmissionType.AMENDMENT);
            }
            if (displayContinuation(currentStatus, pf.getProtocolDocument().getProtocol())) {
                types.add(IacucProtocolSubmissionType.CONTINUATION);
            }
            if (displayContinuationWithAmendment(currentStatus, pf.getProtocolDocument().getProtocol())) {
                types.add(IacucProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
            }
            if (displayResponseToPrevIACUCNotification(currentStatus)) {
                types.add(IacucProtocolSubmissionType.RESPONSE_TO_PREV_IACUC_NOTIFICATION);
            }
        }
        return types;
    }
    
    private boolean displayInitialSubmission(String currentStatus) {
        String validStatuses[] = { 
                                   IacucProtocolStatus.IN_PROGRESS, 
                                   IacucProtocolStatus.WITHDRAWN, 
                                   IacucProtocolStatus.SUBMITTED_TO_IACUC,
                                   IacucProtocolStatus.ADMINISTRATIVELY_WITHDRAWN,
                                   IacucProtocolStatus.ADMINISTRATIVELY_INCOMPLETE
                                  };
        return validateCurrentStatus(currentStatus, validStatuses);
    }
    
    private boolean displayResponseToPrevIACUCNotification(String currentStatus) {
        String validStatuses[] = { IacucProtocolStatus.MINOR_REVISIONS_REQUIRED,
                IacucProtocolStatus.MAJOR_REVISIONS_REQUIRED,
                IacucProtocolStatus.ACTIVE_ON_HOLD, IacucProtocolStatus.SUBMITTED_TO_IACUC, IacucProtocolStatus.RETURN_TO_PI };
        return validateCurrentStatus(currentStatus, validStatuses);
    }
    
    private boolean displayAmendment(String currentStatus, Protocol protocol) {
        String validStatuses[] = { IacucProtocolStatus.WITHDRAWN, IacucProtocolStatus.AMENDMENT_IN_PROGRESS, IacucProtocolStatus.SUBMITTED_TO_IACUC };
        return validateCurrentStatus(currentStatus, validStatuses)  && hasAmmendmentProtocolNumber(protocol.getProtocolNumber());
    }
    
    private boolean displayContinuation(String currentStatus, Protocol protocol) {
        String validStatuses[] = { IacucProtocolStatus.WITHDRAWN, IacucProtocolStatus.RENEWAL_IN_PROGRESS, IacucProtocolStatus.CONTINUATION_IN_PROGRESS, IacucProtocolStatus.SUBMITTED_TO_IACUC };
        return validateCurrentStatus(currentStatus, validStatuses)  && (hasRenewalProtocolNumber(protocol.getProtocolNumber()) || 
                hasContinuationProtocolNumber(protocol.getProtocolNumber()));
    }
    
    private boolean displayContinuationWithAmendment(String currentStatus, Protocol protocol) {
        String validStatuses[] = { IacucProtocolStatus.WITHDRAWN, IacucProtocolStatus.RENEWAL_IN_PROGRESS, IacucProtocolStatus.CONTINUATION_IN_PROGRESS, IacucProtocolStatus.SUBMITTED_TO_IACUC };
        return validateCurrentStatus(currentStatus, validStatuses)  && (hasRenewalProtocolNumber(protocol.getProtocolNumber()) || 
                hasContinuationProtocolNumber(protocol.getProtocolNumber()));
    }
    
    private boolean hasAmmendmentProtocolNumber(String protocolNumber) {
        return protocolNumber.contains("A");
    }
    
    private boolean hasRenewalProtocolNumber(String protocolNumber) {
        return protocolNumber.contains("R");
    }

    private boolean hasContinuationProtocolNumber(String protocolNumber) {
        return protocolNumber.contains("C");
    }
    
    private boolean displayResubmission(String currentStatus) {
        String validStatuses[] = {IacucProtocolStatus.WITHDRAWN, IacucProtocolStatus.SUBMITTED_TO_IACUC};
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
