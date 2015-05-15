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
package org.kuali.coeus.common.budget.framework.version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocumentRule;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

import static org.kuali.kra.infrastructure.KeyConstants.BUDGET_VERSION_EXISTS;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_BUDGET_NAME_MISSING;
import static org.springframework.util.StringUtils.hasText;

/**
 * A composited rule of the {@link BudgetDocumentRule}. It is expected that the {@link BudgetDocumentRule} will call this rule directly on save,
 * so it does not use or require an event.
 * 
 **/
public abstract class BudgetVersionRule {

    private static final Log LOG = LogFactory.getLog(BudgetVersionRule.class);

    /**
     * Entry method for the business rule
     *
     * @return true if it passed, false if it failed
     */
    protected boolean processAddBudgetVersionName(AddBudgetVersionEvent event) {
        boolean retval = true;

        if (!isNameValid(event.getVersionName())) {
            retval = false;
            GlobalVariables.getMessageMap().putError("document.parentDocument.budgetDocumentVersion", 
                    ERROR_BUDGET_NAME_MISSING, "Name");
        }
        
        if (containsVersionName(event.getBudgetParent().getBudgets(), event.getVersionName())) {
            retval = false;
            GlobalVariables.getMessageMap().putError("document.parentDocument.budgetDocumentVersion", BUDGET_VERSION_EXISTS);
        }
        return retval;
    }

    /**
     * Validates the name of the {@link Budget} to be added.
     * 
     * @param newBudgetVersionName name of the {@link Budget} to be added.
     * @return true if the name is valid, false otherwise
     */
    protected boolean isNameValid(String newBudgetVersionName) {
        return hasText(newBudgetVersionName);
    }


    /**
     * Determines if the given {@link List} of {@link Budget} instances contains the given {@link Budget}. It does this by getting
     * the name of the {@link Budget} and compares it to those in the {@link List}. We use this to prevent duplicate names among
     * {@link Budget} instances.
     * 
     * @param existingBudgets {@link List} of {@link Budget} instances
     * @param versionName is the name of the {@link Budget} to look for
     * @return true if it found <code>versionName</code> inside <code>existingBudgets</code>, false otherwise
     */
    protected boolean containsVersionName(List<? extends Budget> existingBudgets, String versionName) {
        for (Budget version : existingBudgets) {            
            LOG.info("Comparing " + version.getName() + " to " + versionName);
            if (version.getName().equals(versionName)) {
                return true;
            }
        }
        return false;
    }

    public boolean processAddBudgetVersion(AddBudgetVersionEvent event) throws WorkflowException {
    	BudgetParent budgetParent = event.getBudgetParent();
        boolean success = true;
        if(budgetParent.getRequestedStartDateInitial()==null){
            GlobalVariables.getMessageMap().putError(event.getErrorPath(), 
                    KeyConstants.ERROR_BUDGET_START_DATE_MISSING, "Name");
            success &= false;
        }
        if(budgetParent.getRequestedEndDateInitial()==null){
            GlobalVariables.getMessageMap().putError(event.getErrorPath(), 
                    KeyConstants.ERROR_BUDGET_END_DATE_MISSING, "Name");
            success &= false;
        }
        return success;
        
    }
}
