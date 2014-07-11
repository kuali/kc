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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
    public ModelAndView addBudget(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
        ProposalDevelopmentBudgetExt budget = (ProposalDevelopmentBudgetExt) getBudgetService().addBudgetVersion(propDevForm.getProposalDevelopmentDocument(), propDevForm.getAddBudgetDTO().getBudgetName());
        if (budget != null) {
	        Properties props = new Properties();
	        props.put("methodToCall", "start");
	        props.put("budgetId", budget.getBudgetId().toString());
	        return getTransactionalDocumentControllerService().performRedirect(propDevForm, "proposalBudget", props);
        } else {
        	return getTransactionalDocumentControllerService().refresh(propDevForm, result, request, response);
        }
    }    

	public BudgetService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetService budgetService) {
		this.budgetService = budgetService;
	}
   
}
