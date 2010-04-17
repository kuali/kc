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
package org.kuali.kra.rules;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.bo.SpecialReview;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.rice.kns.document.MaintenanceDocument;

/**
 * This rule validates the Approval Type Code and Special Review Code fields on a Valid Special Review Approval document.
 */
public class ValidSpecialReviewApprovalMaintenanceRule extends KraMaintenanceDocumentRuleBase {

    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        ValidSpecialReviewApproval specialReviewApproval = (ValidSpecialReviewApproval)document.getDocumentBusinessObject();
        return validate(specialReviewApproval);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        ValidSpecialReviewApproval specialReviewApproval = (ValidSpecialReviewApproval)document.getDocumentBusinessObject();
        return validate(specialReviewApproval);
    }

    private boolean validate(ValidSpecialReviewApproval specialReviewApproval) {
        boolean valid = true;

        Map<String, String> approvalTypePk = new HashMap<String, String>();
        approvalTypePk.put("approvalTypeCode", specialReviewApproval.getApprovalTypeCode());
        valid &= checkExistenceFromTable(SpecialReviewApprovalType.class, approvalTypePk, "approvalTypeCode", "Approval Type Code");
        
        Map<String, String> specialReviewPk = new HashMap<String, String>();
        specialReviewPk.put("specialReviewCode", specialReviewApproval.getSpecialReviewCode());
        valid &= checkExistenceFromTable(SpecialReview.class, specialReviewPk, "specialReviewCode", "Special Review Code");
        
        return valid;
    }
}
