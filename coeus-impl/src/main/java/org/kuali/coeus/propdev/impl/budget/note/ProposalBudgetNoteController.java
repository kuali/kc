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


