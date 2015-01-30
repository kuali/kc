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
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.propdev.impl.lock.ProposalBudgetLockService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("proposalBudgetCommonController")
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetCommonController extends ProposalBudgetControllerBase {
	private static final String CONFIRM_RATE_CHANGES_DIALOG_ID = "PropBudget-BudgetSettings-ChangeRateDialog";
	private static final String BUDGET_SETTINGS_DIALOG_ID = "PropBudget-BudgetSettings-Dialog";
	private static final String BUDGET_DATA_VALIDATION_DIALOG_ID = "DataValidationSection";
	private static final String ACTIVITY_RATE_CHANGE_DIALOG_ID = "PropBudget-ActivityTypeChanged-Dialog";
	private static final String NO_RATES_DIALOG_ID = "PropBudget-NoRates-Dialog";

	@Autowired
	@Qualifier("proposalBudgetSharedControllerService")
	private ProposalBudgetSharedControllerService proposalBudgetSharedController;
	
	@Autowired
	@Qualifier("parameterService")
	private ParameterService parameterService;

    @Autowired
    @Qualifier("proposalBudgetLockService")
    private ProposalBudgetLockService proposalBudgetLockService;
    
    @Autowired
    @Qualifier("budgetRatesService")
    private BudgetRatesService budgetRatesService;
	
	@MethodAccessible
	@Transactional @RequestMapping(params="methodToCall=defaultMapping")
	public ModelAndView defaultMapping(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
        return getTransactionalDocumentControllerService().start(form);
	}

	@MethodAccessible
	@Transactional @RequestMapping(params="methodToCall=start")
	public ModelAndView start(@RequestParam("budgetId") Long budgetId, @RequestParam("viewOnly") String viewOnly, @RequestParam("auditActivated") String auditActivated, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		boolean inViewMode = Boolean.parseBoolean(viewOnly);
		form.setBudget(loadBudget(budgetId));
		if(!inViewMode) {
	        getProposalBudgetLockService().establishBudgetLock(form.getBudget());
		}

		form.initialize();
        if (auditActivated != null){
            form.setAuditActivated(Boolean.parseBoolean(auditActivated));
        }
        //call getModelAndViewWithInit to make sure view is initialized regardless of whether this modelAndView is returned
        ModelAndView result = getModelAndViewService().getModelAndViewWithInit(form, ProposalBudgetConstants.KradConstants.BUDGET_DEFAULT_VIEW);
        form.setViewOnly(inViewMode);
        form.setCanEditView(!inViewMode);
    	boolean canModify = false;
    	if(!inViewMode) {
    		canModify = getProposalBudgetAuthorizer().canModifyBudget(form.getBudget(), getGlobalVariableService().getUserSession().getPerson());
    	}
        
        if (canModify && budgetRatesService.checkActivityTypeChange(form.getBudget().getBudgetRates(), form.getDevelopmentProposal().getActivityTypeCode())) {
        	return getModelAndViewService().showDialog(ACTIVITY_RATE_CHANGE_DIALOG_ID, true, form);
        } else if (canModify && form.getBudget().getBudgetRates().isEmpty()) {
        	return getModelAndViewService().showDialog(NO_RATES_DIALOG_ID, true, form);
        } else {
        	return result;
        }
	}
	
	@MethodAccessible
	@Transactional @RequestMapping(params="methodToCall=initiate")
	public ModelAndView initiate(@RequestParam("budgetId") Long budgetId, @RequestParam("auditActivated") String auditActivated, @RequestParam(value = "summaryBudget", required = false) Boolean summaryBudget, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		form.setBudget(loadBudget(budgetId));
        getProposalBudgetLockService().establishBudgetLock(form.getBudget());
		form.initialize();
        if (auditActivated != null){
            form.setAuditActivated(Boolean.parseBoolean(auditActivated));
        }
		if ((summaryBudget != null && !summaryBudget)
				|| (summaryBudget == null && !form.getBudget().isSummaryBudget())) {
			return getModelAndViewService().getModelAndViewWithInit(form, ProposalBudgetConstants.KradConstants.BUDGET_DEFAULT_VIEW, ProposalBudgetConstants.KradConstants.PERSONNEL_PAGE_ID);
		} else {
			return getModelAndViewService().getModelAndViewWithInit(form, ProposalBudgetConstants.KradConstants.BUDGET_DEFAULT_VIEW, ProposalBudgetConstants.KradConstants.PERIODS_AND_TOTALS_PAGE_ID);
		}
	}
	
	@Transactional @RequestMapping(params="methodToCall=openProposal")
	public ModelAndView openProposal(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		if (form.isCanEditView()) {
            save(form);
        }
		if (getGlobalVariableService().getMessageMap().hasNoErrors()) {
            getProposalBudgetLockService().deleteBudgetLock(form.getBudget());
			form.setDirtyForm(false);	
	        Properties props = new Properties();
	        props.put("methodToCall", KRADConstants.DOC_HANDLER_METHOD);
	        props.put("command", KewApiConstants.DOCSEARCH_COMMAND);
	        props.put("pageId", ProposalDevelopmentConstants.KradConstants.BUDGET_PAGE);
	        props.put("docId", form.getBudget().getDevelopmentProposal().getProposalDocument().getDocumentNumber());
            props.put("auditActivated", String.valueOf(form.isAuditActivated()));
            props.put("viewOnly", String.valueOf(form.isViewOnly()));
	        return getModelAndViewService().performRedirect(form, "proposalDevelopment", props);
		} else {
        	form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATEPAGE.getKey());			
    		return getRefreshControllerService().refresh(form);
    	}	        
	}
	
	@Transactional @RequestMapping(params="methodToCall=openBudget")
	public ModelAndView openBudget(@RequestParam("budgetId") String budgetId, @RequestParam("viewOnly") boolean viewOnly, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		if(!viewOnly) {
			save(form);
		}
		return getProposalBudgetSharedController().openBudget(budgetId, viewOnly, form);
	}	
	
	@Transactional @RequestMapping(params="methodToCall=save")
	public ModelAndView save(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		return super.save(form);
	}

	@RequestMapping(params = "methodToCall=navigate")
	public ModelAndView navigate(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		return super.navigate(form);
	}
	
	@RequestMapping(params="methodToCall=addBudget")
    public ModelAndView addBudget(@RequestParam("addBudgetDto.budgetName") String budgetName, 
    		@RequestParam("addBudgetDto.summaryBudget") Boolean summaryBudget, 
    		@RequestParam(value="addBudgetDto.modularBudget",defaultValue="false") Boolean modularBudget,
    		@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		save(form);
		return getProposalBudgetSharedController().addBudget(budgetName, summaryBudget, modularBudget, form.getDevelopmentProposal(), form);
    }

	@MethodAccessible
    @Transactional @RequestMapping(params="methodToCall=copyBudget")
	public ModelAndView copyBudget(@RequestParam("copyBudgetDto.budgetName") String budgetName, 
			@RequestParam("copyBudgetDto.originalBudgetId") Long originalBudgetId, 
			@RequestParam("copyBudgetDto.allPeriods") Boolean allPeriods,
			@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		save(form);
		return getProposalBudgetSharedController().copyBudget(budgetName, originalBudgetId, allPeriods, form.getDevelopmentProposal(), form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=completeBudget")
	public ModelAndView completeBudget(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		if(!isAllowedToCompleteBudget(form, form.getPageId())) {
	   		form.setAjaxReturnType("update-page");
		   	return getModelAndViewService().getModelAndView(form);
		}
		String budgetStatusCompleteCode = getParameterService().getParameterValueAsString(
                Budget.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        form.getBudget().setBudgetStatus(budgetStatusCompleteCode);
        getDataObjectService().wrap(form.getBudget()).fetchRelationship("budgetStatusDo");
		return super.save(form);
	}
	
	protected boolean isAllowedToCompleteBudget(ProposalBudgetForm form, String errorPath) {
		boolean isRulePassed = ((ProposalBudgetViewHelperServiceImpl)form.getViewHelperService()).applyBudgetAuditRules(form);
		if(!isRulePassed) {
	        getGlobalVariableService().getMessageMap().putError(errorPath, KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
	        form.setAuditActivated(true);
	        return false;
		}
		return true;
	}

	@Transactional @RequestMapping(params="methodToCall=saveBudgetSettings")
	public ModelAndView saveBudgetSettings(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		ProposalDevelopmentBudgetExt budget = form.getBudget();
		ProposalDevelopmentBudgetExt originalBudget = (ProposalDevelopmentBudgetExt)getOriginalBudget(form);
		if(budget.isBudgetComplete() && !isAllowedToCompleteBudget(form, "budget.budgetStatus")) {
			budget.setBudgetStatus(originalBudget.getBudgetStatus());
	   		return getModelAndViewService().getModelAndView(form);
		}
    	if(isRateTypeChanged(originalBudget, budget)) {
        	return getModelAndViewService().showDialog(CONFIRM_RATE_CHANGES_DIALOG_ID, true, form);
    	}
    	getBudgetSummaryService().updateOnOffCampusFlag(budget, budget.getOnOffCampusFlag());
        super.save(form);
        form.setEvaluateFlagsAndModes(true);
		//setting to null to force reevaluation
		form.setCanEditView(null);

		//the budgetStatusDo is not being synced with the budgetStatus from the settings screen
		getDataObjectService().wrap(form.getBudget()).fetchRelationship("budgetStatusDo");
		return getRefreshControllerService().refresh(form);
	}

	@Transactional @RequestMapping(params="methodToCall=closeBudgetSettings")
	public ModelAndView closeBudgetSettings(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		processAuditRuleValidation(form);
		return getKcCommonControllerService().closeDialog(BUDGET_SETTINGS_DIALOG_ID, form);
	}

	@Transactional @RequestMapping(params="methodToCall=closeBudgetValidation")
	public ModelAndView closeBudgetValidation(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		processAuditRuleValidation(form);
		return getKcCommonControllerService().closeDialog(BUDGET_DATA_VALIDATION_DIALOG_ID, form);
	}
	
	protected void processAuditRuleValidation(ProposalBudgetForm form) {
		if(form.isAuditActivated()) {
			((ProposalBudgetViewHelperServiceImpl)form.getViewHelperService()).applyBudgetAuditRules(form);
		}
		form.setAjaxReturnType("update-page");
	}
	
	@Transactional @RequestMapping(params="methodToCall=confirmBudgetSettings")
	public ModelAndView confirmBudgetSettings(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
    	getBudgetSummaryService().updateOnOffCampusFlag(budget, budget.getOnOffCampusFlag());
    	getBudgetCalculationService().resetBudgetLineItemCalculatedAmounts(budget);
    	form.setEvaluateFlagsAndModes(true);
        return super.save(form);
	}

	@Transactional @RequestMapping(params="methodToCall=resetBudgetSettings")
	public ModelAndView resetBudgetSettings(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
        Budget originalBudget = getOriginalBudget(form);
        budget.setOhRateClassCode(originalBudget.getOhRateClassCode());
        budget.setUrRateClassCode(originalBudget.getUrRateClassCode());
		return getModelAndViewService().getModelAndView(form);
	}
	
	public void checkViewAuthorization(@ModelAttribute("KualiForm") ProposalBudgetForm form, String methodToCall) throws AuthorizationException {
        getTransactionalDocumentControllerService().checkViewAuthorization(form);
	}

	@MethodAccessible
	@Transactional @RequestMapping(params="methodToCall=sessionTimeout")
	public ModelAndView sessionTimeout(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
        return getTransactionalDocumentControllerService().sessionTimeout(form);
	}

	@Transactional @RequestMapping(params="methodToCall=addLine")
	public ModelAndView addLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        return getCollectionControllerService().addLine(form);
	}

	@Transactional @RequestMapping(params = "methodToCall=addBlankLine")
	public ModelAndView addBlankLine(@ModelAttribute("KualiForm") ProposalBudgetForm uifForm, HttpServletRequest request,
			HttpServletResponse response) {
        return getCollectionControllerService().addBlankLine(uifForm);
	}

	@Transactional @RequestMapping(params="methodToCall=saveLine")
	public ModelAndView saveLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return super.saveLine(form);
	}

	@Transactional @RequestMapping(params="methodToCall=deleteLine")
	public ModelAndView deleteLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
        return getCollectionControllerService().deleteLine(form);
	}

	@Transactional @RequestMapping(params="methodToCall=back")
	public ModelAndView back(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getNavigationControllerService().back(form);
	}

	@Transactional @RequestMapping(params="methodToCall=returnToPrevious")
	public ModelAndView returnToPrevious(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		return getNavigationControllerService().returnToPrevious(form);
	}

	@Transactional @RequestMapping(params="methodToCall=returnToHub")
	public ModelAndView returnToHub(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		return getNavigationControllerService().returnToHub(form);
	}

	@Transactional @RequestMapping(params="methodToCall=returnToHistory")
	public ModelAndView returnToHistory(@ModelAttribute("KualiForm") ProposalBudgetForm form, boolean homeFlag) {
		return getNavigationControllerService().returnToHistory(form, false, homeFlag, false);
	}

	@MethodAccessible
	@Transactional @RequestMapping(params="methodToCall=refresh")
	public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getRefreshControllerService().refresh(form);
	}

	@Transactional @RequestMapping(params="methodToCall=performLookup")
	public ModelAndView performLookup(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performLookup(form);
	}

	@Transactional @RequestMapping(params="methodToCall=checkForm")
	public ModelAndView checkForm(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getModelAndViewService().checkForm(form);
	}

	@MethodAccessible
	@Transactional @RequestMapping(params="methodToCall=performFieldSuggest")
	public @ResponseBody AttributeQueryResult performFieldSuggest(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performFieldSuggest(form);
	}

	@MethodAccessible
	@Transactional @RequestMapping(params="methodToCall=performFieldQuery")
	public @ResponseBody AttributeQueryResult performFieldQuery(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performFieldQuery(form);
	}

	@Transactional @RequestMapping(params="methodToCall=tableCsvRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableCsvRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return  getUifExportControllerService().tableCsvRetrieval(form, request, response);
	}

	@Transactional @RequestMapping(params="methodToCall=tableXlsRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXlsRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableXlsRetrieval(form, request, response);
	}

	@Transactional @RequestMapping(params="methodToCall=tableXmlRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXmlRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableXmlRetrieval(form, request, response);
	}

	@MethodAccessible
	@Transactional @RequestMapping(params="methodToCall=tableJsonRetrieval")
	public ModelAndView tableJsonRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getCollectionControllerService().tableJsonRetrieval(form);
	}
	
    @MethodAccessible
    @Transactional @RequestMapping(params = "methodToCall=retrieveCollectionPage")
	public ModelAndView retrieveCollectionPage(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getCollectionControllerService().retrieveCollectionPage(form);
	}

    @Transactional @RequestMapping(params="methodToCall=editLineItem")
    public ModelAndView editLineItem(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if(form.getEditableBudgetLineItems().containsKey(selectedCollectionPath)) {
            form.getEditableBudgetLineItems().get(selectedCollectionPath).add(selectedLine);
        } else {
            List<String> newKeyList = new ArrayList<String>();
            newKeyList.add(selectedLine);
            form.getEditableBudgetLineItems().put(selectedCollectionPath,newKeyList);
        }
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(params="methodToCall=cancelEditLineItem")
    public ModelAndView cancelEditLineItem(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableBudgetLineItems().containsKey(selectedCollectionPath)){
            form.getEditableBudgetLineItems().get(selectedCollectionPath).remove(selectedLine);
        }
        return getRefreshControllerService().refresh(form);
    }
    
    @Transactional @RequestMapping(params="methodToCall=retrieveEditLineDialog")
	public ModelAndView retrieveEditLineDialog(UifFormBase form) {
		return getCollectionControllerService().retrieveEditLineDialog(form);
	}

    @Transactional @RequestMapping(params="methodToCall=closeEditLineDialog")
	public ModelAndView closeEditLineDialog(UifFormBase form) {
		return getCollectionControllerService().closeEditLineDialog(form);
	}
    
    @Transactional @RequestMapping(params="methodToCall=editLine")
	public ModelAndView editLine(UifFormBase form) {
		return getCollectionControllerService().editLine(form);
	}    

	public ProposalBudgetSharedControllerService getProposalBudgetSharedController() {
		return proposalBudgetSharedController;
	}

	public void setProposalBudgetSharedController(
			ProposalBudgetSharedControllerService proposalBudgetSharedController) {
		this.proposalBudgetSharedController = proposalBudgetSharedController;
	}

    @Transactional @RequestMapping(params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropBudget-SummaryPage"})
   public ModelAndView navigateToBudgetSummary(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
    	ModelAndView modelAndView = super.navigate(form);
    	if(form.getBudget().getBudgetSummaryDetails().isEmpty()) {
           	getBudgetCalculationService().populateBudgetSummaryTotals(form.getBudget());
    	}
        return modelAndView;
    }

    @Transactional @RequestMapping(params="methodToCall=populateBudgetSummary")
	public ModelAndView populateBudgetSummary(@RequestParam("budgetId") Long budgetId,
                                              @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
    	super.save(form);
    	return getProposalBudgetSharedController().populateBudgetSummary(budgetId, form.getDevelopmentProposal().getBudgets(), form);
    }
    
	@Transactional @RequestMapping(params="methodToCall=populatePrintForms")
    public ModelAndView populatePrintForms(@RequestParam("budgetId") Long budgetId,
			@ModelAttribute("KualiForm") ProposalBudgetForm form)
			throws Exception {
		super.save(form);
		return getProposalBudgetSharedController().populatePrintForms(budgetId, form.getDevelopmentProposal().getBudgets(), form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=printBudgetForms")
	public ModelAndView printBudgetForms(
			@ModelAttribute("KualiForm") ProposalBudgetForm form,
			HttpServletResponse response) throws Exception {
		return getProposalBudgetSharedController().printBudgetForms(form.getSelectedBudget(), form, response);
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
