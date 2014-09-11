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

package org.kuali.coeus.propdev.impl.budget.distribution;

import org.kuali.coeus.common.budget.framework.distribution.BudgetDistributionService;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProposalBudgetUnrecoveredFandAController extends ProposalBudgetControllerBase {

    @Autowired
    @Qualifier("budgetDistributionService")
    private BudgetDistributionService budgetDistributionService;

    @MethodAccessible
    @RequestMapping(value = "/proposalBudget", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropBudget-UnrecoveredFandAPage"})
    public ModelAndView navigate(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        getBudgetDistributionService().initializeUnrecoveredFandACollectionDefaults(form.getBudget());
        return super.navigate(form);
    }

    public BudgetDistributionService getBudgetDistributionService() {
        return budgetDistributionService;
    }

    public void setBudgetDistributionService(BudgetDistributionService budgetDistributionService) {
        this.budgetDistributionService = budgetDistributionService;
    }

}


