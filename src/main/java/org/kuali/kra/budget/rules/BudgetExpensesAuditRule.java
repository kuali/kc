/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.rules;

import static org.kuali.core.util.GlobalVariables.getAuditErrorMap;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class BudgetExpensesAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetExpensesAuditRule.class);
    

    /**
     * 
     * This method is to validate budget expense business rules
     * 
     */
    public boolean processRunAuditBusinessRules(Document document) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
        boolean retval = true;
        Date projectStartDate = budgetDocument.getProposal().getRequestedStartDateInitial();
        Date projectEndDate = budgetDocument.getProposal().getRequestedEndDateInitial();
        int i = 0;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        BudgetForm budgetForm = (BudgetForm)GlobalVariables.getKualiForm();
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod().intValue();
        if(budgetDocument.getBudgetPeriod(sltdBudgetPeriod).getTotalCostLimit().isGreaterThan(new BudgetDecimal(0)) && budgetDocument.getBudgetPeriod(sltdBudgetPeriod).getTotalCost().isGreaterThan(budgetDocument.getBudgetPeriod(sltdBudgetPeriod).getTotalCostLimit())){            
            auditErrors.add(new AuditError("document.budgetPeriod[" + sltdBudgetPeriod + "].totalCostLimit", KeyConstants.WARNING_PERIOD_COST_LIMIT_EXCEEDED, Constants.BUDGET_EXPENSES_PAGE + "." + Constants.BUDGET_EXPENSES_OVERVIEW_PANEL_ANCHOR));
            retval=false;
        }
        
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put("budgetExpensesAuditWarnings", new AuditCluster(Constants.BUDGET_EXPENSES_OVERVIEW_PANEL_NAME + sltdBudgetPeriod + ")", auditErrors, Constants.AUDIT_WARNINGS));
        }
        
        
        return retval;

    }
}


