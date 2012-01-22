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
 * This class is the maintenance document rule for valid submission/type qualifier table.
 */
public class ValidProtoSubTypeQualMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {

    private Long oldTypeId = null;
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        setOldTYpeId(document);
        ValidProtoSubTypeQual validProtoSubTypeQual = (ValidProtoSubTypeQual) document.getNoteTarget();
        return validate(validProtoSubTypeQual);
    }

    private void setOldTYpeId(MaintenanceDocument document) {
        if (document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION)) {
            oldTypeId = ((ValidProtoSubTypeQual)document.getOldMaintainableObject().getDataObject()).getValidProtoSubTypeQualId();
         }        
    }
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        setOldTYpeId(document);
        ValidProtoSubTypeQual validProtoSubTypeQual = (ValidProtoSubTypeQual) document.getNoteTarget();
        return validate(validProtoSubTypeQual);
    }

    private boolean validate(ValidProtoSubTypeQual validProtoSubTypeQual) {
        boolean valid = validateSubmissionType(validProtoSubTypeQual);
        valid &= validateTypeQualifier(validProtoSubTypeQual) && checkSubmTypeQualifierExists(validProtoSubTypeQual);
        return valid;
    }

    private boolean validateSubmissionType(ValidProtoSubTypeQual validProtoSubTypeQual) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubTypeQual.getSubmissionTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", validProtoSubTypeQual.getSubmissionTypeCode());
            List<ProtocolSubmissionType> submissionTypes = (List<ProtocolSubmissionType>) boService.findMatching(
                    ProtocolSubmissionType.class, fieldValues);
            if (submissionTypes.isEmpty()) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.submissionTypeCode",
                        KeyConstants.ERROR_SUBMISSION_TYPE_NOT_EXISTS,
                        new String[] { validProtoSubTypeQual.getSubmissionTypeCode() });
                valid = false;
            }
        }
        return valid;
    }

    private boolean validateTypeQualifier(ValidProtoSubTypeQual validProtoSubTypeQual) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubTypeQual.getSubmissionTypeQualCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionQualifierTypeCode", validProtoSubTypeQual.getSubmissionTypeQualCode());
            List<ProtocolSubmissionQualifierType> typeQualifiers = (List<ProtocolSubmissionQualifierType>) boService.findMatching(ProtocolSubmissionQualifierType.class,
                    fieldValues);
            if (typeQualifiers.isEmpty()) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.submissionTypeQualCode",
                        KeyConstants.ERROR_TYPE_QUALIFIER_NOT_EXISTS,
                        new String[] { validProtoSubTypeQual.getSubmissionTypeQualCode() });
                valid = false;
            }
        }
        return valid;
    }

    private boolean checkSubmTypeQualifierExists(ValidProtoSubTypeQual validProtoSubTypeQual) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubTypeQual.getSubmissionTypeCode())
                && StringUtils.isNotBlank(validProtoSubTypeQual.getSubmissionTypeQualCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", validProtoSubTypeQual.getSubmissionTypeCode());
            fieldValues.put("submissionTypeQualCode", validProtoSubTypeQual.getSubmissionTypeQualCode());
            List<ValidProtoSubTypeQual> validProtoSubTypeQuals = (List<ValidProtoSubTypeQual>) boService.findMatching(
                    ValidProtoSubTypeQual.class, fieldValues);
            if (!validProtoSubTypeQuals.isEmpty()) {
                ValidProtoSubTypeQual existvalidProtoSubTypeQual = validProtoSubTypeQuals.get(0);
                if ((oldTypeId == null || !existvalidProtoSubTypeQual.getValidProtoSubTypeQualId().equals(oldTypeId)) 
                        &&  validProtoSubTypeQual.getSubmissionTypeCode().equals(existvalidProtoSubTypeQual.getSubmissionTypeCode())
                        &&  validProtoSubTypeQual.getSubmissionTypeQualCode().equals(existvalidProtoSubTypeQual.getSubmissionTypeQualCode())) {
                    GlobalVariables.getMessageMap().putError(
                            "document.newMaintainableObject.submissionTypeCode",
                            KeyConstants.ERROR_SUBMISSION_TYPE_QUALIFIER_EXISTS,
                            new String[] { validProtoSubTypeQual.getSubmissionType().getDescription(),
                                    validProtoSubTypeQual.getSubmissionTypeQualifier().getDescription() });
                    valid = false;
                }
            }
        }
        return valid;

    }
}

