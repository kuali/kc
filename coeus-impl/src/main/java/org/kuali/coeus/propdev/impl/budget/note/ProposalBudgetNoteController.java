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

package org.kuali.coeus.propdev.impl.budget.note;

import org.kuali.coeus.common.budget.framework.core.BudgetException;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationService;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProposalBudgetNoteController extends ProposalBudgetControllerBase {

    @Autowired
    @Qualifier("budgetJustificationService")
    private BudgetJustificationService budgetJustificationService;

    public BudgetJustificationService getBudgetJustificationService() {
        return budgetJustificationService;
    }

    public void setBudgetJustificationService(BudgetJustificationService budgetJustificationService) {
        this.budgetJustificationService = budgetJustificationService;
    }

    @MethodAccessible
    @Transactional @RequestMapping(value = "/proposalBudget", params={"methodToCall=consolidateExpenseJustifications"})
    public ModelAndView consolidateExpenseJustifications(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        try {
            getBudgetJustificationService().consolidateExpenseJustifications(form.getBudget(), form.getBudgetJustificationWrapper());
        } catch (BudgetException exc) {
            GlobalVariables.getMessageMap().putError("budgetJustificationWrapper.justificationText", "error.custom", "There are no line item budget justifications");
        }
        return getRefreshControllerService().refresh(form);
    }

}


