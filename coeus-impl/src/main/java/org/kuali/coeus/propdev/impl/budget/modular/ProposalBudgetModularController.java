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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalBudgetModularController extends ProposalBudgetControllerBase  {

    @Transactional @RequestMapping(value = "/proposalBudget", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropBudget-ModularPage"})
    public ModelAndView navigateToModular(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()){
            if (budgetPeriod.getBudgetModular() == null)
                getBudgetModularService().generateModularPeriod(budgetPeriod);
        }
        form.setBudgetModularSummary(getBudgetModularService().generateModularSummary(budget));
        return super.navigate(form);
    }

    @Transactional @RequestMapping(value = "/proposalBudget", params={"methodToCall=synchModular"})
    public ModelAndView synchModular (@ModelAttribute("KualiForm") ProposalBudgetForm form)
            throws Exception{
        Budget budget = form.getBudget();
        getBudgetModularService().synchModularBudget(budget);
        getBudgetModularService().generateModularSummary(budget);
        form.setBudgetModularSummary(getBudgetModularService().generateModularSummary(budget));
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalBudget", params={"methodToCall=recalculateModular"})
    public ModelAndView recalculateModular (@ModelAttribute("KualiForm") ProposalBudgetForm form)
            throws Exception{
        Budget budget = form.getBudget();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()){
                getBudgetModularService().generateModularPeriod(budgetPeriod);
        }
        getBudgetModularService().generateModularSummary(budget);
        form.setBudgetModularSummary(getBudgetModularService().generateModularSummary(budget));
        return getRefreshControllerService().refresh(form);
    }
}
