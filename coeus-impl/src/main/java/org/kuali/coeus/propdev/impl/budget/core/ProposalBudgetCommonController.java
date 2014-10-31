package org.kuali.coeus.propdev.impl.budget.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetCommonController extends ProposalBudgetControllerBase {

	@Autowired
	@Qualifier("proposalBudgetSharedController")
	private ProposalBudgetSharedController proposalBudgetSharedController;
	
	@Autowired
	@Qualifier("parameterService")
	private ParameterService parameterService;
	
	@MethodAccessible
	@RequestMapping(params="methodToCall=defaultMapping")
	public ModelAndView defaultMapping(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
        return getTransactionalDocumentControllerService().start(form);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=start")
	public ModelAndView start(@RequestParam("budgetId") Long budgetId, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		form.setBudget(loadBudget(budgetId));
		form.initialize();
        return getModelAndViewService().getModelAndViewWithInit(form, ProposalBudgetConstants.KradConstants.BUDGET_DEFAULT_VIEW);
	}
	
	@MethodAccessible
	@RequestMapping(params="methodToCall=initiate")
	public ModelAndView initiate(@RequestParam("budgetId") Long budgetId, @RequestParam(value = "summaryBudget", required = false) Boolean summaryBudget, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		form.setBudget(loadBudget(budgetId));
		form.initialize();
		if ((summaryBudget != null && !summaryBudget)
				|| (summaryBudget == null && !form.getBudget().isSummaryBudget())) {
			return getModelAndViewService().getModelAndViewWithInit(form, ProposalBudgetConstants.KradConstants.BUDGET_DEFAULT_VIEW, ProposalBudgetConstants.KradConstants.PERSONNEL_PAGE_ID);
		} else {
			return getModelAndViewService().getModelAndViewWithInit(form, ProposalBudgetConstants.KradConstants.BUDGET_DEFAULT_VIEW, ProposalBudgetConstants.KradConstants.PERIODS_AND_TOTALS_PAGE_ID);
		}
	}
	
	@RequestMapping(params="methodToCall=openProposal")
	public ModelAndView openProposal(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
        Properties props = new Properties();
        props.put("methodToCall", KRADConstants.DOC_HANDLER_METHOD);
        props.put("command", KewApiConstants.DOCSEARCH_COMMAND);
        props.put("pageId", ProposalDevelopmentConstants.KradConstants.BUDGET_PAGE);
        props.put("docId", form.getBudget().getDevelopmentProposal().getProposalDocument().getDocumentNumber());
        return getModelAndViewService().performRedirect(form, "proposalDevelopment", props);
	}
	
	@RequestMapping(params="methodToCall=save")
	public ModelAndView save(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		return super.save(form);
	}

	@MethodAccessible
	@RequestMapping(params = "methodToCall=navigate")
	public ModelAndView navigate(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		return super.navigate(form);
	}
	
	@MethodAccessible
    @RequestMapping(params="methodToCall=addBudget")
    public ModelAndView addBudget(@RequestParam("addBudgetDto.budgetName") String budgetName, 
    		@RequestParam("addBudgetDto.summaryBudget") Boolean summaryBudget, 
    		@RequestParam("addBudgetDto.modularBudget") Boolean modularBudget, 
    		@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		return getProposalBudgetSharedController().addBudget(budgetName, summaryBudget, modularBudget, form.getDevelopmentProposal(), form);
    }

	@MethodAccessible
    @RequestMapping(params="methodToCall=copyBudget")
	public ModelAndView copyBudget(@RequestParam("copyBudgetDto.budgetName") String budgetName, 
			@RequestParam("copyBudgetDto.originalBudgetId") Long originalBudgetId, 
			@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		return getProposalBudgetSharedController().copyBudget(budgetName, originalBudgetId, form.getDevelopmentProposal(), form);
	}
	
	@RequestMapping(params="methodToCall=completeBudget")
	public ModelAndView completeBudget(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        String budgetStatusCompleteCode = getParameterService().getParameterValueAsString(
                Budget.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        form.getBudget().setBudgetStatus(budgetStatusCompleteCode);
        getDataObjectService().wrap(form.getBudget()).fetchRelationship("budgetStatusDo");
		return super.save(form);
	}

	public void checkViewAuthorization(@ModelAttribute("KualiForm") ProposalBudgetForm form, String methodToCall) throws AuthorizationException {
        getTransactionalDocumentControllerService().checkViewAuthorization(form);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=sessionTimeout")
	public ModelAndView sessionTimeout(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
        return getTransactionalDocumentControllerService().sessionTimeout(form);
	}

	@RequestMapping(params="methodToCall=addLine")
	public ModelAndView addLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        return getCollectionControllerService().addLine(form);
	}

	@RequestMapping(params = "methodToCall=addBlankLine")
	public ModelAndView addBlankLine(@ModelAttribute("KualiForm") ProposalBudgetForm uifForm, HttpServletRequest request,
			HttpServletResponse response) {
        return getCollectionControllerService().addBlankLine(uifForm);
	}

	@RequestMapping(params="methodToCall=saveLine")
	public ModelAndView saveLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return super.saveLine(form);
	}

	@RequestMapping(params="methodToCall=deleteLine")
	public ModelAndView deleteLine(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
        return getCollectionControllerService().deleteLine(form);
	}

	@RequestMapping(params="methodToCall=back")
	public ModelAndView back(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getNavigationControllerService().back(form);
	}

	@RequestMapping(params="methodToCall=returnToPrevious")
	public ModelAndView returnToPrevious(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		return getNavigationControllerService().returnToPrevious(form);
	}

	@RequestMapping(params="methodToCall=returnToHub")
	public ModelAndView returnToHub(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		return getNavigationControllerService().returnToHub(form);
	}

	@RequestMapping(params="methodToCall=returnToHistory")
	public ModelAndView returnToHistory(@ModelAttribute("KualiForm") ProposalBudgetForm form, boolean homeFlag) {
		return getNavigationControllerService().returnToHistory(form, false, homeFlag, false);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=refresh")
	public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getRefreshControllerService().refresh(form);
	}

	@RequestMapping(params="methodToCall=performLookup")
	public ModelAndView performLookup(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performLookup(form);
	}

	@RequestMapping(params="methodToCall=checkForm")
	public ModelAndView checkForm(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getModelAndViewService().checkForm(form);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=performFieldSuggest")
	public @ResponseBody AttributeQueryResult performFieldSuggest(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performFieldSuggest(form);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=performFieldQuery")
	public @ResponseBody AttributeQueryResult performFieldQuery(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performFieldQuery(form);
	}

	@RequestMapping(params="methodToCall=tableCsvRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableCsvRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return  getUifExportControllerService().tableCsvRetrieval(form, request, response);
	}

	@RequestMapping(params="methodToCall=tableXlsRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXlsRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableXlsRetrieval(form, request, response);
	}

	@RequestMapping(params="methodToCall=tableXmlRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXmlRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableXmlRetrieval(form, request, response);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=tableJsonRetrieval")
	public ModelAndView tableJsonRetrieval(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getCollectionControllerService().tableJsonRetrieval(form);
	}
	
    @MethodAccessible
    @RequestMapping(params = "methodToCall=retrieveCollectionPage")
	public ModelAndView retrieveCollectionPage(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getCollectionControllerService().retrieveCollectionPage(form);
	}

    @RequestMapping(params="methodToCall=editLineItem")
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

    @RequestMapping(params="methodToCall=cancelEditLineItem")
    public ModelAndView cancelEditLineItem(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableBudgetLineItems().containsKey(selectedCollectionPath)){
            form.getEditableBudgetLineItems().get(selectedCollectionPath).remove(selectedLine);
        }
        return getRefreshControllerService().refresh(form);
    }
    
    @RequestMapping(params="methodToCall=retrieveEditLineDialog")
	public ModelAndView retrieveEditLineDialog(UifFormBase form) {
		return getCollectionControllerService().retrieveEditLineDialog(form);
	}

    @RequestMapping(params="methodToCall=closeEditLineDialog")
	public ModelAndView closeEditLineDialog(UifFormBase form) {
		return getCollectionControllerService().closeEditLineDialog(form);
	}
    
    @RequestMapping(params="methodToCall=editLine")
	public ModelAndView editLine(UifFormBase form) {
		return getCollectionControllerService().editLine(form);
	}    

	public ProposalBudgetSharedController getProposalBudgetSharedController() {
		return proposalBudgetSharedController;
	}

	public void setProposalBudgetSharedController(
			ProposalBudgetSharedController proposalBudgetSharedController) {
		this.proposalBudgetSharedController = proposalBudgetSharedController;
	}

    @RequestMapping(params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropBudget-SummaryPage"})
    public ModelAndView navigateToBudgetSummary(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
    	getBudgetCalculationService().populateBudgetSummaryTotals(form.getBudget());
        return super.navigate(form);
    }

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
    
}
