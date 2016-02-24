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
package org.kuali.coeus.common.impl.compliance.core;

import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.common.framework.compliance.core.ValidSpecialReviewApproval;
import org.kuali.rice.kns.document.MaintenanceDocument;

import java.util.HashMap;
import java.util.Map;

/**
 * This rule validates the Approval Type Code and Special Review Code fields on a Valid Special Review Approval document.
 */
public class ValidSpecialReviewApprovalMaintenanceRule extends KcMaintenanceDocumentRuleBase {
    
    private static final String SPECIAL_REVIEW_TYPE_CODE = "specialReviewTypeCode";
    private static final String SPECIAL_REVIEW_TYPE_TITLE = "Special Review Type Code";
    
    private static final String APPROVAL_TYPE_CODE = "approvalTypeCode";
    private static final String APPROVAL_TYPE_TITLE = "Approval Type Code";

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        ValidSpecialReviewApproval specialReviewApproval = (ValidSpecialReviewApproval) document.getDocumentBusinessObject();
        return validate(specialReviewApproval);
    }
    
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        ValidSpecialReviewApproval specialReviewApproval = (ValidSpecialReviewApproval) document.getDocumentBusinessObject();
        return validate(specialReviewApproval);
    }

    private boolean validate(ValidSpecialReviewApproval specialReviewApproval) {
        boolean valid = true;

        Map<String, String> specialReviewTypePk = new HashMap<String, String>();
        specialReviewTypePk.put(SPECIAL_REVIEW_TYPE_CODE, specialReviewApproval.getSpecialReviewTypeCode());
        valid &= checkExistenceFromTable(SpecialReviewType.class, specialReviewTypePk, SPECIAL_REVIEW_TYPE_CODE, SPECIAL_REVIEW_TYPE_TITLE);
        
        Map<String, String> approvalTypePk = new HashMap<String, String>();
        approvalTypePk.put(APPROVAL_TYPE_CODE, specialReviewApproval.getApprovalTypeCode());
        valid &= checkExistenceFromTable(SpecialReviewApprovalType.class, approvalTypePk, APPROVAL_TYPE_CODE, APPROVAL_TYPE_TITLE);
        
        return valid;
    }
}
