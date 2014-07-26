package org.kuali.coeus.propdev.impl.budget.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.web.controller.MethodAccessible;
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


	@MethodAccessible
	@RequestMapping(params="methodToCall=defaultMapping")
	public ModelAndView defaultMapping(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
        return getTransactionalDocumentControllerService().start(form);
	}

	@MethodAccessible
	@RequestMapping(params="methodToCall=start")
	public ModelAndView start(@RequestParam("budgetId") String budgetId, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		form.setBudget(getDataObjectService().findUnique(ProposalDevelopmentBudgetExt.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("budgetId", Long.valueOf(budgetId))).build()));
		form.initialize();
        return getModelAndViewService().getModelAndViewWithInit(form, "PropBudget-DefaultView");
	}
	
	@MethodAccessible
	@RequestMapping(params = "methodToCall=navigate")
	public ModelAndView navigate(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		return super.navigate(form);
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
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        
        if(form.getEditableBudgetLineItems() != null && selectedCollectionPath !=null && form.getEditableBudgetLineItems().containsKey(selectedCollectionPath)){
            form.getEditableBudgetLineItems().get(selectedCollectionPath).remove(selectedLine);
        }

        return getCollectionControllerService().saveLine(form);
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
	public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
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

}
