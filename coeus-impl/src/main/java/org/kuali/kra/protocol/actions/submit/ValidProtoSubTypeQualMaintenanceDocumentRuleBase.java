/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 * This class is the maintenance document rule for valid submission/type qualifier table.
 */
public abstract class ValidProtoSubTypeQualMaintenanceDocumentRuleBase extends KcMaintenanceDocumentRuleBase {

    private Long oldTypeId = null;
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        setOldTYpeId(document);
        ValidProtoSubTypeQual validProtoSubTypeQual = (ValidProtoSubTypeQual) document.getDocumentBusinessObject();
        return validate(validProtoSubTypeQual);
    }

    private void setOldTYpeId(MaintenanceDocument document) {
        if (document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION) || 
            document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_DELETE_ACTION)    ) {
            oldTypeId = ((ValidProtoSubTypeQual)document.getOldMaintainableObject().getDataObject()).getValidProtoSubTypeQualId();
         }        
    }
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        setOldTYpeId(document);
        ValidProtoSubTypeQual validProtoSubTypeQual = (ValidProtoSubTypeQual) document.getDocumentBusinessObject();
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
            List<ProtocolSubmissionTypeBase> submissionTypes = (List<ProtocolSubmissionTypeBase>) boService.findMatching(getProtocolSubmissionTypeBOClassHook(), fieldValues);
            if (submissionTypes.isEmpty()) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.submissionTypeCode",
                        KeyConstants.ERROR_SUBMISSION_TYPE_NOT_EXISTS,
                        new String[] { validProtoSubTypeQual.getSubmissionTypeCode() });
                valid = false;
            }
        }
        return valid;
    }
    
    protected abstract Class<? extends ProtocolSubmissionTypeBase> getProtocolSubmissionTypeBOClassHook();

    

    private boolean validateTypeQualifier(ValidProtoSubTypeQual validProtoSubTypeQual) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubTypeQual.getSubmissionTypeQualCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionQualifierTypeCode", validProtoSubTypeQual.getSubmissionTypeQualCode());
            List<ProtocolSubmissionQualifierTypeBase> typeQualifiers = (List<ProtocolSubmissionQualifierTypeBase>) boService.findMatching(getProtocolSubmissionQualifierTypeBOClassHook(), fieldValues);
            if (typeQualifiers.isEmpty()) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.submissionTypeQualCode",
                        KeyConstants.ERROR_TYPE_QUALIFIER_NOT_EXISTS,
                        new String[] { validProtoSubTypeQual.getSubmissionTypeQualCode() });
                valid = false;
            }
        }
        return valid;
    }

    protected abstract Class<? extends ProtocolSubmissionQualifierTypeBase> getProtocolSubmissionQualifierTypeBOClassHook();
    
    protected abstract Class<? extends ValidProtoSubTypeQual> getValidProtoSubTypeQualBOClassHook();
    

    private boolean checkSubmTypeQualifierExists(ValidProtoSubTypeQual validProtoSubTypeQual) {
        boolean valid = true;
        if (StringUtils.isNotBlank(validProtoSubTypeQual.getSubmissionTypeCode())
                && StringUtils.isNotBlank(validProtoSubTypeQual.getSubmissionTypeQualCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", validProtoSubTypeQual.getSubmissionTypeCode());
            fieldValues.put("submissionTypeQualCode", validProtoSubTypeQual.getSubmissionTypeQualCode());
            List<ValidProtoSubTypeQual> validProtoSubTypeQuals = (List<ValidProtoSubTypeQual>) boService.findMatching(getValidProtoSubTypeQualBOClassHook(), fieldValues);
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

