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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetSaveEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.coeus.common.budget.impl.print.BudgetPrintForm;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.lock.ProposalBudgetLockService;
import org.kuali.coeus.sys.framework.controller.ControllerFileUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
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
	
    private static final String BUDGET_SUMMARY_DIALOG_ID = "PropBudget-SummaryPage-Dialog";
    private static final String BUDGET_PRINT_FORMS_DIALOG_ID = "PropBudget-PrintForms-Dialog";
    private static final String BUDGET_PRINT_FORMS_REPORT_NAME = "Proposal Budget Reports";
	
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
    
    @Autowired
	@Qualifier("budgetPrintService")
	private BudgetPrintService budgetPrintService;
    
    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("proposalBudgetLockService")
    private ProposalBudgetLockService proposalBudgetLockService;

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

	public ModelAndView openBudget(String budgetId, boolean viewOnly, UifFormBase form) throws Exception {
		if (getGlobalVariableService().getMessageMap().hasNoErrors()) {
			form.setDirtyForm(false);
	        Properties props = new Properties();
	        props.put("methodToCall", "start");
	        props.put("budgetId", budgetId);
	        props.put("viewOnly", String.valueOf(viewOnly));
            props.put("auditActivated", String.valueOf(((Auditable)form).isAuditActivated()));
	        return getModelAndViewService().performRedirect(form, "proposalBudget", props);
		} else {
        	form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATEPAGE.getKey());			
			return getRefreshControllerService().refresh(form);
		}
	}

    public void markBudgetVersionStatus(ProposalDevelopmentBudgetExt budget, String status) {
        String budgetStatus = getParameterService().getParameterValueAsString(
                Budget.class,status);
        budget.setBudgetStatus(budgetStatus);
        getDataObjectService().wrap(budget).fetchRelationship("budgetStatusDo");
        getDataObjectService().save(budget);
    }

    public boolean isBudgetLocked(int budgetVersion, List<PessimisticLock> locks, String errorPath) {
        Person user = getGlobalVariableService().getUserSession().getPerson();
        for (PessimisticLock lock : locks) {
            if (!lock.isOwnedByUser(user)  && getProposalBudgetLockService().doesBudgetVersionMatchDescriptor(lock.getLockDescriptor(),budgetVersion)) {
                getGlobalVariableService().getMessageMap().putError(errorPath,KeyConstants.ERROR_COMPLETE_BUDGET_LOCK);
                return true;
            }
        }
        return false;
    }

    public ProposalDevelopmentBudgetExt getSelectedBudget(Long budgetId, List<ProposalDevelopmentBudgetExt> budgets) {
        for (ProposalDevelopmentBudgetExt curBudget : budgets) {
            if (ObjectUtils.equals(budgetId, curBudget.getBudgetId())) {
                return curBudget;
            }
        }
        return null;
    }

    public boolean isAllowedToCompleteBudget(ProposalDevelopmentBudgetExt budget, String errorPath) {
        boolean isAuditRulePassed = getKcBusinessRulesEngine().applyRules(new BudgetAuditRuleEvent(budget));
		boolean isRulePassed = getKcBusinessRulesEngine().applyRules(new BudgetSaveEvent(budget));
        if(!isAuditRulePassed) {
            getGlobalVariableService().getMessageMap().putError(errorPath, KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
            return false;
        } else if (!isRulePassed) {
			return false;
		}
        return true;
    }
	
    public <T extends UifFormBase & SelectableBudget> ModelAndView populateBudgetSummary(Long budgetId, 
    		List<ProposalDevelopmentBudgetExt> budgets, T form) throws Exception {
        ProposalDevelopmentBudgetExt selectedBudget = null;
        if (budgets != null) {
            for (ProposalDevelopmentBudgetExt curBudget : budgets) {
                if (ObjectUtils.equals(budgetId, curBudget.getBudgetId())) {
                    selectedBudget = curBudget;
                }
            }
        }
        ((SelectableBudget) form).setSelectedBudget(selectedBudget);
        if(selectedBudget.getBudgetSummaryDetails().isEmpty()) {
            getBudgetCalculationService().populateBudgetSummaryTotals(selectedBudget);
        }
        return getModelAndViewService().showDialog(BUDGET_SUMMARY_DIALOG_ID, true, form);
    }	
	
    public <T extends UifFormBase & SelectableBudget> ModelAndView populatePrintForms(Long budgetId, 
    		List<ProposalDevelopmentBudgetExt> budgets, T form) throws Exception {

		ProposalDevelopmentBudgetExt selectedBudget = null;
		if (budgets != null) {
			for (ProposalDevelopmentBudgetExt curBudget : budgets) {
				if (Objects.equals(budgetId, curBudget.getBudgetId())) {
					selectedBudget = curBudget;
				}
			}
		}
		((SelectableBudget) form).setSelectedBudget(selectedBudget);
		if (selectedBudget.getBudgetPrintForms().isEmpty()) {
			getBudgetPrintService().populateBudgetPrintForms(selectedBudget);
		}
		return getModelAndViewService().showDialog(BUDGET_PRINT_FORMS_DIALOG_ID, true, form);
	}
	
	public <T extends UifFormBase & SelectableBudget> ModelAndView printBudgetForms(ProposalDevelopmentBudgetExt selectedBudget, 
			T form, HttpServletResponse response) throws Exception {
		List<BudgetPrintForm> selectedBudgetPrintForms = new ArrayList<BudgetPrintForm>();
		for (BudgetPrintForm selectedForm : selectedBudget.getBudgetPrintForms()) {
			if (Boolean.TRUE.equals(selectedForm.getSelectToPrint())) {
				selectedBudgetPrintForms.add(selectedForm);
			}
		}
		if (selectedBudgetPrintForms != null
				&& selectedBudgetPrintForms.size() > 0) {
			String reportName = selectedBudgetPrintForms.size() > 1 ? BUDGET_PRINT_FORMS_REPORT_NAME : selectedBudgetPrintForms.get(0).getBudgetReportName();
			AttachmentDataSource dataStream = 
					getBudgetPrintService().readBudgetSelectedPrintStreams(selectedBudget,
							selectedBudgetPrintForms, reportName);
			if (dataStream.getData() != null) {
				ControllerFileUtils.streamToResponse(dataStream, response);
				return null;
			}
		}
		return getModelAndViewService().getModelAndView(form);
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

	public BudgetPrintService getBudgetPrintService() {
		return budgetPrintService;
	}

	public void setBudgetPrintService(BudgetPrintService budgetPrintService) {
		this.budgetPrintService = budgetPrintService;
	}

	public BudgetCalculationService getBudgetCalculationService() {
		return budgetCalculationService;
	}

	public void setBudgetCalculationService(
			BudgetCalculationService budgetCalculationService) {
		this.budgetCalculationService = budgetCalculationService;
	}

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public ProposalBudgetLockService getProposalBudgetLockService() {
        return proposalBudgetLockService;
    }

    public void setProposalBudgetLockService(ProposalBudgetLockService proposalBudgetLockService) {
        this.proposalBudgetLockService = proposalBudgetLockService;
    }
}
