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
package org.kuali.kra.irb.actions.modifysubmission;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;


/**
 * 
 * This class maintains the rules for modifying a protocol submission.
 */
public class ProtocolModifySubmissionRule extends ResearchDocumentRuleBase implements ExecuteProtocolModifySubmissionRule {
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.modifysubmission.ExecuteProtocolModifySubmissionRule#processModifySubmissionRule(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionAction)
     */
    public boolean processModifySubmissionRule(ProtocolDocument document, ProtocolModifySubmissionAction actionBean) {
        boolean valid = true;
        if (actionBean.getProtocolReviewTypeCode() == null || "".equals(actionBean.getProtocolReviewTypeCode())) {
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".protocolReviewTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED);
            valid = false;
        } else {
            if (ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE.equals(actionBean.getProtocolReviewTypeCode()) 
                    && !verifyExemptChecklist(actionBean)) {
                GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".exemptStudiesCheckList[0].checked", 
                        KeyConstants.ERROR_PROTOCOL_SUBMISSION_EXEMPT_CHECKBOX_REQ);
                valid = false;
            } else if (ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE.equals(actionBean.getProtocolReviewTypeCode()) 
                    && !verifyExpediteChecklist(actionBean)) {
                GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".expeditedReviewCheckList[0].checked", 
                        KeyConstants.ERROR_PROTOCOL_SUBMISSION_EXPEDITED_CHECKBOX_REQ);
                valid = false;
            }
        }
        if (actionBean.getSubmissionQualifierTypeCode() == null || "".equals(actionBean.getSubmissionQualifierTypeCode())) {
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".submissionQualifierTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_SUBMISSION_QUALIFIER_NOT_SELECTED);
            valid = false;
        }
        if (actionBean.getSubmissionTypeCode() == null  || "".equals(actionBean.getSubmissionTypeCode())) {
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".submissionTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED);
            valid = false;
        }
        return valid;
    }
    
    private boolean verifyExemptChecklist(ProtocolModifySubmissionAction actionBean) {
        for (ExemptStudiesCheckListItem item : actionBean.getExemptStudiesCheckList()) {
            if (item.getChecked()) {
                return true;
            }
        }
        return false;        
    }
    private boolean verifyExpediteChecklist(ProtocolModifySubmissionAction actionBean) {
        for (ExpeditedReviewCheckListItem item : actionBean.getExpeditedReviewCheckList()) {
            if (item.getChecked()) {
                return true;
            }
        }
        return false;        
    }

}
