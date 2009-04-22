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
package org.kuali.kra.irb.rules;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolReviewType;
import org.kuali.kra.irb.bo.ProtocolSubmissionType;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.ExecuteProtocolActionRule;
import org.kuali.kra.irb.web.struts.bean.ProtocolSubmitAction;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProtocolActionRule extends ResearchDocumentRuleBase implements ExecuteProtocolActionRule {

    public boolean processSubmitAction(ProtocolDocument document, ProtocolSubmitAction submitAction) {
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
        
        return isValid;
    }

    private boolean isSubmissionTypeInvalid(String submissionTypeCode) {
        return !existsUnique(ProtocolSubmissionType.class, "submissionTypeCode", submissionTypeCode);
    }
    
    private boolean isReviewTypeInvalid(String reviewTypeCode) {
        return !existsUnique(ProtocolReviewType.class, "reviewTypeCode", reviewTypeCode);
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
