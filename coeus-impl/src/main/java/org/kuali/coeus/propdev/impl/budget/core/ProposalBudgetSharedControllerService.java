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
package org.kuali.coeus.propdev.impl.budget.core;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.web.servlet.ModelAndView;

public interface ProposalBudgetSharedControllerService {
	public ModelAndView addBudget(String budgetName, Boolean summaryBudget, Boolean modularBudget, DevelopmentProposal developmentProposal, UifFormBase form) throws Exception;
	public ModelAndView copyBudget(String budgetName, Long originalBudgetId, Boolean allPeriods, DevelopmentProposal developmentProposal, UifFormBase form) throws Exception;
	public ModelAndView openBudget(String budgetId, boolean viewOnly, UifFormBase form) throws Exception;
    public boolean isAllowedToCompleteBudget(ProposalDevelopmentBudgetExt budget, String errorPath);
    public boolean isBudgetLocked(int budgetVersion, List<PessimisticLock> locks, String errorPath);
    public ProposalDevelopmentBudgetExt getSelectedBudget(Long budgetId, List<ProposalDevelopmentBudgetExt> budgets);
    public void markBudgetVersionStatus(ProposalDevelopmentBudgetExt budget, String status);
	public <T extends UifFormBase & SelectableBudget> ModelAndView populateBudgetSummary(Long budgetId, List<ProposalDevelopmentBudgetExt> budgets, T form) throws Exception;
	public <T extends UifFormBase & SelectableBudget> ModelAndView populatePrintForms(Long budgetId, List<ProposalDevelopmentBudgetExt> budgets, T form) throws Exception;
	public <T extends UifFormBase & SelectableBudget> ModelAndView printBudgetForms(ProposalDevelopmentBudgetExt selectedBudget, T form, HttpServletResponse response) throws Exception;
}
