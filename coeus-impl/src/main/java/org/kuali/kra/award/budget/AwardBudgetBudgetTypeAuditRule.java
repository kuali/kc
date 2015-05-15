/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.budget;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.rice.krad.util.GlobalVariables.getAuditErrorMap;

public class AwardBudgetBudgetTypeAuditRule implements DocumentAuditRule {
    public static final String AWARD_BUDGET_TYPE_ERROR_KEY = "awardBudgetTypeAuditErrors";

    public boolean processRunAuditBusinessRules(Document document) {
        AwardBudgetExt budget = (AwardBudgetExt)((AwardBudgetDocument)document).getBudget();

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
     * add a <code>{@link List&lt;AuditError&gt;}</code> to the auditErrorMap.
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
