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
package org.kuali.kra.irb.actions.submit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * 
 * This class is the maintenance document rule for valid submission/review type table.
 */
public class ValidProtoSubRevTypeMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {
    private Long oldTypeId = null;

    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        setOldTYpeId(document);
        ValidProtoSubRevType validProtoSubRevType = (ValidProtoSubRevType) document.getNoteTarget();
        return validate(validProtoSubRevType);
    }

    private void setOldTYpeId(MaintenanceDocument document) {
        if (document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION)) {
            oldTypeId = ((ValidProtoSubRevType)document.getOldMaintainableObject().getDataObject()).getValidProtoSubRevTypeId();
         }        
    }

    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        setOldTYpeId(document);
        ValidProtoSubRevType validProtoSubRevType = (ValidProtoSubRevType) document.getNoteTarget();
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
            List<ProtocolSubmissionType> submissionTypes = (List<ProtocolSubmissionType>) boService.findMatching(
                    ProtocolSubmissionType.class, fieldValues);
            if (submissionTypes.isEmpty()) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.submissionTypeCode",
                        KeyConstants.ERROR_SUBMISSION_TYPE_NOT_EXISTS,
                        new String[] { validProtoSubRevType.getSubmissionTypeCode() });
                valid = false;
            }
        }
        return valid;
    }

    private boolean validateReviewType(ValidProtoSubRevType validProtoSubRevType) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubRevType.getProtocolReviewTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("reviewTypeCode", validProtoSubRevType.getProtocolReviewTypeCode());
            List<ProtocolReviewType> reviewTypes = (List<ProtocolReviewType>) boService.findMatching(ProtocolReviewType.class,
                    fieldValues);
            if (reviewTypes.isEmpty()) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.protocolReviewTypeCode",
                        KeyConstants.ERROR_REVIEW_TYPE_NOT_EXISTS,
                        new String[] { validProtoSubRevType.getProtocolReviewTypeCode() });
                valid = false;
            }
        }
        return valid;
    }

    private boolean checkSubmReviewTypeExists(ValidProtoSubRevType validProtoSubRevType) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubRevType.getSubmissionTypeCode())
                && StringUtils.isNotBlank(validProtoSubRevType.getProtocolReviewTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", validProtoSubRevType.getSubmissionTypeCode());
            fieldValues.put("protocolReviewTypeCode", validProtoSubRevType.getProtocolReviewTypeCode());
            List<ValidProtoSubRevType> validProtoSubRevTypes = (List<ValidProtoSubRevType>) boService.findMatching(
                    ValidProtoSubRevType.class, fieldValues);
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
