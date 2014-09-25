/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.budget.modular;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalAuditEvent;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This validates the Budget Modular's Total Direct Cost.
 *
 * <p>
 * The validation methods in this class may produce errors and/or warnings.
 * </p>
 *
 * <p>
 * The error handling behavior in this class is important to mention
 * since it is a little different than the rest of KC.
 *
 * This class adds error messages directly to the
 * {@link GlobalVariables#getErrorMap() GlobalVariables.getMessageMap()}
 * Make sure to add to the error map's path before calling the validate method.
 *
 * Currently warning are generated and placed in a {@code Set<String>}.
 * This is different because warnings are not supported by the rice framework
 * and therefore behave differently from errors.
 *
 * See {@link #getErrorMessages() getErrorMessages()}
 * </p>
 */
@KcBusinessRule("budgetModularTotalDirectCostRule")
public class BudgetModularTotalDirectCostRule {
	
	@Autowired
	@Qualifier("parameterService")
    private ParameterService paramService;

    /**
     * Validates the total direct cost (tdc) for each budget version.
     *
     * <p>
     * This method will validate tdc for every <b>completed</b> budget version
     * that has a <b>modular</b> budget.
     * </p>
     *
     * <p>
     * The tdc rule that this method is checking is whether or not the tdc
     * contains a positive value.  If none of the budget versions meeting
     * the aforementioned criteria is positive than an error is produced.
     * If at least one budget version meeting the aforementioned criteria
     * is positive and at least one is not positive than a warning is produced.
     * </p>
     *
     * @param parentDocument the document to check rule against
     * @param reportErrors whether to report errors
     * @param warningMessages container to place warning messages.  Warning messages
     * are added to this set to be accessed by the caller.
     *
     * @throws NullPointerException if the pdDocument or warningMessages are null.
     */
	@KcEventMethod
    public boolean validateTotalDirectCost(ProposalAuditEvent event) {
        boolean passed = true;

        final List<ProposalDevelopmentBudgetExt> budgets = 
        		event.getProposalDevelopmentDocument().getDevelopmentProposal().getBudgets();

        for (int i = 0; i < budgets.size(); i++) {
            final ProposalDevelopmentBudgetExt budget = budgets.get(i);

            if (budget.isBudgetComplete()) {
                passed &= this.checkTotalDirectCost(budget, i);
            }
        }
        return passed;
    }

    /**
     * Checks the tdc on a {@link AwardBudgetDocument BudgetDocument}
     * following the business rules described at
     * {@link #validateTotalDirectCost(ProposalDevelopmentDocument, boolean, Set) validateTotalDirectCost()}
     *
     * @param budgetDocument the current budget document
     * @param currentIndex the current index corresponding to the document.
     * @param reportErrors whether to report errors
     * @param warningMessages container to place warning messages.
     * @return true if no errors false if errors
     */
    private boolean checkTotalDirectCost(final Budget budget,
        final int currentIndex) {

        assert budget != null : "the budget overview was null";
        assert currentIndex >= 0 : "the current index was not valid, index: " + currentIndex;

        if (Boolean.FALSE.equals(budget.getModularBudgetFlag())) {
            return true;
        }

        final Collection<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();

        if (budgetPeriods != null) {

            int positiveCount = -1;

            for (final BudgetPeriod budgetPeriod : budgetPeriods) {
                if (budgetPeriod != null) {
                    final BudgetModular budgetModular = budgetPeriod.getBudgetModular();
                    positiveCount = (positiveCount != -1) ? positiveCount : 0;
                    
                    if (budgetModular != null) {
                        final ScaleTwoDecimal tdc = budgetModular.getTotalDirectCost();
                        if (tdc.isPositive()) {
                            positiveCount++;
                        } else {
                        	GlobalVariables.getMessageMap().putWarning("budgetVersionOverview[" + currentIndex + "].budgetStatus", 
                        			KeyConstants.WARNING_BUDGET_VERSION_MODULAR_INVALID_TDC);
                        }
                    } else {
                    	GlobalVariables.getMessageMap().putWarning("budgetVersionOverview[" + currentIndex + "].budgetStatus", 
                    			KeyConstants.WARNING_BUDGET_VERSION_MODULAR_INVALID_TDC);
                    }
                }
            }
            if (positiveCount == 0) {
                GlobalVariables.getMessageMap().putError("budgetVersionOverview[" + currentIndex + "].budgetStatus",
                    KeyConstants.ERROR_BUDGET_STATUS_COMPLETE_WHEN_NOT_MODULER);
                return false;
            }
        }
        return true;
    }
    
    protected String getBudgetStatusCompleteCode() {
	    return paramService.getParameterValueAsString(
	            Budget.class,
	            Constants.BUDGET_STATUS_COMPLETE_CODE);
    }

	protected ParameterService getParamService() {
		return paramService;
	}

	public void setParamService(ParameterService paramService) {
		this.paramService = paramService;
	}
}
