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
package org.kuali.coeus.propdev.impl.budget.modular;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalAuditEvent;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
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
 * {@link GlobalVariables#getMessageMap() GlobalVariables.getMessageMap()}
 * Make sure to add to the error map's path before calling the validate method.
 *
 * Currently warning are generated and placed in a {@code Set<String>}.
 * This is different because warnings are not supported by the rice framework
 * and therefore behave differently from errors.
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
     * Checks the tdc on a {@link Budget Budget}
     * following the business rules described at
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
