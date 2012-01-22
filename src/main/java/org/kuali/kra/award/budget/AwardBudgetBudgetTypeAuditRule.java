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
package org.kuali.kra.award.budget;

import static org.kuali.rice.kns.util.KNSGlobalVariables.getAuditErrorMap;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class AwardBudgetBudgetTypeAuditRule implements DocumentAuditRule {
    public static final String AWARD_BUDGET_TYPE_ERROR_KEY = "awardBudgetTypeAuditErrors";

    public boolean processRunAuditBusinessRules(Document document) {
        AwardBudgetExt budget = (AwardBudgetExt)((BudgetDocument)document).getBudget();

        boolean valid = true;
        String[] params = { "Budget Overview Comments" };
        if ("2".equals(budget.getAwardBudgetTypeCode()) && StringUtils.isBlank(budget.getComments())) {
            getAuditErrors().add(new AuditError("document.budget.comments",
                    KeyConstants.AUDIT_ERROR_COMMENTS_REQUIRED_FOR_REBUDGET,
                    Constants.BUDGET_PERIOD_PAGE + "." + "topOfForm",
                    params));
            valid = false;
        }

        return valid;
    }
    
    /**
     * This method is a convenience method for obtaining audit errors.
     * @return List of AuditError instances
     */    
    private List<AuditError> getAuditErrors() {
        return getAuditProblems(Constants.AUDIT_ERRORS);
    }
    
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually 
     * add a <code>{@link List<AuditError>}</code> to the auditErrorMap.
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditProblems(String problemType) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!getAuditErrorMap().containsKey(AWARD_BUDGET_TYPE_ERROR_KEY)) {
            getAuditErrorMap().put(AWARD_BUDGET_TYPE_ERROR_KEY, new AuditCluster(Constants.BUDGET_OVERVIEW_PANEL_NAME, auditErrors, problemType));
        }
        else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get(AWARD_BUDGET_TYPE_ERROR_KEY)).getAuditErrorList();
        }
        
        return auditErrors;
    }


}
