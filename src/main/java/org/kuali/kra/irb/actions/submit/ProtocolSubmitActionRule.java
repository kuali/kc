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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolSubmitActionRule extends ResearchDocumentRuleBase implements ExecuteProtocolSubmitActionRule {

    public boolean processSubmitAction(ProtocolDocument document, ProtocolSubmitActionBean submitAction) {
        boolean isValid = true;
        
        String submissionTypeCode = submitAction.getSubmissionTypeCode();
        if (StringUtils.isBlank(submissionTypeCode)) {
            // If the user didn't select a submission type, i.e. he/she choose the "select:" option,
            // then the Submission Type Code will be "blank".
            isValid = false;
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".submissionTypeCode", 
                                                   KeyConstants.ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED);
        }
        else if (isSubmissionTypeInvalid(submissionTypeCode)) {
            isValid = false;
            this.reportError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".submissionTypeCode", 
                             KeyConstants.ERROR_PROTOCOL_SUBMISSION_TYPE_INVALID, new String[] { submissionTypeCode });
        }
        
        String protocolReviewTypeCode = submitAction.getProtocolReviewTypeCode();
        if (StringUtils.isBlank(protocolReviewTypeCode)) {
            // If the user didn't select a review type, i.e. he/she choose the "select:" option,
            // then the Protocol Review Type Code will be "blank".
            isValid = false;
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".protocolReviewTypeCode", 
                                                   KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED);
        }
        else if (isReviewTypeInvalid(protocolReviewTypeCode)) {
            isValid = false;
            this.reportError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".protocolReviewTypeCode", 
                             KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_INVALID, new String[] { protocolReviewTypeCode });
        }
        
        List<ProtocolReviewerBean> reviewers = submitAction.getReviewers();
        for (int i=0; i<reviewers.size(); i++) {
            ProtocolReviewerBean reviewer = reviewers.get(i);
            if (!isReviewerValid(reviewer, i)) {
                isValid = false;
            }
        }
        
        return isValid;
    }

    /**
     * This method tests if the fields for a given reviewer have legal values.
     * @param reviewer
     * @param reviewerIndex - the index of the reviewer in the list of reviewers that was sent to the client
     */
    private boolean isReviewerValid(ProtocolReviewerBean reviewer, int reviewerIndex) {
        boolean isValid = true;
        String reviewerTypeCode = reviewer.getReviewerTypeCode();
        boolean isChecked = reviewer.getChecked();
        
        // test if type code is valid
        if (!StringUtils.isBlank(reviewerTypeCode) && isReviewerTypeInvalid(reviewerTypeCode)) {
            isValid = false;
            this.reportError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[" + reviewerIndex + "].reviewerTypeCode", KeyConstants.ERROR_PROTOCOL_REVIEWER_TYPE_INVALID, reviewer.getFullName());
        }
        
        // if reviewer checked and type code empty, report an error
        if (isChecked && StringUtils.isBlank(reviewerTypeCode)) {
            isValid = false;
            this.reportError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[" + reviewerIndex + "].reviewerTypeCode", KeyConstants.ERROR_PROTOCOL_REVIEWER_NO_TYPE_BUT_REVIEWER_CHECKED, reviewer.getFullName());
        }
        
        // if reviewer unchecked and type code not empty, report an error
        if (!isChecked && !StringUtils.isBlank(reviewerTypeCode)) {
            isValid = false;
            this.reportError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[" + reviewerIndex + "].reviewerTypeCode", KeyConstants.ERROR_PROTOCOL_REVIEWER_NOT_CHECKED_BUT_TYPE_SELECTED, reviewer.getFullName());
        }
        
        return isValid;
    }
    
    private boolean isSubmissionTypeInvalid(String submissionTypeCode) {
        return !existsUnique(ProtocolSubmissionType.class, "submissionTypeCode", submissionTypeCode);
    }
    
    private boolean isReviewTypeInvalid(String reviewTypeCode) {
        return !existsUnique(ProtocolReviewType.class, "reviewTypeCode", reviewTypeCode);
    }
    
    private boolean isReviewerTypeInvalid(String reviewerTypeCode) {
        return !existsUnique(ProtocolReviewerType.class, "reviewerTypeCode", reviewerTypeCode);
    }
    
    /**
     * Returns true if exactly one instance of a given business object type
     * exists in the Database; false otherwise.
     * @param boType
     * @param propertyName the name of the BO field to query
     * @param keyField the field to test against.
     * @return true if one object exists; false if no objects or more than one are found
     */
    private boolean existsUnique(Class<? extends BusinessObject> boType, String propertyName, String keyField) {
        if (keyField != null) {
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,String> fieldValues = new HashMap<String,String>();
            fieldValues.put(propertyName, keyField);
            if (businessObjectService.countMatching(boType, fieldValues) == 1) {
                return true;
            }
        }
        return false;
    }
}
