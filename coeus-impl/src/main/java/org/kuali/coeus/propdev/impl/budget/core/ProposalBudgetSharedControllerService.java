package org.kuali.coeus.propdev.impl.budget.core;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.web.servlet.ModelAndView;

public interface ProposalBudgetSharedControllerService {
	public ModelAndView addBudget(String budgetName, Boolean summaryBudget, Boolean modularBudget, DevelopmentProposal developmentProposal, UifFormBase form) throws Exception;
	public ModelAndView copyBudget(String budgetName, Long originalBudgetId, Boolean allPeriods, DevelopmentProposal developmentProposal, UifFormBase form) throws Exception;
	public ModelAndView openBudget(String budgetId, UifFormBase form) throws Exception;
	public ModelAndView populateBudgetSummary(Long budgetId, List<ProposalDevelopmentBudgetExt> budgets, UifFormBase form) throws Exception;
	public ModelAndView populatePrintForms(Long budgetId, List<ProposalDevelopmentBudgetExt> budgets, UifFormBase form) throws Exception;
	public ModelAndView printBudgetForms(ProposalDevelopmentBudgetExt selectedBudget, UifFormBase form, HttpServletResponse response) throws Exception;
}
