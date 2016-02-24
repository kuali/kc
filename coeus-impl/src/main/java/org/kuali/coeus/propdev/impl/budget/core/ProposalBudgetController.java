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

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.lock.ProposalBudgetLockService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="/proposalDevelopment")
public class ProposalBudgetController extends ProposalDevelopmentControllerBase {

	@Autowired
	@Qualifier("proposalBudgetSharedControllerService")
	private ProposalBudgetSharedControllerService proposalBudgetSharedController;

    @RequestMapping(params="methodToCall=addBudget")
    public ModelAndView addBudget(@RequestParam("addBudgetDto.budgetName") String budgetName, 
    		@RequestParam("addBudgetDto.summaryBudget") Boolean summaryBudget,
            @RequestParam(value="addBudgetDto.modularBudget",defaultValue="false") Boolean modularBudget,
    		@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		super.save(form);
		return getProposalBudgetSharedController().addBudget(budgetName, summaryBudget, modularBudget, form.getDevelopmentProposal(), form);
    }

	@MethodAccessible
    @Transactional @RequestMapping(params="methodToCall=copyBudget")
	public ModelAndView copyBudget(@RequestParam("copyBudgetDto.budgetName") String budgetName, 
			@RequestParam("copyBudgetDto.originalBudgetId") Long originalBudgetId, 
			@RequestParam("copyBudgetDto.allPeriods") Boolean allPeriods,
			@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		super.save(form);
		return getProposalBudgetSharedController().copyBudget(budgetName, originalBudgetId, allPeriods, form.getDevelopmentProposal(), form);
	}

	@Transactional @RequestMapping(params="methodToCall=populateBudgetSummary")
    public ModelAndView populateBudgetSummary(@RequestParam("budgetId") Long budgetId,
                                              @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        super.save(form);
        return getProposalBudgetSharedController().populateBudgetSummary(budgetId, form.getDevelopmentProposal().getBudgets(), form);
    }

    @Transactional @RequestMapping(params="methodToCall=markBudgetVersionComplete")
    public ModelAndView markBudgetVersionComplete(@RequestParam("budgetId") Long budgetId, @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        ProposalDevelopmentBudgetExt budget = getProposalBudgetSharedController().getSelectedBudget(budgetId,form.getDevelopmentProposal().getBudgets());
        form.getDocument().refreshPessimisticLocks();
        if (getProposalBudgetSharedController().isBudgetLocked(budget.getBudgetVersionNumber(),form.getDocument().getPessimisticLocks(),form.getPageId()) ||
                !getProposalBudgetSharedController().isAllowedToCompleteBudget(budget,form.getPageId())) {
            form.setAjaxReturnType("update-page");
            return getModelAndViewService().getModelAndView(form);
        }
        getProposalBudgetSharedController().markBudgetVersionStatus(budget,Constants.BUDGET_STATUS_COMPLETE_CODE);
        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(params="methodToCall=markBudgetVersionIncomplete")
    public ModelAndView markBudgetIncomplete(@RequestParam("budgetId") Long budgetId, @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        ProposalDevelopmentBudgetExt budget = getProposalBudgetSharedController().getSelectedBudget(budgetId,form.getDevelopmentProposal().getBudgets());
        form.getDocument().refreshPessimisticLocks();
        if (getProposalBudgetSharedController().isBudgetLocked(budget.getBudgetVersionNumber(),form.getDocument().getPessimisticLocks(),form.getPageId())) {
            form.setAjaxReturnType("update-page");
            return getModelAndViewService().getModelAndView(form);
        }
        getProposalBudgetSharedController().markBudgetVersionStatus(budget, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(params="methodToCall=markForSubmission")
	public ModelAndView markForSubmission(@RequestParam("budgetId") Long budgetId, @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		ProposalDevelopmentBudgetExt finalBudget = getProposalBudgetSharedController().getSelectedBudget(budgetId,form.getDevelopmentProposal().getBudgets());
		form.getProposalDevelopmentDocument().getDevelopmentProposal().setFinalBudget(finalBudget);
		return super.save(form);
	}

    @Transactional @RequestMapping(params="methodToCall=removeFromSubmission")
    public ModelAndView removeFromSubmission(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        form.getProposalDevelopmentDocument().getDevelopmentProposal().setFinalBudget(null);
        return super.save(form);
    }
	
	@Transactional @RequestMapping(params="methodToCall=openBudget")
	public ModelAndView openBudget(@RequestParam("budgetId") String budgetId, @RequestParam("viewOnly") boolean viewOnly, @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		if(!viewOnly) {
			super.save(form);
		}
		return getProposalBudgetSharedController().openBudget(budgetId, viewOnly, form);
	}		

	@Transactional @RequestMapping(params="methodToCall=populatePrintForms")
    public ModelAndView populatePrintForms(@RequestParam("budgetId") Long budgetId,
			@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)
			throws Exception {
		super.save(form);
		return getProposalBudgetSharedController().populatePrintForms(budgetId, form.getDevelopmentProposal().getBudgets(), form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=printBudgetForms")
	public ModelAndView printBudgetForms(
			@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
			HttpServletResponse response) throws Exception {
		return getProposalBudgetSharedController().printBudgetForms(form.getSelectedBudget(), form, response);
	}

	public ProposalBudgetSharedControllerService getProposalBudgetSharedController() {
		return proposalBudgetSharedController;
	}

	public void setProposalBudgetSharedController(
			ProposalBudgetSharedControllerService proposalBudgetSharedController) {
		this.proposalBudgetSharedController = proposalBudgetSharedController;
	}

}
