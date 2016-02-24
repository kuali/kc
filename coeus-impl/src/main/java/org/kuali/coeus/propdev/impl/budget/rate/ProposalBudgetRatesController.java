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
package org.kuali.coeus.propdev.impl.budget.rate;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetRatesController extends ProposalBudgetControllerBase {

	@Autowired
	@Qualifier("budgetRatesService")
	private BudgetRatesService budgetRatesService;

    @Transactional @RequestMapping(params="methodToCall=syncAllRates")
	public ModelAndView syncAllRates(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        ProposalDevelopmentBudgetExt budget = form.getBudget();
        budget.setRateClassTypesReloaded(true);
        getBudgetRatesService().syncAllBudgetRates(budget);
        
        budget.setRateSynced(true);
        if (!budget.getActivityTypeCode().equals(budget.getDevelopmentProposal().getActivityTypeCode())) {
            budget.setActivityTypeCode(budget.getDevelopmentProposal().getActivityTypeCode());
        }
       return super.save(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=refreshAllRates")
	public ModelAndView refreshAllRates(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        getBudgetRatesService().resetAllBudgetRates(budget);
        return getModelAndViewService().getModelAndView(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=syncRates")
	public ModelAndView syncRates(@RequestParam("rateClassTypeCode") String rateClassTypeCode, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        getBudgetRatesService().syncBudgetRatesForRateClassType(rateClassTypeCode, budget);
        budget.setRateClassTypesReloaded(false);
        if (rateClassTypeCode.equals(org.kuali.coeus.common.budget.api.rate.RateClassType.OVERHEAD.getRateClassType())) {
            budget.setRateSynced(true);
        }
        return getModelAndViewService().getModelAndView(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=resetRates")
	public ModelAndView resetRates(@RequestParam("rateClassTypeCode") String rateClassTypeCode, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        getBudgetRatesService().resetBudgetRatesForRateClassType(rateClassTypeCode, budget);
        return getModelAndViewService().getModelAndView(form);
	}	

	public BudgetRatesService getBudgetRatesService() {
		return budgetRatesService;
	}

	public void setBudgetRatesService(BudgetRatesService budgetRatesService) {
		this.budgetRatesService = budgetRatesService;
	}
}
