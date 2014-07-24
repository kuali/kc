package org.kuali.coeus.propdev.impl.budget.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.framework.controller.UifControllerService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public abstract class ProposalBudgetControllerBase {

	@Autowired
	@Qualifier("uifControllerService")
	private UifControllerService uifControllerService;
	
	@Autowired
	@Qualifier("proposalBudgetService")
	private BudgetService<DevelopmentProposal> budgetService;
	
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
	@Autowired
	@Qualifier("budgetSummaryService")
    private BudgetSummaryService budgetSummaryService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;

    protected UifFormBase createInitialForm(HttpServletRequest request) {
        return new ProposalBudgetForm();
    }
    
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UifFormBase form =  getUifControllerService().initForm(this.createInitialForm(request), request, response);
        return form;
    }	
    
    public ModelAndView save(ProposalBudgetForm form) throws Exception {
    	getDataObjectService().save(form.getBudget());
    	return getUifControllerService().getUIFModelAndView(form);
    }
    
    protected ModelAndView navigate(ProposalBudgetForm form) throws Exception {
		form.setPageId(form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID));
		form.setDirtyForm(false);
		return save(form);
    }

	public BudgetService<DevelopmentProposal> getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetService<DevelopmentProposal> budgetService) {
		this.budgetService = budgetService;
	}

	public UifControllerService getUifControllerService() {
		return uifControllerService;
	}

	public void setUifControllerService(UifControllerService uifControllerService) {
		this.uifControllerService = uifControllerService;
	}

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
	
	public ConfigurationService getKualiConfigurationService() {
		return kualiConfigurationService;
	}

	public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
		this.kualiConfigurationService = kualiConfigurationService;
	}
	
	public BudgetSummaryService getBudgetSummaryService() {
		return budgetSummaryService;
	}

	public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
		this.budgetSummaryService = budgetSummaryService;
	}

}
