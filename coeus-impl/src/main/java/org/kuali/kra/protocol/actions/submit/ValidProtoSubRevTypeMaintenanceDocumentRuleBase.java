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
