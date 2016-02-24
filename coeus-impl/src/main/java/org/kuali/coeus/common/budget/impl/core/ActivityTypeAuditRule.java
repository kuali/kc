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
package org.kuali.coeus.common.budget.impl.core;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleBase;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * This class to check whether activity type has been changed for PD or Award, and budget is not sync'ed.  
 */
@KcBusinessRule("activityTypeAuditRule")
public class ActivityTypeAuditRule extends BudgetAuditRuleBase {

	@KcEventMethod
    public boolean processRunAuditBusinessRules(BudgetAuditEvent event) {
        boolean valid = true;
        
        Budget budget = event.getBudget();

        List<AuditError> auditErrors = new ArrayList<AuditError>();
        if (isActivityTypeChanged(budget)) {
            if (budget.getBudgetParent().getDocument() instanceof AwardDocument) {
                auditErrors.add(new AuditError(Constants.ACTIVITY_TYPE_KEY.replace("developmentProposalList", "awardList"),
                    KeyConstants.WARNING_ACTIVITY_TYPE_CHANGED, Constants.MAPPING_AWARD_HOME_PAGE + "." + "detailsDate"));
            } else {
                auditErrors.add(new AuditError(Constants.ACTIVITY_TYPE_KEY, KeyConstants.WARNING_ACTIVITY_TYPE_CHANGED,
                    Constants.PROPOSAL_PAGE + "." + Constants.REQUIRED_FIELDS_PANEL_ANCHOR));
            }
            getGlobalVariableService().getAuditErrorMap().put("activityTypeAuditWarnings",
                    new AuditCluster("Activity type changed", auditErrors, Constants.AUDIT_WARNINGS));
            valid = false;
        }
        return valid;
    }

	@KcEventMethod
    public boolean processRunAuditBusinessRules(BudgetAuditRuleEvent event) {
        Budget budget = event.getBudget();
        if (isActivityTypeChanged(budget)) {
            BudgetConstants.BudgetAuditRules budgetActivityRule = BudgetConstants.BudgetAuditRules.ACTIVITY_TYPE;
			List<AuditError> auditErrors = getAuditErrors(budgetActivityRule, false);
            auditErrors.add(new AuditError(Constants.ACTIVITY_TYPE_KEY, KeyConstants.WARNING_ACTIVITY_TYPE_CHANGED,
            		budgetActivityRule.getPageId()));
            return false;
        }
        return true;
    }
    
    private boolean isActivityTypeChanged(Budget budget) {
        BudgetParentDocument parentDocument = budget.getBudgetParent().getDocument();
        boolean syncRate = false;
        Collection<BudgetRate> allBudgetRates = getBudgetRatesService().getSavedBudgetRates(budget);
        syncRate = getBudgetRatesService().checkActivityTypeChange(allBudgetRates,
                                    parentDocument.getBudgetParent().getActivityTypeCode());
        return syncRate;
    }

    private BudgetRatesService getBudgetRatesService() {
        return KcServiceLocator.getService(BudgetRatesService.class);
    }


}
