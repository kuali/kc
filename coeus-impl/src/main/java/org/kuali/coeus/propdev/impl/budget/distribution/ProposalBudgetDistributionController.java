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

package org.kuali.coeus.propdev.impl.budget.distribution;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.distribution.BudgetDistributionService;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalBudgetDistributionController extends ProposalBudgetControllerBase {

    @Autowired
    @Qualifier("budgetDistributionService")
    private BudgetDistributionService budgetDistributionService;

    @RequestMapping(value = "/proposalBudget", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropBudget-UnrecoveredFandAPage"})
    public ModelAndView navigateToFandA(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        getBudgetDistributionService().initializeUnrecoveredFandACollectionDefaults(form.getBudget());
        return super.navigate(form);
    }

    @MethodAccessible
    @Transactional @RequestMapping(value = "/proposalBudget", params={"methodToCall=resetUnrecoveredFandA"})
    public ModelAndView resetUnrecoveredFandAToDefault (@ModelAttribute("KualiForm") ProposalBudgetForm form)
            throws Exception{
        Budget budget = form.getBudget();
        budget.getBudgetUnrecoveredFandAs().clear();
        budgetDistributionService.initializeUnrecoveredFandACollectionDefaults(budget);
        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalBudget", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropBudget-CostSharingPage"})
    public ModelAndView navigateToCostShare(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        getBudgetDistributionService().initializeCostSharingCollectionDefaults(form.getBudget());
        return super.navigate(form);
    }

    @MethodAccessible
    @Transactional @RequestMapping(value = "/proposalBudget", params={"methodToCall=resetCostSharing"})
    public ModelAndView resetCostSharingToDefault (@ModelAttribute("KualiForm") ProposalBudgetForm form)
            throws Exception{
        Budget budget = form.getBudget();
        budget.getBudgetCostShares().clear();
        budgetDistributionService.initializeCostSharingCollectionDefaults(budget);
        return getRefreshControllerService().refresh(form);
    }

    public BudgetDistributionService getBudgetDistributionService() {
        return budgetDistributionService;
    }

    public void setBudgetDistributionService(BudgetDistributionService budgetDistributionService) {
        this.budgetDistributionService = budgetDistributionService;
    }

}


