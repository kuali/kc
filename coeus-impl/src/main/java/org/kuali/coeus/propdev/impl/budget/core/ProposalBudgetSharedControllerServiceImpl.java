package org.kuali.coeus.propdev.impl.budget.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.ModelAndViewService;
import org.kuali.rice.krad.web.service.RefreshControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component("proposalBudgetSharedControllerService")
public class ProposalBudgetSharedControllerServiceImpl implements ProposalBudgetSharedControllerService {
	
	@Autowired
	@Qualifier("proposalBudgetService")
    private BudgetService budgetService;
    
    @Autowired
    @Qualifier("modelAndViewService")
    private ModelAndViewService modelAndViewService;
    
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    
    @Autowired
    @Qualifier("kcBusinessRulesEngine")
    private KcBusinessRulesEngine kcBusinessRulesEngine;
    
    @Autowired
    @Qualifier("refreshControllerService")
    private RefreshControllerService refreshControllerService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    public ModelAndView addBudget(String budgetName, Boolean summaryBudget, Boolean modularBudget, DevelopmentProposal developmentProposal, UifFormBase form) throws Exception {
		ProposalDevelopmentBudgetExt budget = null;
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modularBudgetFlag", modularBudget != null ? modularBudget : Boolean.FALSE);
		if (kcBusinessRulesEngine.applyRules(new ProposalAddBudgetVersionEvent("addBudgetDto", developmentProposal, budgetName))) {
			budget = (ProposalDevelopmentBudgetExt) getBudgetService().addBudgetVersion(developmentProposal.getDocument(), budgetName, options);	
		}
        if (budget != null) {
	        Properties props = new Properties();
	        props.put("methodToCall", "initiate");
	        props.put("budgetId", budget.getBudgetId().toString());
	        props.put("summaryBudget", summaryBudget.toString());
            props.put("auditActivated", String.valueOf(((Auditable)form).isAuditActivated()));
	        return getModelAndViewService().performRedirect(form, "proposalBudget", props);
        } else {
        	form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
        	form.setUpdateComponentId("PropDev-BudgetPage-NewBudgetDialog");
        	return this.getModelAndViewService().getModelAndView(form);
        }
        
    }

	public ModelAndView copyBudget(String budgetName, Long originalBudgetId, Boolean allPeriods, DevelopmentProposal developmentProposal, UifFormBase form) throws Exception {
		ProposalDevelopmentBudgetExt budget = null;
		if (kcBusinessRulesEngine.applyRules(new ProposalAddBudgetVersionEvent("copyBudgetDto", developmentProposal, budgetName))) {
			ProposalDevelopmentBudgetExt originalBudget = getDataObjectService().findUnique(ProposalDevelopmentBudgetExt.class, QueryByCriteria.Builder.forAttribute("budgetId", originalBudgetId).build());
			budget = (ProposalDevelopmentBudgetExt) getBudgetService().copyBudgetVersion(originalBudget, !allPeriods.booleanValue());
		}
        if (budget != null) {
        	budget.setName(budgetName);
        	budget = getDataObjectService().save(budget);
	        Properties props = new Properties();
	        props.put("methodToCall", "initiate");
	        props.put("budgetId", budget.getBudgetId().toString());
            props.put("auditActivated", String.valueOf(((Auditable)form).isAuditActivated()));
	        return getModelAndViewService().performRedirect(form, "proposalBudget", props);
        } else {
        	form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
        	form.setUpdateComponentId("PropDev-BudgetPage-CopyBudgetDialog");
        	return this.getModelAndViewService().getModelAndView(form);
        }		
	}

	public ModelAndView openBudget(String budgetId, UifFormBase form) throws Exception {
		if (getGlobalVariableService().getMessageMap().hasNoErrors()) {
			form.setDirtyForm(false);
	        Properties props = new Properties();
	        props.put("methodToCall", "start");
	        props.put("budgetId", budgetId);
            props.put("auditActivated", String.valueOf(((Auditable)form).isAuditActivated()));
	        return getModelAndViewService().performRedirect(form, "proposalBudget", props);
		} else {
        	form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATEPAGE.getKey());			
			return getRefreshControllerService().refresh(form);
		}
	}

	public BudgetService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetService budgetService) {
		this.budgetService = budgetService;
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

	public KcBusinessRulesEngine getKcBusinessRulesEngine() {
		return kcBusinessRulesEngine;
	}

	public void setKcBusinessRulesEngine(KcBusinessRulesEngine kcBusinessRulesEngine) {
		this.kcBusinessRulesEngine = kcBusinessRulesEngine;
	}

	public RefreshControllerService getRefreshControllerService() {
		return refreshControllerService;
	}

	public void setRefreshControllerService(
			RefreshControllerService refreshControllerService) {
		this.refreshControllerService = refreshControllerService;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}
}
