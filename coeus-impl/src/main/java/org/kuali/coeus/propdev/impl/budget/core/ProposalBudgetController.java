/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.budget.core;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionRule;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping(value="/proposalDevelopment")
public class ProposalBudgetController extends ProposalDevelopmentControllerBase {

	@Autowired
	@Qualifier("proposalBudgetSharedController")
	private ProposalBudgetSharedController proposalBudgetSharedController;
    
	@MethodAccessible
    @RequestMapping(params="methodToCall=addBudget")
    public ModelAndView addBudget(@RequestParam("addBudgetDto.budgetName") String budgetName, 
    		@RequestParam("addBudgetDto.summaryBudget") Boolean summaryBudget, 
    		@RequestParam("addBudgetDto.modularBudget") Boolean modularBudget, 
    		@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		return getProposalBudgetSharedController().addBudget(budgetName, summaryBudget, modularBudget, form.getDevelopmentProposal(), form);
    }

	@MethodAccessible
    @RequestMapping(params="methodToCall=copyBudget")
	public ModelAndView copyBudget(@RequestParam("copyBudgetDto.budgetName") String budgetName, 
			@RequestParam("copyBudgetDto.originalBudgetId") Long originalBudgetId, 
			@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		return getProposalBudgetSharedController().copyBudget(budgetName, originalBudgetId, form.getDevelopmentProposal(), form);
	}
	
	@RequestMapping(params="methodToCall=markForSubmission")
	public ModelAndView markForSubmission(@RequestParam("budgetId") Long budgetId, @ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
		ProposalDevelopmentBudgetExt finalBudget = null;
		for (ProposalDevelopmentBudgetExt curBudget : form.getProposalDevelopmentDocument().getDevelopmentProposal().getBudgets()) {
			if (ObjectUtils.equals(budgetId, curBudget.getBudgetId())) {
				finalBudget = curBudget;
			}
		}
		form.getProposalDevelopmentDocument().getDevelopmentProposal().setFinalBudget(finalBudget);
		return getModelAndViewService().getModelAndView(form);
	}

	public ProposalBudgetSharedController getProposalBudgetSharedController() {
		return proposalBudgetSharedController;
	}

	public void setProposalBudgetSharedController(
			ProposalBudgetSharedController proposalBudgetSharedController) {
		this.proposalBudgetSharedController = proposalBudgetSharedController;
	}
}
