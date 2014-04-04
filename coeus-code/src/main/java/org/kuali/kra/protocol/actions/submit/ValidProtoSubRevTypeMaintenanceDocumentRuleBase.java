/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is the maintenance document rule for valid submission/review type table.
 */
public abstract class ValidProtoSubRevTypeMaintenanceDocumentRuleBase extends KcMaintenanceDocumentRuleBase {
    private Long oldTypeId = null;

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        setOldTYpeId(document);
        ValidProtoSubRevType validProtoSubRevType = (ValidProtoSubRevType) document.getDocumentBusinessObject();
        return validate(validProtoSubRevType);
    }

    private void setOldTYpeId(MaintenanceDocument document) {
        if (document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION) ||
            document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_DELETE_ACTION) ) {
            oldTypeId = ((ValidProtoSubRevType)document.getOldMaintainableObject().getDataObject()).getValidProtoSubRevTypeId();
         }        
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        setOldTYpeId(document);
        ValidProtoSubRevType validProtoSubRevType = (ValidProtoSubRevType) document.getDocumentBusinessObject();
        return validate(validProtoSubRevType);
    }

    private boolean validate(ValidProtoSubRevType validProtoSubRevType) {
        boolean valid = validateSubmissionType(validProtoSubRevType);
        valid &= validateReviewType(validProtoSubRevType) && checkSubmReviewTypeExists(validProtoSubRevType);
        return valid;
    }

    private boolean validateSubmissionType(ValidProtoSubRevType validProtoSubRevType) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubRevType.getSubmissionTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", validProtoSubRevType.getSubmissionTypeCode());            
            List<ProtocolSubmissionTypeBase> submissionTypes = (List<ProtocolSubmissionTypeBase>) boService.findMatching(getProtocolSubmissionTypeBOClassHook(), fieldValues);
            if (submissionTypes.isEmpty()) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.submissionTypeCode",
                        KeyConstants.ERROR_SUBMISSION_TYPE_NOT_EXISTS,
                        new String[] { validProtoSubRevType.getSubmissionTypeCode() });
                valid = false;
            }
        }
        return valid;
    }
    
    protected abstract Class<? extends ProtocolSubmissionTypeBase> getProtocolSubmissionTypeBOClassHook();
    
    

    private boolean validateReviewType(ValidProtoSubRevType validProtoSubRevType) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubRevType.getProtocolReviewTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("reviewTypeCode", validProtoSubRevType.getProtocolReviewTypeCode());
            List<ProtocolReviewTypeBase> reviewTypes = (List<ProtocolReviewTypeBase>) boService.findMatching(getProtocolReviewTypeBOClassHook(), fieldValues);
            if (reviewTypes.isEmpty()) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.protocolReviewTypeCode",
                        KeyConstants.ERROR_REVIEW_TYPE_NOT_EXISTS,
                        new String[] { validProtoSubRevType.getProtocolReviewTypeCode() });
                valid = false;
            }
        }
        return valid;
    }
    
    protected abstract Class<? extends ProtocolReviewTypeBase> getProtocolReviewTypeBOClassHook();
    
    protected abstract Class<? extends ValidProtoSubRevType> getValidProtoSubRevTypeBOClassHook();
    

    private boolean checkSubmReviewTypeExists(ValidProtoSubRevType validProtoSubRevType) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubRevType.getSubmissionTypeCode())
                && StringUtils.isNotBlank(validProtoSubRevType.getProtocolReviewTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", validProtoSubRevType.getSubmissionTypeCode());
            fieldValues.put("protocolReviewTypeCode", validProtoSubRevType.getProtocolReviewTypeCode());
            List<ValidProtoSubRevType> validProtoSubRevTypes = (List<ValidProtoSubRevType>) boService.findMatching(getValidProtoSubRevTypeBOClassHook(), fieldValues);
            if (!validProtoSubRevTypes.isEmpty()) {
                ValidProtoSubRevType existvalidProtoSubRevType = validProtoSubRevTypes.get(0);
                if ((oldTypeId == null || !existvalidProtoSubRevType.getValidProtoSubRevTypeId().equals(oldTypeId)) 
                        && validProtoSubRevType.getSubmissionTypeCode().equals(existvalidProtoSubRevType.getSubmissionTypeCode())
                        && validProtoSubRevType.getProtocolReviewTypeCode().equals(existvalidProtoSubRevType.getProtocolReviewTypeCode())) {
                    GlobalVariables.getMessageMap().putError(
                            "document.newMaintainableObject.submissionTypeCode",
                            KeyConstants.ERROR_SUBMISSION_REVIEW_TYPE_EXISTS,
                            new String[] { validProtoSubRevType.getSubmissionType().getDescription(),
                                    validProtoSubRevType.getProtocolReviewType().getDescription() });
                    valid = false;
                }
            }
        }
        return valid;

    }
}
