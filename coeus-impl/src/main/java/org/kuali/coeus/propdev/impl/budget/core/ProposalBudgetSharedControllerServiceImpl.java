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
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintType;
import org.kuali.coeus.common.budget.impl.print.BudgetPrintForm;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.framework.controller.ControllerFileUtils;
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
	
    private static final String BUDGET_SUMMARY_DIALOG_ID = "PropBudget-SummaryPage-Dialog";
    private static final String BUDGET_PRINT_FORMS_DIALOG_ID = "PropBudget-PrintForms-Dialog";
	
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
			String reportName = BudgetPrintType.BUDGET_SUMMARY_REPORT
					.getBudgetPrintType();
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
}
