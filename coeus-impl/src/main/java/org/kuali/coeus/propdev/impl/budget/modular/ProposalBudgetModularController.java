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


import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalBudgetModularController extends ProposalBudgetControllerBase  {

    @Autowired
    @Qualifier("budgetModularService")
    private BudgetModularService budgetModularService;

    @RequestMapping(value = "/proposalBudget", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropBudget-ModularPage"})
    public ModelAndView navigateToModular(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()){
            if (budgetPeriod.getBudgetModular() == null)
                budgetModularService.generateModularPeriod(budgetPeriod);
        }
        form.setBudgetModularSummary(budgetModularService.generateModularSummary(budget));
        return super.navigate(form);
    }

    @MethodAccessible
    @RequestMapping(value = "/proposalBudget", params={"methodToCall=synchModular"})
    public ModelAndView synchModular (@ModelAttribute("KualiForm") ProposalBudgetForm form)
            throws Exception{
        Budget budget = form.getBudget();
        budgetModularService.synchModularBudget(budget);
        budgetModularService.generateModularSummary(budget);
        return getRefreshControllerService().refresh(form);
    }

    @MethodAccessible
    @RequestMapping(value = "/proposalBudget", params={"methodToCall=recalculateModular"})
    public ModelAndView recalculateModular (@ModelAttribute("KualiForm") ProposalBudgetForm form)
            throws Exception{
        Budget budget = form.getBudget();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()){
                budgetModularService.generateModularPeriod(budgetPeriod);
        }
        budgetModularService.generateModularSummary(budget);
        return getRefreshControllerService().refresh(form);
    }
    public BudgetModularService getBudgetModularService() {
        return budgetModularService;
    }

    public void setBudgetModularService(BudgetModularService budgetModularService) {
        this.budgetModularService = budgetModularService;
    }

}
