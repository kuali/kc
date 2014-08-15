package org.kuali.coeus.propdev.impl.budget.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionRule;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("proposalBudgetSharedController")
public class ProposalBudgetSharedController {
	
	@Autowired
	@Qualifier("proposalBudgetService")
    private BudgetService budgetService;
	
    @Autowired
    @Qualifier("proposalBudgetVersionRule")
    private AddBudgetVersionRule addBudgetVersionRule;
    
    @Autowired
    @Qualifier("modelAndViewService")
    private ModelAndViewService modelAndViewService;
    
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    public ModelAndView addBudget(String budgetName, Boolean summaryBudget, Boolean modularBudget, DevelopmentProposal developmentProposal, UifFormBase form) throws Exception {
		ProposalDevelopmentBudgetExt budget = null;
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modularBudgetFlag", modularBudget != null ? modularBudget : Boolean.FALSE);
		if (new AddBudgetVersionEvent("addBudgetDto", developmentProposal, budgetName).invokeRuleMethod(getAddBudgetVersionRule())) {
			budget = (ProposalDevelopmentBudgetExt) getBudgetService().addBudgetVersion(developmentProposal.getDocument(), budgetName, options);	
		}
        if (budget != null) {
	        Properties props = new Properties();
	        props.put("methodToCall", "initiate");
	        props.put("budgetId", budget.getBudgetId().toString());
	        props.put("summaryBudget", summaryBudget.toString());
	        return getModelAndViewService().performRedirect(form, "proposalBudget", props);
        } else {
        	form.setUpdateComponentId("PropDev-BudgetPage-NewBudgetDialog");
        	return this.getModelAndViewService().getModelAndView(form);
        }
        
    }

	public ModelAndView copyBudget(String budgetName, Long originalBudgetId, DevelopmentProposal developmentProposal, UifFormBase form) throws Exception {
		ProposalDevelopmentBudgetExt budget = null;
		if (new AddBudgetVersionEvent("addBudgetDto", developmentProposal, budgetName).invokeRuleMethod(getAddBudgetVersionRule())) {
			ProposalDevelopmentBudgetExt originalBudget = getDataObjectService().findUnique(ProposalDevelopmentBudgetExt.class, QueryByCriteria.Builder.forAttribute("budgetId", originalBudgetId).build());
			budget = (ProposalDevelopmentBudgetExt) getBudgetService().copyBudgetVersion(originalBudget, false);
		}
        if (budget != null) {
        	budget.setName(budgetName);
        	budget = getDataObjectService().save(budget);
	        Properties props = new Properties();
	        props.put("methodToCall", "initiate");
	        props.put("budgetId", budget.getBudgetId().toString());
	        return getModelAndViewService().performRedirect(form, "proposalBudget", props);
        } else {
        	form.setUpdateComponentId("PropDev-BudgetPage-CopyBudgetDialog");
        	return this.getModelAndViewService().getModelAndView(form);
        }		
	}

	public BudgetService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	public AddBudgetVersionRule getAddBudgetVersionRule() {
		return addBudgetVersionRule;
	}

	public void setAddBudgetVersionRule(AddBudgetVersionRule addBudgetVersionRule) {
		this.addBudgetVersionRule = addBudgetVersionRule;
	}

	public ModelAndViewService getModelAndViewService() {
		return modelAndViewService;
	}

	public void setModelAndViewService(ModelAndViewService modelAndViewService) {
		this.modelAndViewService = modelAndViewService;
	}

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
}
