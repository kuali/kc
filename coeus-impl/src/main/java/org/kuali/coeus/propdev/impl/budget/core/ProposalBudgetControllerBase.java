package org.kuali.coeus.propdev.impl.budget.core;

import java.text.DecimalFormat;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationService;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.auth.ProposalBudgetAuthorizer;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.ScaleTwoDecimalEditor;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.TransactionalDocumentControllerService;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public abstract class ProposalBudgetControllerBase {

    @Autowired
    @Qualifier("uifExportControllerService")
    private UifExportControllerService uifExportControllerService;

    @Autowired
    @Qualifier("kcCommonControllerService")
    private KcCommonControllerService kcCommonControllerService;

    @Autowired
    @Qualifier("transactionalDocumentControllerService")
    private TransactionalDocumentControllerService transactionalDocumentControllerService;

    @Autowired
    @Qualifier("collectionControllerService")
    private CollectionControllerService collectionControllerService;

    @Autowired
    @Qualifier("fileControllerService")
    private FileControllerService fileControllerService;

    @Autowired
    @Qualifier("modelAndViewService")
    private ModelAndViewService modelAndViewService;

    @Autowired
    @Qualifier("navigationControllerService")
    private NavigationControllerService navigationControllerService;

    @Autowired
    @Qualifier("queryControllerService")
    private QueryControllerService queryControllerService;

    @Autowired
    @Qualifier("refreshControllerService")
    private RefreshControllerService refreshControllerService;
	
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
	@Autowired
	@Qualifier("proposalBudgetService")
	private ProposalBudgetService budgetService;

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;
    
    @Autowired
    @Qualifier("kcBusinessRulesEngine")
    private KcBusinessRulesEngine kcBusinessRulesEngine;

	@Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;

    @Autowired
    @Qualifier("budgetJustificationService")
    private BudgetJustificationService budgetJustificationService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
    
    @Autowired
    @Qualifier("proposalBudgetAuthorizer")
    private ProposalBudgetAuthorizer proposalBudgetAuthorizer;

    @Autowired
    @Qualifier("budgetModularService")
    private BudgetModularService budgetModularService;
    protected UifFormBase createInitialForm(HttpServletRequest request) {
        return new ProposalBudgetForm();
    }
    
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UifFormBase form =  getKcCommonControllerService().initForm(this.createInitialForm(request), request, response);
        return form;
    }

    protected ProposalDevelopmentBudgetExt loadBudget(Long budgetId) {
    	ProposalDevelopmentBudgetExt budget = getDataObjectService().findUnique(ProposalDevelopmentBudgetExt.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("budgetId", Long.valueOf(budgetId))).build());
    	if (!proposalBudgetAuthorizer.isAuthorizedToViewBudget(budget, globalVariableService.getUserSession().getPerson())) {
    		throw new AuthorizationException(globalVariableService.getUserSession().getPrincipalName(), "open", "Proposal Budget");
    	}
    	return budget;
    }

    public ModelAndView save(ProposalBudgetForm form) {
    	budgetService.calculateBudgetOnSave(form.getBudget());
    	form.setBudget(getDataObjectService().save(form.getBudget()));
        getBudgetJustificationService().preSave(form.getBudget(),form.getBudgetJustificationWrapper());
        form.setBudgetModularSummary(budgetModularService.generateModularSummary(form.getBudget()));
        return getModelAndViewService().getModelAndView(form);
    }
    
    public ModelAndView saveLine(ProposalBudgetForm form) {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableBudgetLineItems() != null && selectedCollectionPath !=null && form.getEditableBudgetLineItems().containsKey(selectedCollectionPath)){
            form.getEditableBudgetLineItems().get(selectedCollectionPath).remove(selectedLine);
        }
        return getCollectionControllerService().saveLine(form);
    }
    
    protected ModelAndView navigate(ProposalBudgetForm form) throws Exception {
		form.setPageId(form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID));
		form.setDirtyForm(false);
		if (form.getView().getCurrentPage().getReadOnly() != null && form.getView().getCurrentPage().getReadOnly()) {
			return getModelAndViewService().getModelAndView(form);
		} else {
			return save(form);
		}
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(ScaleTwoDecimal.class, new ScaleTwoDecimalEditor(new DecimalFormat("##0.00"), true));
    }

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

    public UifExportControllerService getUifExportControllerService() {
        return uifExportControllerService;
    }

    public void setUifExportControllerService(UifExportControllerService uifExportControllerService) {
        this.uifExportControllerService = uifExportControllerService;
    }

    public KcCommonControllerService getKcCommonControllerService() {
        return kcCommonControllerService;
    }

    public void setKcCommonControllerService(KcCommonControllerService kcCommonControllerService) {
        this.kcCommonControllerService = kcCommonControllerService;
    }

    public TransactionalDocumentControllerService getTransactionalDocumentControllerService() {
        return transactionalDocumentControllerService;
    }

    public void setTransactionalDocumentControllerService(TransactionalDocumentControllerService transactionalDocumentControllerService) {
        this.transactionalDocumentControllerService = transactionalDocumentControllerService;
    }

    public CollectionControllerService getCollectionControllerService() {
        return collectionControllerService;
    }

    public void setCollectionControllerService(CollectionControllerService collectionControllerService) {
        this.collectionControllerService = collectionControllerService;
    }

    public FileControllerService getFileControllerService() {
        return fileControllerService;
    }

    public void setFileControllerService(FileControllerService fileControllerService) {
        this.fileControllerService = fileControllerService;
    }

    public ModelAndViewService getModelAndViewService() {
        return modelAndViewService;
    }

    public void setModelAndViewService(ModelAndViewService modelAndViewService) {
        this.modelAndViewService = modelAndViewService;
    }

    public NavigationControllerService getNavigationControllerService() {
        return navigationControllerService;
    }
    public void setNavigationControllerService(NavigationControllerService navigationControllerService) {
        this.navigationControllerService = navigationControllerService;
    }

    public QueryControllerService getQueryControllerService() {
        return queryControllerService;
    }

    public void setQueryControllerService(QueryControllerService queryControllerService) {
        this.queryControllerService = queryControllerService;
    }

    public RefreshControllerService getRefreshControllerService() {
        return refreshControllerService;
    }

    public void setRefreshControllerService(RefreshControllerService refreshControllerService) {
        this.refreshControllerService = refreshControllerService;
    }

	public ProposalBudgetService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(ProposalBudgetService budgetService) {
		this.budgetService = budgetService;
	}

    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

    public ConfigurationService getKualiConfigurationService() {
		return kualiConfigurationService;
	}

	public void setKualiConfigurationService(
			ConfigurationService kualiConfigurationService) {
		this.kualiConfigurationService = kualiConfigurationService;
	}

	public KcBusinessRulesEngine getKcBusinessRulesEngine() {
		return kcBusinessRulesEngine;
	}

	public void setKcBusinessRulesEngine(KcBusinessRulesEngine kcBusinessRulesEngine) {
		this.kcBusinessRulesEngine = kcBusinessRulesEngine;
	}

    public BudgetJustificationService getBudgetJustificationService() {
        return budgetJustificationService;
    }

    public void setBudgetJustificationService(BudgetJustificationService budgetJustificationService) {
        this.budgetJustificationService = budgetJustificationService;
    }

	protected GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

	protected ProposalBudgetAuthorizer getProposalBudgetAuthorizer() {
		return proposalBudgetAuthorizer;
	}

	public void setProposalBudgetAuthorizer(
			ProposalBudgetAuthorizer proposalBudgetAuthorizer) {
		this.proposalBudgetAuthorizer = proposalBudgetAuthorizer;
	}

    public BudgetModularService getBudgetModularService() {
        return budgetModularService;
    }

    public void setBudgetModularService(BudgetModularService budgetModularService) {
        this.budgetModularService = budgetModularService;
    }

}
