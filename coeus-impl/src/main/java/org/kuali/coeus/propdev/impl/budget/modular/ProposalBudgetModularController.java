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


import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetSaveEvent;
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
        form.setBudgetModularSummary(getBudgetModularService().processModularSummary(budget,true));
        return super.navigate(form);
    }

    @Transactional @RequestMapping(value = "/proposalBudget", params={"methodToCall=synchModular"})
    public ModelAndView synchModular (@ModelAttribute("KualiForm") ProposalBudgetForm form)
            throws Exception{
        Budget budget = form.getBudget();
        getBudgetModularService().synchModularBudget(budget);
        form.setBudgetModularSummary(getBudgetModularService().processModularSummary(budget,true));
        super.saveBudget(form);
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalBudget", params={"methodToCall=recalculateModular"})
    public ModelAndView recalculateModular (@ModelAttribute("KualiForm") ProposalBudgetForm form)
            throws Exception{
        Budget budget = form.getBudget();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()){
                getBudgetModularService().generateModularPeriod(budgetPeriod);
        }
        form.setBudgetModularSummary(getBudgetModularService().processModularSummary(budget,false));
        return getRefreshControllerService().refresh(form);
    }
    
}
