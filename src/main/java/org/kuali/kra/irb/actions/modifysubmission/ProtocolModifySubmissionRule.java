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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ValidProtoSubRevType;
import org.kuali.kra.irb.actions.submit.ValidProtoSubTypeQual;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;


/**
 * 
 * This class maintains the rules for modifying a protocol submission.
 */
public class ProtocolModifySubmissionRule extends ResearchDocumentRuleBase implements ExecuteProtocolModifySubmissionRule {
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.modifysubmission.ExecuteProtocolModifySubmissionRule#processModifySubmissionRule(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionBean)
     */
    public boolean processModifySubmissionRule(ProtocolDocument document, ProtocolModifySubmissionBean actionBean) {
        boolean valid = true;
        String errorParameters = null;
        if (StringUtils.isBlank(actionBean.getProtocolReviewTypeCode())) {
            GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".protocolReviewTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED, errorParameters);
            valid = false;
        } else {
            if (ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE.equals(actionBean.getProtocolReviewTypeCode()) 
                    && !verifyExemptChecklist(actionBean)) {
                GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".exemptStudiesCheckList[0].checked", 
                        KeyConstants.ERROR_PROTOCOL_SUBMISSION_EXEMPT_CHECKBOX_REQ, errorParameters);
                valid = false;
            } else if (ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE.equals(actionBean.getProtocolReviewTypeCode()) 
                    && !verifyExpediteChecklist(actionBean)) {
                GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".expeditedReviewCheckList[0].checked", 
                        KeyConstants.ERROR_PROTOCOL_SUBMISSION_EXPEDITED_CHECKBOX_REQ, errorParameters);
                valid = false;
            }
        }
        if (StringUtils.isBlank(actionBean.getSubmissionTypeCode())) {
            GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".submissionTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED, errorParameters);
            valid = false;
        }
        if (StringUtils.isNotBlank(actionBean.getSubmissionTypeCode())) {
            valid &= isValidSubmTypeQual(actionBean);
            if (StringUtils.isNotBlank(actionBean.getProtocolReviewTypeCode())) {
                valid &= isValidSubmReviewType(actionBean);
            }
        }
        return valid;
    }
    
    private boolean verifyExemptChecklist(ProtocolModifySubmissionBean actionBean) {
        for (ExemptStudiesCheckListItem item : actionBean.getExemptStudiesCheckList()) {
            if (item.getChecked()) {
                return true;
            }
        }
        return false;        
    }
    private boolean verifyExpediteChecklist(ProtocolModifySubmissionBean actionBean) {
        for (ExpeditedReviewCheckListItem item : actionBean.getExpeditedReviewCheckList()) {
            if (item.getChecked()) {
                return true;
            }
        }
        return false;        
    }
    
    private boolean isValidSubmReviewType(ProtocolModifySubmissionBean submitAction) {
        boolean valid = true;
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())
                && StringUtils.isNotBlank(submitAction.getProtocolReviewTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", submitAction.getSubmissionTypeCode());
            List<ValidProtoSubRevType> validProtoSubRevTypes = (List<ValidProtoSubRevType>) getBusinessObjectService()
                    .findMatching(ValidProtoSubRevType.class, fieldValues);
            if (!validProtoSubRevTypes.isEmpty()) {
                List<String> reviewTypes = new ArrayList<String>();
                for (ValidProtoSubRevType validProtoSubRevType : validProtoSubRevTypes) {
                    reviewTypes.add(validProtoSubRevType.getProtocolReviewTypeCode());
                }
                if (!reviewTypes.contains(submitAction.getProtocolReviewTypeCode())) {
                    GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".protocolReviewTypeCode",
                            KeyConstants.INVALID_SUBMISSION_REVIEW_TYPE,
                            new String[] { ((ProtocolSubmissionType)getBo(ProtocolSubmissionType.class, "submissionTypeCode", submitAction.getSubmissionTypeCode())).getDescription(), 
                            ((ProtocolReviewType)getBo(ProtocolReviewType.class, "reviewTypeCode", submitAction.getProtocolReviewTypeCode())).getDescription() });
                    valid = false;
                }

            }
        }
        return valid;
    }
    
    private boolean isValidSubmTypeQual(ProtocolModifySubmissionBean submitAction) {
        boolean valid = true;
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", submitAction.getSubmissionTypeCode());
            List<ValidProtoSubTypeQual> validProtoSubTypeQuals = (List<ValidProtoSubTypeQual>) getBusinessObjectService()
                    .findMatching(ValidProtoSubTypeQual.class, fieldValues);
            if (!validProtoSubTypeQuals.isEmpty()) {
                List<String> typeQuals = new ArrayList<String>();
                for (ValidProtoSubTypeQual validProtoSubTypeQual : validProtoSubTypeQuals) {
                    typeQuals.add(validProtoSubTypeQual.getSubmissionTypeQualCode());
                }
                if (StringUtils.isBlank(submitAction.getSubmissionQualifierTypeCode()) || !typeQuals.contains(submitAction.getSubmissionQualifierTypeCode())) {
                    String desc = "";
                    ProtocolSubmissionQualifierType typeQual = (ProtocolSubmissionQualifierType)getBo(ProtocolSubmissionQualifierType.class, "submissionQualifierTypeCode", submitAction.getSubmissionQualifierTypeCode());
                    if (typeQual != null) {
                        desc = typeQual.getDescription();
                    }
                    GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_MODIFY_SUBMISSION_KEY + ".submissionQualifierTypeCode",
                            KeyConstants.INVALID_SUBMISSION_TYPE_QUALIFIER,
                            new String[] { ((ProtocolSubmissionType)getBo(ProtocolSubmissionType.class, "submissionTypeCode", submitAction.getSubmissionTypeCode())).getDescription(), 
                            desc});
                    valid = false;
                }

            }
        }
        return valid;
    }

    @SuppressWarnings("unchecked")
    private BusinessObject getBo(Class<? extends BusinessObject> boType, String propertyName, String keyField) {
        Map<String,String> fieldValues = new HashMap<String,String>();
        fieldValues.put(propertyName, keyField);
        List<BusinessObject> results = (List<BusinessObject>) getBusinessObjectService().findMatching(boType, fieldValues);
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

}
