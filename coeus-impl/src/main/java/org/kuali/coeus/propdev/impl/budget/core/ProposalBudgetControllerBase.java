/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.budget.core;

import java.text.DecimalFormat;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetSaveEvent;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationService;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.impl.nonpersonnel.BudgetExpensesRuleEvent;
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
    
    @Autowired
    @Qualifier("budgetSummaryService")
    private BudgetSummaryService budgetSummaryService;

    @Autowired
    @Qualifier("budgetRatesService")
    private BudgetRatesService budgetRatesService;
    
    protected UifFormBase createInitialForm(HttpServletRequest request) {
        return new ProposalBudgetForm();
    }
    
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return  getKcCommonControllerService().initForm(this.createInitialForm(request), request, response);
    }

    protected ProposalDevelopmentBudgetExt loadBudget(Long budgetId) {
    	ProposalDevelopmentBudgetExt budget = getDataObjectService().findUnique(ProposalDevelopmentBudgetExt.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("budgetId", budgetId)).build());
    	budget.setStartDate(budget.getDevelopmentProposal().getRequestedStartDateInitial());
    	budget.setEndDate(budget.getDevelopmentProposal().getRequestedEndDateInitial());
        getBudgetSummaryService().setupOldStartEndDate(budget, false);
    	if (!proposalBudgetAuthorizer.isAuthorizedToViewBudget(budget, globalVariableService.getUserSession().getPerson())) {
    		throw new AuthorizationException(globalVariableService.getUserSession().getPrincipalName(), "open", "Proposal Budget");
    	}
    	return budget;
    }

    public ModelAndView save(ProposalBudgetForm form) {
        if (form.isCanEditView()) {
        	saveBudget(form);
        } else {
            getBudgetCalculationService().populateBudgetSummaryTotals(form.getBudget());
            getBudgetSummaryService().setupOldStartEndDate(form.getBudget(), false);
            form.setBudgetModularSummary(budgetModularService.processModularSummary(form.getBudget(),true));
            validateBudgetExpenses(form);
        }

        checkAudit(form);
        return getModelAndViewService().getModelAndView(form);
    }

    protected void checkAudit(ProposalBudgetForm form) {
        if (form.isAuditActivated()){
            getGlobalVariableService().getAuditErrorMap().clear();
            ((ProposalBudgetViewHelperServiceImpl)form.getViewHelperService()).applyBudgetAuditRules(form);
        }
    }
    
    protected void saveBudget(ProposalBudgetForm form) {
    	if(getKcBusinessRulesEngine().applyRules(new BudgetSaveEvent(form.getBudget()))) {
            budgetService.calculateBudgetOnSave(form.getBudget());
            form.setBudget(getDataObjectService().save(form.getBudget()));
            getBudgetCalculationService().populateBudgetSummaryTotals(form.getBudget());
            getBudgetJustificationService().preSave(form.getBudget(), form.getBudgetJustificationWrapper());
            getBudgetSummaryService().setupOldStartEndDate(form.getBudget(), false);
            form.setBudgetModularSummary(budgetModularService.processModularSummary(form.getBudget(),true));
            validateBudgetExpenses(form);
    	}
    }

    
    protected void validateBudgetExpenses(ProposalBudgetForm form) {
    	String errorPath = null;
    	if(form.getPageId().equalsIgnoreCase(BudgetConstants.BudgetAuditRules.NON_PERSONNEL_COSTS.getPageId())) {
    		errorPath = BudgetConstants.BudgetAuditRules.NON_PERSONNEL_COSTS.getPageId();
    	}else if(form.getPageId().equalsIgnoreCase(BudgetConstants.BudgetAuditRules.PERSONNEL_COSTS.getPageId())) {
    		errorPath = BudgetConstants.BudgetAuditRules.PERSONNEL_COSTS.getPageId();
    	}

    	if(errorPath != null) {
    		getKcBusinessRulesEngine().applyRules(new BudgetExpensesRuleEvent(form.getBudget(), errorPath));
    	}
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
    	ModelAndView modelAndView = save(form);
		if (getGlobalVariableService().getMessageMap().hasNoErrors()) {
	        String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
	        if (StringUtils.isNotEmpty(pageId)) {
	            form.setDirtyForm(false);
	            form.setPageId(pageId);
	        }
		}
		return modelAndView;
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(ScaleTwoDecimal.class, new ScaleTwoDecimalEditor(new DecimalFormat("##0.00"), true));
    }

    protected Budget getOriginalBudget(ProposalBudgetForm form) {
    	return getDataObjectService().find(Budget.class, form.getBudget().getBudgetId());
    }

	protected boolean isRateTypeChanged(Budget originalBudget, Budget currentBudget) {
        return (!StringUtils.equalsIgnoreCase(originalBudget.getOhRateClassCode(), currentBudget.getOhRateClassCode())
            || !StringUtils.equalsIgnoreCase(originalBudget.getUrRateClassCode(), currentBudget.getUrRateClassCode()));
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

	public BudgetSummaryService getBudgetSummaryService() {
		return budgetSummaryService;
	}

	public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
		this.budgetSummaryService = budgetSummaryService;
	}
    
    public BudgetRatesService getBudgetRatesService() {
		return budgetRatesService;
	}

	public void setBudgetRatesService(BudgetRatesService budgetRatesService) {
		this.budgetRatesService = budgetRatesService;
	}
}
