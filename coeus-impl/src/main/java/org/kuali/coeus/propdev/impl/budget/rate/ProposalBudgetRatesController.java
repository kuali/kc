package org.kuali.coeus.propdev.impl.budget.rate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonSalaryDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelBudgetService;
import org.kuali.coeus.common.budget.framework.personnel.TbnPerson;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.RateClassType;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.kra.infrastructure.PersonTypeConstants;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.uif.UifParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetRatesController extends ProposalBudgetControllerBase {

	@Autowired
	@Qualifier("budgetRatesService")
	private BudgetRatesService budgetRatesService;
	
	@RequestMapping(params="methodToCall=syncAllRates")
	public ModelAndView syncAllRates(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        budget.setRateClassTypesReloaded(true);
        getBudgetRatesService().syncAllBudgetRates(budget);
        
        budget.setRateSynced(true);
        BudgetParentDocument parentDocument = budget.getBudgetParent().getDocument();
        if (!budget.getActivityTypeCode().equals(parentDocument.getBudgetParent().getActivityTypeCode())) {
            budget.setActivityTypeCode(parentDocument.getBudgetParent().getActivityTypeCode());
        }
       return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=refreshAllRates")
	public ModelAndView refreshAllRates(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        getBudgetRatesService().resetAllBudgetRates(budget);
        return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=syncRates")
	public ModelAndView syncRates(@RequestParam("rateClassTypeCode") String rateClassTypeCode, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        getBudgetRatesService().syncBudgetRatesForRateClassType(rateClassTypeCode, budget);
        budget.setRateClassTypesReloaded(false);
        if (rateClassTypeCode.equals("O")) {
            budget.setRateSynced(true);
        }
        return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=resetRates")
	public ModelAndView resetRates(@RequestParam("rateClassTypeCode") String rateClassTypeCode, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        Budget budget = form.getBudget();
        getBudgetRatesService().resetBudgetRatesForRateClassType(rateClassTypeCode, budget);
        return getModelAndViewService().getModelAndView(form);
	}	

	public BudgetRatesService getBudgetRatesService() {
		return budgetRatesService;
	}

	public void setBudgetRatesService(BudgetRatesService budgetRatesService) {
		this.budgetRatesService = budgetRatesService;
	}
}
