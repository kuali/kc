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

import org.kuali.coeus.common.budget.framework.core.BudgetService;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping(value="/proposalDevelopment")
public class ProposalBudgetController extends ProposalDevelopmentControllerBase {
   
	@Autowired
	@Qualifier("proposalBudgetService")
    private BudgetService budgetService;
	
	@MethodAccessible
    @RequestMapping(params="methodToCall=addBudget")
    public ModelAndView addBudget(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modularBudgetFlag", form.getAddBudgetDto().getModularBudget() != null ? form.getAddBudgetDto().getModularBudget() : Boolean.FALSE);
		options.put("summaryBudget", form.getAddBudgetDto().getSummaryBudget() != null ? form.getAddBudgetDto().getSummaryBudget() : Boolean.FALSE);
        ProposalDevelopmentBudgetExt budget = (ProposalDevelopmentBudgetExt) getBudgetService().addBudgetVersion(form.getProposalDevelopmentDocument(), form.getAddBudgetDto().getBudgetName(), options);
        if (budget != null) {
	        Properties props = new Properties();
	        props.put("methodToCall", "initiate");
	        props.put("budgetId", budget.getBudgetId().toString());
	        return getModelAndViewService().performRedirect(form, "proposalBudget", props);
        } else {
        	return this.getModelAndViewService().getModelAndView(form);
        }
    }

	@MethodAccessible
    @RequestMapping(params="methodToCall=copyBudget")
	public ModelAndView copyBudget(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
		ProposalDevelopmentBudgetExt originalBudget = getDataObjectService().findUnique(ProposalDevelopmentBudgetExt.class, QueryByCriteria.Builder.forAttribute("budgetId", form.getCopyBudgetDto().getOriginalBudgetId()).build());
		ProposalDevelopmentBudgetExt budget = (ProposalDevelopmentBudgetExt) getBudgetService().copyBudgetVersion(originalBudget, false);
        if (budget != null) {
        	budget.setName(form.getCopyBudgetDto().getBudgetName());
        	budget = getDataObjectService().save(budget);
	        Properties props = new Properties();
	        props.put("methodToCall", "initiate");
	        props.put("budgetId", budget.getBudgetId().toString());
	        return getModelAndViewService().performRedirect(form, "proposalBudget", props);
        } else {
        	return this.getModelAndViewService().getModelAndView(form);
        }		
	}

	public BudgetService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetService budgetService) {
		this.budgetService = budgetService;
	}
}
